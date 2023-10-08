package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/8/17 15:47
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class HcsSummaryInfoService {
    @Autowired
    private HcsZoneSummaryInfoMapper hcsZoneSummaryInfoMapper;
    @Autowired
    private OcService ocService;
    @Autowired
    private HcsZoneSummaryCapacityMapper hcsZoneSummaryCapacityMapper;
     @Autowired
    private HcsRegionSummaryInfoMapper hcsRegionSummaryInfoMapper;
     @Autowired
     private HcsRegionSummaryCapacityMapper hcsRegionSummaryCapacityMapper;

    /**
     * 获取hcs下region的resId与name对应的Map
     *
     * @return
     */
    public Map getOcRegionInfo() {
        String sys_businessRegion = ocService.getOcClassInstancesByCondition("SYS_BusinessRegion", 1, 20, "{\"constraint\":[{\"simple\":{\"caseSensitive\":false,\"name\":\"name\",\n" +
                "\"value\":\"信创\",\"operator\":\"contain\"}},{\"logOp\":\"or\",\"simple\":{\"name\":\"name\",\"value\":[\"北京-股份2区\",\"北京-成员2区\"],\"operator\":\"in\"}}]}");
        JSONObject regionJson = JSON.parseObject(sys_businessRegion);
        JSONArray objList = regionJson.getJSONArray("objList");
        //hcs region列表
        List<Map> regionList = JSONObject.parseArray(objList.toJSONString(), Map.class);
        Map<String, String> regionMap = regionList.stream().collect(Collectors.toMap(k -> (String) k.get("id"), v -> (String) v.get("name")));
        return regionMap;
    }
    /**
     * 获取hcs下所有azone信息
     *
     * @return
     */
    public Map getOcAzoneInfo() {
        String sys_businessRegion = ocService.getOcClassInstancesByCondition("SYS_BusinessRegion", 1, 20, "{\"constraint\":[{\"simple\":{\"caseSensitive\":false,\"name\":\"name\",\n" +
                "\"value\":\"信创\",\"operator\":\"contain\"}},{\"logOp\":\"or\",\"simple\":{\"name\":\"name\",\"value\":[\"北京-股份2区\",\"北京-成员2区\"],\"operator\":\"in\"}}]}");
        JSONObject regionJson = JSON.parseObject(sys_businessRegion);
        JSONArray objList = regionJson.getJSONArray("objList");

        String sys_azoneStr = ocService.getOcClassInstances("SYS_Azone", 1, 1000);
        JSONObject azoneJson = JSON.parseObject(sys_azoneStr);
        JSONArray azoneJsonArr = azoneJson.getJSONArray("objList");
        //hcs region列表
        List<Map> regionList = JSONObject.parseArray(objList.toJSONString(), Map.class);
        Map<String, String> regionMap = regionList.stream().collect(Collectors.toMap(k -> (String) k.get("id"), v -> (String) v.get("name")));
        //hcs下所有zone
        Map<String, Map> zoneMap = new HashMap<>();
        for (int i = 0; i < azoneJsonArr.size(); i++) {
            JSONObject singleZoneJson = azoneJsonArr.getJSONObject(i);
            String bizRegionId = singleZoneJson.getString("bizRegionId");
            String resId = singleZoneJson.getString("resId");
            if (regionMap.containsKey(bizRegionId)) {
                singleZoneJson.put("regionName", regionMap.get(bizRegionId));
                zoneMap.put(resId, singleZoneJson);
            }
        }
        return zoneMap;


    }


    /**
     * 根据azone查询所有的主机组
     * @return
     */
    public JSONArray getClusterByAzoneId(String azoneId){
        JSONObject paramJson = new JSONObject();
        paramJson.put("azoneId",azoneId);
        return  ocService.AllInstanceInfoByCondition(paramJson, "SYS_CLUSTER");
    }

    public List<Map>getRegionSummaryInfo(Map params){
        List<Map> regionOverviewInfo = hcsZoneSummaryInfoMapper.getRegionOverviewInfo(params);
        return regionOverviewInfo;
    }

    public List<Map>getRegionStorageInfo(Map params){
        List<Map> regionStorageInfo = hcsZoneSummaryInfoMapper.getRegionStorageInfo(params);
        return regionStorageInfo;
    }

    public List<Map>getRegionSummaryInfoTmp(Map params){
        List<Map> regionOverviewInfo = hcsRegionSummaryInfoMapper.getRegionSummaryInfoTmp(params);
        return regionOverviewInfo;
    }

    public List<Map>getRegionStorageInfoTmp(Map params){
        List<Map> regionStorageInfo = hcsRegionSummaryCapacityMapper.getRegionStorageInfoTmp(params);
        return regionStorageInfo;
    }

    /**
     * 获取集群资源信息
     * @param params
     * @return
     */
    public List<Map>getHcsClusterSummaryInfo(Map params){
        List<Map> clusterSummaryInfo = hcsZoneSummaryInfoMapper.getClusterSummaryInfo(params);
        return clusterSummaryInfo;
    }


    public List<HcsZoneSummaryInfo>getAzoneInfo(HcsZoneSummaryInfo params){
        List<HcsZoneSummaryInfo> regionOverviewInfo = hcsZoneSummaryInfoMapper.getRegionAzoneInfo(params);
        return regionOverviewInfo;
    }



    /**
     * 获取并保存oc侧容量 资源个数等信息
     */
    public void saveHcsSummaryInfo() {
        saveHcsRegionInfo();
        saveHcsAzoneInfo();
    }

    /**
     * 获取并保存oc侧Region下相关容量 资源个数等信息
     */
    public void saveHcsRegionInfo() {
        Map<String, String> ocRegionMap = getOcRegionInfo();
        for (Map.Entry<String, String> entry : ocRegionMap.entrySet()) {
            String regionId = entry.getKey();
            String regionName = entry.getValue();
            HcsRegionSummaryInfo hcsRegionSummaryInfo = new HcsRegionSummaryInfo();
            hcsRegionSummaryInfo.setRegionId(regionId);
            hcsRegionSummaryInfo.setRegionName(regionName);
            /**
             * 获取cpu以及内存信息
             */
            JSONObject ocRegionCapInfo = ocService.getOcRegionCapInfo(regionId);
            JSONObject cpuJson = ocRegionCapInfo.getJSONObject("cpu");
            if (cpuJson != null) {
                JSONObject overSubCapacityJson = cpuJson.getJSONObject("oversubscriptionCapacity");
                Integer totalCpu = overSubCapacityJson.getJSONObject("totalCapacity").getInteger("capacityValue");
                Integer usedCpu = overSubCapacityJson.getJSONObject("allocatedCapacity").getInteger("capacityValue");
                BigDecimal usedRatio = overSubCapacityJson.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                hcsRegionSummaryInfo.setCpuTotal(totalCpu);
                hcsRegionSummaryInfo.setCpuUsed(usedCpu);
                hcsRegionSummaryInfo.setCpuUsedRatio(usedRatio);
            }

            JSONObject memoryJson = ocRegionCapInfo.getJSONObject("memory");
            if (memoryJson != null) {
                JSONObject overSubMemJson = memoryJson.getJSONObject("oversubscriptionCapacity");
                Double totalMem = overSubMemJson.getJSONObject("totalCapacity").getDouble("capacityValue");
                Double usedMem = overSubMemJson.getJSONObject("allocatedCapacity").getDouble("capacityValue");
                BigDecimal usedMemRatio = overSubMemJson.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                hcsRegionSummaryInfo.setMemoryTotal(totalMem);
                hcsRegionSummaryInfo.setMemoryUsed(usedMem);
                hcsRegionSummaryInfo.setMemoryUsedRatio(usedMemRatio);
            }

            /**
             * 维护虚机物理机个数
             */

            JSONObject ocRegionStaInfo = ocService.getOcRegionStatisticsInfo(regionId);
            JSONObject hostJson = ocRegionStaInfo.getJSONObject("host");

            Integer totalOnHost = hostJson.getInteger("normal");
            Integer totalOffHost = hostJson.getInteger("poweroff");
            hcsRegionSummaryInfo.setServerOnNum(totalOnHost);
            hcsRegionSummaryInfo.setServerOffNum(totalOffHost);

            JSONObject vmJson = ocRegionStaInfo.getJSONObject("vm");
            Integer vmOnHost = vmJson.getInteger("running");
            Integer vmOffHost = vmJson.getInteger("stopped");
            hcsRegionSummaryInfo.setVmOnNum(vmOnHost);
            hcsRegionSummaryInfo.setVmOffNum(vmOffHost);

            HcsRegionSummaryInfo existHcsRegionSummaryInfo = hcsRegionSummaryInfoMapper.selectByPrimaryKey(regionId);
            if (existHcsRegionSummaryInfo == null) {
                hcsRegionSummaryInfoMapper.insert(hcsRegionSummaryInfo);
            } else {
                hcsRegionSummaryInfoMapper.updateByPrimaryKey(hcsRegionSummaryInfo);
            }
            /**
             * 维护容量信息表
             */
            JSONObject ocRegionStorageInfo = ocService.getOcRegionStorageInfo(regionId);
            JSONArray capacityList = ocRegionStorageInfo.getJSONArray("capacityList");
            for(int i = 0;i < capacityList.size();i++){
                JSONObject singleCapJson = capacityList.getJSONObject(i);
                JSONObject capacity = singleCapJson.getJSONObject("capacity");
                String dimensionTypeValue = singleCapJson.getJSONArray("dimensions").getJSONObject(0).getString("dimensionValue");
                JSONObject oversubscriptionCapacity = capacity.getJSONObject("oversubscriptionCapacity");
                Double totalCap = oversubscriptionCapacity.getJSONObject("totalCapacity").getDouble("capacityValue");
                Double usedCap = oversubscriptionCapacity.getJSONObject("allocatedCapacity").getDouble("capacityValue");
                BigDecimal usedCapRatio = oversubscriptionCapacity.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                Double freeCap = oversubscriptionCapacity.getJSONObject("freeCapacity").getDouble("capacityValue");
                HcsRegionSummaryCapacity hcsRegionSummaryCapacity = new HcsRegionSummaryCapacity();
                hcsRegionSummaryCapacity.setRegionId(regionId);
                hcsRegionSummaryCapacity.setRegionName(regionName);
                hcsRegionSummaryCapacity.setDimensionType(dimensionTypeValue);
                hcsRegionSummaryCapacity.setTotalCapacity(totalCap);
                hcsRegionSummaryCapacity.setFreeCapacity(freeCap);
                hcsRegionSummaryCapacity.setUsedCapacity(usedCap);
                hcsRegionSummaryCapacity.setCapUsedRatio(usedCapRatio);

                HcsRegionSummaryCapacity tmpHcsRegionSummaryCapacity = hcsRegionSummaryCapacityMapper.selectByPrimaryKey(regionId, dimensionTypeValue);
                if(tmpHcsRegionSummaryCapacity == null){
                    hcsRegionSummaryCapacityMapper.insert(hcsRegionSummaryCapacity);
                }else{
                    hcsRegionSummaryCapacityMapper.updateByPrimaryKey(hcsRegionSummaryCapacity);
                }
            }
        }
    }

    /**
     * 获取并保存oc侧Azone下相关容量 资源个数等信息
     */
    public void saveHcsAzoneInfo() {
        Map<String, Map> ocAzoneInfo = getOcAzoneInfo();
        for (Map.Entry<String, Map> entry : ocAzoneInfo.entrySet()) {
            String azoneId = entry.getKey();
            Map<String, String> zoneMap = entry.getValue();
            String regionName = zoneMap.get("regionName");
            String regionId = zoneMap.get("bizRegionId");
            String azoneName = zoneMap.get("name");
            HcsZoneSummaryInfo hcsZoneSummaryInfo = new HcsZoneSummaryInfo();
            hcsZoneSummaryInfo.setRegionId(regionId);
            hcsZoneSummaryInfo.setRegionName(regionName);
            hcsZoneSummaryInfo.setAzoneId(azoneId);
            hcsZoneSummaryInfo.setAzoneName(azoneName);
            /**
             * 获取cpu以及内存信息
             */
            JSONObject ocZoneCapInfo = ocService.getOcZoneCapInfo(azoneId);
            JSONObject cpuJson = ocZoneCapInfo.getJSONObject("cpu");
            if (cpuJson != null) {
                JSONObject overSubCapacityJson = cpuJson.getJSONObject("oversubscriptionCapacity");
                Integer totalCpu = overSubCapacityJson.getJSONObject("totalCapacity").getInteger("capacityValue");
                Integer usedCpu = overSubCapacityJson.getJSONObject("allocatedCapacity").getInteger("capacityValue");
                BigDecimal usedRatio = overSubCapacityJson.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                hcsZoneSummaryInfo.setCpuTotal(totalCpu);
                hcsZoneSummaryInfo.setCpuUsed(usedCpu);
                hcsZoneSummaryInfo.setCpuUsedRatio(usedRatio);
            }

            JSONObject memoryJson = ocZoneCapInfo.getJSONObject("memory");
            if (memoryJson != null) {
                JSONObject overSubMemJson = memoryJson.getJSONObject("oversubscriptionCapacity");
                Double totalMem = overSubMemJson.getJSONObject("totalCapacity").getDouble("capacityValue");
                Double usedMem = overSubMemJson.getJSONObject("allocatedCapacity").getDouble("capacityValue");
                BigDecimal usedMemRatio = overSubMemJson.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                hcsZoneSummaryInfo.setMemoryTotal(totalMem);
                hcsZoneSummaryInfo.setMemoryUsed(usedMem);
                hcsZoneSummaryInfo.setMemoryUsedRatio(usedMemRatio);
            }


            /**
             * 维护虚机物理机个数
             */

            JSONObject ocZoneStaInfo = ocService.getOcZoneStatisticsInfo(azoneId);
            JSONObject hostJson = ocZoneStaInfo.getJSONObject("host");

            Integer totalOnHost = hostJson.getInteger("normal");
            Integer totalOffHost = hostJson.getInteger("poweroff");
            hcsZoneSummaryInfo.setServerOnNum(totalOnHost);
            hcsZoneSummaryInfo.setServerOffNum(totalOffHost);

            JSONObject vmJson = ocZoneStaInfo.getJSONObject("vm");
            Integer vmOnHost = vmJson.getInteger("running");
            Integer vmOffHost = vmJson.getInteger("stopped");
            hcsZoneSummaryInfo.setVmOnNum(vmOnHost);
            hcsZoneSummaryInfo.setVmOffNum(vmOffHost);

            HcsZoneSummaryInfo existHcsZoneSummaryInfo = hcsZoneSummaryInfoMapper.selectByPrimaryKey(azoneId);
            if (existHcsZoneSummaryInfo == null) {
                hcsZoneSummaryInfoMapper.insert(hcsZoneSummaryInfo);
            } else {
                hcsZoneSummaryInfoMapper.updateByPrimaryKey(hcsZoneSummaryInfo);
            }
            /**
             * 维护容量信息表
             */
            JSONObject ocZoneStorageInfo = ocService.getOcZoneStorageInfo(azoneId);
            JSONArray capacityList = ocZoneStorageInfo.getJSONArray("capacityList");
            for(int i = 0;i<capacityList.size();i++){
                JSONObject singleCapJson = capacityList.getJSONObject(i);
                JSONObject capacity = singleCapJson.getJSONObject("capacity");
                String dimensionTypeValue = singleCapJson.getJSONArray("dimensions").getJSONObject(0).getString("dimensionValue");
                JSONObject oversubscriptionCapacity = capacity.getJSONObject("oversubscriptionCapacity");
                Double totalCap = oversubscriptionCapacity.getJSONObject("totalCapacity").getDouble("capacityValue");
                Double usedCap = oversubscriptionCapacity.getJSONObject("allocatedCapacity").getDouble("capacityValue");
                BigDecimal usedCapRatio = oversubscriptionCapacity.getJSONObject("allocatedCapacity").getBigDecimal("ratio");
                Double freeCap = oversubscriptionCapacity.getJSONObject("freeCapacity").getDouble("capacityValue");
                HcsZoneSummaryCapacity hcsZoneSummaryCapacity = new HcsZoneSummaryCapacity();
                hcsZoneSummaryCapacity.setAzoneId(azoneId);
                hcsZoneSummaryCapacity.setAzoneName(azoneName);
                hcsZoneSummaryCapacity.setRegionId(regionId);
                hcsZoneSummaryCapacity.setRegionName(regionName);
                hcsZoneSummaryCapacity.setDimensionType(dimensionTypeValue);
                hcsZoneSummaryCapacity.setTotalCapacity(totalCap);
                hcsZoneSummaryCapacity.setFreeCapacity(freeCap);
                hcsZoneSummaryCapacity.setUsedCapacity(usedCap);
                hcsZoneSummaryCapacity.setCapUsedRatio(usedCapRatio);

                HcsZoneSummaryCapacity tmpHcsZoneSummaryCapacity = hcsZoneSummaryCapacityMapper.selectByPrimaryKey(azoneId, dimensionTypeValue);
                if(tmpHcsZoneSummaryCapacity == null){
                    hcsZoneSummaryCapacityMapper.insert(hcsZoneSummaryCapacity);
                }else{
                    hcsZoneSummaryCapacityMapper.updateByPrimaryKey(hcsZoneSummaryCapacity);
                }
            }
        }
    }

}
