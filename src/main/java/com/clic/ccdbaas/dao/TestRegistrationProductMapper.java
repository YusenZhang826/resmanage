package com.clic.ccdbaas.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.clic.ccdbaas.entity.TestRegistrationProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface TestRegistrationProductMapper extends BaseMapper<TestRegistrationProduct> {
    List<TestRegistrationProduct> getAllInstance(TestRegistrationProduct testRegistrationProduct);

    void addVmProduct(HashMap<String, Object> map);

    void addPhysicalProduct(HashMap<String, Object> map);

    List<Map<String, Object>> getAuthorizeProduct();
}
