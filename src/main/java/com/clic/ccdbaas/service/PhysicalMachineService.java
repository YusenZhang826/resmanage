package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.CloudBms;
import com.clic.ccdbaas.entity.PhysicalMachine;
import com.clic.ccdbaas.entity.PhysicalServer;
import com.clic.ccdbaas.entity.kafka.PhysicalHostKafka;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.CompareObjectUtils;
import com.clic.ccdbaas.utils.PageUtils;
import com.clic.ccdbaas.utils.StringUtils;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("SYS_X86Server")
public class PhysicalMachineService {
    private static final Logger logger = LoggerFactory.getLogger(PhysicalMachineService.class);
    @Autowired
    private PhysicalHostMapper physicalHostMapper;
    @Autowired
    private CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    private PhysicalMachineMapper physicalMachineMapper;
    @Autowired
    private CloudBmsMapper cloudBmsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OcService ocService;
    @Autowired
    private CloudVmareService cloudVmareService;
    @Value("${oc.cmdb.vm}")
    private String vm;
    @Value("${oc.cmdb.server}")
    private String physicalserver;
    @Value("${oc.cmdb.host}")
    private String physicalList;
    @Value("${oc.cmdb.bms}")
    private String bms;

    /**
     * 获取物理服务器数据列表
     *
     * @param physicalMachine
     * @return
     */
    public List<PhysicalMachine> getPhysicalList(PhysicalMachine physicalMachine) {
        if (StringUtils.isNotEmpty(physicalMachine.getStatus())) {
            String status = physicalMachine.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            physicalMachine.setStatusArr(statusArr);
        }
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalMachine.getDeployEnv())) {
            String deployEnv = physicalMachine.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            physicalMachine.setDeployEnvArr(deployEnvArr);
        }

        List<PhysicalMachine> allInstance = physicalMachineMapper.getAllInstance(physicalMachine);
        return allInstance;
    }

    /**
     * 获取不同状态值下的实例数
     *
     * @return
     */
    public List<HashMap> countInstanceByStatus(PhysicalMachine physicalMachine) {
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
        return physicalMachineMapper.countInstanceByStatus(physicalMachine);
    }

    public int getPhysicalCount() {
        return physicalMachineMapper.getPhysicalCount();
    }

    public List<PhysicalMachine> getAllPhysicalHost(PhysicalMachine physicalMachine) {
        if (com.clic.ccdbaas.utils.StringUtils.isNotEmpty(physicalMachine.getStatus())) {
            String status = physicalMachine.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            physicalMachine.setStatusArr(statusArr);
        }

        List<PhysicalMachine> allInstance = physicalMachineMapper.getAllInstance(physicalMachine);
        for (PhysicalMachine singleVm : allInstance) {
            //进行时间格式处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(singleVm.getLast_Modified()))));
        }
        return allInstance;
    }

    /**
     * 使用参数修改单个资源实例
     *
     * @return
     */
    public String findResid(JSONObject jsob) {
        if (!jsob.containsKey("id")) {
            return "";
        } else {
            return jsob.getString("id");
        }
    }

    public boolean updateData(String postJson) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(JSON.parse(postJson));
        //判断json串中有没有res字段
        String resid = "";
        if (findResid(jsonObject) == "") {
            return false;
        } else {
            resid = findResid(jsonObject);
        }
        Map json = (Map) JSONObject.parse(postJson);
        jsonObject.remove("id");
        String tmp = jsonObject.toString();
        String resId = "{" + "\"id\"" + ":" + "\"" + resid + "\"" + ",";
        tmp = tmp.substring(1);
        tmp = "[" + resId + tmp + "]";
        try {
            ocService.updateOCInstances(physicalList, tmp);
            ocService.updateOCInstances(vm, tmp);
            ocService.updateOCInstances(physicalserver, tmp);
            physicalMachineMapper.updateFindResource(json);
            physicalHostMapper.updateFindResource(json);
            cloudVmNovaMapper.updateFindResource(json);
            logger.info("更新成功");
        } catch (Exception e) {
            logger.info("更新失败");
        }
        return true;
    }

    /**
     * 解析Cmdb操作系统实例中的所有物理机(生产)
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void uploadCmdbPhysicalServer(PhysicalServer physicalServer) {
        List<PhysicalServer> temp = new ArrayList<>();
        List<PhysicalHostKafka> physicalHostTemp = new ArrayList<>();
        if (!StringUtils.isEmpty(physicalServer.getMainIp())) {
            temp = physicalMachineMapper.getPhysicalServerByMainIp(physicalServer.getMainIp());
            physicalHostTemp = physicalHostMapper.getSinglePhysicalHostInstance(physicalServer.getMainIp());
        } else {
            temp = physicalMachineMapper.getPhysicalServerByLocationCode(physicalServer.getLocationCode());
        }

        //基本信息配置和转义
        physicalServer.setResId(IdUtils.generatedUUID());
        if (physicalServer.getNativeId() == null) {
            physicalServer.setNativeId(String.valueOf(UUID.randomUUID()));
        }

        if (physicalServer.getSystemIndex() != null) {
            if (physicalServer.getSystemIndex().equals("4A") || physicalServer.getSystemIndex().equals("PKI")) {
                physicalServer.setClass_Name("Safe_Equipment");
            } else {
                physicalServer.setClass_Name("SYS_X86Server");
            }
        }

        if (physicalServer.getIsPhysicalServer().equals("安全设备")) {
            physicalServer.setClass_Name("Safe_Equipment");
        } else if (physicalServer.getIsPhysicalServer().equals("物理机")) {
            physicalServer.setClass_Name("SYS_X86Server");
        } else {
            throw new RuntimeException("设备类型不符合要求：" + physicalServer.getIsPhysicalServer());
        }

        if (physicalServer.getMemoryCapacity() != null) {
            physicalServer.setMemoryCapacity(String.valueOf(Integer.valueOf(physicalServer.getMemoryCapacity()) * 1024));
        }
        physicalServer.setStatus("active");
        if (!StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setIpAddress(physicalServer.getMainIp());
        }

        if (physicalServer.getDeployEnv() != null) {
            if (physicalServer.getDeployEnv().equals("生产")) {
                physicalServer.setDeployEnv("0");
            } else if (physicalServer.getDeployEnv().equals("测试")) {
                physicalServer.setDeployEnv("1");
                String deployAdminA = physicalServer.getDeployAdminA();
                String[] sysAdminArr = physicalServer.getSysAdministrator().split(",");
                for (String sysAdmin : sysAdminArr) {
                    if (!deployAdminA.contains(sysAdmin)) {
                        deployAdminA = deployAdminA + "," + sysAdmin;
                    }
                }
                physicalServer.setDeployAdminA(deployAdminA);
                physicalServer.setSysAdministrator("王新剑-18200427");
                physicalServer.setSysAdminBackup("满开亮-18200493");
            } else {
                physicalServer.setDeployEnv("2");
            }
        }

        if (physicalServer.getLast_Modified() != null) {
            long timeMillis;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(physicalServer.getLast_Modified());
                timeMillis = date.getTime();
            } catch (ParseException e) {
                logger.info("时间戳转换失败");
                throw new RuntimeException(e);
            }
            physicalServer.setLast_Modified(String.valueOf(timeMillis));
        } else {
            physicalServer.setLast_Modified(String.valueOf(System.currentTimeMillis()));
        }

        if (physicalServer.getDeployOs() != null) {
            if (physicalServer.getDeployOs().contains("ARM")) {
                physicalServer.setCpuArch("arm");
            } else if (physicalServer.getDeployOs().contains("X86")) {
                physicalServer.setCpuArch("x86");
            } else if (physicalServer.getDeployOs().contains("小型机")) {
                physicalServer.setCpuArch("powerPC");
            } else if (physicalServer.getDeployOs().contains("龙芯")) {
                physicalServer.setCpuArch("mips");
            } else {
                physicalServer.setCpuArch("other");
            }
        }

        if (StringUtils.isEmpty(physicalServer.getName()) && !StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setName(physicalServer.getMainIp());
        }

        //获取部署运营人员
        if (!StringUtils.isEmpty(physicalServer.getDeployAdminA())) {
            if (!physicalServer.getDeployAdminA().contains("-")) {
                String userName = physicalServer.getDeployAdminA();
                List<String> empNoList = userMapper.getSysUserEmpNoByUserName(userName);
                if (empNoList.size() == 1) {
                    String empNo = empNoList.get(0);
                    physicalServer.setDeployAdminA(userName + "-" + empNo);
                }
            }
        }

        if (temp.size() == 0 && physicalHostTemp.size() == 0) {
            physicalMachineMapper.insertSinglePhysicalServerInstance(physicalServer);
        } else if (temp.size() == 1 && physicalHostTemp.size() == 0) {
            physicalServer.setNativeId(null);
            physicalServer.setIsPhysicalServer(null);
            physicalServer.setSystemIndex(null);
            PhysicalServer initialValue = temp.get(0);
            Map<String, Object> compareObjectMap = null;
            try {
                compareObjectMap = CompareObjectUtils.compareObject(initialValue, physicalServer);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (compareObjectMap.size() > 2) {
                int update = 0;
                if (physicalServer.getMainIp() == null && physicalServer.getLocationCode() != null) {
                    update = physicalMachineMapper.updatePhysicalServerInstanceByLocationCode(physicalServer);
                } else {
                    update = physicalMachineMapper.updateSinglePhysicalServerInstance(physicalServer);
                }
                System.out.println(compareObjectMap);
                if (update != 0) {
                    for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                        if (entry.getKey().equals("resId") || entry.getKey().equals("last_Modified")) {
                            continue;
                        } else {
                            String str = (String) entry.getValue();
                            AsyncManager.me().execute(AsyncFactory.recordOper(initialValue.getResId(), initialValue.getClass_Name(), initialValue.getName(),
                                    "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                                    str.substring(str.indexOf("*") + 7), 0));
                        }
                    }
                }
            }
        }
    }

    public JSONObject getInstanceDetail(String resId) {
        PhysicalMachine res = physicalMachineMapper.getPhysicalServerByResId(resId);
        return JSONObject.parseObject(JSON.toJSONString(res));
    }

    public TableDataInfo getAllInstance() {
        List<PhysicalMachine> res = physicalMachineMapper.getAllInstance(null);
        return PageUtils.getDataTable(res);
    }

    public int getResCount() {
        return physicalMachineMapper.getPhysicalCount();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTestPhysicalServer(PhysicalServer physicalServer) {
        Map<String, Object> compareObjectMap = null;
        List<PhysicalServer> temp = physicalMachineMapper.getPhysicalServerByLocationCode(physicalServer.getLocationCode());
        physicalServer.setNativeId(null);
        physicalServer.setIsPhysicalServer(null);
        PhysicalServer initialValue = temp.get(0);

        //基本信息配置和转义
        if (physicalServer.getMemoryCapacity() != null) {
            physicalServer.setMemoryCapacity(String.valueOf(Integer.valueOf(physicalServer.getMemoryCapacity()) * 1024));
        }
        if (!StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setIpAddress(physicalServer.getMainIp());
        }

        if (physicalServer.getLast_Modified() != null) {
            long timeMillis;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(physicalServer.getLast_Modified());
                timeMillis = date.getTime();
            } catch (ParseException e) {
                logger.info("时间戳转换失败");
                throw new RuntimeException(e);
            }
            physicalServer.setLast_Modified(String.valueOf(timeMillis));
        }

        if (physicalServer.getDeployOs() != null) {
            if (physicalServer.getDeployOs().contains("ARM")) {
                physicalServer.setCpuArch("arm");
            } else if (physicalServer.getDeployOs().contains("X86")) {
                physicalServer.setCpuArch("x86");
            } else if (physicalServer.getDeployOs().contains("小型机")) {
                physicalServer.setCpuArch("powerPC");
            } else if (physicalServer.getDeployOs().contains("龙芯")) {
                physicalServer.setCpuArch("mips");
            } else {
                physicalServer.setCpuArch("other");
            }
        }

        if (StringUtils.isEmpty(physicalServer.getName()) && !StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setName(physicalServer.getMainIp());
        }

        //获取部署运营人员
        if (!StringUtils.isEmpty(physicalServer.getDeployAdminA())) {
            if (!physicalServer.getDeployAdminA().contains("-")) {
                String userName = physicalServer.getDeployAdminA();
                List<String> empNoList = userMapper.getSysUserEmpNoByUserName(userName);
                if (empNoList.size() == 1) {
                    String empNo = empNoList.get(0);
                    physicalServer.setDeployAdminA(userName + "-" + empNo);
                }
            }
        }

        try {
            compareObjectMap = CompareObjectUtils.compareObject(initialValue, physicalServer);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (compareObjectMap.size() > 2) {
            physicalMachineMapper.updatePhysicalServerInstanceByLocationCode(physicalServer);
            System.out.println(compareObjectMap);
            for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                if (entry.getKey().equals("resId") || entry.getKey().equals("last_Modified")) {
                    continue;
                } else {
                    String str = (String) entry.getValue();
                    AsyncManager.me().execute(AsyncFactory.recordOper(initialValue.getResId(), initialValue.getClass_Name(), initialValue.getName(),
                            "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                            str.substring(str.indexOf("*") + 7), 0));
                }
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void addTestPhysicalServer(PhysicalServer physicalServer) {
        //基本信息配置和转义
        physicalServer.setResId(IdUtils.generatedUUID());
        if (physicalServer.getNativeId() == null) {
            physicalServer.setNativeId(String.valueOf(UUID.randomUUID()));
        }
        physicalServer.setClass_Name("SYS_X86Server");
        if (physicalServer.getMemoryCapacity() != null) {
            physicalServer.setMemoryCapacity(String.valueOf(Integer.valueOf(physicalServer.getMemoryCapacity()) * 1024));
        }
        physicalServer.setStatus("active");
        if (!StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setIpAddress(physicalServer.getMainIp());
        }

        if (physicalServer.getDeployEnv() != null) {
            if (physicalServer.getDeployEnv().equals("生产")) {
                physicalServer.setDeployEnv("0");
            } else if (physicalServer.getDeployEnv().equals("测试")) {
                physicalServer.setDeployEnv("1");
            } else {
                physicalServer.setDeployEnv("2");
            }
        }

        if (physicalServer.getLast_Modified() != null) {
            long timeMillis;
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = simpleDateFormat.parse(physicalServer.getLast_Modified());
                timeMillis = date.getTime();
            } catch (ParseException e) {
                logger.info("时间戳转换失败");
                throw new RuntimeException(e);
            }
            physicalServer.setLast_Modified(String.valueOf(timeMillis));
        }

        if (physicalServer.getDeployOs() != null) {
            if (physicalServer.getDeployOs().contains("ARM")) {
                physicalServer.setCpuArch("arm");
            } else if (physicalServer.getDeployOs().contains("X86")) {
                physicalServer.setCpuArch("x86");
            } else if (physicalServer.getDeployOs().contains("小型机")) {
                physicalServer.setCpuArch("powerPC");
            } else if (physicalServer.getDeployOs().contains("龙芯")) {
                physicalServer.setCpuArch("mips");
            } else {
                physicalServer.setCpuArch("other");
            }
        }

        if (StringUtils.isEmpty(physicalServer.getName()) && !StringUtils.isEmpty(physicalServer.getMainIp())) {
            physicalServer.setName(physicalServer.getMainIp());
        }

        //获取部署运营人员
        if (!StringUtils.isEmpty(physicalServer.getDeployAdminA())) {
            if (!physicalServer.getDeployAdminA().contains("-")) {
                String userName = physicalServer.getDeployAdminA();
                List<String> empNoList = userMapper.getSysUserEmpNoByUserName(userName);
                if (empNoList.size() == 1) {
                    String empNo = empNoList.get(0);
                    physicalServer.setDeployAdminA(userName + "-" + empNo);
                }
            }
        }
        physicalMachineMapper.insertSinglePhysicalServerInstance(physicalServer);
        AsyncManager.me().execute(AsyncFactory.recordOper(physicalServer.getResId(), physicalServer.getClass_Name(),
                physicalServer.getName(), "resManage", 1,
                "All", "", "", 0));
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteTestPhysicalServer(Map<String, PhysicalMachine> physicalServerListMap) {
        for (String key : physicalServerListMap.keySet()) {
            PhysicalMachine physicalMachine = physicalServerListMap.get(key);
            physicalMachineMapper.deletePhysicalServerByLocationCode(physicalMachine);
            AsyncManager.me().execute(AsyncFactory.recordOper(physicalMachine.getResId(), physicalMachine.getClass_Name(),
                    physicalMachine.getName(), "resManage", 3,
                    "All", "", "", 0));
        }
    }

    /**
     * oc裸金属资源同步（新增、更新、删除）
     */
    @XxlJob("executeCloudBmsSynchronization")
    public String executeCloudBmsSynchronization() {
        int insertCount = 0;
        int updateCount = 0;
        int updateInstanceCount = 0;
        int deleteCount = 0;
        JSONArray jsonArray = ocService.getAllInstanceInfo(bms);
        List<CloudBms> cloudBmsList = JSONObject.parseArray(jsonArray.toJSONString(), CloudBms.class);
        try {
            List<CloudBms> ocCloudBmsList = getOcCloudBmsInstance(cloudBmsList);
            List<CloudBms> originalCloudBmsList = cloudBmsMapper.getAllCloudBmsInstance(new CloudBms());
            Map<String, CloudBms> originalCloudBmsListMap = originalCloudBmsList.stream()
                    .collect(Collectors.toMap(k -> k.getResId(), CloudBms -> CloudBms));

            //遍历执行新增和更新虚机操作
            for (CloudBms cloudBms : ocCloudBmsList) {
                cloudBms.setProperties(null);
                if (originalCloudBmsListMap.containsKey(cloudBms.getResId())) {
                    Map<String, Object> compareObjectMap = CompareObjectUtils
                            .compareObject(originalCloudBmsListMap.get(cloudBms.getResId()), cloudBms);
                    if (compareObjectMap.size() > 1) {
                        System.out.println(compareObjectMap);
                        QueryWrapper<CloudBms> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("resId", cloudBms.getResId());
                        cloudBmsMapper.update(cloudBms, queryWrapper);
                        updateInstanceCount++;

                        //记录所有变更日志信息
                        for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                            if (entry.getKey().equals("last_Modified")) {
                                continue;
                            } else {
                                String str = (String) entry.getValue();
                                AsyncManager.me().execute(AsyncFactory.recordOper(cloudBms.getResId(),"CLOUD_BMS_NODE",
                                        cloudBms.getName(),"resManage",2, entry.getKey(),
                                        str.substring(7, str.indexOf("*")),str.substring(str.indexOf("*") + 7),0));
                            }
                        }
                    }
                    updateCount++;
                    originalCloudBmsListMap.remove(cloudBms.getResId());
                    continue;
                }
                cloudBmsMapper.insert(cloudBms);
                AsyncManager.me().execute(AsyncFactory.recordOper(cloudBms.getResId(), "CLOUD_BMS_NODE",
                        cloudBms.getName(), "resManage", 1, "All", "", "", 0));
                insertCount++;
            }

            //遍历执行删除虚机操作
            if (originalCloudBmsListMap.size() > 0) {
                for (String key : originalCloudBmsListMap.keySet()) {
                    CloudBms cloudBms = originalCloudBmsListMap.get(key);
                    QueryWrapper<CloudBms> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("resId", cloudBms.getResId());
                    cloudBmsMapper.delete(queryWrapper);
                    AsyncManager.me().execute(AsyncFactory.recordOper(cloudBms.getResId(), "CLOUD_BMS_NODE",
                            cloudBms.getName(), "resManage", 3, "All", "", "", 0));
                    deleteCount++;
                }
            }
            logger.info("裸金属服务器同步---------结束");
            logger.info("新增 " + insertCount + " 条裸金属服务器资源");
            logger.info("待更新 " + updateCount + " 条裸金属服务器资源，实际更新 " + updateInstanceCount + " 条裸金属服务器资源");
            logger.info("删除 " + deleteCount + " 条裸金属服务器资源");
        } catch (Exception e) {
            logger.info("同步裸金属服务器资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 处理oc裸金属服务器数据
     */
    public List<CloudBms> getOcCloudBmsInstance(List<CloudBms> cloudBmsList) {
        Map<String, Map<String, String>> cloudAzoneMap = cloudVmareService.getAllCloudAzoneMap();
        for (CloudBms cloudBms : cloudBmsList) {
            //获取azoneName
            if (StringUtils.isEmpty(cloudBms.getAzoneName()) && !StringUtils.isEmpty(cloudBms.getAzoneId())) {
                cloudBms.setAzoneName(cloudAzoneMap.get(cloudBms.getAzoneId()).get("name"));
            }

            //处理properties
            if (!StringUtils.isEmpty(cloudBms.getProperties())) {
                String str = cloudBms.getProperties().replaceAll("\"|\\{", "").replace("capabilities:", "");
                String[] propertyList = str.split(",");
                Map<String, String> propertyMap = Arrays.stream(propertyList).map(s -> s.split(":"))
                        .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
                for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
                    if (entry.getKey().equals("cpu_arch")) {
                        cloudBms.setCpuArch(entry.getValue());
                    } else if (entry.getKey().equals("board_type")) {
                        cloudBms.setBoardType(entry.getValue());
                    } else if (entry.getKey().equals("cpus")) {
                        cloudBms.setCpuCores(entry.getValue());
                    } else if (entry.getKey().equals("memory_mb")) {
                        cloudBms.setMemoryCapacity(entry.getValue());
                    } else if (entry.getKey().equals("local_gb")) {
                        cloudBms.setDiskCapacity(entry.getValue());
                    } else {
                        continue;
                    }
                }
            }
            cloudBms.setLast_Modified(String.valueOf(System.currentTimeMillis()));
            cloudBms.setBelongCompany("数据中心");
        }
        return cloudBmsList;
    }
}
