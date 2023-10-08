package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.ObClusterMapper;
import com.clic.ccdbaas.dao.ObTenantMapper;
import com.clic.ccdbaas.entity.ObTenant;
import com.clic.ccdbaas.entity.ObTenantExample;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.model.ComparbleResult;
import com.clic.ccdbaas.utils.AESUtils;
import com.clic.ccdbaas.utils.ComparbleUtils;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/4 14:55
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class ObTenantService {
    @Autowired
    private ObTenantMapper obTenantMapper;
    @Autowired
    private ObClusterMapper obClusterMapper;
    @Value("${ob.cluster.list}")
    private String clusterListUrl;
    @Value("${ob.tenant.list}")
    private String tenantListUrl;
    @Value("${ob.tenant.detail}")
    private String tenantDetailUrl;
    /**
     * 根据集群id以及租户id获取全量用户信息
     *
     * @return
     */
    public JSONObject getTenUserInfo(String clusterId, String tenantId) {
        String userName = "admin";
        String password = "@(&XMIhU99_9in{h_";
        String url = "http://10.18.63.10:8080/api/v2/ob/clusters/" + clusterId + "/tenants/" + tenantId + "/users";
        String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
        JSONObject resJson = JSON.parseObject(resStr);
        return resJson.getJSONObject("data");

    }


    /**
     * 根据集群id以及租户id获取全量用户信息
     *
     * @return
     */
    public JSONObject getTenInfo(String clusterId, String tenantId) {
        String userName = "admin";
        String password = "@(&XMIhU99_9in{h_";
        String url = "http://10.18.63.10:8080/api/v2/ob/clusters/" + clusterId + "/tenants/" + tenantId + "/users";
        String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
        JSONObject resJson = JSON.parseObject(resStr);
        return resJson.getJSONObject("data");

    }


    /**
     * 保存集群信息
     *
     * @return
     */
  //  @XxlJob("saveTenInfo")
    public void saveTenInfo() {
        List<Map> allOcp = obClusterMapper.selectAllOcpInfo("ocp");
        List<ObTenant> originalObTenants = obTenantMapper.selectByExample(new ObTenantExample());
        List<ObTenant> remoteObTenants = new ArrayList<ObTenant>();
        for (int i = 0; i < 1; i++) {

            Map ocpInfoMap = allOcp.get(i);
            String encryptName = (String) ocpInfoMap.get("userName");
            String encryptPassword = (String) ocpInfoMap.get("password");
            String ocpId = String.valueOf(ocpInfoMap.get("id"));

            String linkUrl = (String) ocpInfoMap.get("linkUrl");
            String userName = AESUtils.decrypt(encryptName);
            String password = AESUtils.decrypt(encryptPassword);
            //  password = "@(&XMIhU99_9in{h_";
            String url = linkUrl + tenantListUrl;
            //获取ocp 租户信息
            String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
            JSONObject resJson = JSON.parseObject(resStr);
            String status = resJson.getString("status");
            if ("200".equals(status)) {
                JSONArray tenantArr = resJson.getJSONObject("data").getJSONArray("contents");

                List<ObTenant> singleClusterTenantList = JSONObject.parseArray(tenantArr.toJSONString(), ObTenant.class);
                for (int j = 0; j < singleClusterTenantList.size(); j++) {
                    ObTenant obTenant = singleClusterTenantList.get(j);
                    String unitDetailUrl = linkUrl + tenantDetailUrl;
                    unitDetailUrl = unitDetailUrl.replace("{id}",obTenant.getClusterId());
                    unitDetailUrl = unitDetailUrl.replace("{tenantId}",obTenant.getId());
                    JSONObject resData = getDetailInfo(unitDetailUrl, userName, password);
                    JSONObject unitInfo = resData.getJSONObject("unitInfo");

                    int maxCpuCoreCount = unitInfo.getInteger("maxCpuCoreCount");
                    Double maxMemorySize = unitInfo.getDouble("maxMemorySize");
                    int minCpuCoreCount = unitInfo.getInteger("minCpuCoreCount");
                    Double minMemorySize = unitInfo.getDouble("minMemorySize");
                    if(resData.containsKey("proxyJson")){
                        JSONObject proxyJson = resData.getJSONObject("proxyJson");
                        String obProxyAddress = proxyJson.getString("obProxyAddress");
                        String connectionString = proxyJson.getString("connectionString");
                        Integer obProxyPort = proxyJson.getInteger("obProxyPort");
                        obTenant.setObProxyAddress(obProxyAddress);
                        obTenant.setConnectionString(connectionString);
                        obTenant.setObProxyPort(obProxyPort);
                    }


                    obTenant.setOcpId(ocpId);
                    obTenant.setMaxMemorySize(maxMemorySize);
                    obTenant.setMinMemorySize(minMemorySize);
                    obTenant.setMaxCpuCoreCount(maxCpuCoreCount);
                    obTenant.setMinCpuCoreCount(minCpuCoreCount);

                    remoteObTenants.add(obTenant);
                }
            }

        }

        // remoteObTenants = remoteObTenants.subList(0, 1);
        //当前数据库里面的记录
        Map<String, ObTenant> localObTenantMap = originalObTenants.stream().collect(Collectors.toMap(k -> k.getId() + k.getClusterId() + k.getName() + k.getClusterName() + k.getCreateTime(), ObTenant -> ObTenant));

        //将ob调用的结果转换为map信息
        Map<String, ObTenant> remoteTenantMap = remoteObTenants.stream().collect(Collectors.toMap(k -> k.getId() + k.getClusterId() + k.getName() + k.getClusterName() + k.getCreateTime(), obTenant -> obTenant));

       /*
       遍历所有的ob信息准备比对入库
        */
        for (Map.Entry<String, ObTenant> entry : remoteTenantMap.entrySet()) {
            String remoteTenantId = entry.getKey();
            ObTenant remoteTenant = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localObTenantMap.containsKey(remoteTenantId)) {
                Date updateTime = remoteTenant.getUpdateTime();
                ObTenant localObTenant = localObTenantMap.get(remoteTenantId);
                String resId = localObTenant.getResId();
                String name = localObTenant.getName();
                Date localObTenantUpdateTime = localObTenant.getUpdateTime();
                //最后更新时间变化了才更新
              //  if (!ObjectUtils.equals(updateTime, localObTenantUpdateTime)) {
                    //获取变更记录的字段信息
                    obTenantMapper.updateByPrimaryKey(remoteTenant);
                    ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localObTenant, remoteTenant);
                    //记录所有变更日志信息
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(resId, "Ob_Tenant", name,
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
             //  }
            } else {
                String resId = IdUtils.generatedUUID();
                remoteTenant.setResId(resId);
                obTenantMapper.insert(remoteTenant);
                AsyncManager.me().execute(AsyncFactory.recordOper(resId, "Ob_Tenant", remoteTenant.getName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }


        }


    }



    public JSONObject getTenantDetail(String resId){

        List<Map> tenantInfoList = obTenantMapper.getInstanceByResId(resId);
        if(tenantInfoList.size()>0){
            Map ocpInfoMap  = tenantInfoList.get(0);
            String encryptName = (String) ocpInfoMap.get("userName");
            String encryptPassword = (String) ocpInfoMap.get("password");
            String tenantId = String.valueOf(ocpInfoMap.get("id"));
            String clusterId = String.valueOf(ocpInfoMap.get("clusterId"));
            String linkUrl = (String) ocpInfoMap.get("linkUrl");
            String userName = AESUtils.decrypt(encryptName);
            String password = AESUtils.decrypt(encryptPassword);

            String url = linkUrl + tenantDetailUrl;
            url = url.replace("{id}",clusterId);
            url = url.replace("{tenantId}",tenantId);

            //获取ocp 租户信息
            String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
            JSONObject resJson = JSON.parseObject(resStr);
            String status = resJson.getString("status");
            if ("200".equals(status)) {
                JSONObject resData = resJson.getJSONObject("data");
                JSONObject unitJson = resJson.getJSONObject("data").getJSONArray("zones").getJSONObject(0).getJSONObject("resourcePool").getJSONObject("unitConfig");
                resData.put("unitInfo",unitJson);
                return resData;
            }
        }

        return new JSONObject();
    }

    public JSONObject getDetailInfo(String url, String userName, String password){
        //获取ocp 租户信息
        String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
        JSONObject resJson = JSON.parseObject(resStr);
        String status = resJson.getString("status");
        if ("200".equals(status)) {
            JSONObject resData = resJson.getJSONObject("data");
            JSONObject unitJson = resData.getJSONArray("zones").getJSONObject(0).getJSONObject("resourcePool").getJSONObject("unitConfig");
            if(resData.containsKey("obproxyAndConnectionStrings")){
                JSONObject proxyJson = resData.getJSONArray("obproxyAndConnectionStrings").getJSONObject(0);
                resData.put("proxyJson",proxyJson);
            }


            resData.put("unitInfo",unitJson);

            return resData;
        }
        return null;
    }

    /**
     * 条件查询所有租户数据
     *
     * @param obTenant
     * @return
     */
    public List<ObTenant> getAllTenantInfo(ObTenant obTenant) {
        if (StringUtils.isNotEmpty(obTenant.getStatus())) {
            String status = obTenant.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            obTenant.setStatusArr(statusArr);
        }
        return obTenantMapper.getAllInstance(obTenant);
    }

}
