package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.ObCluster;
import com.clic.ccdbaas.entity.ObClusterExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ObClusterMapper {
    int countByExample(ObClusterExample example);

    int deleteByExample(ObClusterExample example);

    int deleteByPrimaryKey(String id);

    int insert(ObCluster record);

    int insertSelective(ObCluster record);

    List<ObCluster> selectByExample(ObClusterExample example);
    List<ObCluster> getAllInstance(ObCluster example);

    ObCluster selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ObCluster record, @Param("example") ObClusterExample example);

    int updateByExample(@Param("record") ObCluster record, @Param("example") ObClusterExample example);

    int updateByPrimaryKeySelective(ObCluster record);

    int updateByPrimaryKey(ObCluster record);

    List<Map> selectAllOcpInfo(@Param("systemType") String systemType);
}