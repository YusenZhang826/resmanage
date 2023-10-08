package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.EquipmentContract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EquipmentContractMapper {
    int deleteByPrimaryKey(String resId);

    void batchDelete(@Param("resIdArr") List<String> resIdArr);

    int insert(EquipmentContract record);

    int insertSelective(EquipmentContract record);

    EquipmentContract selectByPrimaryKey(String resId);

    List<EquipmentContract> getAllInstance(EquipmentContract record);

    int updateByPrimaryKeySelective(EquipmentContract record);

    int updateByPrimaryKey(EquipmentContract record);
}