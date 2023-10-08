package com.clic.ccdbaas.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Mapper
public interface RelationMapper {
    String getResIdByClassName(HashMap<String, String> map);

    void addRelation(HashMap<String, Object> map);

    void addRelationInstance(HashMap<String, Object> map);

    void updateRelation(HashMap<String, Object> map);

    String getResIdByInstanceId(HashMap<String, Object> map);

    void updateRelationInstance(HashMap<String, Object> map);

    List<String> getRemarkByResourceInstance(String sourceInstanceId);

    List<HashMap> getRelationBySingleClassName(String className);

    List<HashMap> getInstanceByTarget(HashMap map);

    List<HashMap> getInstanceBySource(HashMap map);

    List<String> getRelationResIdByClassName(String className);

    HashMap getRelationByClassName(HashMap<String, String> map);

    List<String> getExtraSpecBySourceIdAndTargetClassName(HashMap map);

    List<HashMap> getInstancesByRelationId(List relationId);

    void deleteInstanceByResId(String resId);

    List<HashMap> getInstancesByRelationIdAndExtraSpec(HashMap<String, Object> map);

    List<HashMap> getAllRelationInstance();

    Set<String> getResourceClassName(@Param("columnName") String columnName);

    String getResIdByRelationName(String relationName);

}
