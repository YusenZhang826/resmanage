package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.RelationDb;
import com.clic.ccdbaas.entity.RelationDbExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RelationDbMapper extends BaseMapper {
    int countByExample(RelationDbExample example);

    int deleteByExample(RelationDbExample example);

    int deleteByPrimaryKey(String resid);

    int insert(RelationDb record);

    int insertSelective(RelationDb record);

    List<RelationDb> selectByExample(RelationDbExample example);
    List<RelationDb> getAllInstance(RelationDb relationDb);

    RelationDb selectByPrimaryKey(String resid);

    int updateByExampleSelective(@Param("record") RelationDb record, @Param("example") RelationDbExample example);

    int updateByExample(@Param("record") RelationDb record, @Param("example") RelationDbExample example);

    int updateByPrimaryKeySelective(RelationDb record);

    int updateByPrimaryKey(RelationDb record);
}