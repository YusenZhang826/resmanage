package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CCEService {

    @Autowired
    private CloudVmareService cloudVmareService;
    @Autowired
    private OcService ocService;

    @Value("${oc.cmdb.vm}")
    private String vmClassName;
    @Value("${oc.cmdb.cce.cluster}")
    private String clusterClassName;
    @Value("${oc.cmdb.cce.workload}")
    private String workloadClassName;
    private static final Map<String, String> optDict;

    private static final ExecutorService fixedPool = Executors.newFixedThreadPool(15);

    static {
        String containOpt = "contain";
        String equalOpt = "equal";
        optDict = new HashMap<>();
        optDict.put("name", containOpt);
        optDict.put("bizRegionName", containOpt);
        optDict.put("type", equalOpt);
        optDict.put("vdcName", containOpt);
        optDict.put("clusterName", containOpt);
        optDict.put("tenantName", containOpt);
    }

    /**
     * 获取集群列表数据
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @param value
     * @return
     */
    public JSONObject getClusterListInfo(int pageNum, int pageSize, String name, String value) {
        String condition = ocService.getCondition(name, value, optDict.get(name), "");
        JSONObject jsonObj = JSONObject.parseObject(ocService.getOcInstancesByCondition(clusterClassName, pageNum, pageSize, condition));
        return jsonObj;
    }

    /**
     * 获取工作负载列表数据
     *
     * @return
     */
    public JSONObject getWorkloadListInfo(int pageNum, int pageSize, String name, String value) {
        Map<String, String> clusterMap = new HashMap<>();
        JSONArray clusterArr = ocService.getAllInstanceInfo(clusterClassName);
        // 将cluster映射到map里
        for (int i = 0; i < clusterArr.size(); i++) {
            JSONObject clusterTmp = clusterArr.getJSONObject(i);
            clusterMap.put(clusterTmp.getString("resId"), clusterTmp.getString("name"));
        }

        boolean isSerachClusterName = name.equals("clusterName");
        if (isSerachClusterName && StringUtils.isNotEmpty(value))
            return getWorkloadListByClusterName(value, pageNum, pageSize, clusterMap);

        JSONObject jsonObj = null;
        if (!isSerachClusterName) {
            String condition = ocService.getCondition(name, value, optDict.get(name), "");
            jsonObj = JSONObject.parseObject(ocService.getOcInstancesByCondition(workloadClassName, pageNum, pageSize, condition));
        } else {
            jsonObj = JSONObject.parseObject(ocService.getOcClassInstances(workloadClassName, pageNum, pageSize));
        }

        JSONArray jsonArr = jsonObj.getJSONArray("objList");

        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject workload = jsonArr.getJSONObject(i);
            String relateId = workload.getString("ref_clusterId");

            if (!clusterMap.containsKey(relateId)) continue;

            String clusterName = clusterMap.get(relateId);
            workload.put("clusterName", clusterName);
        }

        return jsonObj;
    }

    /**
     * 根据集群名查询工作负载列表
     *
     * @param clusterName
     * @param pageNum
     * @param pageSize
     * @param clusterMap
     * @return
     */
    public JSONObject getWorkloadListByClusterName(String clusterName, int pageNum, int pageSize, Map<String, String> clusterMap) {
        JSONArray allWorkloadInfo = ocService.getAllInstanceInfo(workloadClassName);

        int totalNum = 0;
        JSONObject jsonObjRet = new JSONObject();
        JSONArray jsonArrRet = new JSONArray();
        for (int i = 0; i < allWorkloadInfo.size(); i++) {
            JSONObject workloadJSON = allWorkloadInfo.getJSONObject(i);
            String refClusterId = workloadJSON.getString("ref_clusterId");
            if (clusterMap.containsKey(refClusterId) && clusterMap.get(refClusterId).contains(clusterName)) {
                totalNum++;
                workloadJSON.put("clusterName", clusterMap.get(refClusterId));
                jsonArrRet.add(workloadJSON);
            }
        }

        jsonObjRet.put("objList", jsonArrRet.subList((pageNum - 1) * pageSize, Math.min(pageNum * pageSize, jsonArrRet.size())));
        jsonObjRet.put("totalNum", totalNum);

        return jsonObjRet;
    }

    /**
     * 根据resId获取实例单条数据
     *
     * @param resId
     * @return
     */
    public JSONObject getDetailById(String className, String resId) {
        JSONObject jsonObj = JSONObject.parseObject(ocService.getOcClassInstances(className, resId));

        if (className.equals(workloadClassName)) {
            String relateId = jsonObj.getString("ref_clusterId");
            JSONObject relateJsonObj = JSONObject.parseObject(ocService.getOcClassInstances(clusterClassName, relateId));
            jsonObj.put("clusterName", relateJsonObj.getString("name"));
        }

        return jsonObj;
    }

    /**
     * 获取有关联关系的实例数据
     *
     * @param resId
     * @param relateAttr
     * @param relateClassName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public JSONObject getRelateInfo(String resId, String relateAttr, String relateClassName, int pageNum, int pageSize) {
        String condition = ocService.getCondition(relateAttr, resId, "equal", "");
        JSONObject jsonObj = JSONObject.parseObject(ocService.getOcInstancesByCondition(relateClassName, pageNum, pageSize, condition));

        return jsonObj;
    }
}