package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.clic.ccdbaas.dao.*;
import com.clic.ccdbaas.entity.PhysicalServer;
import com.clic.ccdbaas.entity.ResourceApplication;
import com.clic.ccdbaas.entity.kafka.*;
import com.clic.ccdbaas.manager.AsyncManager;
import com.clic.ccdbaas.manager.factory.AsyncFactory;
import com.clic.ccdbaas.utils.uuid.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ResourceApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceApplicationService.class);
    @Autowired
    ResourceApplicationMapper resourceApplicationMapper;
    @Autowired
    CloudVmNovaMapper cloudVmNovaMapper;
    @Autowired
    ReserveServerMapper reserveServerMapper;
    @Autowired
    PhysicalMachineMapper physicalMachineMapper;
    @Autowired
    PhysicalHostMapper physicalHostMapper;
    @Autowired
    ReserveDiskMapper reserveDiskMapper;

    String mainIp = "";

    public List<ResourceApplication> getResourceApplicationList(ResourceApplication resourceApplication) {
        List<ResourceApplication> allInstance = resourceApplicationMapper.getAllInstance(resourceApplication);
        return allInstance;
    }


    /**
     * consumerMessage 落库插入数据
     *
     * @param requestParams
     */
    public void insertResourceApplication(JSONObject requestParams) {
        int insertCount = 0;
        List<Map> list = getConsumerMessage(requestParams);
        String workOrder = (String) list.get(0).get("workOrder");
        if (resourceApplicationMapper.selectResourceApplicationByWorkOrder(workOrder).size() != 0) {
            logger.info("库中已有信息，出现错误");
            return;
        }
        for (Map<String, Object> map : list) {
            resourceApplicationMapper.insertResourceApplication(map);
            insertCount++;
        }
        logger.info("插入 " + insertCount + " 条数据");
    }

    /**
     * 更新纳管信息
     */
    public boolean updateResourceApplicationStatus(JSONObject requestParams) {
        Map<String, Object> map = new HashMap<>(requestParams);
        if (resourceApplicationMapper.selectResourceApplicationStatusByNativeId((String) map.get("nativeId")).equals("1")) {
            logger.info("不可重复纳管");
            return false;
        }
        if (resourceApplicationMapper.updateResourceApplication(map) != 0) {
            logger.info("更新纳管信息");
            return true;
        }
        return false;
    }

    /**
     * 汇总producerMessage
     */
    public JSONObject getProducerMessage(String workOrder) {
        List<Map<String, Object>> list = resourceApplicationMapper.getProducerSendMessage(workOrder);
        JSONObject jsonObject = new JSONObject();
        JSONArray additionalInfo = new JSONArray();
        Map<String, Object> productInfo = new HashMap<>();
        try {
            jsonObject.put("workOrder", workOrder);
            productInfo.put("from", list.get(0).get("belongProduct"));
            productInfo.put("abbr", list.get(0).get("productAbbr"));
            productInfo.put("productToken", list.get(0).get("productToken"));
            jsonObject.put("productInfo", productInfo);
            for (Map<String, Object> map : list) {
                JSONArray projectList = new JSONArray();
                Map<String, Object> projectMap = new HashMap<>();
                Map<String, Object> additionalInfoMap = new HashMap<>();
                projectMap.put("name", map.get("projectName"));
                projectMap.put("abbr", map.get("projectAbbr"));
                projectMap.put("token", map.get("projectToken"));
                projectList.add(projectMap);
                additionalInfoMap.put("mainIp", map.get("mainIp"));
                additionalInfoMap.put("projectList", projectList);
                additionalInfo.add(additionalInfoMap);
            }
            jsonObject.put("additionalInfo", additionalInfo);
            logger.info("汇总成功 " + additionalInfo.size() + " 条数据");
        } catch (Exception e) {
            logger.info("汇总produceMessage过程中出现错误");
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

    /**
     * 解析consumerMessage
     */
    public List<Map> getConsumerMessage(JSONObject requestParams) {
        List<Map> list = new ArrayList<>();
//        JSONArray jsonArray = (JSONArray) requestParams.get("additionalInfo");
        List<Map> requestParamsList = (List<Map>) requestParams.get("additionalInfo");
        List<Map<String, Object>> additionalInfoList = new ArrayList<>();
        requestParamsList.stream().forEach(pb -> {
            Map<String, Object> rightMap = (Map<String, Object>) pb;
            additionalInfoList.add(rightMap);
        });
        System.out.println(additionalInfoList);
        try {
            for (Map<String, Object> additionalInfoMap : additionalInfoList) {
                Map<String, Object> map = new HashMap<>();
                map.put("nativeId", UUID.randomUUID().toString());
                map.put("title", requestParams.get("title"));
                map.put("moreInfo", requestParams.get("moreInfo"));
                map.put("belongProduct", ((Map<String, Object>) requestParams.get("productInfo")).get("name"));
                map.put("productAbbr", ((Map<String, Object>) requestParams.get("productInfo")).get("abbr"));
//                List<Map<String, Object>> productInfoList = (List<Map<String, Object>>) ((Map<String, Object>)requestParams.get("productInfo")).get("versionHistory");
//                Map<String, Object> productMap = productInfoList.get(0);
//                map.put("productToken", productMap.get("productToken"));
                map.put("productToken", ((Map<String, Object>) requestParams.get("productInfo")).get("token"));
                map.put("type", additionalInfoMap.get("type"));
                if (additionalInfoMap.containsKey("projectList")) {
                    List<Map<String, Object>> projectList = (List<Map<String, Object>>) additionalInfoMap.get("projectList");
                    Map<String, Object> projectMap = projectList.get(0);
                    map.put("projectName", projectMap.get("name"));
                    map.put("projectAbbr", projectMap.get("abbr"));
                    map.put("projectToken", projectMap.get("token"));
                }
                map.put("resourceCode", additionalInfoMap.get("allocCode"));
                map.put("networkArea", additionalInfoMap.get("netArea"));
                map.put("allocNum", additionalInfoMap.get("allocNum"));
                map.put("allocMachineType", additionalInfoMap.get("allocMachineType"));
                map.put("allocHostSpecs", additionalInfoMap.get("allocHostSpecs"));
                map.put("allocIsITII", additionalInfoMap.get("allocIsITII"));
                map.put("allocStorageType", additionalInfoMap.get("allocStorageType"));
                map.put("allocStorageNum", additionalInfoMap.get("allocStorageNum"));
                String str1 = (String) additionalInfoMap.get("allocOpinion");
                String allocOpinion = str1.replaceAll("\\s*|\r|\n|\t", "");
                map.put("allocOpinion", allocOpinion);
                if (additionalInfoMap.get("isAllocIp").equals(1)) {
                    map.put("allocIp", null);
                    map.put("reviewOpinion", null);
                } else {
                    map.put("allocIp", additionalInfoMap.get("allocIP"));
                    String str2 = (String) additionalInfoMap.get("reviewOpinion");
                    String reviewOpinion = str2.replaceAll("\\s*|\r|\n|\t", "");
                    map.put("reviewOpinion", reviewOpinion);
                }
                map.put("createdDate", requestParams.get("createdDate"));
                map.put("manageNum", additionalInfoMap.get("allocNum"));
                List<Map<String, Object>> userList = (List<Map<String, Object>>) requestParams.get("approvalUser");
                String sysAdministrator = "";
                for (Map<String, Object> userMap : userList) {
                    if (userMap.get("label").equals("系统管理员审批")) {
                        sysAdministrator = userMap.get("name") + "-" + userMap.get("uid");
                        break;
                    }
                }
                map.put("sysAdministrator", sysAdministrator);
                map.put("createdUser", requestParams.get("username") + "-" + requestParams.get("uid"));
                map.put("status", "0");
                map.put("workOrder", requestParams.get("workOrder"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                map.put("lastModified", simpleDateFormat.format(new Date()));
                list.add(map);
            }
            System.out.println(list);
            logger.info("解析成功 " + list.size() + " 条数据");
        } catch (Exception e) {
            logger.info("处理数据过程中出现错误");
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateLocalhostResource(JSONObject requestParams) {
        try {
            Integer count = (Integer) requestParams.get("count");
            Map<String, String> json = (Map) JSONObject.parse(requestParams.toString());
            if (count == 0) {
                mainIp = mainIp + json.get("IpList");
                json.put("mainIp", mainIp);
                resourceApplicationMapper.updateCount(json);
                resourceApplicationMapper.updateStatus(json);
                mainIp = "";
            } else {
                mainIp = mainIp + json.get("IpList") + ";";
                resourceApplicationMapper.updateCount(json);
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public int checkResourceStatus(JSONObject requestParams) {
        int count = resourceApplicationMapper.checkStatusByworkOrder(requestParams.getString("workOrder"));
        return count;
    }

    /**
     * 解析并应用Kafka资源申请工单
     */
    public void updateResourceApplication(JSONObject requestParams) {
        int insertCount = 0;
        int updateVmCount = 0;
        int updateReserveServerCount = 0;
        int updatePhysicalServerCount = 0;
        int insertPhysicalServerCount = 0;
        int updatePhysicalHostCount = 0;
        int insertPhysicalHostCount = 0;
        int deletePhysicalServerCount = 0;
        int deletePhysicalHostCount = 0;
        List<ResourceApplication> list = getConsumerResourceApplicationMessage(requestParams);
        if (list.size() == 0) {
            logger.info("提供资源申请信息有误,没有list");
            return;
        }
        String workOrder = list.get(0).getWorkOrder();
        if (workOrder.contains("测试")) {
            logger.info("提供资源申请信息为测试样例，不落库");
            return;
        }
        List<ResourceApplication> resourceApplicationList = resourceApplicationMapper.selectResourceApplicationByWorkOrder(workOrder);
        if (resourceApplicationList.size() != 0) {
            for (ResourceApplication resourceApplication : resourceApplicationList) {
                if (resourceApplication.getMachineType().equals("1")) {
                    physicalMachineMapper.deleteSinglePhysicalServerInstance(resourceApplication);
                    ReserveServerKafka reserveServerKafka = getInitialReserveServerKafkaMessage(resourceApplication);
                    reserveServerMapper.updateReserveServerKafka(reserveServerKafka);
                    deletePhysicalServerCount++;
                } else if (resourceApplication.getMachineType().equals("2")) {
                    physicalHostMapper.deleteSinglePhysicalHostInstance(resourceApplication);
                    ReserveServerKafka reserveServerKafka = getInitialReserveServerKafkaMessage(resourceApplication);
                    reserveServerMapper.updateReserveServerKafka(reserveServerKafka);
                    deletePhysicalHostCount++;
                }
            }
            resourceApplicationMapper.deleteResourceApplication(workOrder);
            logger.info("库中已有信息，更新资源申请工单消息，删除原有物理机 " + deletePhysicalServerCount + " 条数据；" +
                    "删除原有宿主机 " + deletePhysicalHostCount + " 条数据");
        }
        for (ResourceApplication resourceApplication : list) {
            resourceApplicationMapper.insertSingleResourceApplicationMessage(resourceApplication);
            insertCount++;
            if (resourceApplication.getMachineType().equals("0")) {
                CloudVmKafka cloudVmKafka = getCloudVmKafkaMessage(resourceApplication);
                if (cloudVmNovaMapper.getCloudVmByMainIp(cloudVmKafka.getMainIp()).size() != 0) {
                    cloudVmNovaMapper.updateCloudVmKafka(cloudVmKafka);
                    updateVmCount++;
                } else {
                    logger.info("虚机表中没有对应资源信息");
                }
            } else {
                if (resourceApplication.getMachineType().equals("1")) {
                    PhysicalServer physicalServer = getPhysicalServerKafkaMessage(resourceApplication);
                    if (physicalMachineMapper.getPhysicalServerByMainIp(physicalServer.getMainIp()).size() != 0) {
                        physicalMachineMapper.updateSinglePhysicalServerInstance(physicalServer);
                        updatePhysicalServerCount++;
                    } else {
                        physicalMachineMapper.insertSinglePhysicalServerInstance(physicalServer);
                        insertPhysicalServerCount++;
                    }
                    System.out.println("物理机");
                } else if (resourceApplication.getMachineType().equals("2")) {
                    PhysicalHostKafka physicalHostKafka = getPhysicalHostKafkaMessage(resourceApplication);
                    if (physicalHostMapper.getSinglePhysicalHostInstance(physicalHostKafka.getMainIp()).size() != 0) {
                        physicalHostMapper.updateSinglePhysicalHostInstance(physicalHostKafka);
                        updatePhysicalHostCount++;
                    } else {
                        physicalHostMapper.insertSinglePhysicalHostInstance(physicalHostKafka);
                        insertPhysicalHostCount++;
                    }
                    System.out.println("宿主机");
                } else {
                    logger.info("提供资源申请信息有误");
                }
                if (reserveServerMapper.getSingleReserveServerInstanceBySn(resourceApplication.getSn()) != null) {
                    ReserveServerKafka reserveServerKafka = getReserveServerKafkaMessage(resourceApplication);
                    reserveServerMapper.updateReserveServerKafka(reserveServerKafka);
                    updateReserveServerCount++;
                } else {
                    logger.info("库存设备表中没有对应资源信息");
                }
            }
        }
        logger.info("落库 " + insertCount + " 条数据");
        logger.info("更新库存设备 " + updateReserveServerCount + " 条数据");
        logger.info("更新虚机 " + updateVmCount + " 条数据");
        logger.info("本次共新增物理机 " + insertPhysicalServerCount + " 条数据；更新物理机 " + updatePhysicalServerCount + " 条数据");
        logger.info("本次共新增宿主机 " + insertPhysicalHostCount + " 条数据；更新宿主机 " + updatePhysicalHostCount + " 条数据");
    }

    /**
     * 解析Kafka资源申请工单
     */
    public List<ResourceApplication> getConsumerResourceApplicationMessage(JSONObject inventoryEquipmentInfo) {
        List<ResourceApplication> resourceApplicationList = new ArrayList<>();
        JSONObject requestParams = (JSONObject) inventoryEquipmentInfo.get("inventoryEquipmentInfo");

        List<Map> requestParamsList = (List<Map>) requestParams.get("list");
        List<Map<String, Object>> list = new ArrayList<>();
        requestParamsList.stream().forEach(pb -> {
            Map<String, Object> rightMap = (Map<String, Object>) pb;
            list.add(rightMap);
        });
        try {
            for (Map<String, Object> listMap : list) {
                Map<String, Object> newMap = new HashMap<>();
                //工单信息
                newMap.put("resId", IdUtils.generatedUUID());
                newMap.put("workOrder", requestParams.get("workOrder"));
                newMap.put("uid", requestParams.get("uid"));
                newMap.put("sysAdministrator", requestParams.get("actualUid"));
                newMap.put("deployEnv", requestParams.get("environment"));
                newMap.put("sysAdminTeam", requestParams.get("actualIdpath"));

                //产品信息
                if (!StringUtils.isEmpty(requestParams.get("productInfo"))) {
                    Map<String, Object> productMap = (Map<String, Object>) requestParams.get("productInfo");
                    newMap.put("belongProduct", productMap.get("name"));
                    newMap.put("productAbbr", productMap.get("abbr"));
                    newMap.put("productToken", productMap.get("token"));
                    if (!StringUtils.isEmpty(productMap.get("omUids"))) {
                        List<String> omUidsList = (List<String>) productMap.get("omUids");
                        newMap.put("omUids", generatedString(omUidsList));
                    }
                    if (!StringUtils.isEmpty(productMap.get("smUids"))) {
                        List<String> smUidsList = (List<String>) productMap.get("smUids");
                        newMap.put("smUids", generatedString(smUidsList));
                    }
                }

                //资源ip
                newMap.put("mainIp", listMap.get("ip"));
                if (!StringUtils.isEmpty(listMap.get("virtualIp"))) {
                    List<String> virtualIpList = (List<String>) listMap.get("virtualIp");
                    newMap.put("virtualIp", generatedString(virtualIpList));
                }
                if (!StringUtils.isEmpty(listMap.get("relatedIp"))) {
                    List<String> relateIpList = (List<String>) listMap.get("relatedIp");
                    newMap.put("relatedIp", generatedString(relateIpList));
                }

                //应用信息
                if (!StringUtils.isEmpty(listMap.get("projectList"))) {
                    List<Map<String, Object>> projectList = (List<Map<String, Object>>) listMap.get("projectList");
                    Map<String, Object> projectMap = projectList.get(0);
                    newMap.put("projectName", projectMap.get("name"));
                    newMap.put("projectAbbr", projectMap.get("abbr"));
                    newMap.put("projectToken", projectMap.get("token"));
                }

                //资源信息
                newMap.put("sn", listMap.get("sn"));
                newMap.put("machineType", listMap.get("machineType"));
                newMap.put("hostSpecs", listMap.get("hostSpecs"));
                newMap.put("storageNum", listMap.get("storageNum"));
                newMap.put("systemType", listMap.get("systemType"));
                newMap.put("resourceDetail", listMap.get("resourceDetail"));
                newMap.put("databaseType", listMap.get("databaseType"));
                newMap.put("middlewareType", listMap.get("middlewareType"));
                newMap.put("networkArea", listMap.get("netArea"));
                newMap.put("resourceCode", listMap.get("assignCode"));
                newMap.put("allocOpinion", listMap.get("assignComment"));
                newMap.put("reviewOpinion", listMap.get("reviewOpinion"));
                newMap.put("isApply", "1");

                //map转换成java实体类
                JSONObject jsonObject = new JSONObject(newMap);
                ResourceApplication resourceApplication = jsonObject.toJavaObject(ResourceApplication.class);
                resourceApplication.setLast_Modified(new Date());
                resourceApplicationList.add(resourceApplication);
            }
            logger.info("解析成功 " + resourceApplicationList.size() + " 条数据");
        } catch (Exception e) {
            logger.info("处理数据过程中出现错误");
            throw new RuntimeException(e);
        }
        return resourceApplicationList;
    }

    /**
     * 获取资源申请工单虚机信息
     */
    public CloudVmKafka getCloudVmKafkaMessage(ResourceApplication resourceApplication) {
        CloudVmKafka cloudVmKafka = new CloudVmKafka();
        cloudVmKafka.setResourceCode(resourceApplication.getResourceCode());
        cloudVmKafka.setBelongProduct(resourceApplication.getBelongProduct());
        cloudVmKafka.setProductToken(resourceApplication.getProductToken());
        cloudVmKafka.setAppModName(resourceApplication.getProjectName());
        cloudVmKafka.setAppModToken(resourceApplication.getProjectToken());
        cloudVmKafka.setMainIp(resourceApplication.getMainIp());
        cloudVmKafka.setSysAdminTeam(resourceApplication.getSysAdminTeam());
        if (!StringUtils.isEmpty(resourceApplication.getDeployEnv()) && resourceApplication.getDeployEnv().equals("1")) {
            cloudVmKafka.setSysAdminBackup("满开亮-18200493");
            cloudVmKafka.setSysAdministrator("王新剑-18200427");
            cloudVmKafka.setDeployAdminA(resourceApplication.getUid());
        } else {
            cloudVmKafka.setSysAdministrator(resourceApplication.getSysAdministrator());
        }
        return cloudVmKafka;
    }

    /**
     * 获取资源申请工单物理机信息
     */
    public PhysicalServer getPhysicalServerKafkaMessage(ResourceApplication resourceApplication) {
        PhysicalServer physicalServer = new PhysicalServer();
        physicalServer.setResId(IdUtils.generatedUUID());
        physicalServer.setMainIp(resourceApplication.getMainIp());
        physicalServer.setRelateIp(resourceApplication.getRelatedIp());
        physicalServer.setVirtualIp(resourceApplication.getVirtualIp());
        physicalServer.setNetworkArea(resourceApplication.getNetworkArea());
        physicalServer.setClass_Name("SYS_X86Server");
        physicalServer.setResourceCode(resourceApplication.getResourceCode());
        physicalServer.setDeployOs(resourceApplication.getSystemType());
        physicalServer.setBelongProduct(resourceApplication.getBelongProduct());
        physicalServer.setSysAdminTeam(resourceApplication.getSysAdminTeam());
        physicalServer.setDeployEnv(resourceApplication.getDeployEnv());
        if (!StringUtils.isEmpty(physicalServer.getDeployEnv()) && physicalServer.getDeployEnv().equals("1")) {
            physicalServer.setSysAdminBackup("满开亮-18200493");
            physicalServer.setSysAdministrator("王新剑-18200427");
            physicalServer.setDeployAdminA(resourceApplication.getUid());
        } else {
            physicalServer.setSysAdministrator(resourceApplication.getSysAdministrator());
        }
        physicalServer.setLast_Modified(String.valueOf(System.currentTimeMillis()));
        physicalServer.setProductToken(resourceApplication.getProductToken());
        physicalServer.setSn(resourceApplication.getSn());
        physicalServer.setAssetStatus("运行");
        physicalServer.setAppModName(resourceApplication.getProjectName());
        physicalServer.setAppModToken(resourceApplication.getProjectToken());
        physicalServer.setUsageDes(resourceApplication.getAllocOpinion());
        physicalServer.setIpAddress(resourceApplication.getMainIp());
        physicalServer.setName(resourceApplication.getMainIp());
        physicalServer.setStatus("active");
        ReserveServerKafka reserveServerKafka = reserveServerMapper.getSingleReserveServerInstanceBySn(resourceApplication.getSn());
        if (reserveServerKafka != null) {
            physicalServer.setCpuArch(reserveServerKafka.getCpuArch());
            physicalServer.setLocationCode(reserveServerKafka.getShelfPosition());
            physicalServer.setBelongCompany(reserveServerKafka.getAssetBelong());
            physicalServer.setMgmtIp(reserveServerKafka.getAllocateBMCIP());
            String cpuCores = String.valueOf(reserveServerKafka.getCpuCount() * reserveServerKafka.getCpuSize());
            String memoryCapacity = String.valueOf(reserveServerKafka.getMemCount() * reserveServerKafka.getMemSize() * 1024);
            physicalServer.setMemoryCapacity(memoryCapacity);
            physicalServer.setCpuCores(cpuCores);
            List<Integer> reserveDiskCapacity = reserveDiskMapper.selectDiskSizeByServerId(reserveServerKafka.getResId());
            if (reserveDiskCapacity.size() != 0) {
                Integer sumDiskCapacity = 0;
                for (Integer singleDiskCapacity : reserveDiskCapacity) {
                    sumDiskCapacity = sumDiskCapacity + singleDiskCapacity;
                }
                String diskCapacity = String.valueOf(sumDiskCapacity);
                physicalServer.setDiskCapacity(diskCapacity);
            }

        }
        return physicalServer;
    }

    /**
     * 获取资源申请工单宿主机信息
     */
    public PhysicalHostKafka getPhysicalHostKafkaMessage(ResourceApplication resourceApplication) {
        PhysicalHostKafka physicalHostKafka = new PhysicalHostKafka();
        physicalHostKafka.setResId(IdUtils.generatedUUID());
        physicalHostKafka.setNetworkArea(resourceApplication.getNetworkArea());
        physicalHostKafka.setVirtualIp(resourceApplication.getVirtualIp());
        physicalHostKafka.setRelatedIp(resourceApplication.getRelatedIp());
        physicalHostKafka.setClass_Name("SYS_PhysicalHost");
        physicalHostKafka.setDeployOs(resourceApplication.getSystemType());
        physicalHostKafka.setResourceCode(resourceApplication.getResourceCode());
        physicalHostKafka.setBelongProduct(resourceApplication.getBelongProduct());
        physicalHostKafka.setSysAdminTeam(resourceApplication.getSysAdminTeam());
        physicalHostKafka.setDeployEnv(resourceApplication.getDeployEnv());
        if (!StringUtils.isEmpty(physicalHostKafka.getDeployEnv()) && physicalHostKafka.getDeployEnv().equals("1")) {
            physicalHostKafka.setSysAdminBackup("满开亮-18200493");
            physicalHostKafka.setSysAdministrator("王新剑-18200427");
            physicalHostKafka.setDeployAdminA(resourceApplication.getUid());
        } else {
            physicalHostKafka.setSysAdministrator(resourceApplication.getSysAdministrator());
        }
        physicalHostKafka.setMainIp(resourceApplication.getMainIp());
        physicalHostKafka.setLast_Modified(String.valueOf(System.currentTimeMillis()));
        physicalHostKafka.setProductToken(resourceApplication.getProductToken());
        physicalHostKafka.setSn(resourceApplication.getSn());
        physicalHostKafka.setAppModName(resourceApplication.getProjectName());
        physicalHostKafka.setAppModToken(resourceApplication.getProjectToken());
        physicalHostKafka.setUsageDes(resourceApplication.getAllocOpinion());
        physicalHostKafka.setIpAddress(resourceApplication.getMainIp());
        physicalHostKafka.setName(resourceApplication.getMainIp());
        physicalHostKafka.setStatus("active");
        ReserveServerKafka reserveServerKafka = reserveServerMapper.getSingleReserveServerInstanceBySn(resourceApplication.getSn());
        if (reserveServerKafka != null) {
            physicalHostKafka.setCpuArch(reserveServerKafka.getCpuArch());
            physicalHostKafka.setLocationCode(reserveServerKafka.getShelfPosition());
            physicalHostKafka.setBelongCompany(reserveServerKafka.getAssetBelong());
            physicalHostKafka.setBmcIp(reserveServerKafka.getAllocateBMCIP());
            String cpuCores = String.valueOf(reserveServerKafka.getCpuCount() * reserveServerKafka.getCpuSize());
            String memoryCapacity = String.valueOf(reserveServerKafka.getMemCount() * reserveServerKafka.getMemSize() * 1024);
            physicalHostKafka.setTotalMemory(memoryCapacity);
            physicalHostKafka.setTotalVcpuCores(cpuCores);
            List<Integer> reserveDiskSize = reserveDiskMapper.selectDiskSizeByServerId(reserveServerKafka.getResId());
            if (reserveDiskSize.size() != 0) {
                Integer sumDiskSize = 0;
                for (Integer singleDiskSize : reserveDiskSize) {
                    sumDiskSize = sumDiskSize + singleDiskSize;
                }
                String totalDiskSizeMB = String.valueOf(sumDiskSize * 1024);
                physicalHostKafka.setTotalDiskSizeMB(totalDiskSizeMB);
            }
        }
        return physicalHostKafka;
    }

    /**
     * 获取资源申请工单库存设备信息(运行)
     */
    public ReserveServerKafka getReserveServerKafkaMessage(ResourceApplication resourceApplication) {
        ReserveServerKafka reserveServerKafka = new ReserveServerKafka();
        reserveServerKafka.setSn(resourceApplication.getSn());
        reserveServerKafka.setAllocateStatus("运行");
        return reserveServerKafka;
    }

    /**
     * 获取资源申请工单库存设备信息(已上架待分配)
     */
    public ReserveServerKafka getInitialReserveServerKafkaMessage(ResourceApplication resourceApplication) {
        ReserveServerKafka reserveServerKafka = new ReserveServerKafka();
        reserveServerKafka.setSn(resourceApplication.getSn());
        reserveServerKafka.setAllocateStatus("已上架待分配");
        return reserveServerKafka;
    }

    /**
     * 将List<String>中的元素用逗号拼接
     */
    public String generatedString(List<String> initialList) {
        //拼接工号或ip，以,号拼接
        String generatedString = "";
        if (!CollectionUtils.isEmpty(initialList)) {
            generatedString = initialList.stream().collect(Collectors.joining(","));
        }
        generatedString = generatedString.trim().replace("\"", "");
        return generatedString;
    }

    /**
     * 解析并应用Kafka资源回收工单
     */
    public void updateResourceRecycling(JSONObject requestParams) {
        int insertCount = 0;
        int updateReserveServerCount = 0;
        int insertResourceRecyclingCloudVmCount = 0;
        int insertResourceRecyclingPhysicalServerCount = 0;
        int insertResourceRecyclingPhysicalHostCount = 0;
        List<ResourceApplication> list = getConsumerResourceRecyclingMessage(requestParams);
        if (list.size() == 0) {
            logger.info("提供资源申请信息有误,没有list");
            return;
        }
        String workOrder = list.get(0).getWorkOrder();
        List<ResourceApplication> resourceApplicationList = resourceApplicationMapper.selectResourceApplicationByWorkOrder(workOrder);
        if (resourceApplicationList.size() != 0) {
            logger.info("库中已有信息, 需要回退资源回收工单");
            return;
        }
        for (ResourceApplication resourceApplication : list) {
            resourceApplicationMapper.insertSingleResourceApplicationMessage(resourceApplication);
            insertCount++;
            if (resourceApplication.getMachineType().equals("0")) {
                List<Map<String, Object>> getCloudVmList = cloudVmNovaMapper.getCloudVmByMainIp(resourceApplication.getMainIp());
                if (getCloudVmList.size() == 1) {
                    ResourceRecycling resourceRecyclingCloudVm = getResourceRecyclingCloudVm(getCloudVmList.get(0));
                    resourceRecyclingCloudVm.setWorkOrder(resourceApplication.getWorkOrder());
                    resourceApplicationMapper.insertSingleResourceRecyclingMessage(resourceRecyclingCloudVm);
                    insertResourceRecyclingCloudVmCount++;
                } else {
                    logger.info("虚机表中没有对应信息, mainIp为：" + resourceApplication.getMainIp());
                }
                continue;
            } else if (resourceApplication.getMachineType().equals("1")) {
                List<PhysicalServer> physicalServerList = physicalMachineMapper.getPhysicalServerByMainIp(resourceApplication.getMainIp());
                if (physicalServerList.size() != 1) {
                    logger.info("待回收物理机信息与表中物理机信息不一致, mainIp为：" + resourceApplication.getMainIp());
                    continue;
                }
                ResourceRecycling resourceRecyclingPhysicalServer = getResourceRecyclingPhysicalServer(physicalServerList.get(0));
                resourceRecyclingPhysicalServer.setWorkOrder(resourceApplication.getWorkOrder());
                resourceApplicationMapper.insertSingleResourceRecyclingMessage(resourceRecyclingPhysicalServer);
                physicalMachineMapper.deleteSinglePhysicalServerInstance(resourceApplication);
                if (reserveServerMapper.getSingleReserveServerInstanceBySn(resourceRecyclingPhysicalServer.getSn()) != null) {
                    ReserveServerKafka reserveServerKafka = getReserveServerResourceRecyclingMessage(resourceRecyclingPhysicalServer);
                    reserveServerMapper.updateReserveServerResourceRecycling(reserveServerKafka);
                    updateReserveServerCount++;
                } else {
                    logger.info("库存设备表中没有对应资源信息, sn号为: " + resourceRecyclingPhysicalServer.getSn());
                }
                insertResourceRecyclingPhysicalServerCount++;
            } else if (resourceApplication.getMachineType().equals("2")) {
                List<PhysicalHostKafka> physicalHostKafkaList = physicalHostMapper.getSinglePhysicalHostInstance(resourceApplication.getMainIp());
                if (physicalHostKafkaList.size() != 1) {
                    logger.info("待回收宿主机信息与表中宿主机信息不一致, mainIp为：" + resourceApplication.getMainIp());
                    continue;
                }
                ResourceRecycling resourceRecyclingPhysicalHost = getResourceRecyclingPhysicalHost(physicalHostKafkaList.get(0));
                resourceRecyclingPhysicalHost.setWorkOrder(resourceApplication.getWorkOrder());
                resourceApplicationMapper.insertSingleResourceRecyclingMessage(resourceRecyclingPhysicalHost);
                physicalHostMapper.deleteSinglePhysicalHostInstance(resourceApplication);
                if (reserveServerMapper.getSingleReserveServerInstanceBySn(resourceRecyclingPhysicalHost.getSn()) != null) {
                    ReserveServerKafka reserveServerKafka = getReserveServerResourceRecyclingMessage(resourceRecyclingPhysicalHost);
                    reserveServerMapper.updateReserveServerResourceRecycling(reserveServerKafka);
                    updateReserveServerCount++;
                } else {
                    logger.info("库存设备表中没有对应资源信息, sn号为: " + resourceRecyclingPhysicalHost.getSn());
                }
                insertResourceRecyclingPhysicalHostCount++;
            } else {
                logger.info("提供资源回收信息有误");
            }
        }
        logger.info("落库 " + insertCount + " 条数据");
        logger.info("更新库存设备 " + updateReserveServerCount + " 条数据");
        logger.info("资源回收新增 " + insertResourceRecyclingCloudVmCount + " 条虚机数据; " +
                insertResourceRecyclingPhysicalServerCount + " 条物理机数据; " +
                insertResourceRecyclingPhysicalHostCount + " 条宿主机数据。");
    }

    /**
     * 解析Kafka资源回收工单
     */
    public List<ResourceApplication> getConsumerResourceRecyclingMessage(JSONObject serverRecycleInfo) {
        List<ResourceApplication> resourceRecyclingList = new ArrayList<>();
        JSONObject requestParams = (JSONObject) serverRecycleInfo.get("serverRecycleInfo");

        List<Map> requestParamsList = (List<Map>) requestParams.get("list");
        List<Map<String, Object>> list = new ArrayList<>();
        requestParamsList.stream().forEach(pb -> {
            Map<String, Object> rightMap = (Map<String, Object>) pb;
            list.add(rightMap);
        });

        try {
            for (Map<String, Object> listMap : list) {
                Map<String, Object> newMap = new HashMap<>();
                //工单信息
                newMap.put("resId", IdUtils.generatedUUID());
                newMap.put("workOrder", requestParams.get("workOrder"));
                newMap.put("uid", requestParams.get("uid"));
                newMap.put("deployEnv", requestParams.get("environment"));

                //资源ip
                newMap.put("mainIp", listMap.get("ip"));

                //资源信息
                newMap.put("sn", listMap.get("sn"));
                newMap.put("machineType", listMap.get("machineType"));
                newMap.put("hostSpecs", listMap.get("hostSpecs"));
                newMap.put("storageNum", listMap.get("storageNum"));
                newMap.put("bmsNetAreaNo", listMap.get("bmsNetAreaNo"));
                newMap.put("bmsWwnNo", listMap.get("bmsWwnNo"));
                newMap.put("isApply", "0");

                //map转换成java实体类
                JSONObject jsonObject = new JSONObject(newMap);
                ResourceApplication resourceApplication = jsonObject.toJavaObject(ResourceApplication.class);
                resourceApplication.setLast_Modified(new Date());
                resourceRecyclingList.add(resourceApplication);
            }
            logger.info("解析成功 " + resourceRecyclingList.size() + " 条数据");
        } catch (Exception e) {
            logger.info("处理数据过程中出现错误");
            throw new RuntimeException(e);
        }
        return resourceRecyclingList;
    }

    /**
     * 获取资源回收工单物理机信息
     */
    public PhysicalServer getPhysicalServerResourceRecyclingMessage(ResourceApplication resourceApplication) {
        PhysicalServer physicalServer = new PhysicalServer();
        physicalServer.setAssetStatus("下线");
        physicalServer.setStatus("offline");
        physicalServer.setLocationCode(null);
        physicalServer.setSn(null);
        physicalServer.setMainIp(resourceApplication.getMainIp());
        return physicalServer;
    }

    /**
     * 获取资源回收工单宿主机信息
     */
    public PhysicalHostKafka getPhysicalHostResourceRecyclingMessage(ResourceApplication resourceApplication) {
        PhysicalHostKafka physicalHostKafka = new PhysicalHostKafka();
        physicalHostKafka.setStatus("offline");
        physicalHostKafka.setLocationCode(null);
        physicalHostKafka.setSn(null);
        physicalHostKafka.setMainIp(resourceApplication.getMainIp());
        return physicalHostKafka;
    }

    /**
     * 获取资源回收工单库存设备信息(已上架待分配)
     */
    public ReserveServerKafka getReserveServerResourceRecyclingMessage(ResourceRecycling resourceRecycling) {
        ReserveServerKafka reserveServerKafka = new ReserveServerKafka();
        reserveServerKafka.setResAllocationCode("");
        reserveServerKafka.setSn(resourceRecycling.getSn());
        reserveServerKafka.setAllocateStatus("已上架待分配");
        return reserveServerKafka;
    }

    /**
     * 虚机资源回收信息
     */
    public ResourceRecycling getResourceRecyclingCloudVm(Map<String, Object> cloudVm) {
        cloudVm.put("cpuCores", cloudVm.get("cpuCoreNum"));
        cloudVm.put("memoryCapacity", cloudVm.get("memorySize"));
        cloudVm.put("last_Modified", new Date());
        JSONObject jsonObject = new JSONObject(cloudVm);
        ResourceRecycling resourceRecycling = jsonObject.toJavaObject(ResourceRecycling.class);
        return resourceRecycling;
    }

    /**
     * 物理机资源回收信息
     */
    public ResourceRecycling getResourceRecyclingPhysicalServer(PhysicalServer physicalServer) {
        ResourceRecycling resourceRecycling = new ResourceRecycling();
        BeanUtils.copyProperties(physicalServer, resourceRecycling);
        resourceRecycling.setRelatedIp(physicalServer.getRelateIp());
        resourceRecycling.setLast_Modified(new Date());
        return resourceRecycling;
    }

    /**
     * 宿主机资源回收信息
     */
    public ResourceRecycling getResourceRecyclingPhysicalHost(PhysicalHostKafka physicalHostKafka) {
        ResourceRecycling resourceRecycling = new ResourceRecycling();
        BeanUtils.copyProperties(physicalHostKafka, resourceRecycling);
        resourceRecycling.setCpuCores(physicalHostKafka.getTotalVcpuCores());
        resourceRecycling.setMemoryCapacity(physicalHostKafka.getTotalMemory());
        resourceRecycling.setDiskCapacity(physicalHostKafka.getTotalDiskSizeMB());
        resourceRecycling.setLast_Modified(new Date());
        return resourceRecycling;
    }

    public List<ResourceApplication> getExportResourceApplicationInfo(List<String> resIdList) {
        List<ResourceApplication> resourceApplicationList = new ArrayList<>();
        ResourceApplication resourceApplication = new ResourceApplication();
        if (resIdList.size() == 0) {
            resourceApplicationList = resourceApplicationMapper.getAllInstance(resourceApplication);
        } else {
            for (String resId : resIdList) {
                resourceApplication = resourceApplicationMapper.getResourceApplicationInfoByResId(resId);
                resourceApplicationList.add(resourceApplication);
            }
        }
        return resourceApplicationList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateShelfPositionByResourceApplication() {
        int count = 0;
        List<ResourceApplication> resourceApplicationList = resourceApplicationMapper.getAllInstance(new ResourceApplication());

        for (ResourceApplication resourceApplication : resourceApplicationList) {
            if (!StringUtils.isEmpty(resourceApplication.getSn()) && resourceApplication.getMachineType().equals("1")) {
                PhysicalServer physicalServer = new PhysicalServer();
                List<PhysicalServer> originalPhysicalServerList = physicalMachineMapper.getPhysicalServerByMainIp(resourceApplication.getMainIp());
                if (originalPhysicalServerList.size() != 1) {
                    throw new RuntimeException("物理机数据有误");
                }
                PhysicalServer originalPhysicalServer = originalPhysicalServerList.get(0);
                String locationCode = originalPhysicalServer.getLocationCode();
                ReserveServerKafka reserveServerKafka = reserveServerMapper.getSingleReserveServerInstanceBySn(resourceApplication.getSn());
                if (reserveServerKafka == null) {
                    continue;
                }
                if (!StringUtils.isEmpty(reserveServerKafka.getShelfPosition()) && StringUtils.isEmpty(locationCode)) {
                    physicalServer.setLocationCode(reserveServerKafka.getShelfPosition());
                    physicalServer.setMainIp(resourceApplication.getMainIp());
                    physicalMachineMapper.updateSinglePhysicalServerInstance(physicalServer);
                    count++;
                    //日志
                    AsyncManager.me().execute(AsyncFactory.recordOper(originalPhysicalServer.getResId(),
                            "SYS_X86Server", originalPhysicalServer.getName(), "resManage",
                            2, "locationCode", locationCode, physicalServer.getLocationCode(),
                            0));
                }
            }
        }
        logger.info("更新" + count + "台物理机位置信息");
    }


    @Transactional(rollbackFor = Exception.class)
    public void deletePhysicalServerByResourceApplication(List<String> workOrderList) {
        int count = 0;
        List<ResourceApplication> resourceApplicationList = new ArrayList<>();
        if (workOrderList.size() == 0) {
            throw new IllegalArgumentException("必须传工单号");
        }
        for (String workOrder : workOrderList) {
            resourceApplicationList = resourceApplicationMapper.selectResourceApplicationByWorkOrder(workOrder);
            if (resourceApplicationList != null) {
                for (ResourceApplication resourceApplication : resourceApplicationList) {
                    physicalMachineMapper.deleteSinglePhysicalServerInstance(resourceApplication);
                    count++;
                }
            }
            resourceApplicationMapper.deleteResourceApplication(workOrder);
        }
        logger.info("手动删除" + count + "台物理机信息");
    }

    @Transactional(rollbackFor = Exception.class)
    public void executePhysicalServerResourceRecycling(ResourceApplication resourceApplication) {
        List<PhysicalServer> physicalServerList = physicalMachineMapper.getPhysicalServerByMainIp(resourceApplication.getMainIp());
        if (physicalServerList.size() != 1) {
            throw new IllegalArgumentException("待回收物理机信息与表中物理机信息不一致, mainIp为："
                    + resourceApplication.getMainIp());
        }

        ResourceRecycling resourceRecyclingPhysicalServer = getResourceRecyclingPhysicalServer(physicalServerList.get(0));
        resourceRecyclingPhysicalServer.setWorkOrder(resourceApplication.getWorkOrder());
        System.out.println(resourceRecyclingPhysicalServer);
        if (resourceApplicationMapper.getResourceRecyclingInfoByWorkOrder(resourceRecyclingPhysicalServer) == null) {
            resourceApplicationMapper.insertSingleResourceRecyclingMessage(resourceRecyclingPhysicalServer);
        } else {
            throw new IllegalArgumentException("该物理机信息已回收，请勿重复回收");
        }

        physicalMachineMapper.deleteSinglePhysicalServerInstance(resourceApplication);
        if (reserveServerMapper.getSingleReserveServerInstanceBySn(resourceRecyclingPhysicalServer.getSn()) != null) {
            ReserveServerKafka reserveServerKafka = getReserveServerResourceRecyclingMessage(resourceRecyclingPhysicalServer);
            reserveServerMapper.updateReserveServerResourceRecycling(reserveServerKafka);
        } else {
            logger.info("库存设备表中没有对应资源信息, sn号为: " + resourceRecyclingPhysicalServer.getSn());
        }
    }
}
