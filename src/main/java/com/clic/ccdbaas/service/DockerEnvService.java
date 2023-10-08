package com.clic.ccdbaas.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.DockerEnvMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.DockerEnv;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.model.ComparbleResult;
import com.clic.ccdbaas.utils.ComparbleUtils;
import com.clic.ccdbaas.utils.HttpClientUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/22 13:27
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class DockerEnvService {
    @Autowired
    private DockerEnvMapper dockerEnvMapper;
    @Autowired
    private HidsService hidsService;
    @Value("${docker.token}")
    private String dockerCloudToken;
    @Value("${docker.env.list.url}")
    private String dockerEnvListUrl;
    @Value("${docker.host.url}")
    private String dockerEnvHostUrl;
    private static final Logger logger = LoggerFactory.getLogger(DockerEnvService.class);


    /**
     * 保存hids所有的稻客云环境
     */
    //  @XxlJob("saveAllDockerEnv")
    public void saveAllDockerEnv() {
        //获取所有的稻客云环境信息
        JSONArray remoteDockerEnvArr = getRemoteDockerEnvList();

        List<DockerEnv> allInstance = dockerEnvMapper.getAllInstance();
        Map<String, DockerEnv> localDockerEnvMap = allInstance.stream().collect(Collectors.toMap(k -> k.getId(), DockerEnv -> DockerEnv));

        List<DockerEnv> remoteDockerEnvList = JSONObject.parseArray(remoteDockerEnvArr.toJSONString(), DockerEnv.class);

        Map<String, DockerEnv> remoteClusterMap = remoteDockerEnvList.stream().collect(Collectors.toMap(k -> k.getId(), DockerEnv -> DockerEnv));

        //遍历hids数据如果存在即更新,不存在则新增
        for (Map.Entry<String, DockerEnv> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            DockerEnv remoteDockerEnv = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localDockerEnvMap.containsKey(remoteAgentId)) {
                DockerEnv localDockerEnv = localDockerEnvMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localDockerEnv, remoteDockerEnv);
                if (comparedResults.size() > 0) {
                    remoteDockerEnv.setResId(localDockerEnv.getResId());
                    dockerEnvMapper.updateByPrimaryKey(remoteDockerEnv);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerEnv.getResId(), "DockerEnv", remoteDockerEnv.getDisplayName(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }
                //记录所有变更日志信息
            } else {
                String resId = IdUtils.generatedUUID();
                remoteDockerEnv.setResId(resId);
                dockerEnvMapper.insert(remoteDockerEnv);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerEnv.getResId(), "DockerEnv", remoteDockerEnv.getDisplayName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, DockerEnv> entry : localDockerEnvMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getDelFlag();
            DockerEnv remoteDockerEnv = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteDockerEnv.setDelFlag("1");
                remoteDockerEnv.setResId(entry.getValue().getResId());
                dockerEnvMapper.updateByPrimaryKey(remoteDockerEnv);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerEnv.getResId(), "DockerEnv", remoteDockerEnv.getDisplayName(),
                        "resManage", 2, "status", "",
                        "1", 0));
            }
        }

    }

    private Boolean judgeAContainB(String a, String b) {
        if (StringUtils.isEmpty(a)) {
            return false;
        } else {
            return a.contains(b);
        }
    }


    /**
     * 条件查询所有的稻客云环境
     *
     * @param dockerEnv
     * @return
     */
    public List getAllDockerEnv(DockerEnv dockerEnv) {
        return dockerEnvMapper.getAllInstance();
    }


    /**
     * 获取稻客云环境信息列表
     *
     * @return
     */
    public JSONArray getRemoteDockerEnvList() {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("tocken", dockerCloudToken);
        headers.put("Content-Type", "application/json");
        String dockerEnvListInfo = HttpClientUtils.httpGetRequest(dockerEnvListUrl, headers, new HashMap<>());
        JSONObject resDockerJson = JSON.parseObject(dockerEnvListInfo);
        String code = resDockerJson.getString("code");
        if ("200".equals(code)) {
            JSONArray data = resDockerJson.getJSONArray("data");
            return data;
        }
        return null;
    }


    /**
     * 获取稻客云环境信息列表
     *
     * @return
     */
    public List<CloudVmare> getRemoteEnvHostJSONArray(String envId) {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("tocken", dockerCloudToken);
        headers.put("Content-Type", "application/json");
        dockerEnvHostUrl = dockerEnvHostUrl.replace("{envId}", envId);

        String dockerEnvListInfo = HttpClientUtils.httpGetRequest(dockerEnvHostUrl, headers, new HashMap<>());
        JSONObject resDockerJson = JSON.parseObject(dockerEnvListInfo);
        String code = resDockerJson.getString("code");
        if ("200".equals(code)) {
            JSONArray data = resDockerJson.getJSONArray("data");
            return getAllHostByEnv(data);
        }
        return null;
    }


    /**
     * 查询环境下所有主机信息
     *
     * @param mainIpArr
     * @return
     */
    public List getAllHostByEnv(JSONArray mainIpArr) {
        List<String> mainList = JSON.parseArray(mainIpArr.toJSONString(), String.class);
        //  mainList.add("10.18.33.40");
        return dockerEnvMapper.getEnvHost(mainList);
    }
}
