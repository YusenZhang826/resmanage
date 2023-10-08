package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.RegistrationProduct;
import com.clic.ccdbaas.entity.RegistrationProductExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface RegistrationProductMapper extends BaseMapper<RegistrationProduct> {
    int countByExample(RegistrationProductExample example);

    int deleteByExample(RegistrationProductExample example);

    int deleteByPrimaryKey(String resid);

    int insert(RegistrationProduct record);

    int insertSelective(RegistrationProduct record);

    List<RegistrationProduct> selectByExample(RegistrationProductExample example);

    RegistrationProduct selectByPrimaryKey(String resid);

    int updateByExampleSelective(@Param("record") RegistrationProduct record, @Param("example") RegistrationProductExample example);

    int updateByExample(@Param("record") RegistrationProduct record, @Param("example") RegistrationProductExample example);

    int updateByPrimaryKeySelective(RegistrationProduct record);

    int updateByPrimaryKey(RegistrationProduct record);
    List<RegistrationProduct> getAllInstance(RegistrationProduct record);
    List<Map> getProductCountInfo(RegistrationProduct record);

    int updateByProductToken(RegistrationProduct record);
}