package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.clic.ccdbaas.dao.VlanMapper;
import com.clic.ccdbaas.entity.Vlan;
import com.clic.ccdbaas.entity.VlanPlan;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


@Service("VLAN")
public class VlanService {

    private static final Logger logger = LoggerFactory.getLogger(VlanService.class);
    @Autowired
    VlanMapper vlanMapper;
    @Autowired
    OcService ocService;
    @Autowired
    CmdbService cmdbService;
    @Value("${oc.cmdb.vlan.plan.device}")
    String vlanPlanDevice;

    public int getVlanCount() {
        return vlanMapper.getVlanCount();
    }

    public List<Vlan> getAllVlanInfo() {
        return vlanMapper.getAllVlan();
    }

    public HashMap getVlanInfoByResId(String resId) {
        return vlanMapper.getVlanAndAreaInfoByResId(resId);
    }

    public List<VlanPlan> getAllVlanInstance(VlanPlan vlanPlan) {
        return vlanMapper.getAllVlanInstance(vlanPlan);
    }

    public JSONObject insertSingleVlanInfo(JSONObject requestParams) {
        JSONObject jsonObject = null;
        String vlanPlanJson = "";
        try {
            VlanPlan vlanPlan = JSON.parseObject(String.valueOf(requestParams), VlanPlan.class);
            vlanPlan.setResId(IdUtils.generatedUUID());
            vlanPlan.setLast_Modified(new Date());
            vlanMapper.insertSingleVlanInfo(vlanPlan);
            logger.info("成功插入单条数据");
            vlanPlanJson = JSON.toJSONString(vlanPlan);
            jsonObject = JSONObject.parseObject(vlanPlanJson);
        } catch (Exception e) {
            logger.info("插入单条数据失败");
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    public void deleteSingleVlanInfo(String resId) {
        try {
            vlanMapper.deleteSingleVlanInfo(resId);
            logger.info("成功删除resId为 " + resId + " 的单条数据");
        } catch (Exception e) {
            logger.info("删除单条数据失败");
            throw new RuntimeException(e);
        }
    }

    public JSONObject updateSingleVlanInfo(JSONObject requestParams) {
        JSONObject jsonObject = null;
        String vlanPlanJson = "";
        try {
            VlanPlan vlanPlan = JSON.parseObject(String.valueOf(requestParams), VlanPlan.class);
            vlanPlan.setLast_Modified(new Date());
            vlanMapper.updateSingleVlanInfo(vlanPlan);
            logger.info("成功更新resId为 " + vlanPlan.getResId() + " 的单条数据");
            vlanPlanJson = JSON.toJSONString(vlanPlan);
            jsonObject = JSONObject.parseObject(vlanPlanJson);
        } catch (Exception e) {
            logger.info("更新单条数据失败");
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    public int getResCount() {
        return vlanMapper.getVlanCount();
    }


    /**
     * 解析vlan规划
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateVlanInstanceByExcel(VlanPlan vlanPlan) {
        VlanPlan temp = vlanMapper.getVlanInfoByName(vlanPlan.getName());
        vlanPlan.setResId(IdUtils.generatedUUID());
        if (vlanPlan.getLast_Modified() == null) {
            vlanPlan.setLast_Modified(new Date());
        }
        if (temp == null) {
            vlanMapper.insertSingleVlanInfo(vlanPlan);
        } else {
            Map<String, Object> compareObjectMap = null;
            try {
                compareObjectMap = CompareObjectUtils.compareObject(temp, vlanPlan);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (compareObjectMap.size() > 2) {
                vlanMapper.updateSingleVlanInfoByName(vlanPlan);
                System.out.println(compareObjectMap);
            }
        }
    }

    public List<VlanPlan> getExportVlanInstanceInfo(List<String> resIdList, VlanPlan vlanPlan) {
        List<VlanPlan> vlanPlanList = new ArrayList<>();
        if (resIdList.size() == 0) {
            vlanPlanList = vlanMapper.getAllVlanInstance(vlanPlan);
        } else {
            for (String resId : resIdList) {
                vlanPlan = vlanMapper.getVlanInfoByResId(resId);
                vlanPlanList.add(vlanPlan);
            }
        }
        return vlanPlanList;
    }

    /**
     * oc VLAN区域同步（新增、更新、上报）
     */
    @XxlJob("executeVlanPlanSynchronization")
    public String executeVlanPlanSynchronization() {
        int insertCount = 0;
        int updateCount = 0;
        int updateInstanceCount = 0;
        int deleteCount = 0;
        JSONArray jsonArray = ocService.getAllInstanceInfo(vlanPlanDevice);
        List<VlanPlan> vlanPlanList = JSONObject.parseArray(jsonArray.toJSONString(), VlanPlan.class);
        try {
            List<VlanPlan> originalVlanPlanList = vlanMapper.getAllVlanInstance(new VlanPlan());
            Map<String, VlanPlan> originalVlanPlanListMap = originalVlanPlanList.stream().collect(Collectors.toMap(k -> k.getResId(), VlanPlan -> VlanPlan));

            //遍历执行新增和更新网络逻辑区域操作
            for (VlanPlan vlanPlan : vlanPlanList) {
                if (originalVlanPlanListMap.containsKey(vlanPlan.getResId())) {
                    vlanPlan.setLast_Modified(new Date());
                    VlanPlan originalVlanPlan = originalVlanPlanListMap.get(vlanPlan.getResId());
                    originalVlanPlan.setIpCount(0);
                    Map<String, Object> compareObjectMap = CompareObjectUtils.compareObject(originalVlanPlan, vlanPlan);
                    if (compareObjectMap.size() > 1) {
                        System.out.println(compareObjectMap);
                        vlanMapper.updateSingleVlanInfo(vlanPlan);
                        cmdbService.uploadVlanPlan(vlanPlan);
                        updateInstanceCount++;

                        //记录所有变更日志信息
                        for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                            if (entry.getKey().equals("last_Modified")) {
                                continue;
                            } else {
                                String str = (String) entry.getValue();
                                AsyncManager.me().execute(AsyncFactory.recordOper(vlanPlan.getResId(), "Vlan_Plan_Device", vlanPlan.getName(),
                                        "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                                        str.substring(str.indexOf("*") + 7), 0));
                            }
                        }
                    }
                    updateCount++;
                    originalVlanPlanListMap.remove(vlanPlan.getResId());
                    continue;
                }
                vlanMapper.insertSingleVlanInfo(vlanPlan);
                cmdbService.uploadVlanPlan(vlanPlan);
                AsyncManager.me().execute(AsyncFactory.recordOper(vlanPlan.getResId(), "Vlan_Plan_Device",
                        vlanPlan.getName(), "resManage", 1, "All", "", "", 0));
                insertCount++;
            }

            //遍历执行删除网络逻辑区域操作
            for (String key : originalVlanPlanListMap.keySet()) {
                VlanPlan vlanPlan = originalVlanPlanListMap.get(key);
                vlanMapper.deleteSingleVlanInfo(vlanPlan.getResId());
                AsyncManager.me().execute(AsyncFactory.recordOper(vlanPlan.getResId(), "Vlan_Plan_Device",
                        vlanPlan.getName(), "resManage", 3, "All", "", "", 0));
                deleteCount++;
            }
            logger.info("同步---------结束");
            logger.info("新增 " + insertCount + " 条VLAN区域资源");
            logger.info("待更新 " + updateCount + " 条VLAN区域资源，实际更新 " + updateInstanceCount + " 条VLAN区域资源");
            logger.info("删除 " + deleteCount + " 条VLAN区域资源");
        } catch (Exception e) {
            logger.info("同步VLAN区域资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

    public List<VlanPlan> getAllVlanNameAndIpCount(VlanPlan vlanPlan) {
        return vlanMapper.getAllVlanNameAndIpCount(vlanPlan);
    }
}
