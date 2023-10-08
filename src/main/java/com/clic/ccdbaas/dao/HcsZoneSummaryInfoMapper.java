package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.HcsZoneSummaryInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HcsZoneSummaryInfoMapper {
    int deleteByPrimaryKey(String azoneId);

    int insert(HcsZoneSummaryInfo record);

    int insertSelective(HcsZoneSummaryInfo record);

    HcsZoneSummaryInfo selectByPrimaryKey(String azoneId);

    int updateByPrimaryKeySelective(HcsZoneSummaryInfo record);

    int updateByPrimaryKey(HcsZoneSummaryInfo record);

    List<Map>getRegionOverviewInfo(Map params);
    List<HcsZoneSummaryInfo>getRegionAzoneInfo(HcsZoneSummaryInfo hcsZoneSummaryInfo);

    List<Map>getRegionStorageInfo(Map params);

    List<Map>getClusterSummaryInfo(Map params);


}