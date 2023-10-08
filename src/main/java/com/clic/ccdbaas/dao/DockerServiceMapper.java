package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.DockerEnv;
import com.clic.ccdbaas.entity.DockerService;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DockerServiceMapper {
    int deleteByPrimaryKey(String resId);

    int insert(DockerService record);

    int insertSelective(DockerService record);

    List<DockerService> getAllInstance();


    DockerService selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(DockerService record);

    int updateByPrimaryKey(DockerService record);
}