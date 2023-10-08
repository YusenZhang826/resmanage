package com.clic.ccdbaas.dao;


import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.kafka.CloudVmKafka;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;


@Mapper
public interface CloudVmNovaMapper {

    List<CloudVmare> getAllInstance(CloudVmare cloudVmare);

    List<CloudVmare> getMergeInstance(CloudVmare cloudVmare);

    List<CloudVmare> getAllCloudInstance();

    List<CloudVmare> getNullBelongProductList(CloudVmare cloudVmare);

    List<HashMap> countInstanceByStatus(CloudVmare cloudVmare);

    List<HashMap> countInstanceBydeployenv(CloudVmare cloudVmare);

    List<HashMap> countPhysicalServerByDeployEnv(CloudVmare cloudVmare);

    List<HashMap> countPhysicalHostByDeployEnv(CloudVmare CloudVmare);

    List<HashMap> countMergeInstanceByDeployEnv(CloudVmare cloudVmare);

    List<HashMap> countInstanceByStatusGroudByAzoneName(CloudVmare cloudVmare);

    List<HashMap> countMergeInstanceByStatus(CloudVmare cloudVmare);

    void updateAdministrator(Map<String, String> map);

    void saveResCountInfo();

    void saveResTrendInfo();
    void saveDbTrendInfo();

    void updatebackupAdministrator(Map<String, String> map);

    void updateStakeholder(Map<String, String> map);

    int countNonCompliantZabbixStatus();

    int countNonCompliantHidsStatus();

    int countNonCompliantMainIp();

    int countNonCompliantDeployEnv();

    int countAttrNullActive(@Param("params") Map params);

    int countAttrNull(@Param("params") Map params);


    int getCloudVmareCount();

    int getVdeskVMCount();
    int getSafeEquipmentCount();
    int getBmsCount();

    void updateFindResource(Map json);

    Map<String, String> getCloudVmareInfo(String resid);

    List<Map> getCloudVMByResIds(@Param("resIds") Set<String> resIds);

    List<Map> getResCountInfoByClassName(@Param("className") String className);
    Map getResCountByDate(@Param("paramValue") Date paramValue);
    List<Map> getProductHostNumInfo(@Param("teamName") String teamName);

    CloudVmare getCloudVMInfoByResId(String resId);

    void updateUsageDes(String usageDes, String resId);

    List<HashMap> getResIdByIp(String ip);

    int updateCloudVmKafka(CloudVmKafka cloudVmKafka);

    List<Map<String, Object>> getCloudVmByMainIp(String mainIp);

    void testDelete();

    List<Map> getRegionNameOfMergedInstance();

    List<Map> getAllTrendName();

    List<Map> getTrendDetailInfo();

    List<Map> getAllResDefModel();

    int getAllResCount(@Param("resModelArr") List<Map>resModelArr);

    List<CloudVmare> getAllInstanceExceptCloudDesktop();

    int insertSingleCloudVmInstance(CloudVmare cloudVmware);

    int updateSingleCloudVmInstance(CloudVmare cloudVmware);

    int deleteSingleCloudVmInstance(CloudVmare cloudVmware);

    List<Map<String, Object>> getProjectInfoByBizRegionName(String bizRegionName);

    List<CloudVmare> getCloudVmInstanceByProjectInfo(String projectId, String projectName);

    int updateSingleCloudVmInfoByNativeId(CloudVmare cloudVmware);
}
