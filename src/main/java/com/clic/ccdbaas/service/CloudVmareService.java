package com.clic.ccdbaas.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.clic.ccdbaas.dao.CloudVmNovaMapper;
import com.clic.ccdbaas.dao.RelationMapper;
import com.clic.ccdbaas.dao.UserMapper;
import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.RelationInstance;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.page.TableDataInfo;
import com.clic.ccdbaas.utils.*;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service("CLOUD_VM_NOVA")
public class CloudVmareService {
    @Autowired
    OcService ocService;
    @Value("${oc.cmdb.vm}")
    private String vm;
    @Value("${nginx.cmdb.url}")
    private String cmdbUrl;
    @Value("${nginx.cmdb.location}")
    private String cmdbLocation;
    @Value("${oc.cmdb.instances.relations.volume-nova}")
    private String cloudVolumeMountNova;
    @Value("${oc.cmdb.volume}")
    private String volume;
    @Value("${oc.cmdb.image}")
    private String image;
    @Value("${oc.cmdb.flavor}")
    private String flavor;
    @Value("${oc.cmdb.azone}")
    private String azone;
    @Value("${oc.cmdb.tenant.user}")
    private String tenantUser;
    @Value("${oc.cmdb.tenant.project}")
    private String tenantProject;

    @Autowired
    private CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelationService relationService;
    @Autowired
    private HiCloudService hiCloudService;
    private static final Logger logger = LoggerFactory.getLogger(CloudVmareService.class);
    private static final ExecutorService fixedPool = Executors.newFixedThreadPool(15);


    /**
     * 导出弹性云服务器
     */
    @SneakyThrows
    public JSONObject exportCloudVmare(JSONObject requestParams) {
        JSONArray vmjs = new JSONArray();
        if (!requestParams.isEmpty()) {
            vmjs = getCloudVmare(requestParams);
        } else {
            vmjs = getCloudVmare();
        }
        List<CloudVmare> vmList = JSONObject.parseArray(vmjs.toJSONString(), CloudVmare.class);
        String nowTime = Long.toString(System.currentTimeMillis());
        String fileName = nowTime + ".xlsx";
        File filePath = new File(cmdbLocation + vm);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String path = filePath + "\\" + fileName;
        String url = cmdbUrl + vm + '/' + fileName;
        System.out.println("\npath:" + path);
        EasyExcel.write(path, CloudVmare.class).sheet("弹性云服务器列表").doWrite(vmList);
        JSONObject message = new JSONObject();
        message.put("url", url);
        return message;
    }

    /**
     * 获取所有的虚机资源
     *
     * @param cloudVmare
     * @return
     */
    public List<CloudVmare> getCloudvmList(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String bizRegionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(bizRegionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        List<CloudVmare> allInstance = cloudVmNovaMapper.getAllInstance(cloudVmare);
        for (CloudVmare singleVm : allInstance) {
            //进行时间格式处理
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(singleVm.getLast_Modified()))));
            String azoneName = singleVm.getAzoneName();
            if (StringUtils.isNotEmpty(azoneName)) {
                String cpuArch = azoneName.contains("鲲鹏") ? "arm" : "x86";
                singleVm.setCpuArch(cpuArch);
            } else {
                singleVm.setCpuArch("x86");
            }

        }
        return allInstance;
    }


    /**
     * 获取近一周资源个数信息
     *
     * @return
     */
    public JSONObject getResCountInfo() {
        JSONObject resJson = new JSONObject();
        String[] classNameArr = {"VM", "VM-VDESK", "SERVER", "HOST"};
        ArrayList<String> days = DateUtils.getDays(7);
        for (String className :
                classNameArr) {
            List tmpCountArr = new ArrayList();
            List<Map> resCountInfoByClassName = cloudVmNovaMapper.getResCountInfoByClassName(className);
            for (Map map :
                    resCountInfoByClassName) {
                tmpCountArr.add(map.get("count"));
            }
            resJson.put(className, tmpCountArr);
        }
        resJson.put("date", days);
        return resJson;

    }

    /**
     * 获取环比信息
     * @return
     */
    public Map getResProportion(){
        HashMap<String, String> resMap = new HashMap<>();
        Map resCountByDateMap = cloudVmNovaMapper.getResCountByDate(new Date());
        BigDecimal nowCountInfo = (BigDecimal)resCountByDateMap.get("count");
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(Calendar.DAY_OF_YEAR,-7);
        Map weekResCountByDateMap = cloudVmNovaMapper.getResCountByDate(instance.getTime());
        BigDecimal lastWeekCountInfo = (BigDecimal)weekResCountByDateMap.get("count");
        instance.add(Calendar.DAY_OF_YEAR,-23);
        Map monthResCountByDateMap = cloudVmNovaMapper.getResCountByDate(instance.getTime());
        BigDecimal lastMonthCountInfo = (BigDecimal)monthResCountByDateMap.get("count");
        String weekProportion = MathUtils.d2dRatio(nowCountInfo,lastWeekCountInfo);
        String monthProportion = MathUtils.d2dRatio(nowCountInfo,lastMonthCountInfo);
        resMap.put("weekProportion",weekProportion);
        resMap.put("monthProportion",monthProportion);
        return resMap;
    }


    /**
     * 获取近一周资源个数信息
     *
     * @return
     */
    public JSONObject getChangeRecordCountInfo() {
        JSONObject resJson = new JSONObject();
        ArrayList<String> days = DateUtils.getDays(7);

        List tmpCountArr = new ArrayList();
        int totalCount = 0;
        List<Map> resCountInfoByClassName = cloudVmNovaMapper.getResCountInfoByClassName("CHANGERECORD");
        for (Map map :
                resCountInfoByClassName) {
            totalCount += (int) map.get("count");
            tmpCountArr.add(map.get("count"));
        }
        resJson.put("trendInfo", tmpCountArr);
        resJson.put("totalCount", totalCount);
        resJson.put("date", days);

        return resJson;

    }


    /**
     * 获取所有资源信息物理机、弹性云服务器、宿主机
     *
     * @param cloudVmare
     * @return
     */
    public List<CloudVmare> getAllResourceList(CloudVmare cloudVmare) {

        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        List<CloudVmare> allInstance = cloudVmNovaMapper.getMergeInstance(cloudVmare);
        for (CloudVmare singleVm : allInstance) {
            processCpuArch(singleVm);

//            //进行时间格式处理
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(singleVm.getLast_Modified()))));
        }
        return allInstance;
    }

    public void processCpuArch(CloudVmare cloudVmare) {
        String azoneName = cloudVmare.getAzoneName();
        //弹性云服务器的架构需要特殊处理
        String className = cloudVmare.getClassName();
        if ("CLOUD_VM_NOVA".equals(className)) {
            if (StringUtils.isNotEmpty(azoneName)) {
                String cpuArch = azoneName.contains("鲲鹏") ? "arm" : "x86";
                cloudVmare.setCpuArch(cpuArch);
            } else {
                cloudVmare.setCpuArch("x86");
            }
        }
    }


    /**
     * 获取产品top10主机信息
     *
     * @return
     */
    public List getTopProduct(String teamName) {

        List<Map> resCountInfoByClassName = cloudVmNovaMapper.getProductHostNumInfo(teamName);

        return resCountInfoByClassName;

    }

    /**
     * 获取产品top10主机信息
     *
     * @return
     */
    public JSONObject getAllResCountInfo() {
        JSONObject resJson = new JSONObject();

        List<Map> resModelInfoByClassName = cloudVmNovaMapper.getAllResDefModel();
        if (resModelInfoByClassName.size() > 0) {
            int allResCount = cloudVmNovaMapper.getAllResCount(resModelInfoByClassName);
            resJson.put("totalNum", allResCount);
        } else {
            resJson.put("totalNum", 0);

        }
        return resJson;
    }


    /**
     * 获取资源桑吉图信息
     *
     * @return
     */
    public JSONObject getResTrendInfo() {
        JSONObject trendData = new JSONObject();
        JSONObject resJson = new JSONObject();
        List<Map> allTrendName = cloudVmNovaMapper.getAllTrendName();
        List<Map> trendDetailInfo = cloudVmNovaMapper.getTrendDetailInfo();
        trendData.put("nodes", allTrendName);
        trendData.put("links", trendDetailInfo);
        resJson.put("trendData", trendData);

        return resJson;
    }


    public JSONObject getAllResByParams(JSONObject queryJson) {
        String className = queryJson.getString("className");
        if (StringUtils.isEmpty(className)) {
            throw new BusinessException("className资源类型为必填参数");
        }
        String pageNo = queryJson.getString("pageNum");
        String pageSize = queryJson.getString("pageSize");
        int resPageNo = StringUtils.isEmpty(pageNo) ? 1 : Integer.valueOf(pageNo);
        int resPageSize = StringUtils.isEmpty(pageSize) ? 10 : Integer.valueOf(pageSize);
        String condition = ocService.dealParams(queryJson);
        String ocClassInstances = ocService.getOcInstancesByCondition(className, resPageNo, resPageSize, condition);

        JSONObject ocInstance = JSONObject.parseObject(ocClassInstances);
        JSONArray objList = ocInstance.getJSONArray("objList");

        //磁盘需要特殊处理获取resId查询关联的服务器id
        if ("CLOUD_VOLUME".equals(className) && objList.size() > 0) {
            JSONArray resVolumeArr = new JSONArray();
            Map volumeToNovaIdMap = new HashMap();
            List thisPageVolumeIdList = new ArrayList();
            List thisPageVmIdList = new ArrayList();
            for (int i = 0; i < objList.size(); i++) {
                JSONObject volumeJson = objList.getJSONObject(i);
                thisPageVolumeIdList.add(volumeJson.getString("resId"));
            }
            String getVolumeCondition = ocService.dealInParams(thisPageVolumeIdList);
            //一页最多查询50条  所以1000条肯定够了
            String pageRelationInfo = ocService.getPageRelation(cloudVolumeMountNova, getVolumeCondition, 1, 1000);
            JSONArray relationJsonArr = JSON.parseObject(pageRelationInfo).getJSONArray("objList");
            for (int i = 0; i < relationJsonArr.size(); i++) {
                JSONObject singleRelationJson = relationJsonArr.getJSONObject(i);
                //云服务器id组成的集合作为查询条件
                thisPageVmIdList.add(singleRelationJson.getString("vmId"));
                volumeToNovaIdMap.put(singleRelationJson.getString("volumeId"), singleRelationJson.getString("vmId"));
            }
            String vmIdsContion = ocService.dealInstanceInParams(thisPageVmIdList);
            String ocVmInstances = ocService.getOcInstancesByCondition(vm, resPageNo, resPageSize, vmIdsContion);
            JSONArray thisPageVmArr = JSON.parseObject(ocVmInstances).getJSONArray("objList");
            Map thisPageVmMap = new HashMap();
            for (int i = 0; i < thisPageVmArr.size(); i++) {
                JSONObject singleVmJson = thisPageVmArr.getJSONObject(i);
                //云服务器id组成的集合作为查询条件
                thisPageVmMap.put(singleVmJson.getString("resId"), singleVmJson.getString("mainIp"));
            }
            for (int i = 0; i < objList.size(); i++) {
                JSONObject singleVolumeJson = objList.getJSONObject(i);
                String volumeId = singleVolumeJson.getString("resId");
                String vmId = (String) volumeToNovaIdMap.get(volumeId);
                String mainIp = (String) thisPageVmMap.get(vmId);
                singleVolumeJson.put("mainIp", mainIp);
                resVolumeArr.add(singleVolumeJson);
            }
            ocInstance.put("objList", resVolumeArr);

        }


        return ocInstance;


    }

    /**
     * 每日资源量统计
     */
    @XxlJob("saveResCountInfo")
    public void saveResCountInfo() {
        cloudVmNovaMapper.saveResCountInfo();
    }

    /**
     * 统计每日资源流向信息
     */
    @XxlJob("saveResTrendInfo")
    public void saveResTrendInfo() {
        cloudVmNovaMapper.saveResTrendInfo();
    }

    /**
     * 统计每日资源流向信息
     */
    @XxlJob("saveDbTrendInfo")
    public void saveDbTrendInfo() {
        cloudVmNovaMapper.saveDbTrendInfo();
    }

    public JSONObject getAllInstanceCount(JSONArray queryJson) {
        //创建一个与JSONArray 长度相同的String数组
        String[] strings = new String[queryJson.size()];
        //使用JSONArray 中的toArray进行转换
        String[] classNameArr = queryJson.toArray(strings);
        CountDownLatch countdown = new CountDownLatch(classNameArr.length);
        JSONObject resJson = new JSONObject();
        for (String singleClassName : classNameArr) {
            fixedPool.submit(new Runnable() {
                @Override
                public void run() {
                    int instanceCount = getInstanceCount(singleClassName);
                    resJson.put(singleClassName, instanceCount);
                    countdown.countDown();
                }
            });
        }
        try {
            countdown.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resJson;
    }


    /**
     * 获取统计信息个数
     *
     * @return
     */
    public int getInstanceCount(String className) {

        if (StringUtils.isEmpty(className)) {
            throw new BusinessException("className资源类型为必填参数");
        }

        String ocClassInstances = ocService.getOcStatistics(className);

        JSONObject ocInstanceJson = JSONObject.parseObject(ocClassInstances);
        Integer count = ocInstanceJson.getInteger("count");
        return count;

    }


    /**
     * 导出所有的虚机资源
     *
     * @param cloudVmare
     * @return
     */
    public List<CloudVmare> exportCloudvmList(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }

        List<CloudVmare> allInstance = cloudVmNovaMapper.getAllInstance(cloudVmare);
        for (CloudVmare singleVm : allInstance) {
            //进行时间格式处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(singleVm.getLast_Modified()))));
            String azoneName = singleVm.getAzoneName();
            if (StringUtils.isNotEmpty(azoneName)) {
                String cpuArch = azoneName.contains("鲲鹏") ? "arm" : "x86";
                singleVm.setCpuArch(cpuArch);
            } else {
                singleVm.setCpuArch("x86");
            }

        }
        return allInstance;
    }

    /**
     * 有资源信息物理机、弹性云服务器、宿主机
     *
     * @param cloudVmare
     * @return
     */
    public List<CloudVmare> exportAllResourceList(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }
        List<CloudVmare> allInstance = cloudVmNovaMapper.getMergeInstance(cloudVmare);
        for (CloudVmare singleVm : allInstance) {
            processCpuArch(singleVm);

            //进行时间格式处理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            singleVm.setLast_Modified(sdf.format(new Date(Long.parseLong(singleVm.getLast_Modified()))));
        }
        return allInstance;
    }


    public List<CloudVmare> getNullBelongProductList(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        List<CloudVmare> nullBelongProductInstance = cloudVmNovaMapper.getNullBelongProductList(cloudVmare);
        return nullBelongProductInstance;
    }


    /**
     * 查询所有的弹性云服务器
     */
    public JSONArray getCloudVmare() {
        return ocService.getAllInstanceInfo(vm);
    }

    /**
     * 按照一定的条件对弹性云服务器进行查询
     */
    public JSONArray getCloudVmare(JSONObject requestParams) {
        return ocService.AllInstanceInfoByCondition(requestParams, vm);
    }

    /**
     * 获取不同状态值下的虚拟机实例数
     *
     * @return
     */
    public List<HashMap> countByStatus(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countInstanceByStatus(cloudVmare);
    }

    public List<HashMap> countInstanceBydeployenv(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countInstanceBydeployenv(cloudVmare);
    }

    public List<HashMap> countByStatusGroudByAzoneName(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countInstanceByStatusGroudByAzoneName(cloudVmare);
    }

    public void updateAdministrator(Map<String, String> map) {
        cloudVmNovaMapper.updateAdministrator(map);
    }

    public void updatebackupAdministrator(Map<String, String> map) {
        cloudVmNovaMapper.updatebackupAdministrator(map);
    }

    public void updateStakeholder(Map<String, String> map) {
        cloudVmNovaMapper.updateStakeholder(map);
    }

    /**
     * 获取不同状态值下的所有实例数
     *
     * @return
     */
    public List<HashMap> countAllResourceByStatus(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countMergeInstanceByStatus(cloudVmare);
    }

    public int countNonCompliantZbxStatus() {
        return cloudVmNovaMapper.countNonCompliantZabbixStatus();
    }

    public int countNonCompliantHidsStatus() {
        return cloudVmNovaMapper.countNonCompliantHidsStatus();
    }

    public int countNonCompliantMainIp() {
        return cloudVmNovaMapper.countNonCompliantMainIp();
    }

    public int countNoncompliantAttr(String attr1, String attr2, String attr3) {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isNotEmpty(attr1)) params.put("t_cloudvm", attr1);
        if (StringUtils.isNotEmpty(attr2)) params.put("t_physicalserver", attr2);
        if (StringUtils.isNotEmpty(attr3)) params.put("t_physicalhost", attr3);
        return cloudVmNovaMapper.countAttrNull(params);
    }

    public int countNoncompliantAttrActive(String attr1, String attr2, String attr3) {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isNotEmpty(attr1)) params.put("t_cloudvm", attr1);
        if (StringUtils.isNotEmpty(attr2)) params.put("t_physicalserver", attr2);
        if (StringUtils.isNotEmpty(attr3)) params.put("t_physicalhost", attr3);
        return cloudVmNovaMapper.countAttrNullActive(params);
    }

    public int countNoncompliantDeployEnv() {
        return cloudVmNovaMapper.countNonCompliantDeployEnv();
    }

    public int getCloudVmareCount() {
        return cloudVmNovaMapper.getCloudVmareCount();
    }

    public int getVdeskVMCount() {
        return cloudVmNovaMapper.getVdeskVMCount();
    }

    public int getBmsCount() {
        return cloudVmNovaMapper.getBmsCount();
    }
    public int getSafeEquCount() {
        return cloudVmNovaMapper.getSafeEquipmentCount();
    }
    public List<Map> getCloudVMByResIds(Set<String> resIds) {
        return cloudVmNovaMapper.getCloudVMByResIds(resIds);
    }


    public void testDeleteAllData() {
        cloudVmNovaMapper.testDelete();
    }


    /**
     * 获取单条存储信息详情
     */
    public JSONObject getStorageInstance(String vmId, String className) {
        String condition = "";
        String message = "";
        String storageCondition = "";
        String storageMessage = "";
        JSONObject messageJson = new JSONObject();
        List<String> storageIdList = new ArrayList<>();
        if (className.equals("CLOUD_VM_NOVA")) {
            condition = ocService.getCondition("vmId", vmId, "equal", "");
            message = ocService.getRelationInstancesByCondition(cloudVolumeMountNova, 1, 20, condition);
        } else if (className.equals("SYS_X86Server")) {
            return new JSONObject();
        }
        messageJson = JSONObject.parseObject(message);
        List<Map> messageObList = (List<Map>) messageJson.get("objList");
        for (Map messageObListMap : messageObList) {
            storageIdList.add((String) messageObListMap.get("volumeId"));
        }
        storageCondition = ocService.getStorageCondition("resId", storageIdList, "in", "");
        storageMessage = ocService.getStorageInstanceByCondition(volume, 1, 20, storageCondition);
        return JSONObject.parseObject(storageMessage);
    }

    public JSONObject getInstanceDetail(String resId) {
        CloudVmare res = cloudVmNovaMapper.getCloudVMInfoByResId(resId);
        return JSONObject.parseObject(JSON.toJSONString(res));
    }

    public TableDataInfo getAllInstance() {
        List<CloudVmare> list = cloudVmNovaMapper.getAllInstance(null);
        TableDataInfo res = PageUtils.getDataTable(list);
        return res;
    }

    /**
     * 获取不同部署环境下的物理机数量
     *
     * @return
     */
    public List<HashMap> countPhysicalServerByDeployEnv(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countPhysicalServerByDeployEnv(cloudVmare);
    }

    /**
     * 获取不同部署环境下的宿主机数量
     *
     * @return
     */
    public List<HashMap> countPhysicalHostByDeployEnv(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countPhysicalHostByDeployEnv(cloudVmare);
    }

    /**
     * 获取不同部署环境下的虚拟机、物理机、宿主机数量
     *
     * @return
     */
    public List<HashMap> countMergeInstanceByDeployEnv(CloudVmare cloudVmare) {
        if (StringUtils.isNotEmpty(cloudVmare.getStatus())) {
            String status = cloudVmare.getStatus();
            List<String> statusArr = Arrays.asList(status.split(","));
            cloudVmare.setStatusArr(statusArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getDeployEnv())) {
            String deployEnv = cloudVmare.getDeployEnv();
            List<String> deployEnvArr = Arrays.asList(deployEnv.split(","));
            cloudVmare.setDeployEnvArr(deployEnvArr);
        }
        if (StringUtils.isNotEmpty(cloudVmare.getBizRegionName())) {
            String regionName = cloudVmare.getBizRegionName();
            List<String> regionArr = Arrays.asList(regionName.split(","));
            cloudVmare.setBizRegionNameArr(regionArr);
        }

        return cloudVmNovaMapper.countMergeInstanceByDeployEnv(cloudVmare);
    }

    /**
     * 获取弹性云服务器、物理机和宿主机的regionName分类
     *
     * @return
     */
    public List<Map> getRegionNameOfMergedInstance() {
        return cloudVmNovaMapper.getRegionNameOfMergedInstance();
    }

    public int getResCount() {
        return cloudVmNovaMapper.getCloudVmareCount();
    }

    /**
     * 获取云硬盘关联关系并落库
     */
    @XxlJob("getCloudVolumeRelationsInstance")
    public String getCloudVolumeRelationsInstance() {
        int insertCount = 0;
        int updateCount = 0;
        try {
            JSONArray jsonArray = ocService.getAllRelationInfo(cloudVolumeMountNova);
            List<Map> cloudVolumeRelation = JSONObject.parseArray(jsonArray.toJSONString(), Map.class);
            String relationId = relationService.getResIdByClassName(volume, vm);
            List<HashMap> list = relationMapper.getAllRelationInstance();
            List<RelationInstance> originalRelationList = JSON.parseObject(JSON.toJSONString(list), new TypeReference<List<RelationInstance>>() {
            });
            Map<String, RelationInstance> originalRelationListMap = originalRelationList.stream().collect(Collectors.toMap(k -> k.getResId(), RelationInstance -> RelationInstance));
            for (Map<String, Object> cloudVolumeRelationMap : cloudVolumeRelation) {
                RelationInstance relationInstance = getCloudVolumeRelationsMessage(cloudVolumeRelationMap, relationId);
                HashMap<String, Object> relationInstanceMap = JSON.parseObject(JSON.toJSONString(relationInstance), HashMap.class);
                relationInstanceMap.put("lastModified", new Date());
                if (originalRelationListMap.containsKey(relationInstanceMap.get("resId"))) {
                    Map<String, Object> compareObjectMap = CompareObjectUtils.compareObject(originalRelationListMap.get(relationInstanceMap.get("resId")), relationInstanceMap);
                    if (compareObjectMap.size() > 1) {
                        System.out.println(compareObjectMap);
                        relationMapper.updateRelationInstance(relationInstanceMap);
                        updateCount++;
                    }
                } else {
                    relationMapper.addRelationInstance(relationInstanceMap);
                    insertCount++;
                }
            }
            logger.info("新增 " + insertCount + " 条云硬盘关联关系；更新 " + updateCount + " 条云硬盘关联关系。");
        } catch (Exception e) {
            logger.info("云硬盘关联关系落库失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 解析云硬盘关联关系
     */
    public RelationInstance getCloudVolumeRelationsMessage(Map<String, Object> cloudVolumeRelationMap, String relationId) {
        RelationInstance relationInstance = new RelationInstance();
        relationInstance.setResId((String) cloudVolumeRelationMap.get("resId"));
        relationInstance.setSourceInstanceId((String) cloudVolumeRelationMap.get("volumeId"));
        relationInstance.setTargetInstanceId((String) cloudVolumeRelationMap.get("vmId"));
        relationInstance.setRelationId(relationId);
        relationInstance.setExtraSpec(JSON.toJSONString(cloudVolumeRelationMap));
        return relationInstance;
    }

    /**
     * oc虚机资源同步（新增、更新、删除）
     */
    @XxlJob("executeCloudVmSynchronization")
    public String executeCloudVmSynchronization() {
        int insertCount = 0;
        int updateCount = 0;
        int updateInstanceCount = 0;
        int deleteCount = 0;
        JSONArray jsonArray = ocService.getAllInstanceInfo(vm);
        List<CloudVmare> cloudVmwareList = JSONObject.parseArray(jsonArray.toJSONString(), CloudVmare.class);
        try {
            List<CloudVmare> ocCloudVmwareList = getOcCloudVmInstance(cloudVmwareList);
            List<CloudVmare> originalCloudVmList = cloudVmNovaMapper.getAllInstanceExceptCloudDesktop();
            Map<String, CloudVmare> originalCloudVmListMap = originalCloudVmList.stream().collect(Collectors.toMap(k -> k.getResId(), CloudVmare -> CloudVmare));

            //遍历执行新增和更新虚机操作
            for (CloudVmare cloudVmware : ocCloudVmwareList) {
                if (originalCloudVmListMap.containsKey(cloudVmware.getResId())) {
                    CloudVmare newCloudVmware = cloudVmwareSetNull(cloudVmware);
                    Map<String, Object> compareObjectMap = CompareObjectUtils.compareObject(originalCloudVmListMap.get(cloudVmware.getResId()), newCloudVmware);
                    if (compareObjectMap.size() > 2) {
                        System.out.println(compareObjectMap);
                        cloudVmNovaMapper.updateSingleCloudVmInstance(newCloudVmware);
                        updateInstanceCount++;

                        //记录所有变更日志信息
                        for (Map.Entry<String, Object> entry : compareObjectMap.entrySet()) {
                            if (entry.getKey().equals("id") || entry.getKey().equals("last_Modified") || entry.getKey().equals("ownerType")
                                    || entry.getKey().equals("tenantType") || entry.getKey().equals("extraSpecs") || entry.getKey().equals("powerState")
                                    || entry.getKey().equals("hypervisorType") || entry.getKey().equals("keystoneId") || entry.getKey().equals("is_Local")
                                    || entry.getKey().equals("dataPlane")) {
                                continue;
                            } else {
                                String str = (String) entry.getValue();
                                AsyncManager.me().execute(AsyncFactory.recordOper(newCloudVmware.getResId(), "CLOUD_VM_NOVA", newCloudVmware.getName(),
                                        "resManage", 2, entry.getKey(), str.substring(7, str.indexOf("*")),
                                        str.substring(str.indexOf("*") + 7), 0));
                            }
                        }
                    }
                    updateCount++;
                    originalCloudVmListMap.remove(cloudVmware.getResId());
                    continue;
                }
                cloudVmNovaMapper.insertSingleCloudVmInstance(cloudVmware);
                AsyncManager.me().execute(AsyncFactory.recordOper(cloudVmware.getResId(), "CLOUD_VM_NOVA",
                        cloudVmware.getName(), "resManage", 1, "All", "", "", 0));
                insertCount++;
            }

            //遍历执行删除虚机操作
            if (originalCloudVmListMap.size() < 200) {
                for (String key : originalCloudVmListMap.keySet()) {
                    CloudVmare cloudVmware = originalCloudVmListMap.get(key);
                    cloudVmNovaMapper.deleteSingleCloudVmInstance(cloudVmware);
                    AsyncManager.me().execute(AsyncFactory.recordOper(cloudVmware.getResId(), "CLOUD_VM_NOVA",
                            cloudVmware.getName(), "resManage", 3, "All", "", "", 0));
                    deleteCount++;
                }
            }
            logger.info("虚机同步---------结束");
            logger.info("新增 " + insertCount + " 条虚机资源");
            logger.info("待更新 " + updateCount + " 条虚机资源，实际更新 " + updateInstanceCount + " 条虚机资源");
            logger.info("删除 " + deleteCount + " 条虚机资源");
        } catch (Exception e) {
            logger.info("同步虚机资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 处理oc虚机数据
     */
    public List<CloudVmare> getOcCloudVmInstance(List<CloudVmare> cloudVmwareList) {
        Map<String, Map<String, String>> cloudImageMap = getAllCloudImageMap();
        Map<String, Map<String, String>> cloudFlavorMap = getAllCloudFlavorMap();
        Map<String, Map<String, String>> cloudAzoneMap = getAllCloudAzoneMap();
        Map<String, Map<String, String>> tenantUserMap = getAllTenantUserMap();
        Map<String, Map<String, String>> tenantProjectMap = getAllTenantProjectMap();
        List smIds = new ArrayList<>();
        for (CloudVmare cloudVmware : cloudVmwareList) {
            //获取状态
            if (cloudVmware.getStatus().equalsIgnoreCase("stopped") || cloudVmware.getStatus().equalsIgnoreCase("shutoff")
                    || cloudVmware.getStatus().equalsIgnoreCase("pause") || cloudVmware.getStatus().equalsIgnoreCase("suspended")) {
                cloudVmware.setStatus("offline");
            }
            //获取操作系统
            cloudVmware.setDeployOs(null);
            if (!StringUtils.isEmpty(cloudVmware.getImageId()) && cloudImageMap.containsKey(cloudVmware.getImageId())) {
                Map<String, String> imageMap = cloudImageMap.get(cloudVmware.getImageId());
                if (!StringUtils.isEmpty(imageMap.get("osVersion"))) {
                    cloudVmware.setDeployOs(imageMap.get("osVersion"));
                } else if (!StringUtils.isEmpty(imageMap.get("name"))) {
                    cloudVmware.setDeployOs(imageMap.get("name"));
                } else if (!StringUtils.isEmpty(cloudVmware.getFlavorId()) && cloudFlavorMap.containsKey(cloudVmware.getFlavorId())) {
                    cloudVmware.setDeployOs(cloudFlavorMap.get(cloudVmware.getFlavorId()).get("name"));
                } else {
                    cloudVmware.setDeployOs("");
                }
            }
            //获取azoneName
            if (StringUtils.isEmpty(cloudVmware.getAzoneName()) && !StringUtils.isEmpty(cloudVmware.getAzoneId())) {
                cloudVmware.setAzoneName(cloudAzoneMap.get(cloudVmware.getAzoneId()).get("name"));
            }

            //获取mainIp ip
            String ip = cloudVmware.getIpAddress();
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
            cloudVmware.setMainIp(mainIp);

            //获取deployEnv(0-生产 1-测试)
            if (StringUtils.isEmpty(cloudVmware.getDeployEnv())) {
                if (IpSegmentUtils.isTestMachineIp(cloudVmware.getMainIp())) {
                    cloudVmware.setDeployEnv("1");
                } else {
                    cloudVmware.setDeployEnv("0");
                }
            }

            //获取createUser
            if (!StringUtils.isEmpty(cloudVmware.getUserId()) && tenantUserMap.containsKey(cloudVmware.getUserId())) {
                String userId = tenantUserMap.get(cloudVmware.getUserId()).get("name");
                smIds.add(userId);
                if (userMapper.getProductUserInfo(smIds).size() != 0) {
                    String userName = (String) userMapper.getProductUserInfo(smIds).get(0).get("user_name");
                    cloudVmware.setCreateUser(userName + "-" + userId);
                } else {
                    cloudVmware.setCreateUser(userId);
                }
                smIds.clear();
            }

            //获取projectName
            if (!StringUtils.isEmpty(cloudVmware.getProjectId()) && tenantProjectMap.containsKey(cloudVmware.getProjectId())) {
                String projectName = tenantProjectMap.get(cloudVmware.getProjectId()).get("projectName");
                cloudVmware.setProjectName(projectName);
            }
            cloudVmware.setLast_Modified(String.valueOf(System.currentTimeMillis()));

            //补充vdcName和belongCompany
            if (!StringUtils.isEmpty(cloudVmware.getVdcName()) && StringUtils.isEmpty(cloudVmware.getBelongCompany())) {
                cloudVmware.setBelongCompany(cloudVmware.getVdcName());
            }
        }
        return cloudVmwareList;
    }

    /**
     * 获取镜像map
     *
     * @return
     */
    public Map<String, Map<String, String>> getAllCloudImageMap() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(image);
        Map<String, Map<String, String>> cloudImageMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, String> tempMap = new HashMap<>();
            JSONObject jo = jsonArray.getJSONObject(i);
            String osVersion = "";
            if (!StringUtils.isEmpty(jo.getString("osVersion"))) {
                osVersion = jo.getString("osVersion");
            }
            String name = jo.getString("name");
            String resId = jo.getString("resId");
            tempMap.put("osVersion", osVersion);
            tempMap.put("name", name);
            tempMap.put("resId", resId);
            cloudImageMap.put(resId, tempMap);
        }
        return cloudImageMap;
    }

    /**
     * 获取规格map
     *
     * @return
     */
    public Map<String, Map<String, String>> getAllCloudFlavorMap() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(flavor);
        Map<String, Map<String, String>> cloudFlavorMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, String> tempMap = new HashMap<>();
            JSONObject jo = jsonArray.getJSONObject(i);
            String name = jo.getString("name");
            String resId = jo.getString("resId");
            tempMap.put("name", name);
            tempMap.put("resId", resId);
            cloudFlavorMap.put(resId, tempMap);
        }
        return cloudFlavorMap;
    }

    /**
     * 获取可用域map
     *
     * @return
     */
    public Map<String, Map<String, String>> getAllCloudAzoneMap() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(azone);
        Map<String, Map<String, String>> cloudAzoneMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, String> tempMap = new HashMap<>();
            JSONObject jo = jsonArray.getJSONObject(i);
            String name = jo.getString("name");
            String resId = jo.getString("resId");
            tempMap.put("name", name);
            tempMap.put("resId", resId);
            cloudAzoneMap.put(resId, tempMap);
        }
        return cloudAzoneMap;
    }

    /**
     * 获取用户map
     *
     * @return
     */
    public Map<String, Map<String, String>> getAllTenantUserMap() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(tenantUser);
        Map<String, Map<String, String>> tenantUserMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, String> tempMap = new HashMap<>();
            JSONObject jo = jsonArray.getJSONObject(i);
            String name = jo.getString("name");
            String nativeId = jo.getString("nativeId");
            tempMap.put("name", name);
            tempMap.put("nativeId", nativeId);
            tenantUserMap.put(nativeId, tempMap);
        }
        return tenantUserMap;
    }

    /**
     * 获取项目map
     *
     * @return
     */
    public Map<String, Map<String, String>> getAllTenantProjectMap() {
        JSONArray jsonArray = ocService.getAllInstanceInfo(tenantProject);
        Map<String, Map<String, String>> tenantProjectMap = new HashMap<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, String> tempMap = new HashMap<>();
            JSONObject jo = jsonArray.getJSONObject(i);
            String projectName = jo.getString("projectName");
            String nativeId = jo.getString("nativeId");
            tempMap.put("projectName", projectName);
            tempMap.put("nativeId", nativeId);
            tenantProjectMap.put(nativeId, tempMap);
        }
        return tenantProjectMap;
    }

    /**
     * 虚机更新处理，置空不更新元素
     *
     * @return
     */
    public CloudVmare cloudVmwareSetNull(CloudVmare cloudVmware) {
        cloudVmware.setUsageDes(null);
        cloudVmware.setSysAdministrator(null);
        cloudVmware.setSysAdminBackup(null);
        cloudVmware.setDeployOs(null);
        cloudVmware.setClass_Name(null);
        cloudVmware.setOsAdminUser(null);
        cloudVmware.setOsNormalUser(null);
        cloudVmware.setSysAdminTeam(null);
        cloudVmware.setBelongProduct(null);
        cloudVmware.setProductToken(null);
        cloudVmware.setDeployEnv(null);
        cloudVmware.setRegionId(null);
        cloudVmware.setRegionName(null);
        cloudVmware.setNetworkArea(null);
        cloudVmware.setMainIp(null);
        cloudVmware.setRelatedIp(null);
        cloudVmware.setVirtualIp(null);
        cloudVmware.setResourceCode(null);
        cloudVmware.setStakeholder(null);
        cloudVmware.setCreateTime(null);
        cloudVmware.setLaunchedAt(null);
        cloudVmware.setRelateIp(null);
        cloudVmware.setAppModToken(null);
        cloudVmware.setAppModName(null);
        cloudVmware.setCpuCoreNum(null);
        cloudVmware.setMemorySize(null);
        cloudVmware.setUserId(null);
        return cloudVmware;
    }

    /**
     * hicloud虚机资源同步（deployOs）
     */
    @XxlJob("executeHiCloudVmSynchronizationDeployOs")
    public String executeHiCloudVmSynchronizationDeployOs() {
        int updateCount = 0;
        String token = "";
        CloudVmare cloudVmware = new CloudVmare();
        JSONObject jsonObject = new JSONObject();
        List<CloudVmare> cloudVmList = new ArrayList<>();
        try {
            List<Map<String, Object>> projectInfoList = cloudVmNovaMapper.getProjectInfoByBizRegionName("股份-VMware区");
            for (Map<String, Object> projectInfo : projectInfoList) {
                cloudVmList = cloudVmNovaMapper.getCloudVmInstanceByProjectInfo((String) projectInfo.get("projectId"), (String) projectInfo.get("projectName"));
                Map<String, CloudVmare> cloudVmListMap = cloudVmList.stream().collect(Collectors.toMap(k -> k.getResId(), CloudVmare -> CloudVmare));
                token = hiCloudService.getHiCloudToken((String) projectInfo.get("projectName"));
                if (StringUtils.isEmpty(token)) {
                    continue;
                }
                for (Map.Entry<String, CloudVmare> cloudVmMap : cloudVmListMap.entrySet()) {
                    if (!StringUtils.isEmpty(cloudVmMap.getValue().getDeployOs())) {
                        continue;
                    }
                    cloudVmware = cloudVmMap.getValue();
                    jsonObject = JSONObject.parseObject(hiCloudService.getHiCloudInstance(cloudVmware.getProjectId(), cloudVmware.getNativeId(), token));
                    jsonObject= (JSONObject) jsonObject.get("compute");
                    if (!StringUtils.isEmpty(jsonObject.getString("osVersion"))) {
                        cloudVmware.setDeployOs(jsonObject.getString("osVersion"));
                        cloudVmNovaMapper.updateSingleCloudVmInfoByNativeId(cloudVmware);
                        updateCount++;
                        //日志
                        AsyncManager.me().execute(AsyncFactory.recordOper(cloudVmware.getResId(), "CLOUD_VM_NOVA",
                                cloudVmware.getName(), "resManage", 2, "deployOs",
                                null, cloudVmware.getDeployOs(), 0));
                    }
                }

            }
            logger.info("同步hicloud " + updateCount + " 条虚机操作系统信息。");
        } catch (Exception e) {
            logger.info("同步hicloud虚机资源失败");
            throw new RuntimeException(e);
        }
        return "success";
    }

}
