package com.clic.ccdbaas.dao;

import com.clic.ccdbaas.entity.DockerService;
import com.clic.ccdbaas.entity.DockerStack;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DockerStackMapper {
    int deleteByPrimaryKey(String resId);

    int insert(DockerStack record);

    int insertSelective(DockerStack record);

    List<DockerStack> getAllInstance();


    DockerStack selectByPrimaryKey(String resId);

    int updateByPrimaryKeySelective(DockerStack record);

    int updateByPrimaryKey(DockerStack record);
}