package com.clic.ccdbaas.service;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.PhysicalHostMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.entity.StorageDevice;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.IpSegmentUtils;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("CLOUD_HOST")
public class PhysicalHostService {


    private static final Logger logger = LoggerFactory.getLogger(PhysicalHostService.class);

    @Autowired
    private OcService ocService;
    @Autowired
    private CloudVmareService cloudVmareService;

    @Value("${oc.cmdb.host}")
    private String physicalList;
    @Value("${oc.cmdb.physicalhost}")
    private String host;

    @Autowired
    PhysicalHostMapper physicalHostMapper;

    //查找JSONObject的resid
    public String findResid(JSONObject jsob) {
        if (!jsob.containsKey("id")) {
            return "";
        } else {
            return jsob.getString("id");
        }
    }

    public JSONArray getJson(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }

    public JSONArray getX86ServerArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }


    public JSONArray getPhysicalHostArr(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, physicalList);
    }

    public JSONArray getJson() {
        return ocService.getAllInstanceInfo(physicalList);
    }

    /**
     * 使用参数修改单个资源实例
     *
     * @param postJson
     * @return
     */
    public boolean updateData(String postJson) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(JSON.parse(postJson));
        //判断json串中有没有res字段
        String resid = "";
        if (findResid(jsonObject) == "") {
            return false;
        } else {
            resid = findResid(jsonObject);
        }
        jsonObject.remove("id");
        String tmp = jsonObject.toString();
        String resId = "{" + "\"id\"" + ":" + "\"" + resid + "\"" + ",";
        tmp = tmp.substring(1);
        tmp = "[" + resId + tmp + "]";
        System.out.println("tmp" + tmp);
        try {
            ocService.updateOCInstances(physicalList, tmp);
            logger.info("更新成功");
        } catch (Exception e) {
            logger.info("更新失败");
        }
        return true;
    }

    /**
     * 使用参数批量修改资源实例
     *
     * @param physiclist
     * @return
     */
    public boolean updatePhysicalList(JSONArray physiclist) {
        try {
            for (int i = 0; i < physiclist.size(); i++) {
                JSONObject obj = physiclist.getJSONObject(i);
                updateData(obj.toString());
            }
            logger.info("物理服务器批量更新成功");
        } catch (Exception e) {
            logger.info("物理服务器批量更新失败");
            return false;
        }
        return true;
    }


    /**
     * 批量删除
     *
     * @param paramJson
     */
    public void delelteDevice(JSONArray paramJson) {
        logger.info("删除设备请求报文为:" + paramJson.toString());
        for (int i = 0; i < paramJson.size(); i++) {
            ocService.deleteOcClassInstances(physicalList, paramJson.get(i).toString());
            logger.info("删除资源成功,设备id为" + paramJson.get(i).toString());
        }
    }

    /**
     * 批量删除
     *
     * @param instanceId
     */
    public JSONObject getMessage(String instanceId) {
        logger.info("查询设备详情id为" + instanceId);
        JSONObject allInstanceInfo = ocService.getAllInstanceInfo(physicalList, instanceId);
        logger.info("设备详情：" + allInstanceInfo);
        return allInstanceInfo;
    }

    public JSONObject insertDevice(JSONObject requestParams) {
        List list = new ArrayList();
        list.add(requestParams.toString());
        String result = ocService.addInstances(physicalList, list.toString());
        return JSON.parseObject(result);
    }

    public void exportDevice(JSONArray list, HttpServletResponse response) {
        JSONArray request = null;
        try {
            if (!StringUtils.isEmpty(list)) {
                request = list;
            } else {
                request = getJson();
            }
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("utf-8");
            ocService.setExcelRespProp(response, "库存设备列表");
            List<StorageDevice> memberList = request.toJavaList(StorageDevice.class);
            logger.info("Excel导出库存设备信息" + memberList.toString());
            EasyExcel.write(response.getOutputStream())
                    .head(StorageDevice.class)
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet("库存设备列表")
                    .doWrite(memberList);
        } catch (Exception e) {
            logger.info("库存设备列表失败");
        }
    }


    public void updateAdministrator(Map<String, String> map) {
        try {
            physicalHostMapper.updateAdministrator(map);
        } catch (Exception e) {
            logger.info("主岗更新失败");
        }

    }

    public void updatebackupAdministrator(Map<String, String> map) {
        physicalHostMapper.updatebackupAdministrator(map);
    }

    public void updateStakeholder(Map<String, String> map) {
        physicalHostMapper.updateStakeholder(map);
    }

    /**
     * 获取宿主机数据列表
     */
    public List<PhysicalHost> getPhysicalHostList(PhysicalHost physicalHost) {
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalHost.getStatus())) {
            String status = physicalHost.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            physicalHost.setStatusArr(statusArr);
        }

        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalHost.getDeployEnv())) {
            String deployEnv = physicalHost.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            physicalHost.setDeployEnvArr(deployEnvArr);
        }
        List<PhysicalHost> allInstance = physicalHostMapper.getAllInstance(physicalHost);
        return allInstance;
    }

    public List<HashMap> countHostInstanceByStatus() {
        return physicalHostMapper.countHostInstanceByStatus();
    }

    public int getPhysicalHostCount() {
        return physicalHostMapper.getPhysicalHostCount();
    }

    public List<PhysicalHost> getAllPhysicalHost(PhysicalHost physicalHost) {
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalHost.getStatus())) {
            String status = physicalHost.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            physicalHost.setStatusArr(statusArr);
        }

        List<PhysicalHost> allInstance = physicalHostMapper.getAllInstance(physicalHost);
        for (PhysicalHost singleVm : allInstance) {
            //进行时间格式处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String lastModTime = singleVm.getLast_Modified();
            if (!StringUtils.isEmpty(lastModTime)) {
                singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(lastModTime))));

            }
        }
        return allInstance;
    }

    public List<CloudVmare> getCloudVmByResId(String resId) {
        List<CloudVmare> allInstance = physicalHostMapper.getCloudVmByResId(resId);
        return allInstance;
    }

    public String insertList(JSONArray requestParams) {
        try {
            for (Object param : requestParams) {
                JSONObject json = JSONObject.parseObject(param.toString());
                PhysicalHost physicalHost = json.toJavaObject(PhysicalHost.class);
                physicalHostMapper.insert(physicalHost);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    public List<HashMap> countInstanceByStatus(PhysicalHost physicalMachine) {
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalMachine.getStatus())) {
            String status = physicalMachine.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            physicalMachine.setStatusArr(statusArr);
        }
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalMachine.getDeployEnv())) {
            String deployEnv = physicalMachine.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            physicalMachine.setDeployEnvArr(deployEnvArr);
        }
        return physicalHostMapper.countInstanceByStatus(physicalMachine);
    }

    public JSONObject getInstanceDetail(String resId) {
        PhysicalHost res = physicalHostMapper.getPhysicalHostByResId(resId);
        return JSONObject.parseObject(JSON.toJSONString(res));
    }

    public TableDataInfo getAllInstance() {
        List<PhysicalHost> res = physicalHostMapper.getAllInstance(null);
        return PageUtils.getDataTable(res);
    }

    public int getResCount() {
        return getPhysicalHostCount();
    }

    /**
     * oc宿主机资源同步（新增、更新、删除）
     */
    @XxlJob("executePhysicalHostSynchronization")
    public String executePhysicalHostSynchronization() {
        int insertCount = 0;
        int updateCount = 0;
        int updateInstanceCount = 0;
        int deleteCount = 0;
        JSONArray jsonArray = ocService.getAllInstanceInfo(host);
        List<PhysicalHost> physicalHostList = JSONObject.parseArray(jsonArray.toJSONString(), PhysicalHost.class);

        try {
            List<PhysicalHost> ocPhysicalHostList = getOcPhysicalHostInstance(physicalHostList);
            List<PhysicalHost> originalPhysicalHostList = physicalHostMapper.getAllInstanceExceptCloudDesktop();
            Map<String, PhysicalHost> originalPhysicalHostListMap = originalPhysicalHostList.stream().collect(Collectors.toMap(k -> k.getResId(), PhysicalHost -> PhysicalHost));

            //遍历执行新增和更新宿主机操作
            for (PhysicalHost physicalHost : ocPhysicalHostList) {
                if (originalPhysicalHostListMap.containsKey(physicalHost.getResId())) {
                    PhysicalHost newPhysicalHost = physicalHostSetNull(physicalHost);
                    Map<String, Object> compareObjectMap = CompareObjectUtils.compareObject(originalPhysicalHostListMap.get(physicalHost.getResId()), newPhysicalHost);
                    if (compareObjectMap.size() > 2) {
                        System.out.println(compareObjectMap);
                        physicalHostMapper.updatePhysicalHostInstance(newPhysicalHost);
                        updateInstanceCount++;

                        //记录所有变更日志信息
                        for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                            if (entry.getKey().equals("id") || entry.getKey().equals("last_Modified")) {
                                continue;
                            } else {
                                String str = (String) entry.getValue();
                                AsyncManager.me().execute(AsyncFactory.recordOper(newPhysicalHost.getResId(), "SYS_PhysicalHost", newPhysicalHost.getName(),
                                        "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                                        str.substring(str.indexOf("*") + 7), 0));
                            }
                        }
                    }
                    updateCount++;
                    originalPhysicalHostListMap.remove(physicalHost.getResId());
                    continue;
                }
                physicalHostMapper.insertPhysicalHostInstance(physicalHost);
                AsyncManager.me().execute(AsyncFactory.recordOper(physicalHost.getResId(), "SYS_PhysicalHost",
                        physicalHost.getName(), "resManage", 1, "All", "", "", 0));
                insertCount++;
            }

            //遍历执行删除宿主机操作
            if (originalPhysicalHostListMap.size() < 200) {
                for (String key : originalPhysicalHostListMap.keySet()) {
                    PhysicalHost physicalHost = originalPhysicalHostListMap.get(key);
                    physicalHostMapper.deletePhysicalHostInstance(physicalHost);
                    AsyncManager.me().execute(AsyncFactory.recordOper(physicalHost.getResId(), "SYS_PhysicalHost",
                            physicalHost.getName(), "resManage", 3, "All", "", "", 0));
                    deleteCount++;
                }
            }

            logger.info("宿主机同步---------结束");
            logger.info("新增 " + insertCount + " 条宿主机资源");
            logger.info("待更新 " + updateCount + " 条宿主机资源，实际更新 " + updateInstanceCount + " 条宿主机资源");
            logger.info("删除 " + deleteCount + " 条宿主机资源");
        } catch (Exception e) {
            logger.info("同步宿主机资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 处理oc宿主机数据
     */
    public List<PhysicalHost> getOcPhysicalHostInstance(List<PhysicalHost> physicalHostList) {
        Map<String, Map<String, String>> cloudAzoneMap = cloudVmareService.getAllCloudAzoneMap();
        for (PhysicalHost physicalHost : physicalHostList) {

            //status同步
            if (physicalHost.getStatus().equalsIgnoreCase("normal")) {
                physicalHost.setStatus("active");
            }
            if (physicalHost.getStatus().equalsIgnoreCase("fault")) {
                physicalHost.setStatus("error");
            }
            if (physicalHost.getStatus().equalsIgnoreCase("poweroff")) {
                physicalHost.setStatus("offline");
            }

            //获取mainIp ip
            String ip = physicalHost.getIpAddress();
            String mainIp = "";
            if (ip != null) {
                String[] strs = ip.split(";");
                for (String str : strs) {
                    String[] temp = str.split("\\.");
                    if (temp[0].equals("10") || temp[0].equals("9")) {
                        if (mainIp != "") {
                            mainIp += (";" + str);
                        } else {
                            mainIp += str;
                        }
                    }
                }
            }
            physicalHost.setMainIp(mainIp);

            //获取deployEnv(0-生产 1-测试)
            if (StringUtils.isEmpty(physicalHost.getDeployEnv())) {
                if (IpSegmentUtils.isTestMachineIp(physicalHost.getMainIp())) {
                    physicalHost.setDeployEnv("1");
                } else {
                    physicalHost.setDeployEnv("0");
                }
            }
            physicalHost.setLast_Modified(String.valueOf(System.currentTimeMillis()));
            physicalHost.setBelongCompany("数据中心");

            //获取azoneName
            if (StringUtils.isEmpty(physicalHost.getAzoneName()) && !StringUtils.isEmpty(physicalHost.getAzoneId())) {
                physicalHost.setAzoneName(cloudAzoneMap.get(physicalHost.getAzoneId()).get("name"));
            }
        }
        return physicalHostList;
    }

    /**
     * 宿主机更新处理，置空不更新元素
     *
     * @return
     */
    public PhysicalHost physicalHostSetNull(PhysicalHost physicalHost) {
        physicalHost.setNetworkArea(null);
        physicalHost.setDeployEnv(null);
        physicalHost.setUsageDes(null);
        physicalHost.setDeployOs(null);
        physicalHost.setResourceCode(null);
        physicalHost.setBelongProduct(null);
        physicalHost.setSysAdministrator(null);
        physicalHost.setSysAdminBackup(null);
        physicalHost.setStakeholder(null);
        physicalHost.setOsAdminUser(null);
        physicalHost.setOsNormalUser(null);
        physicalHost.setSysAdminTeam(null);
        physicalHost.setProductToken(null);
        physicalHost.setLocationCode(null);
        physicalHost.setBmcIp(null);
        physicalHost.setSn(null);
        physicalHost.setAppModToken(null);
        physicalHost.setAppModName(null);
        physicalHost.setRelatedIp(null);
        physicalHost.setVirtualIp(null);
        physicalHost.setTotalCpu(null);
        physicalHost.setRegionId(null);
        return physicalHost;
    }

    public void addExcel(HashMap<String, PhysicalHost> hosts, PhysicalHost physicalHost) {
        if (hosts.get(physicalHost.getMainIp()) != null) {
            physicalHost.setNativeId(hosts.get(physicalHost.getMainIp()).getNativeId());
            physicalHost.setName(hosts.get(physicalHost.getMainIp()).getName());
            physicalHost.setDeployEnv(hosts.get(physicalHost.getMainIp()).getDeployEnv());
            physicalHost.setNetworkArea(hosts.get(physicalHost.getMainIp()).getNetworkArea());
            physicalHost.setSysAdminTeam(hosts.get(physicalHost.getMainIp()).getSysAdminTeam());
            physicalHost.setTotalVcpuCores(hosts.get(physicalHost.getMainIp()).getTotalVcpuCores());
            physicalHost.setTotalCpu(hosts.get(physicalHost.getMainIp()).getTotalCpu());
            physicalHost.setTotalMemory(hosts.get(physicalHost.getMainIp()).getTotalMemory());
            physicalHost.setDeployOs(hosts.get(physicalHost.getMainIp()).getDeployOs());
            physicalHost.setResourceCode(hosts.get(physicalHost.getMainIp()).getResourceCode());
            physicalHost.setHidsStatus(hosts.get(physicalHost.getMainIp()).getHidsStatus());
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                physicalHost.setLast_Modified(String.valueOf(sdf.parse(hosts.get(physicalHost.getMainIp()).getLast_Modified()).getTime()));
            } catch (Exception e) {
                physicalHost.setLast_Modified(String.valueOf(System.currentTimeMillis()));
            }
        }
        physicalHost.setClass_Name("SYS_PhysicalHost");
        physicalHost.setStatus("active");
        physicalHost.setDeployEnv("0");
        PhysicalHost temp = physicalHostMapper.getPhysicalHostByIpAndLocation(physicalHost);
        if (temp == null) {
            physicalHost.setResId(IdUtils.generatedUUID());
            if (physicalHost.getName() == null) {
                physicalHost.setName(physicalHost.getMainIp());
            }
            physicalHostMapper.insertPhysicalHostInstance(physicalHost);
        } else {
            physicalHost.setResId(temp.getResId());
            physicalHostMapper.updatePhysicalHostInstance(physicalHost);
        }
    }

    public void addPhysicalHostByExcel(PhysicalHost physicalHost) {
        PhysicalHost temp = new PhysicalHost();
        if (physicalHost.getLast_Modified() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                physicalHost.setLast_Modified(String.valueOf(sdf.parse(physicalHost.getLast_Modified())));
            } catch (Exception e) {
                physicalHost.setLast_Modified(String.valueOf(System.currentTimeMillis()));
            }
        } else {
            physicalHost.setLast_Modified(String.valueOf(System.currentTimeMillis()));
        }
        physicalHost.setClass_Name("SYS_PhysicalHost");
        if (physicalHost.getDeployEnv() != null) {
            if (physicalHost.getDeployEnv().equals("生产")) {
                physicalHost.setDeployEnv("0");
            } else {
                physicalHost.setDeployEnv("1");
            }
        }

        if (StringUtils.isEmpty(physicalHost.getLocationCode()) && !StringUtils.isEmpty(physicalHost.getMainIp())) {
            temp = physicalHostMapper.getPhysicalHostByMainIp(physicalHost);
        } else {
            temp = physicalHostMapper.getPhysicalHostByIpAndLocation(physicalHost);
        }

        if (temp == null) {
            physicalHost.setResId(IdUtils.generatedUUID());
            if (physicalHost.getName() == null) {
                physicalHost.setName(physicalHost.getMainIp());
            }
            physicalHostMapper.insertPhysicalHostInstance(physicalHost);
        } else {
            physicalHost.setResId(temp.getResId());
            physicalHostMapper.updatePhysicalHostInstance(physicalHost);
        }
    }
}
