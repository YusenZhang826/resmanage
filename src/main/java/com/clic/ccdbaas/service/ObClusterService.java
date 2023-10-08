package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.ObClusterMapper;
import com.clic.ccdbaas.entity.ObCluster;
import com.clic.ccdbaas.entity.ObClusterExample;
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
public class ObClusterService {
    @Autowired
    private ObClusterMapper obClusterMapper;
    @Value("${ob.cluster.list}")
    private String clusterListUrl;


    /**
     * 保存集群信息
     *
     * @return
     */
    @XxlJob("saveObCluster")
    public void saveObCluster() {
        JSONArray ocpClusterArr = new JSONArray();
        List<Map> allOcp = obClusterMapper.selectAllOcpInfo("ocp");
        for (int i = 0; i < allOcp.size(); i++) {

            Map ocpInfoMap = allOcp.get(i);
            String encryptName = (String) ocpInfoMap.get("userName");
            String encryptPassword = (String) ocpInfoMap.get("password");
            String ocpId = String.valueOf(ocpInfoMap.get("id")) ;

            String linkUrl = (String) ocpInfoMap.get("linkUrl");
            String userName = AESUtils.decrypt(encryptName);
            String password = AESUtils.decrypt(encryptPassword);
            //  password = "@(&XMIhU99_9in{h_";
            String url = linkUrl + clusterListUrl;
            //获取ocp 集群信息
            String resStr = HttpClientUtils.HttpGetByAuth(url, userName, password);
            JSONObject resJson = JSON.parseObject(resStr);
            String status = resJson.getString("status");
            if ("200".equals(status)) {
                JSONArray clusterArr = resJson.getJSONObject("data").getJSONArray("contents");
                for (int j = 0; j < clusterArr.size(); j++) {
                    JSONObject ocpCluster = clusterArr.getJSONObject(j);
                    ocpCluster.put("ocpId",ocpId);
                    ocpClusterArr.add(ocpCluster);
                }

            }
        }
        //获取本地的集群数据
        List<ObCluster> originalObClusters = obClusterMapper.selectByExample(new ObClusterExample());
        Map<String, ObCluster> localObClusterMap = originalObClusters.stream().collect(Collectors.toMap(k -> k.getId() + k.getName() + k.getObClusterId()+k.getOcpId() , ObCluster -> ObCluster));
        //   JSONArray clusterArr = resJson.getJSONObject("data").getJSONArray("contents");
        List<ObCluster> remoteObClusterList = JSONObject.parseArray(ocpClusterArr.toJSONString(), ObCluster.class);
        //    remoteObClusterList = remoteObClusterList.subList(0,1);
        Map<String, ObCluster> remoteClusterMap = remoteObClusterList.stream().collect(Collectors.toMap(k -> k.getId() + k.getName() + k.getObClusterId()+k.getOcpId(), ObCluster -> ObCluster));
        //遍历处理ocp集群信息
        for (Map.Entry<String, ObCluster> entry : remoteClusterMap.entrySet()) {
            String remoteClusterId = entry.getKey();
            ObCluster remoteCluster = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localObClusterMap.containsKey(remoteClusterId)) {
                Date updateTime = remoteCluster.getUpdateTime();

                ObCluster localObCluster = localObClusterMap.get(remoteClusterId);
                String resId = localObCluster.getResId();
                String name = localObCluster.getName();
                Date localObClusterUpdateTime = localObCluster.getUpdateTime();
                //最后更新时间变化了才更新
                if (!ObjectUtils.equals(updateTime, localObClusterUpdateTime)) {
                    //获取变更记录的字段信息
                    obClusterMapper.updateByPrimaryKey(remoteCluster);
                    ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localObCluster, remoteCluster);
                    //记录所有变更日志信息
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(resId, "Ob_Cluster", name,
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }
            } else {
                String resId = IdUtils.generatedUUID();
                remoteCluster.setResId(resId);
                obClusterMapper.insert(remoteCluster);
                AsyncManager.me().execute(AsyncFactory.recordOper(resId, "Ob_Cluster", remoteCluster.getName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }


        }


    }

    /**
     * 条件查询所有集群数据
     *
     * @param obCluster
     * @return
     */
    public List<ObCluster> getAllClusterInfo(ObCluster obCluster) {
        if (StringUtils.isNotEmpty(obCluster.getStatus())) {
            String status = obCluster.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            obCluster.setStatusArr(statusArr);
        }
        return obClusterMapper.getAllInstance(obCluster);
    }


}
