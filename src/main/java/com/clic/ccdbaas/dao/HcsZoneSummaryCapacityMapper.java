package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.HcsZoneSummaryCapacity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface HcsZoneSummaryCapacityMapper {
    int deleteByPrimaryKey(@Param("azoneId") String azoneId, @Param("dimensionType") String dimensionType);

    int insert(HcsZoneSummaryCapacity record);

    int insertSelective(HcsZoneSummaryCapacity record);

    HcsZoneSummaryCapacity selectByPrimaryKey(@Param("azoneId") String azoneId, @Param("dimensionType") String dimensionType);

    int updateByPrimaryKeySelective(HcsZoneSummaryCapacity record);

    int updateByPrimaryKey(HcsZoneSummaryCapacity record);
}