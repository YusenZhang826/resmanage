package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.RelationDbMapper;
import com.clic.ccdbaas.entity.RelationDb;
import com.clic.ccdbaas.entity.RelationDbExample;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.model.ComparbleResult;
import com.clic.ccdbaas.utils.ComparbleUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RelationDbService {
    @Autowired
    private RelationDbMapper relationDbMapper;
    @Autowired
    private HidsService hidsService;
    private static final Logger logger = LoggerFactory.getLogger(RelationDbService.class);

    /**
     * 保存hids所有的关系型数据库
     */
    @XxlJob("saveAllRelationDb")
    public void saveAllRelationDb() {
        //获取hids所有数据库信息
        JSONArray tmpRelationDbArr = hidsService.getAllHidsHostArr();
        JSONArray hidsResDbArr = new JSONArray();
        for (int i = 0; i < tmpRelationDbArr.size(); i++) {
            JSONObject singleRelationDb = tmpRelationDbArr.getJSONObject(i);
            String name = singleRelationDb.getString("name");
            //postgres的数据特殊处理 postgres不入库跟postgrepsql有重复
            if (!"postgres".equals(name)) {
                singleRelationDb.fluentRemove("dbName");
                String confPath = singleRelationDb.getString("confPath");
                String logPath = singleRelationDb.getString("logPath");
                String dataDir = singleRelationDb.getString("dataDir");
                String binPath = singleRelationDb.getString("binPath");
                String configPath = singleRelationDb.getString("configPath");
                //任意路径包含tbase,则name改为tbase存储
                if (judgeAContainB(confPath, "tbase") || judgeAContainB(logPath, "tbase") || judgeAContainB(dataDir, "tbase") || judgeAContainB(binPath, "tbase") || judgeAContainB(configPath, "tbase")) {
                    name = "Tbase";
                    singleRelationDb.put("name", name);
                }
                hidsResDbArr.add(singleRelationDb);

            }
        }

        //  JSONArray relationDbArr = new JSONArray();

        //获取进程信息数据库
        JSONArray processDbArr = getAllProcessDb();
        hidsResDbArr.addAll(processDbArr);

        List<RelationDb> originalRelationDbs = relationDbMapper.selectByExample(new RelationDbExample());
        Map<String, RelationDb> localRelationDbMap = originalRelationDbs.stream().collect(Collectors.toMap(k -> k.getDisplayip() + k.getName() + k.getPort(), RelationDb -> RelationDb));
        //agentId跟name拼接成key
        List<RelationDb> remoteRelationDbList = JSONObject.parseArray(hidsResDbArr.toJSONString(), RelationDb.class);
        Map<String, RelationDb> remoteClusterMap = new HashMap<>();

        //   Map<String, RelationDb> remoteClusterMap = remoteRelationDbList.stream().collect(Collectors.toMap(k -> k.getAgentid()+k.getHostname()+k.getName(), RelationDb -> RelationDb));
        for (RelationDb singleRelationDb : remoteRelationDbList) {
            String agentId = singleRelationDb.getDisplayip() + singleRelationDb.getName() + singleRelationDb.getPort();
            if (remoteClusterMap.containsKey(agentId)) {
                //     logger.info("该数据已存在,agentId为:"+agentId);
            } else {
                remoteClusterMap.put(agentId, singleRelationDb);
            }
        }
        //遍历hids数据如果存在即更新,不存在则新增
        for (Map.Entry<String, RelationDb> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            RelationDb remoteRealtionDb = entry.getValue();
            String displayIp = remoteRealtionDb.getDisplayip();
            if (StringUtils.isNotEmpty(displayIp)) {
                if (displayIp.startsWith("10.18") || displayIp.startsWith("10.30") || displayIp.startsWith("10.38")) {
                    remoteRealtionDb.setDeployEnv(1);
                } else {
                    remoteRealtionDb.setDeployEnv(0);
                }
            }

            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localRelationDbMap.containsKey(remoteAgentId)) {
                RelationDb localRelationDb = localRelationDbMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localRelationDb, remoteRealtionDb);
                if (comparedResults.size() > 0) {
                    remoteRealtionDb.setResid(localRelationDb.getResid());
                    relationDbMapper.updateByPrimaryKey(remoteRealtionDb);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getAgentid(), "RelationDb", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayip() + "_" + remoteRealtionDb.getPort(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }

                //记录所有变更日志信息


            } else {
                String resId = IdUtils.generatedUUID();
                remoteRealtionDb.setResid(resId);
                relationDbMapper.insert(remoteRealtionDb);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getAgentid(), "RelationDb", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayip() + "_" + remoteRealtionDb.getPort(),
                        "resManage", 1, "All", "",
                        "", 0));
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, RelationDb> entry : localRelationDbMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getStatus();
            RelationDb remoteRealtionDb = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteRealtionDb.setStatus("1");
                remoteRealtionDb.setResid(entry.getValue().getResid());
                relationDbMapper.updateByPrimaryKey(remoteRealtionDb);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getAgentid(), "RelationDb", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayip() + "_" + remoteRealtionDb.getPort(),
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
     * 获取所有进程类数据库信息
     */
    public JSONArray getAllProcessDb() {
        //获取hids所有数据库信息
        JSONArray resJsonArr = new JSONArray();
        JSONArray hidsPortArr = hidsService.getAllHidsPort();
        JSONArray hidsProcessArr = hidsService.getAllHidsProcess();

        Map hidsPortMap = new HashMap();
        //维护hids中达梦，hana 端口信息
        for (int i = 0; i < hidsPortArr.size(); i++) {
            JSONObject singlePortJson = hidsPortArr.getJSONObject(i);
            String keyName = singlePortJson.getString("agentId") + singlePortJson.getString("processName");
            String port = singlePortJson.getString("port");
            hidsPortMap.put(keyName, port);
        }

        for (int i = 0; i < hidsProcessArr.size(); i++) {
            JSONObject processJson = hidsProcessArr.getJSONObject(i);
            String processKey = processJson.getString("agentId") + processJson.getString("name");
            //windows跟linux 版本字段不一致 需要合并一下
            String newVersion = StringUtils.isEmpty(processJson.getString("version")) ? processJson.getString("packageVersions") : processJson.getString("version");
            //如果端口信息有该进程，则维护端口信息
            if (hidsPortMap.containsKey(processKey)) {
                processJson.put("port", hidsPortMap.get(processKey));
                if (StringUtils.isNotEmpty(newVersion))
                    processJson.put("version", newVersion);
                processJson.put("user", processJson.getString("uname"));
                resJsonArr.add(processJson);
            }
        }
        return resJsonArr;


    }


    /**
     * 条件查询所有的关系型数据库
     *
     * @param relationDb
     * @return
     */
    public List getAllRelationDb(RelationDb relationDb) {
        return relationDbMapper.getAllInstance(relationDb);
    }

}
