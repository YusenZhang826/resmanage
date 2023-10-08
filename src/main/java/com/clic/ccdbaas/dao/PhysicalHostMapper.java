package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.PhysicalHost;
import com.clic.ccdbaas.entity.kafka.PhysicalHostKafka;
import com.clic.ccdbaas.entity.ResourceApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface PhysicalHostMapper {
    void updateAdministrator(Map<String, String> map);

    void updatebackupAdministrator(Map<String, String> map);

    void updateStakeholder(Map<String, String> map);

    List<PhysicalHost> getAllInstance(PhysicalHost physicalHost);

    List<HashMap> countHostInstanceByStatus();

    int getPhysicalHostCount();

    void updateFindResource(Map json);

    List<CloudVmare> getCloudVmByResId(String resId);

    Map<String, String> getPhysicalHostInfo(String resid);

    int insert(PhysicalHost physicalHost);

    List<HashMap> countInstanceByStatus(PhysicalHost physicalHost);

    List<PhysicalHostKafka> getSinglePhysicalHostInstance(String mainIp);

    List<HashMap> getResIdByIp(String ip);

    PhysicalHost getPhysicalHostByResId(String resId);

    int insertSinglePhysicalHostInstance(PhysicalHostKafka physicalHostKafka);

    int updateSinglePhysicalHostInstance(PhysicalHostKafka physicalHostKafka);

    int deleteSinglePhysicalHostInstance(ResourceApplication resourceApplication);

    int updateSinglePhysicalHostInstanceRecycling(PhysicalHostKafka physicalHostKafka);

    PhysicalHostKafka selectSinglePhysicalHostInstanceRecycling(ResourceApplication resourceApplication);

    List<PhysicalHost> getAllInstanceExceptCloudDesktop();

    int insertPhysicalHostInstance(PhysicalHost physicalHost);

    int updatePhysicalHostInstance(PhysicalHost physicalHost);

    int deletePhysicalHostInstance(PhysicalHost physicalHost);

    PhysicalHost getPhysicalHostByIpAndLocation(PhysicalHost physicalHost);

    PhysicalHost getPhysicalHostByMainIp(PhysicalHost physicalHost);
}
