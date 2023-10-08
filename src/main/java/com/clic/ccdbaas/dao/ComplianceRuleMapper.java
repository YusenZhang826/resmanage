package com.clic.ccdbaas.dao;

import com.alibaba.fastjson.JSONArray;
import com.clic.ccdbaas.entity.ComplianceRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ComplianceRuleMapper {

    //    int getVlanCount();
    Map getComplianceRuleByResId(String resId);

    List<Map> getAllComplianceRules(ComplianceRule complianceRule);

    JSONArray executeSelectByParam(@Param("tableName") String tableName, @Param("conditions") String conditions);

    int updateExecResult(Map updateInfo);

    void addComplianceRule(ComplianceRule toJavaObject);

    void updateComplianceRule(ComplianceRule toJavaObject);

    void deleteComplianceRule(String resId);

    void updateComplianceRuleStatus(HashMap<String, Object> map);

    HashMap selectRuleClassByName(String name);

    void addComplianceRuleClass(HashMap<String, Object> map);

    void updateComplianceRuleClass(HashMap<String, Object> map);

    List<HashMap> getAllComplianceRuleClass(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    void deleteComplianceRuleClass(String id);

    Map selectRuleLockedInfo(String resId);

    Integer selectRunningStatus(String resId);

    String selectResourceTypeId(String resId);

    int selectStatus(String resId);

    int countAppointedName(@Param("name") String name, @Param("resId") String resId);

    List<Map> countRuleGroupByStatus(ComplianceRule complianceRule);

    void updateConfigFlag(List<String> resIdArr);

//    int update

}
