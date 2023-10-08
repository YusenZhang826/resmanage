package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.HcsRegionSummaryCapacity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HcsRegionSummaryCapacityMapper {
    int deleteByPrimaryKey(@Param("regionId") String regionId, @Param("dimensionType") String dimensionType);

    int insert(HcsRegionSummaryCapacity record);

    int insertSelective(HcsRegionSummaryCapacity record);

    HcsRegionSummaryCapacity selectByPrimaryKey(@Param("regionId") String regionId, @Param("dimensionType") String dimensionType);

    int updateByPrimaryKeySelective(HcsRegionSummaryCapacity record);

    int updateByPrimaryKey(HcsRegionSummaryCapacity record);

    List<Map> getRegionStorageInfoTmp(Map params);
}