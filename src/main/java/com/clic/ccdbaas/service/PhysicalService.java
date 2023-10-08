package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.CloudVmNovaMapper;
import com.clic.ccdbaas.dao.PhysicalHostMapper;
import com.clic.ccdbaas.dao.PhysicalMachineMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.PhysicalMachine;
import com.clic.ccdbaas.entity.PhysicalServer;
import com.clic.ccdbaas.entity.StorageDevice;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class PhysicalService {

    private static final Logger logger = LoggerFactory.getLogger(StorageDeviceService.class);
    @Autowired
    private OcService ocService;
    //物理机
    @Value("${oc.cmdb.server}")
    private String physicalList;
    //弹性云服务器
    @Value("${oc.cmdb.vm}")
    private String cloudVmNova;
    //宿主机
    @Value("${oc.cmdb.host}")
    private String cloudHost;

    @Autowired
    CloudVmareService cloudVmareService;
    @Autowired
    PhysicalHostService physicalHostService;
    @Autowired
    PhysicalMachineMapper physicalMachineMapper;
    @Autowired
    PhysicalHostMapper physicalHostMapper;
    @Autowired
    CloudVmNovaMapper cloudVmNovaMapper;

    String userNameInfo = "";

    //查找JSONObject的resid
    public String findResid(JSONObject jsob) {
        if (!jsob.containsKey("id")) {
            return "";
        } else {
            return jsob.getString("id");
        }
    }

    public JSONArray getJson(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }


    /**
     * 获取弹性云服务器、物理机、宿主机合并列表
     */
    public JSONArray getMergeCloudResouce(JSONObject requestParams) {
        JSONArray mergeJsonArr = new JSONArray();
        //物理机列表
        JSONArray physicalHostArr = ocService.AllInstanceInfoByCondition(requestParams, physicalList);
        //弹性云服务器列表
        JSONArray cloudVmNovaArr = ocService.AllInstanceInfoByCondition(requestParams, cloudVmNova);
        //宿主机列表
        JSONArray cloudHostArr = ocService.AllInstanceInfoByCondition(requestParams, cloudHost);
        //以目前物理机的参数为准,降低前端代码改动量
        mergeJsonArr = physicalHostArr;
        for (int i = 0; i < cloudVmNovaArr.size(); i++) {
            JSONObject newVmNovaJson = new JSONObject();

            JSONObject singleVmNova = cloudVmNovaArr.getJSONObject(i);
            newVmNovaJson = singleVmNova;
            newVmNovaJson.put("ipAddress", singleVmNova.getString("mainIp"));
            newVmNovaJson.put("cpuCores", singleVmNova.getString("cpuCoreNum"));
            newVmNovaJson.put("memoryCapacity", singleVmNova.getString("memorySize"));
            newVmNovaJson.put("cpuCores", singleVmNova.getString("cpuCoreNum"));
            //根据azoneName判断架构
            String cpuArch = singleVmNova.getString("azoneName").contains("鲲鹏") ? "arm" : "x86";
            newVmNovaJson.put("cpuArch", cpuArch);
            mergeJsonArr.add(newVmNovaJson);
        }
        return mergeJsonArr;
    }

    /**
     * 获取物理机列表
     *
     * @param requestParams
     * @return
     */
    public JSONArray getX86ServerArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }

    /**
     * 获取弹性云服务器列表
     *
     * @param requestParams
     * @return
     */
    public JSONArray getCloudVmNovaArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, cloudVmNova);
    }

    /**
     * 获取宿主机列表
     *
     * @param requestParams
     * @return
     */
    public JSONArray getCloudHostArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, cloudHost);
    }

    public JSONArray getJson() {
        return ocService.getAllInstanceInfo(physicalList);
    }

    /**
     * 使用参数修改单个资源实例
     *
     * @param paramJson
     * @param operator
     * @return
     */
    public boolean updateData(JSONObject paramJson, String operator) {
        //判断json串中有没有res字段
        try {
            String resId = paramJson.getString("resId");
            CloudVmare queryVm = new CloudVmare(resId);

            List<CloudVmare> allResourceList = cloudVmareService.getAllResourceList(queryVm);

            if (allResourceList.size() > 0) {
                CloudVmare localVm = allResourceList.get(0);
                Map localVmMap = new HashMap();
                Field[] declaredFields = localVm.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    String fileName = field.getName();
                    localVmMap.put(fileName, field.get(localVm));
                }

                /**
                 * 更新物理机、宿主机、虚机信息
                 */
                Map whiteInfoMap = new HashMap();
                for (Map.Entry<String, Object> entry : paramJson.entrySet()) {
                    String key = entry.getKey();
                    Object newValue = entry.getValue();
                    Object oldValue = localVmMap.get(key);
                    whiteInfoMap.put(key, newValue);
                    if (!newValue.equals(oldValue)) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(resId, localVm.getClassName(), localVm.getName(),
                                operator, 2, key, oldValue,
                                newValue, 0));
                    }

                }
                physicalMachineMapper.updateAdministrator(whiteInfoMap);
                cloudVmareService.updateAdministrator(whiteInfoMap);
                physicalHostService.updateAdministrator(whiteInfoMap);
            }

            logger.info("更新成功");
        } catch (Exception e) {
            logger.info("更新失败");
        }
        return true;
    }

    /**
     * 使用参数批量修改资源实例
     *
     * @param requestParams
     * @return
     */
    public boolean updatePhysicalList(JSONObject requestParams) {
        try {
            JSONArray physiclist = requestParams.getJSONArray("list");
            String operator = requestParams.getString("operator");
            for (int i = 0; i < physiclist.size(); i++) {
                JSONObject obj = physiclist.getJSONObject(i);
                updateData(obj, operator);
            }
            logger.info("物理服务器批量更新成功");
        } catch (Exception e) {
            logger.info("物理服务器批量更新失败");
            return false;
        }
        return true;
    }


    /**
     * 批量删除
     *
     * @param paramJson
     */
    public void delelteDevice(JSONArray paramJson) {
        logger.info("删除设备请求报文为:" + paramJson.toString());
        for (int i = 0; i < paramJson.size(); i++) {
            ocService.deleteOcClassInstances(physicalList, paramJson.get(i).toString());
            logger.info("删除资源成功,设备id为" + paramJson.get(i).toString());
        }
    }

    /**
     * 查询单个
     *
     * @param instanceId
     */
    public JSONObject getMessage(String instanceId) {
        logger.info("查询设备详情id为" + instanceId);
        JSONObject allInstanceInfo = ocService.getAllInstanceInfo(physicalList, instanceId);
        logger.info("设备详情：" + allInstanceInfo);
        return allInstanceInfo;
    }

    public JSONObject insertDevice(JSONObject requestParams) {
        List list = new ArrayList();
        list.add(requestParams.toString());
        String result = ocService.addInstances(physicalList, list.toString());
        return JSON.parseObject(result);
    }

    public void exportDevice(JSONArray list, HttpServletResponse response) {
        JSONArray request = null;
        try {
            if (!StringUtils.isEmpty(list)) {
                request = list;
            } else {
                request = getJson();
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ocService.setExcelRespProp(response, "库存设备列表");
            List<StorageDevice> memberList = request.toJavaList(StorageDevice.class);
            logger.info("Excel导出库存设备信息" + memberList.toString());
            EasyExcel.write(response.getOutputStream())
                    .head(StorageDevice.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("库存设备列表")
                    .doWrite(memberList);
        } catch (Exception e) {
            logger.info("库存设备列表失败");
        }
    }

    public void getName(String userName) {
        userNameInfo = userName;
    }

    public JSONObject getCloudVMInfo(String resid) {
        PhysicalMachine physicalInfoByResId = physicalMachineMapper.getPhysicalInfoByResId(resid);
        CloudVmare cloudVMInfoByResId = cloudVmNovaMapper.getCloudVMInfoByResId(resid);
        if (physicalInfoByResId != null) {
            return (JSONObject) JSON.toJSON(physicalInfoByResId);
        }
        if (cloudVMInfoByResId != null) {
            return (JSONObject) JSON.toJSON(cloudVMInfoByResId);
        }
        return null;
    }

    public String updateComputeInfo(String resId, String usageDes) {
        try {
            cloudVmNovaMapper.updateUsageDes(usageDes, resId);
        } catch (Exception e) {
            return "Error";
        }
        return "Success";
    }

    public String updatePhysicalComputeInfo(String resId, String usageDes) {
        try {
            physicalMachineMapper.updateUsageDes(usageDes, resId);
        } catch (Exception e) {
            return "Error";
        }
        return "Success";
    }

    public String insertList(JSONArray requestParams) {
        try {
            for (Object param : requestParams) {
                JSONObject json = JSONObject.parseObject(param.toString());
                PhysicalServer physicalMachine = json.toJavaObject(PhysicalServer.class);
                physicalMachineMapper.insert(physicalMachine);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
