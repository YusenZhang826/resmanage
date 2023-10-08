package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.ObTenant;
import com.clic.ccdbaas.entity.ObTenantExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ObTenantMapper extends BaseMapper {
  //  int countByExample(ObTenantExample example);

  //  int deleteByExample(ObTenantExample example);

  //  int deleteByPrimaryKey(Integer id);

    int insert(ObTenant record);


  //  int insertSelective(ObTenant record);

    List<ObTenant> selectByExample(ObTenantExample example);
    List<ObTenant> getAllInstance(ObTenant example);

    List<Map> getInstanceByResId(String resId);

    ObTenant selectByPrimaryKey(Integer id);

   // int updateByExampleSelective(@Param("record") ObTenant record, @Param("example") ObTenantExample example);

 //   int updateByExample(@Param("record") ObTenant record, @Param("example") ObTenantExample example);

  //  int updateByPrimaryKeySelective(ObTenant record);

    int updateByPrimaryKey(ObTenant record);
}