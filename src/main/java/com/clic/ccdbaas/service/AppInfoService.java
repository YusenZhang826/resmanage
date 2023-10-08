package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.AppInfoMapper;
import com.clic.ccdbaas.entity.AppInfo;
import com.clic.ccdbaas.entity.DockerStack;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/22 13:27
 * @email chenjianhua@bmsoft.com.cn
 */
@Service
public class AppInfoService {
    @Autowired
    private AppInfoMapper appInfoMapper;
    @Autowired
    private HidsService hidsService;
    @Value("${hids.need.record.apptype}")
    private String appTypeInfo;
    private static final Logger logger = LoggerFactory.getLogger(AppInfoService.class);

    /**
     * 保存hids所有的关系型数据库
     */
    @XxlJob("saveAllAppInfo")
    public void saveAllAppInfo() {
        //获取hids所有应用信息
        JSONArray hidsAppArr = hidsService.getAllAppInfo();
        JSONArray remoteHidsAppArr = new JSONArray();
        String[] appType = appTypeInfo.split(",");
        List<String> needSaveResInfoList = Arrays.asList(appType);

        for (int i = 0; i < hidsAppArr.size(); i++) {
            JSONObject singleAppInfo = hidsAppArr.getJSONObject(i);
            String name = singleAppInfo.getString("name");
            if(needSaveResInfoList.contains(name)){
                remoteHidsAppArr.add(singleAppInfo);
            }
        }
        List<AppInfo> originalAppInfos = appInfoMapper.getAllInstance(new AppInfo());
        Map<String, AppInfo> localAppInfoMap = originalAppInfos.stream().collect(Collectors.toMap(k -> k.getAgentId() + k.getName() + k.getVersion()+k.getDisplayIp()+k.getUname()+k.getProcesses(), AppInfo -> AppInfo));
        //agentId跟name拼接成key
        List<AppInfo> remoteAppInfoList = JSONObject.parseArray(remoteHidsAppArr.toJSONString(), AppInfo.class);
        Map<String, AppInfo> remoteClusterMap = remoteAppInfoList.stream().collect(Collectors.toMap(k -> k.getAgentId() + k.getName() + k.getVersion()+k.getDisplayIp()+k.getUname()+k.getProcesses(), AppInfo -> AppInfo));


        //遍历hids数据如果存在即更新,不存在则新增
        for (Map.Entry<String, AppInfo> entry : remoteClusterMap.entrySet()) {
            String remoteAgentId = entry.getKey();
            AppInfo remoteRealtionDb = entry.getValue();
            String displayIp = remoteRealtionDb.getDisplayIp();
            if (StringUtils.isNotEmpty(displayIp)) {
                if (displayIp.startsWith("10.18") || displayIp.startsWith("10.30") || displayIp.startsWith("10.38")) {
                    remoteRealtionDb.setDeployEnv(1);
                } else {
                    remoteRealtionDb.setDeployEnv(0);
                }
            }

            //如果本地记录的id已经存在则是更新  否则就是新增
            if (localAppInfoMap.containsKey(remoteAgentId)) {
                AppInfo localAppInfo = localAppInfoMap.get(remoteAgentId);
                ArrayList<ComparbleResult> comparedResults = ComparbleUtils.compareInstance(localAppInfo, remoteRealtionDb);
                if (comparedResults.size() > 0) {
                    remoteRealtionDb.setResId(localAppInfo.getResId());
                    appInfoMapper.updateByPrimaryKey(remoteRealtionDb);
                    for (ComparbleResult singleResult : comparedResults) {
                        AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getAgentId(), "AppInfo", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayIp() + "_" + remoteRealtionDb.getVersion(),
                                "resManage", 2, singleResult.getFieldName(), singleResult.getFieldContent(),
                                singleResult.getNewFieldContent(), 0));
                    }
                }

                //记录所有变更日志信息


            } else {
                String resId = IdUtils.generatedUUID();
                remoteRealtionDb.setResId(resId);
                appInfoMapper.insert(remoteRealtionDb);
            /*    AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getAgentId(), "AppInfo", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayIp() + "_" + remoteRealtionDb.getVersion(),
                        "resManage", 1, "All", "",
                        "", 0));*/
            }
        }

        //遍历本地数据库信息,如果存在则不管,不存在说明端口已变更,则状态置为离线
        for (Map.Entry<String, AppInfo> entry : localAppInfoMap.entrySet()) {
            String localAgentId = entry.getKey();
            String status = entry.getValue().getStatus();
            AppInfo remoteRealtionDb = entry.getValue();
            if (!remoteClusterMap.containsKey(localAgentId) && !"1".equals(status)) {
                remoteRealtionDb.setStatus("1");
                remoteRealtionDb.setResId(entry.getValue().getResId());
                appInfoMapper.updateByPrimaryKey(remoteRealtionDb);
                AsyncManager.me().execute(AsyncFactory.recordOper(remoteRealtionDb.getResId(), "AppInfo", remoteRealtionDb.getName() + "_" + remoteRealtionDb.getDisplayIp() + "_" + remoteRealtionDb.getVersion(),
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
     * @param appInfo
     * @return
     */
    public List getAllAppInfo(AppInfo appInfo) {
        return appInfoMapper.getAllInstance(appInfo);
    }

}
