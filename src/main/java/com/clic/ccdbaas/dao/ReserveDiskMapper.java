package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ReserveDisk;
import com.clic.ccdbaas.entity.example.ReserveDiskExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReserveDiskMapper {
    int countByExample(ReserveDiskExample example);

    int deleteByExample(ReserveDiskExample example);

    int deleteByPrimaryKey(String resId);

    int insert(ReserveDisk record);

    int insertSelective(ReserveDisk record);

    List<ReserveDisk> selectByExample(ReserveDiskExample example);

    ReserveDisk selectByPrimaryKey(String resId);

    int updateByExampleSelective(@Param("record") ReserveDisk record, @Param("example") ReserveDiskExample example);

    int updateByExample(@Param("record") ReserveDisk record, @Param("example") ReserveDiskExample example);

    int updateByPrimaryKeySelective(ReserveDisk record);

    int updateByPrimaryKey(ReserveDisk record);

    List<Integer> selectDiskSizeByServerId(String serverId);
}