package com.clic.ccdbaas.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ResourceTypeMapper {
    List<Map> selectAllResourceType();

    String selectEntityClassName(String resourceId);

    String getDBTableNameById(String resourceTypeId);

    int insertResourceType(Map<String, String> updateInfo);

    String selectNameByClassName(String className);

    String selectOcClassName(String resourceId);

    Map selectEntityByRId(String resId);

}
