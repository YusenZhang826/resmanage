package com.clic.ccdbaas.service;

import com.alibaba.fastjson.JSON;
import com.clic.ccdbaas.dao.GenericMapper;
import com.clic.ccdbaas.dao.ResourceSearchMapper;
import com.clic.ccdbaas.utils.excel.EasyExcelUtil;
import com.clic.ccdbaas.utils.excel.ExcelUtil;
import com.clic.ccdbaas.utils.sql.AdvancedSearch;
import com.clic.ccdbaas.utils.sql.CountByGroupSearch;
import com.clic.ccdbaas.utils.sql.CountInfo;
import com.clic.ccdbaas.utils.sql.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class ResourceSearchService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceSearchService.class);
    @Autowired
    GenericMapper genericMapper;
    @Autowired
    ResourceSearchMapper resourceSearchMapper;


    public List<Map<String, Object>> advancedSearchInstance(AdvancedSearch advancedSearch) {
        advancedSearch.validate();
        return resourceSearchMapper.selectByAdvancedSearch(advancedSearch);
    }

    public void advancedExport(HttpServletResponse httpServletResponse, AdvancedSearch advancedSearch) {
        Class clazz = SqlUtil.getClass(advancedSearch.getClassName());
        List mapList = advancedSearchInstance(advancedSearch);
        List objectList = JSON.parseArray(JSON.toJSONString(mapList), clazz);
        ExcelUtil util = new ExcelUtil<>(clazz);
        util.exportExcel(httpServletResponse, objectList, advancedSearch.getClassName());
    }

    public void advancedExportByEasyExcel(HttpServletResponse httpServletResponse, AdvancedSearch advancedSearch) {
        Class clazz = SqlUtil.getClass(advancedSearch.getClassName());
        List mapList = advancedSearchInstance(advancedSearch);
        List objectList = JSON.parseArray(JSON.toJSONString(mapList), clazz);
        EasyExcelUtil.getInstance().exportExcel(httpServletResponse, clazz, objectList, advancedSearch.getClassName(), advancedSearch.getClassName());
    }

    public List<CountInfo> countSearchInstance(CountByGroupSearch countByGroupSearch) {
        countByGroupSearch.validate();
        return countByGroupSearch.getCountInfo(resourceSearchMapper.countByGroupSearch(countByGroupSearch));
    }

    public Class getResourceClassInfo(String className) {
        Class clazz;
        try {
            clazz = Class.forName("com.clic.ccdbaas.entity." + className);
        } catch (Exception e) {
            throw new RuntimeException("获取目标类型失败，请正确填写ClassName");
        }
        return clazz;
    }

    public Integer advancedCountInstance(AdvancedSearch advancedSearch) {
        advancedSearch.validate();
        return resourceSearchMapper.countByAdvancedSearch(advancedSearch);
    }
}
