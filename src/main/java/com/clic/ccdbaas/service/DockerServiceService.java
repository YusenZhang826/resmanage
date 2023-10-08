package com.clic.ccdbaas.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.DockerServiceMapper;
import com.clic.ccdbaas.entity.DockerService;
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
public class DockerServiceService {
    @Autowired
    private DockerServiceMapper dockerServiceMapper;
    @Autowired
    private HidsService hidsService;
    @Value("${docker.token}")
    private String dockerCloudToken;
    @Value("${docker.service.list.url}")
    private String dockerServiceListUrl;
    private static final Logger logger = LoggerFactory.getLogger(DockerServiceService.class);


    /**
     * 保存hids所有的稻客云环境
     */
  //  @XxlJob("saveAllDockerService")
    public void saveAllDockerService() {
        //获取所有的稻客云环境信息
        JSONArray remoteDockerServiceArr = getRemoteDockerServiceList();

        List<DockerService> allInstance = dockerServiceMapper.getAllInstance();
        Map<String, DockerService> localDockerServiceMap = allInstance.stream().collect(Collectors.toMap(k -> k.getCloudName()+k.getId(), DockerService -> DockerService));

        List<DockerService> remoteDockerServiceList = JSONObject.parseArray(remoteDockerServiceArr.toJSONString(), DockerService.class);

        Map<String, DockerService> remoteClusterMap = remoteDockerServiceList.stream().collect(Collectors.toMap(k -> k.getCloudName()+k.getId(), DockerService -> DockerService));
      
        //遍历稻客云数据如果存在即更新,不存在则新增
        for (Map.Entry<String, DockerService> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            DockerService remoteDockerService = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localDockerServiceMap.containsKey(remoteAgentId)) {
                DockerService localDockerService = localDockerServiceMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localDockerService, remoteDockerService);
                if (comparedResults.size() > 0) {
                    remoteDockerService.setResId(localDockerService.getResId());
                    dockerServiceMapper.updateByPrimaryKey(remoteDockerService);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerService.getResId(), "DockerService", remoteDockerService.getResId(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }
                //记录所有变更日志信息
            } else {
                String resId = IdUtils.generatedUUID();
                remoteDockerService.setResId(resId);
                dockerServiceMapper.insert(remoteDockerService);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerService.getResId(), "DockerService", remoteDockerService.getResId(),
                        "resManage", 1, "All", "",
                        "", 0));
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, DockerService> entry : localDockerServiceMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getDelFlag();
            DockerService remoteDockerService = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteDockerService.setDelFlag("1");
                remoteDockerService.setResId(entry.getValue().getResId());
                dockerServiceMapper.updateByPrimaryKey(remoteDockerService);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerService.getResId(), "DockerService", remoteDockerService.getResId(),
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
     * @param dockerService
     * @return
     */
    public List getAllDockerService(DockerService dockerService) {
        return dockerServiceMapper.getAllInstance();
    }


    /**
     * 获取稻客云环境信息列表
     * @return
     */
    public JSONArray getRemoteDockerServiceList(){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("tocken", dockerCloudToken);
        headers.put("Content-Type", "application/json");
        String dockerServiceListInfo = HttpClientUtils.httpGetRequest(dockerServiceListUrl, headers, new HashMap<>());
        JSONObject resDockerJson = JSON.parseObject(dockerServiceListInfo);
        String code = resDockerJson.getString("code");
        if("200".equals(code)){
            JSONArray data = resDockerJson.getJSONArray("data");
            return data;
        }
        return null;
    }
}
