package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.EquipmentContract;
import com.clic.ccdbaas.entity.MaintenanceContract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MaintenanceContractMapper {
    int deleteByPrimaryKey(String resId);

    int insert(MaintenanceContract record);

    int insertSelective(MaintenanceContract record);

    MaintenanceContract selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(MaintenanceContract record);

    int updateByPrimaryKey(MaintenanceContract record);

    List<MaintenanceContract> getAllInstance(MaintenanceContract record);
    void batchDelete(@Param("resIdArr") List<String> resIdArr);
}