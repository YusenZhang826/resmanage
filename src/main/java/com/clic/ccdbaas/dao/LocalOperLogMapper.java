package com.clic.ccdbaas.dao;


import com.clic.ccdbaas.entity.LocalOperLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface LocalOperLogMapper {



    void insertOperlog(LocalOperLog sysOperLog);

    List<LocalOperLog> selectLogListByParam(LocalOperLog localOperLog);

}
