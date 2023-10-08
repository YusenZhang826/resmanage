package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.DockerEnv;
import com.clic.ccdbaas.entity.DockerProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DockerProjectMapper {
    int deleteByPrimaryKey(String resId);

    int insert(DockerProject record);

    List<DockerProject> getAllInstance();

    int insertSelective(DockerProject record);

    DockerProject selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(DockerProject record);

    int updateByPrimaryKey(DockerProject record);
}