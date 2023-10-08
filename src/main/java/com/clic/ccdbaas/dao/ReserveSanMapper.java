package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ReserveSan;
import com.clic.ccdbaas.entity.example.ReserveSanExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveSanMapper extends BaseMapper<ReserveSan> {
    int countByExample(ReserveSanExample example);

    int deleteByExample(ReserveSanExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ReserveSan record);

    int insertSelective(ReserveSan record);

    List<ReserveSan> selectByExample(ReserveSanExample example);

    ReserveSan selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ReserveSan record, @Param("example") ReserveSanExample example);

    int updateByExample(@Param("record") ReserveSan record, @Param("example") ReserveSanExample example);

    int updateByPrimaryKeySelective(ReserveSan record);

    int updateByPrimaryKey(ReserveSan record);
}