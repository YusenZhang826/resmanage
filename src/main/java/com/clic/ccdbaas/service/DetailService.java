package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.SpringReflectUtils;
import com.clic.ccdbaas.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class DetailService {

    @Autowired
    OcService ocService;
    @Autowired
    CCEService cceService;

    @Value("${oc.cmdb.cce.cluster}")
    private String clusterClassName;
    @Value("${oc.cmdb.cce.workload}")
    private String workloadClassName;
    @Value("${oc.cmdb.cce.node}")
    private String nodeClassName;
    @Value("${oc.cmdb.vm}")
    private String vmClassName;
    @Value("${oc.cmdb.volume}")
    private String volumeClassName;
    @Value("${oc.cmdb.router}")
    private String routerClassName;
    @Value("${oc.cmdb.router.vm}")
    private String routerVMClassName;
    @Value("${oc.cmdb.instances.relations.volume-nova}")
    private String cloudVolumeMountNova;
    @Value("${oc.cmdb.port}")
    private String portClassName;
    @Value("${oc.cmdb.subnet}")
    private String subnetClassName;
    @Value("${oc.cmdb.vfw}")
    private String vfwClassName;
    @Value("${oc.cmdb.port.vfw}")
    private String portVfwClassName;
    @Value("${oc.cmdb.port.subnet}")
    private String portSubnetClassName;
    @Value("${oc.cmdb.network}")
    private String networkClassName;
    @Value("${oc.cmdb.flavor}")
    private String flavorClassName;
    private static final ExecutorService pool = Executors.newFixedThreadPool(15);

    /**
     * 获取单条oc实例数据
     *
     * @param className
     * @param resId
     * @return
     */
    public JSONObject getInstanceInfo(String className, String resId) {
        if (className.equals(workloadClassName))
            return cceService.getDetailById(className, resId);

        String objStr = ocService.getOcClassInstances(className, resId);
        return JSONObject.parseObject(objStr);
    }

    /**
     * 分页获取关联资源列表数据
     *
     * @param baseClassName
     * @param relateClassName
     * @param resId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public JSONObject getRelateInstanceInfo(String baseClassName, String relateClassName, String resId, int pageNum, int pageSize) {
        List<String> resIdValue = new ArrayList<>();
        resIdValue.add(resId);

        if (baseClassName.equals(clusterClassName)) {
            if (relateClassName.equals(vmClassName))
                return getRelateInstanceByInstance(nodeClassName, "ref_clusterId", resIdValue, "ref_vmId", vmClassName, "resId", pageNum, pageSize);
            return cceService.getRelateInfo(resId, "ref_clusterId", relateClassName, pageNum, pageSize);
        } else if (baseClassName.equals(workloadClassName))
            return cceService.getRelateInfo(resId, "ref_workloadId", relateClassName, pageNum, pageSize);
        else if (baseClassName.equalsIgnoreCase(vmClassName) && relateClassName.equals(volumeClassName))
            return getRelateInstanceByRelation(cloudVolumeMountNova, "vmId", resIdValue, "volumeId", volumeClassName, "resId", pageNum, pageSize);
        else if (baseClassName.equals(routerClassName)) {
            if (relateClassName.equals(vmClassName))
                return getRelateInstanceByInstance(routerVMClassName, "routerId", resIdValue, "vmId", vmClassName, "resId", pageNum, pageSize);

            JSONObject baseInstance = getInstanceInfo(routerClassName, resId);
            String nativeId = baseInstance.getString("nativeId");
            return getRouterSubnetVFW(nativeId, relateClassName, pageNum, pageSize);
        }

        return null;
    }

    /**
     * 根据实例接口分页查找关联数据
     *
     * @param searchClassName      借助查询的实例classname
     * @param conditionAttr        构建condition的查询属性名
     * @param conditionValue       构建condition的查询值
     * @param relateAttrOfRelation 根据condition查询结果的关联资源属性名
     * @param relateClassName      关联资源classname
     * @param relateAttr           关联资源中与relateAttrOfRelation对应的字段名
     * @param pageNum              分页页数
     * @param pageSize             分页大小
     * @return
     */
    public JSONObject getRelateInstanceByInstance(String searchClassName, String conditionAttr, List<String> conditionValue,
                                                  String relateAttrOfRelation, String relateClassName, String relateAttr,
                                                  int pageNum, int pageSize) {
        List<Map<String, Object>> conditionParams = new ArrayList<>();
        conditionParams.add(getConditionParam(conditionAttr, conditionValue, "in", ""));
        JSONArray jsonArr = getInstanceAssociateArr(searchClassName, conditionParams);
        if (jsonArr == null || jsonArr.size() == 0) return null;

        JSONObject vmJSON = getInstanceByList(jsonArr, relateAttrOfRelation, relateClassName, relateAttr, pageNum, pageSize);
        if (vmJSON == null || vmJSON.size() == 0) return null;

        JSONArray vmArr = vmJSON.getJSONArray("objList");
        for (int i = 0; i < vmArr.size(); i++) {
            JSONObject vm = vmArr.getJSONObject(i);
            String flavorId = vm.getString("flavorId");
            JSONObject flavorJSON = getInstanceInfo(flavorClassName, flavorId);
            vm.put("flavorName", flavorJSON.getString("name"));
        }
        return vmJSON;
    }

    /**
     * 根据关系接口分页查找关联数据
     *
     * @param relationClassName    借助查询的关联关系classname
     * @param conditionAttr        构建condition的查询属性名
     * @param conditionValue       构建condition的查询值
     * @param relateAttrOfRelation 根据condition查询结果的关联资源属性名
     * @param relateClassName      关联资源classname
     * @param relateAttr           关联资源中与relateAttrOfRelation对应的字段名
     * @param pageNum              分页页数
     * @param pageSize             分页大小
     * @return
     */
    public JSONObject getRelateInstanceByRelation(String relationClassName, String conditionAttr, List<String> conditionValue,
                                                  String relateAttrOfRelation, String relateClassName, String relateAttr,
                                                  int pageNum, int pageSize) {
        JSONArray messageJson = getRelationAssociateArr(relationClassName, conditionAttr, conditionValue);
        return getInstanceByList(messageJson, relateAttrOfRelation, relateClassName, relateAttr, pageNum, pageSize);
    }

    /**
     * 根据condition条件在实例中查询
     *
     * @param className
     * @param conditionParams condition参数
     * @return
     */
    public JSONArray getInstanceAssociateArr(String className, List<Map<String, Object>> conditionParams) {
        String condition = ocService.getConditionWithMultiParams(conditionParams);
        return ocService.getAllInstanceWithCondition(className, condition);
    }

    /**
     * 根据condition条件在实例关系中查询
     *
     * @param relationName
     * @param conditionAttr
     * @param conditionValue
     * @return
     */
    public JSONArray getRelationAssociateArr(String relationName, String conditionAttr, List<String> conditionValue) {
        String condition = ocService.getStorageCondition(conditionAttr, conditionValue, "in", "");
        return ocService.getAllRelationWithCondition(relationName, condition);
    }

    /**
     * 分页查询和jsonArr有关联的实例数据
     *
     * @param arr                  有待提取id列表的jsonArr
     * @param relateAttrOfRelation arr和关联实例的关联属性
     * @param relateClassName      关联实例classname
     * @param relateAttr           关联实例的查询属性
     * @param pageNum
     * @param pageSize
     * @return
     */
    public JSONObject getInstanceByList(JSONArray arr, String relateAttrOfRelation, String relateClassName, String relateAttr, int pageNum, int pageSize) {
        if (arr.size() == 0) return new JSONObject();

        List<String> idList = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            idList.add(arr.getJSONObject(i).getString(relateAttrOfRelation));
        }
        String relateCondition = ocService.getStorageCondition(relateAttr, idList, "in", "");
        String relateMessage = ocService.getOcInstancesByCondition(relateClassName, pageNum, pageSize, relateCondition);
        return JSONObject.parseObject(relateMessage);
    }

    /**
     * 分页获取私有云关联子网或网络ACL
     *
     * @param routerNativeId  私有云nativeid
     * @param relateClassName 关联资源classname，子网或网络ACL类型
     * @param pageNum
     * @param pageSize
     * @return
     */
    public JSONObject getRouterSubnetVFW(String routerNativeId, String relateClassName, int pageNum, int pageSize) {
        List<Map<String, Object>> conditionParams = new ArrayList<>();
        conditionParams.add(getConditionParam("devNativeId", routerNativeId, "equal", ""));
        conditionParams.add(getConditionParam("vdcId", "", "not equal", "and"));
        JSONArray portArr = getInstanceAssociateArr(portClassName, conditionParams);
        if (portArr == null || portArr.size() == 0) return null;

        List<String> portResIds = new ArrayList<>();
        for (int i = 0; i < portArr.size(); i++)
            portResIds.add(portArr.getJSONObject(i).getString("resId"));

        if (relateClassName.equals(networkClassName)) {
            JSONObject retJSON = new JSONObject();
            JSONArray retArr = new JSONArray();
//            JSONObject networkJSON = getInstanceByList(portArr, "networkId", networkClassName, "resId", pageNum, pageNum);
            JSONObject subnetJSON = getRelateInstanceByRelation(portSubnetClassName, "portId", portResIds, "subnetId", subnetClassName, "resId", pageNum, pageSize);
            JSONArray subnetArr = subnetJSON.getJSONArray("objList");
            int totalNum = subnetJSON.getInteger("totalNum");
            for (int i = 0; i < subnetArr.size(); i++) {
                JSONObject subnet = subnetArr.getJSONObject(i);
                String networkId = subnet.getString("networkId");
                String cidr = subnet.getString("cidr");
                String gatewayIp = subnet.getString("gatewayIp");
                JSONObject networkJSON = getInstanceInfo(networkClassName, networkId);
                networkJSON.put("cidr", cidr);
                networkJSON.put("gatewayIp", gatewayIp);
                retArr.add(networkJSON);
            }
            retJSON.put("objList", retArr);
            retJSON.put("totalNum", totalNum);
            return retJSON;
        } else if (relateClassName.equals(vfwClassName))
            return getRelateInstanceByRelation(portVfwClassName, "portId", portResIds, "vfwId", vfwClassName, "resId", pageNum, pageSize);

        return null;
    }

    /**
     * 构造condition参数字典
     *
     * @param name
     * @param value
     * @param operator
     * @param logOp
     * @return
     */
    public Map<String, Object> getConditionParam(String name, Object value, String operator, String logOp) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("value", value);
        param.put("operator", operator);
        if (StringUtils.isNotEmpty(logOp)) param.put("logOp", logOp);
        return param;
    }

    public TableDataInfo getAllInstance(String className) {
        TableDataInfo res;
        try {
            res = (TableDataInfo) SpringReflectUtils.springInvokeMethod(className, "getAllInstance", null);
            //obj = o;
            //return o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return res;

    }

    public JSONObject getInstanceDetail(String className, String resId) {
        JSONObject obj = new JSONObject();
        try {
            Object[] para = new Object[]{resId};
            JSONObject o = (JSONObject) SpringReflectUtils.springInvokeMethod(className, "getInstanceDetail", para);
            obj = o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return obj;
    }

    public JSONObject getResCount(JSONArray queryJson) {
        //创建一个与JSONArray 长度相同的String数组
        String[] strings = new String[queryJson.size()];
        //使用JSONArray 中的toArray进行转换
        String[] classNameArr = queryJson.toArray(strings);
        CountDownLatch countdown = new CountDownLatch(classNameArr.length);
        JSONObject resJson = new JSONObject();
        for (String singleClassName : classNameArr) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    int instanceCount = 0;
                    try {
                        instanceCount = (int) SpringReflectUtils.springInvokeMethod(singleClassName, "getResCount", null);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    resJson.put(singleClassName, instanceCount);
                    countdown.countDown();
                }
            });
        }
        try {
            countdown.await(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resJson;
    }
}
