package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.NetworkAreaMapper;
import com.clic.ccdbaas.dao.VlanMapper;
import com.clic.ccdbaas.entity.NetworkArea;
import com.clic.ccdbaas.entity.NetworkVlanRelation;
import com.clic.ccdbaas.entity.VlanPlan;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.clic.ccdbaas.utils.uuid.UUID;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service("Network_Area")
public class NetworkAreaService {
    @Autowired
    NetworkAreaMapper networkAreaMapper;
    @Autowired
    VlanMapper vlanMapper;
    @Autowired
    OcService ocService;
    @Autowired
    CmdbService cmdbService;
    @Value("${oc.cmdb.network.logic.area}")
    String networkLogicArea;

    private static final Logger logger = LoggerFactory.getLogger(NetworkAreaService.class);

    public int getNetworkAreaCount() {
        return networkAreaMapper.getNetworkAreaCount();
    }

    public List<NetworkArea> getNetworkAreaList(NetworkArea networkArea) {
        if (networkArea != null) {
            if (networkArea.getUserOrg() != null && networkArea.getUserOrg().contains(",")) {
                List<String> userOrgArray = Arrays.asList(networkArea.getUserOrg().split(","));
                networkArea.setUserOrgArray(userOrgArray);
                networkArea.setUserOrg("");
            }
            if (networkArea.getDeployEnv() != null && networkArea.getDeployEnv().contains(",")) {
                List<String> deployEnvArray = Arrays.asList(networkArea.getDeployEnv().split(","));
                networkArea.setDeployEnvArray(deployEnvArray);
                networkArea.setDeployEnv("");
            }
        }
        List<NetworkArea> allInstance = networkAreaMapper.getAllNetworkAreaInstance(networkArea);
        for (NetworkArea instance : allInstance) {
            if (instance.getUserOrg() != null) {
                if (instance.getUserOrg().contains("数据") || instance.getUserOrg().contains("研发")) {
                    instance.setGroupFlag("股份");
                }
                if (instance.getUserOrg().contains("成员") || instance.getUserOrg().contains("财产")) {
                    instance.setGroupFlag("成员");
                }
                if (instance.getUserOrg().contains("分公司")) {
                    instance.setGroupFlag("分公司");
                }
            }
        }
        return allInstance;
    }

    public NetworkArea getNetworkAreaById(String id) {
        NetworkArea networkArea = networkAreaMapper.getNetworkAreaById(id);
        return networkArea;
    }

    public NetworkArea getNetworkAreaByName(String name) {
        String resId = networkAreaMapper.getNetworkAreaByName(name);
        return getNetworkAreaById(resId);
    }

    public void deleteNetworkArea(JSONArray networkAreas) {
        for (int i = 0; i < networkAreas.size(); i++) {
            networkAreaMapper.deleteNetworkArea((String) (networkAreas.getJSONObject(i)).get("resId"));
        }
    }

    public void updateNetworkAreaById(JSONObject networkArea) {
        NetworkArea networkAreaNew = JSON.toJavaObject(networkArea, NetworkArea.class);
        networkAreaNew.setLast_Modified(new Date());
        networkAreaMapper.updateNetworkAreaById(networkAreaNew);
    }

    public void addNetworkArea(JSONObject networkArea) {
        NetworkArea networkAreaNew = JSON.toJavaObject(networkArea, NetworkArea.class);
        if (networkAreaNew.getName() == null) {
            return;
        }
        if (networkAreaNew.getLast_Modified() == null) {
            networkAreaNew.setLast_Modified(new Date());
        }
        if (networkAreaNew.getResId() == null) {
            String temp = networkAreaMapper.getNetworkAreaByName(networkAreaNew.getName());
            if (temp != null) {
                networkAreaNew.setResId(temp);
                networkAreaMapper.updateNetworkAreaById(networkAreaNew);
            } else {
                String id = (UUID.randomUUID()).toString();
                id = (id.replace("-", "")).toUpperCase();
                networkAreaNew.setResId(id);
                networkAreaMapper.addNetworkArea(networkAreaNew);
            }
        } else {
            if (networkAreaMapper.getNetworkAreaById(networkAreaNew.getResId()) != null) {
                networkAreaMapper.updateNetworkAreaById(networkAreaNew);
            } else {
                networkAreaMapper.addNetworkArea(networkAreaNew);
            }
        }

    }

    public void addResourceRelationByExcel(MultipartFile file, int sheetNum) {
        List<NetworkVlanRelation> networkVlanRelationList = new ArrayList<NetworkVlanRelation>();
        try {
            networkVlanRelationList = EasyExcel.read(file.getInputStream())
                    .head(NetworkVlanRelation.class)
                    .sheet(sheetNum)
                    .headRowNumber(4)
                    .doReadSync();
        } catch (IOException e) {
            logger.info("导入资源excel文件过程中出现了错误：");
            e.printStackTrace();
        }
        for (int i = 0; i < networkVlanRelationList.size(); i++) {
            JSONObject jb = (JSONObject) JSONObject.toJSON(networkVlanRelationList.get(i));
            addNetworkVlanRelation(jb);
        }
    }

    public void addNetworkVlanRelation(JSONObject obj) {
        try {
            NetworkVlanRelation relation = JSON.toJavaObject(obj, NetworkVlanRelation.class);
            relation.setAreaId(networkAreaMapper.getNetworkAreaByName(relation.getAreaName()));
            relation.setVlanId(vlanMapper.getVlanByName(relation.getVlanName()));
            NetworkVlanRelation temp = networkAreaMapper.getVlanRelation(relation.getVlanId());
            if (temp != null) {
                networkAreaMapper.deleteAreaVlanRelation(temp.getResId());
            }
            relation.setResId(IdUtils.generatedUUID());
            if (relation.getVlanId() != null && relation.getAreaId() != null) {
                networkAreaMapper.addNetworkVlanRelation(relation);
                HashMap<String, String> map = new HashMap<>();
                map.put("resId", relation.getVlanId());
                map.put("areaName", relation.getAreaName());
                networkAreaMapper.updateAreaNameById(map);
            }
        } catch (Exception e) {
            logger.info("添加资源关系过程中出现了错误：");
            e.printStackTrace();
        }
    }

    public void deleteNetworkVlanRelation(JSONObject relation) {
        try {
            networkAreaMapper.deleteAreaVlanRelation((String) relation.get("resId"));
        } catch (Exception e) {
            logger.info("删除资源关系过程中出现了错误：");
            e.printStackTrace();
        }
    }

    public TableDataInfo getVlanRelationByAreaId(String resId) {
        String areaName = networkAreaMapper.getNetworkAreaById(resId).getName();
        PageUtils.startPage();
        VlanPlan vlanPlan = new VlanPlan();
        vlanPlan.setAreaName(areaName);
        List<VlanPlan> relations = vlanMapper.getAllVlanInstance(vlanPlan);
        return PageUtils.getDataTable(relations);
    }

    public JSONObject getAreaRelationByVlanId(String resId) {
        NetworkVlanRelation relation = networkAreaMapper.getAreaRelationByVlanId(resId);
        if (relation != null) {
            relation.setAreaName(networkAreaMapper.getNetworkAreaNameById(relation.getAreaId()));
            relation.setVlanName(vlanMapper.getVlanNameById(resId));
        }
        return (JSONObject) JSONObject.toJSON(relation);
    }

    public int getResCount() {
        return networkAreaMapper.getNetworkAreaCount();
    }

    public JSONObject getDetailCount() {
        JSONObject count = new JSONObject();
        List<HashMap<String, Object>> lists = networkAreaMapper.getDeployEnvGroupCount();
        JSONObject deployEnv = new JSONObject();
        for (HashMap hashMap : lists) {
            deployEnv.put((String) hashMap.get("deployEnv"), hashMap.get("countNum"));
        }
        count.put("deployEnv", deployEnv);
        List<HashMap<String, Object>> userOrgList = networkAreaMapper.getUserOrgGroupCount();
        JSONObject userOrg = new JSONObject();
        for (HashMap hashMap : userOrgList) {
            userOrg.put((String) hashMap.get("userOrg"), hashMap.get("countNum"));
        }
        count.put("userOrg", userOrg);
        return count;
    }

    /**
     * oc网络逻辑区域同步（新增、更新、上报）
     */
    @XxlJob("executeNetworkAreaSynchronization")
    public String executeNetworkAreaSynchronization() {
        int insertCount = 0;
        int updateCount = 0;
        int updateInstanceCount = 0;
        int deleteCount = 0;
        JSONArray jsonArray = ocService.getAllInstanceInfo(networkLogicArea);
        List<NetworkArea> networkLogicAreaList = JSONObject.parseArray(jsonArray.toJSONString(), NetworkArea.class);
        try {
            List<NetworkArea> originalNetworkLogicAreaList = networkAreaMapper.getAllNetworkAreaInstance(new NetworkArea());
            Map<String, NetworkArea> originalNetworkLogicAreaListMap = originalNetworkLogicAreaList.stream().collect(Collectors.toMap(k -> k.getResId(), NetworkArea -> NetworkArea));

            //遍历执行新增和更新网络逻辑区域操作
            for (NetworkArea networkArea : networkLogicAreaList) {
                if (originalNetworkLogicAreaListMap.containsKey(networkArea.getResId())) {
                    networkArea.setLast_Modified(new Date());
                    Map<String, Object> compareObjectMap = CompareObjectUtils.compareObject(originalNetworkLogicAreaListMap.get(networkArea.getResId()), networkArea);
                    if (compareObjectMap.size() > 1) {
                        System.out.println(compareObjectMap);
                        networkAreaMapper.updateNetworkAreaById(networkArea);
                        cmdbService.uploadNetworkArea(networkArea);
                        updateInstanceCount++;

                        //记录所有变更日志信息
                        for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                            if (entry.getKey().equals("last_Modified")) {
                                continue;
                            } else {
                                String str = (String) entry.getValue();
                                AsyncManager.me().execute(AsyncFactory.recordOper(networkArea.getResId(), "Network_Logic_Area", networkArea.getName(),
                                        "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                                        str.substring(str.indexOf("*") + 7), 0));
                            }
                        }
                    }
                    updateCount++;
                    originalNetworkLogicAreaListMap.remove(networkArea.getResId());
                    continue;
                }
                networkAreaMapper.addNetworkArea(networkArea);
                cmdbService.uploadNetworkArea(networkArea);
                AsyncManager.me().execute(AsyncFactory.recordOper(networkArea.getResId(), "Network_Logic_Area",
                        networkArea.getName(), "resManage", 1, "All", "", "", 0));
                insertCount++;
            }

            //遍历执行删除网络逻辑区域操作
            for (String key : originalNetworkLogicAreaListMap.keySet()) {
                NetworkArea networkArea = originalNetworkLogicAreaListMap.get(key);
                networkAreaMapper.deleteNetworkArea(networkArea.getResId());
                AsyncManager.me().execute(AsyncFactory.recordOper(networkArea.getResId(), "Network_Logic_Area",
                        networkArea.getName(), "resManage", 3, "All", "", "", 0));
                deleteCount++;
            }
            logger.info("同步---------结束");
            logger.info("新增 " + insertCount + " 条网络逻辑区域资源");
            logger.info("待更新 " + updateCount + " 条网络逻辑区域资源，实际更新 " + updateInstanceCount + " 条网络逻辑区域资源");
            logger.info("删除 " + deleteCount + " 条网络逻辑区域资源");
        } catch (Exception e) {
            logger.info("同步网络逻辑区域资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }
}
