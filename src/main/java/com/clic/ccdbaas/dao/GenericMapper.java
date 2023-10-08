package com.clic.ccdbaas.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GenericMapper {
    List<Map<String, Object>> selectPublicItemList(@Param(value = "sqlStr") String sqlStr);

    List<String> selectPublicItemPKList(@Param("sqlStr") String sqlStr);
}
