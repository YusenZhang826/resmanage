package com.clic.ccdbaas.utils.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Data
//select count(*), searchedField from user group by searchedField;
public class CountByGroupSearch {
    private static String COUNT_STAR = "count(*)";
    private String searchedField;
    private String className;
    private List<Condition> conditions;
    private List<Condition> preConditions;

    @JsonIgnore
    private String tableName;

    public void validate() {
        Class clazz = SqlUtil.getClass(className);
        tableName = SqlUtil.getTableName(clazz);
        if (searchedField == null || searchedField.equals("")) {
            throw new IllegalArgumentException("分组字段不能为空");
        }
        if (!Pattern.matches(SqlUtil.DomainNameRegexp, searchedField)) {
            throw new IllegalArgumentException("待分组字段不合法");
        }
        if (preConditions != null) {
            for (Condition condition : preConditions) {
                condition.validate();
            }
        }
        if (conditions != null) {
            for (Condition condition : conditions) {
                condition.validate();
            }
        }
    }

    public List<CountInfo> getCountInfo(List<Map<String, Object>> originCountList) {
        List<CountInfo> result = new ArrayList<>();
        long unknownCount = 0;
        for (Map originCount : originCountList) {
            Object objField = originCount.get(searchedField);
            String field = objField == null ? null : objField.toString();
            long count = (long) originCount.get(COUNT_STAR);
            if (field == null || field.equals("")) {
                unknownCount += count;
            } else {
                CountInfo countInfo = new CountInfo();
                countInfo.label = field;
                countInfo.count = (int) count;
                result.add(countInfo);
            }
        }
        if (unknownCount > 0) {
            CountInfo countInfo = new CountInfo();
            countInfo.label = "其他";
            countInfo.count = (int) unknownCount;
            result.add(countInfo);
        }
        return result;
    }
}
