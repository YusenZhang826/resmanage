package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.VmSummaryInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VmSummaryInfoMapper {
    int insert(VmSummaryInfo record);

    int insertSelective(VmSummaryInfo record);
}