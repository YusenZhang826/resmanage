package com.clic.ccdbaas.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.DockerProjectMapper;
import com.clic.ccdbaas.entity.DockerProject;
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
public class DockerProjectService {
    @Autowired
    private DockerProjectMapper dockerProjectMapper;
    @Autowired
    private HidsService hidsService;
    @Value("${docker.token}")
    private String dockerCloudToken;
    @Value("${docker.project.list.url}")
    private String dockerProjectListUrl;
    private static final Logger logger = LoggerFactory.getLogger(DockerProjectService.class);


    /**
     * 保存hids所有的稻客云环境
     */
  //  @XxlJob("saveAllDockerProject")
    public void saveAllDockerProject() {
        //获取所有的稻客云环境信息
        JSONArray remoteDockerProjectArr = getRemoteDockerProjectList();

        List<DockerProject> allInstance = dockerProjectMapper.getAllInstance();
        Map<String, DockerProject> localDockerProjectMap = allInstance.stream().collect(Collectors.toMap(k -> k.getId()+k.getCloudName(), DockerProject -> DockerProject));

        List<DockerProject> remoteDockerProjectList = JSONObject.parseArray(remoteDockerProjectArr.toJSONString(), DockerProject.class);

        Map<String, DockerProject> remoteClusterMap = remoteDockerProjectList.stream().collect(Collectors.toMap(k -> k.getId()+k.getCloudName(), DockerProject -> DockerProject));
      
        //遍历稻客云数据如果存在即更新,不存在则新增
        for (Map.Entry<String, DockerProject> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            DockerProject remoteDockerProject = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localDockerProjectMap.containsKey(remoteAgentId)) {
                DockerProject localDockerProject = localDockerProjectMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localDockerProject, remoteDockerProject);
                if (comparedResults.size() > 0) {
                    remoteDockerProject.setResId(localDockerProject.getResId());
                    dockerProjectMapper.updateByPrimaryKey(remoteDockerProject);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerProject.getResId(), "DockerProject", remoteDockerProject.getDisplayName(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }
                //记录所有变更日志信息
            } else {
                String resId = IdUtils.generatedUUID();
                remoteDockerProject.setResId(resId);
                dockerProjectMapper.insert(remoteDockerProject);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerProject.getResId(), "DockerProject", remoteDockerProject.getDisplayName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, DockerProject> entry : localDockerProjectMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getDelFlag();
            DockerProject remoteDockerProject = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteDockerProject.setDelFlag("1");
                remoteDockerProject.setResId(entry.getValue().getResId());
                dockerProjectMapper.updateByPrimaryKey(remoteDockerProject);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerProject.getResId(), "DockerProject", remoteDockerProject.getDisplayName(),
                        "resManage", 2, "status", "",
                        "1", 0));
            }
        }

    }


    /**
     * 获取稻客云环境信息列表
     * @return
     */
    public JSONArray getRemoteDockerProjectList(){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("tocken", dockerCloudToken);
        headers.put("Content-Type", "application/json");
        String dockerProjectListInfo = HttpClientUtils.httpGetRequest(dockerProjectListUrl, headers, new HashMap<>());
        JSONObject resDockerJson = JSON.parseObject(dockerProjectListInfo);
        String code = resDockerJson.getString("code");
        if("200".equals(code)){
            JSONArray data = resDockerJson.getJSONArray("data");
            return data;
        }
        return null;
    }
}
