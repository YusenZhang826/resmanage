package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.CloudVmare;
import com.clic.ccdbaas.entity.DockerEnv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface DockerEnvMapper {
    int deleteByPrimaryKey(String resId);

    int insert(DockerEnv record);

    int insertSelective(DockerEnv record);

    DockerEnv selectByPrimaryKey(String resId);
    List<DockerEnv> getAllInstance();
    List<CloudVmare> getEnvHost(@Param("mainIpArr") List<String> mainIpArr);


    int updateByPrimaryKeySelective(DockerEnv record);

    int updateByPrimaryKey(DockerEnv record);
}