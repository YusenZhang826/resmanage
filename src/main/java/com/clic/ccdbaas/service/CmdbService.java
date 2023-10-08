package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.clic.ccdbaas.dao.CloudVmNovaMapper;
import com.clic.ccdbaas.dao.NetworkAreaMapper;
import com.clic.ccdbaas.dao.ResourceSearchMapper;
import com.clic.ccdbaas.dao.VlanMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.NetworkArea;
import com.clic.ccdbaas.entity.VlanPlan;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CmdbService {
    private static final Logger logger = LoggerFactory.getLogger(CmdbService.class);
    private static final Integer ComputeResourceBatchSize = 100;
    private static final Integer ReserveDeviceBatchSize = 100;
    private static final List<CloudVmare> tempCloudVmList = new ArrayList<>();
    private static final Map<String, Object> tempUploadCmdbMap = new HashMap<String, Object>() {{
        put("objList", tempCloudVmList);
    }};

    private static final List<Map<String, Object>> tempReserveDeviceList = new ArrayList<>();
    private static final Map<String, Object> tempReserveDeviceMap = new HashMap<String, Object>() {{
        put("objList", tempReserveDeviceList);
    }};
    @Value("${cloudVm.url}")
    String computeResourceUrl;
    @Value("${netWorkArea.url}")
    String networkAreaUrl;
    @Value("${vlanPlan.url}")
    String vlanPlanUrl;
    @Value("${reserveDevice.url}")
    String reserveDeviceUrl;
    @Autowired
    CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    NetworkAreaMapper networkAreaMapper;
    @Autowired
    VlanMapper vlanMapper;
    @Autowired
    ResourceSearchMapper resourceSearchMapper;


    private Integer uploadCloudVm(List<CloudVmare> subList) {
        tempCloudVmList.clear();
        tempCloudVmList.addAll(subList);
        Integer successNum = 0;
        String json = JSON.toJSONString(tempUploadCmdbMap, SerializerFeature.WriteMapNullValue);
        try {
            HttpClientUtils.httpPostWithJSON(computeResourceUrl, json);
            successNum = subList.size();
        } catch (Exception e) {
            logger.error("上传计算资源失败", e);
            e.printStackTrace();
        }
        return successNum;
    }

    /**
     * 上传全部计算资源至CMDB
     */
    @XxlJob("uploadAllCloudVms")
    public Integer uploadAllCloudVms() {
        List<CloudVmare> cloudVms = cloudVmNovaMapper.getAllCloudInstance();
        int successNum = 0;
        int batchSize = ComputeResourceBatchSize;
        int size = cloudVms.size();
        int batchNum = size / batchSize;
        int lastBatchSize = size % batchSize;
        for (int i = 0; i < batchNum; i++) {
            List<CloudVmare> subList = cloudVms.subList(i * batchSize, (i + 1) * batchSize);
            successNum += uploadCloudVm(subList);
        }
        if (lastBatchSize > 0) {
            List<CloudVmare> subList = cloudVms.subList(batchNum * batchSize, size);
            successNum += uploadCloudVm(subList);
        }
        String log = "上传计算资源成功数量：" + successNum + "，失败数量：" + (size - successNum);
        logger.info(log);
        XxlJobHelper.log(log);

        return successNum;
    }

    /**
     * 处理VLAN区域上报数据
     */
    public String uploadVlanPlan(VlanPlan vlanPlan) {
        String message = "";
        Map map = new HashMap<>();
        try {
            map.put("resid", vlanPlan.getResId());
            map.put("vlanid", vlanPlan.getVlanId());
            map.put("netarea", vlanPlan.getAreaName());
            map.put("useage1", vlanPlan.getUserParType());
            map.put("useage2", vlanPlan.getUserchiType());
            map.put("remark", vlanPlan.getRemarks());
            map.put("tag", "");
            map.put("ipscope", vlanPlan.getIpSegment());
            map.put("begin", vlanPlan.getStartIp());
            map.put("end", vlanPlan.getEndIp());
            map.put("getway", vlanPlan.getGatewayIp());
            map.put("name", vlanPlan.getName());
            String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
            message = HttpClientUtils.httpPostWithJSON(vlanPlanUrl, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 处理网络逻辑区域上报数据
     */
    public String uploadNetworkArea(NetworkArea networkArea) {
        String message = "";
        Map map = new HashMap<>();
        try {
            map.put("netarea", networkArea.getName());
            map.put("resid", networkArea.getResId());
            map.put("location", networkArea.getLocation());
            map.put("runenv", networkArea.getDeployEnv());
            map.put("organization", networkArea.getUserOrg());
            map.put("safety", networkArea.getCategory());
            map.put("tag", "");
            map.put("admin", networkArea.getNetAdministrator());
            map.put("remark", networkArea.getPurposeDesc());
            map.put("name", networkArea.getName());
            String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
            message = HttpClientUtils.httpPostWithJSON(networkAreaUrl, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    @XxlJob("executeCmdbNetworkVlanUpload")
    public String executeCmdbNetworkVlanUpload() {
        Integer networkAreaCount = 0;
        Integer vlanPlanCount = 0;
        List<NetworkArea> networkAreaList = networkAreaMapper.getAllNetworkAreaInstance(new NetworkArea());
        List<VlanPlan> vlanPlanList = vlanMapper.getAllVlanInstance(new VlanPlan());
        for (NetworkArea networkArea : networkAreaList) {
            try {
                uploadNetworkArea(networkArea);
                networkAreaCount++;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        for (VlanPlan vlanPlan : vlanPlanList) {
            try {
                uploadVlanPlan(vlanPlan);
                vlanPlanCount++;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        logger.info(String.format("成功上报了%d条网络逻辑区域信息", networkAreaCount));
        logger.info(String.format("成功上报了%d条VLAN逻辑区域信息", vlanPlanCount));
        return "success";
    }

    private Integer uploadReserveDeviceList(List<Map<String, Object>> subList) {
        tempReserveDeviceList.clear();
        tempReserveDeviceList.addAll(subList);
        Integer successNum = 0;
        String json = JSON.toJSONString(tempReserveDeviceMap, SerializerFeature.WriteMapNullValue);
        try {
            HttpClientUtils.httpPostWithJSON(reserveDeviceUrl, json);
            successNum = subList.size();
        } catch (Exception e) {
            logger.error("上传库存设备失败", e);
            e.printStackTrace();
        }
        return successNum;
    }

    @XxlJob("executeCmdbReserveDeviceUpload")
    public Integer executeCmdbReserveDeviceUpload() {
        AdvancedSearch advancedSearch = new AdvancedSearch();
        advancedSearch.setClassName("ReserveDevice");
        advancedSearch.validate();
        List<Map<String, Object>> objList = resourceSearchMapper.selectByAdvancedSearch(advancedSearch);
        int successNum = 0;
        int batchSize = ReserveDeviceBatchSize;
        int size = objList.size();
        int batchNum = size / batchSize;
        int lastBatchSize = size % batchSize;
        for (int i = 0; i < batchNum; i++) {
            List subList = objList.subList(i * batchSize, (i + 1) * batchSize);
            successNum += uploadReserveDeviceList(subList);
        }
        if (lastBatchSize > 0) {
            List subList = objList.subList(batchNum * batchSize, size);
            successNum += uploadReserveDeviceList(subList);
        }
        String log = "上传库存设备成功数量：" + successNum + "，失败数量：" + (size - successNum);
        logger.info(log);
        XxlJobHelper.log(log);

        return successNum;
    }
}
