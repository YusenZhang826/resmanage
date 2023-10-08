package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.HcsRegionSummaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HcsRegionSummaryInfoMapper {
    int deleteByPrimaryKey(String regionId);

    int insert(HcsRegionSummaryInfo record);

    int insertSelective(HcsRegionSummaryInfo record);

    HcsRegionSummaryInfo selectByPrimaryKey(String regionId);

    int updateByPrimaryKeySelective(HcsRegionSummaryInfo record);

    int updateByPrimaryKey(HcsRegionSummaryInfo record);

    List<Map> getRegionSummaryInfoTmp(Map params);
}