package com.clic.ccdbaas.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface ScheduledTaskMapper {
    List<HashMap<String, Object>> getAllRunningScheduledTask();

    int deleteExpireData(String ruleId);

    int batchInsertResult(@Param("ruleId") String ruleId, @Param("resourceTypeId") String resourceTypeId, @Param("idsList") List<Map> idsList);

    int getResourceTotal(@Param("tableName") String tableName, @Param("primaryKey") String primaryKey);

    List<Map> getResult(@Param("params") Map params, @Param("fuzzyParam") Map fuzzySearchParam,
                        @Param("enumParam") Map enumSearchParam, @Param("resIds") Set<String> resIds);

    void deleteScheduledTaskByRuleId(String resId);

}
