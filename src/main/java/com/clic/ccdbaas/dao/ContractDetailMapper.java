package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ContractDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractDetailMapper {
    int deleteByPrimaryKey(String resId);

    int insert(ContractDetail record);

    int insertSelective(ContractDetail record);

    ContractDetail selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(ContractDetail record);

    int updateByPrimaryKey(ContractDetail record);

    List<ContractDetail> getAllInstance(ContractDetail record);

}