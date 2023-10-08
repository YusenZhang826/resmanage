package com.clic.ccdbaas.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.DockerStackMapper;
import com.clic.ccdbaas.entity.DockerStack;
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
public class DockerStackService {
    @Autowired
    private DockerStackMapper dockerStackMapper;
    @Autowired
    private HidsService hidsService;
    @Value("${docker.token}")
    private String dockerCloudToken;
    @Value("${docker.stack.list.url}")
    private String dockerStackListUrl;
    private static final Logger logger = LoggerFactory.getLogger(DockerStackService.class);


    /**
     * 保存hids所有的稻客云环境
     */
  //  @XxlJob("saveAllDockerStack")
    public void saveAllDockerStack() {
        //获取所有的稻客云环境信息
        JSONArray remoteDockerStackArr = getRemoteDockerStackList();

        List<DockerStack> allInstance = dockerStackMapper.getAllInstance();
        Map<String, DockerStack> localDockerStackMap = allInstance.stream().collect(Collectors.toMap(k -> k.getCloudName()+k.getProjectId()+k.getId(), DockerStack -> DockerStack));

        List<DockerStack> remoteDockerStackList = JSONObject.parseArray(remoteDockerStackArr.toJSONString(), DockerStack.class);

        Map<String, DockerStack> remoteClusterMap = remoteDockerStackList.stream().collect(Collectors.toMap(k -> k.getCloudName()+k.getProjectId()+k.getId(), DockerStack -> DockerStack));
      
        //遍历稻客云数据如果存在即更新,不存在则新增
        for (Map.Entry<String, DockerStack> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            DockerStack remoteDockerStack = entry.getValue();
            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localDockerStackMap.containsKey(remoteAgentId)) {
                DockerStack localDockerStack = localDockerStackMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localDockerStack, remoteDockerStack);
                if (comparedResults.size() > 0) {
                    remoteDockerStack.setResId(localDockerStack.getResId());
                    dockerStackMapper.updateByPrimaryKey(remoteDockerStack);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerStack.getResId(), "DockerStack", remoteDockerStack.getName(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }
                //记录所有变更日志信息
            } else {
                String resId = IdUtils.generatedUUID();
                remoteDockerStack.setResId(resId);
                dockerStackMapper.insert(remoteDockerStack);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerStack.getResId(), "DockerStack", remoteDockerStack.getName(),
                        "resManage", 1, "All", "",
                        "", 0));
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, DockerStack> entry : localDockerStackMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getDelFlag();
            DockerStack remoteDockerStack = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteDockerStack.setDelFlag("1");
                remoteDockerStack.setResId(entry.getValue().getResId());
                dockerStackMapper.updateByPrimaryKey(remoteDockerStack);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteDockerStack.getResId(), "DockerStack", remoteDockerStack.getName(),
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
     * @param dockerStack
     * @return
     */
    public List getAllDockerStack(DockerStack dockerStack) {
        return dockerStackMapper.getAllInstance();
    }


    /**
     * 获取稻客云环境信息列表
     * @return
     */
    public JSONArray getRemoteDockerStackList(){
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("tocken", dockerCloudToken);
        headers.put("Content-Type", "application/json");
        String dockerStackListInfo = HttpClientUtils.httpGetRequest(dockerStackListUrl, headers, new HashMap<>());
        JSONObject resDockerJson = JSON.parseObject(dockerStackListInfo);
        String code = resDockerJson.getString("code");
        if("200".equals(code)){
            JSONArray data = resDockerJson.getJSONArray("data");
            return data;
        }
        return null;
    }
}
