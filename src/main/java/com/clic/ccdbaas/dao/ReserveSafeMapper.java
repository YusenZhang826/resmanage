package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ReserveSafe;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReserveSafeMapper extends BaseMapper<ReserveSafe> {
    List<ReserveSafe> getAllInstance(ReserveSafe reserveSafe);
}