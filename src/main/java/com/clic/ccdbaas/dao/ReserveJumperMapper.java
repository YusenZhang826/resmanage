package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ReserveJumper;
import com.clic.ccdbaas.entity.example.ReserveJumperExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveJumperMapper {
    int countByExample(ReserveJumperExample example);

    int deleteByExample(ReserveJumperExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ReserveJumper record);

    int insertSelective(ReserveJumper record);

    List<ReserveJumper> selectByExample(ReserveJumperExample example);

    ReserveJumper selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ReserveJumper record, @Param("example") ReserveJumperExample example);

    int updateByExample(@Param("record") ReserveJumper record, @Param("example") ReserveJumperExample example);

    int updateByPrimaryKeySelective(ReserveJumper record);

    int updateByPrimaryKey(ReserveJumper record);
}