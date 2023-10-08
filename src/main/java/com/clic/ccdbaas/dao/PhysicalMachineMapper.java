package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.PhysicalMachine;
import com.clic.ccdbaas.entity.PhysicalServer;
import com.clic.ccdbaas.entity.ResourceApplication;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface PhysicalMachineMapper {
    List<PhysicalMachine> getAllInstance(PhysicalMachine physicalmachine);

    List<HashMap> countInstanceByStatus(PhysicalMachine physicalMachine);

    void updateAdministrator(Map<String, String> map);

    void updatebackupAdministrator(Map<String, String> map);

    void updateStakeholder(Map<String, String> map);

    int getPhysicalCount();

    void updateFindResource(Map json);

    Map<String,String> getPhysicalInfo(String resId);

    PhysicalMachine getPhysicalInfoByResId(String resId);

    void updateUsageDes(String usageDes,String resId);


    int insert(PhysicalServer physicalServer);

    List<PhysicalServer> getPhysicalServerByMainIp(String mainIp);

    int insertSinglePhysicalServerInstance(PhysicalServer physicalServer);

    List<HashMap> getResIdByIp(String ip);

    PhysicalMachine getPhysicalServerByResId(String resId);

    int updateSinglePhysicalServerInstance(PhysicalServer physicalServer);

    int deleteSinglePhysicalServerInstance(ResourceApplication resourceApplication);

    int updateSinglePhysicalServerInstanceRecycling(PhysicalServer physicalServer);

    PhysicalServer selectSinglePhysicalServerInstanceRecycling(ResourceApplication resourceApplication);

    List<PhysicalServer> getPhysicalServerByLocationCode(String locationCode);

    int updatePhysicalServerInstanceByLocationCode(PhysicalServer physicalServer);

    int deletePhysicalServerByLocationCode(PhysicalMachine physicalMachine);
}
