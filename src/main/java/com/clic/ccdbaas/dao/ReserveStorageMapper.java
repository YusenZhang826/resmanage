package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ReserveStorage;
import com.clic.ccdbaas.entity.example.ReserveStorageExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveStorageMapper extends BaseMapper<ReserveStorage> {
    int countByExample(ReserveStorageExample example);

    int deleteByExample(ReserveStorageExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ReserveStorage record);

    int insertSelective(ReserveStorage record);

    List<ReserveStorage> selectByExample(ReserveStorageExample example);

    ReserveStorage selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ReserveStorage record, @Param("example") ReserveStorageExample example);

    int updateByExample(@Param("record") ReserveStorage record, @Param("example") ReserveStorageExample example);

    int updateByPrimaryKeySelective(ReserveStorage record);

    int updateByPrimaryKey(ReserveStorage record);
}