package com.clic.ccdbaas.utils.sql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class AdvancedSearch {
    //可以以正负号开头，后面为属性名
    private static final String SORT_REGEXP = "^[-+]?[a-zA-Z0-9_]+$";
    private String className;
    private List<Condition> conditions;
    private List<Condition> preConditions;
    private List<String> sortBy;
    private FuzzySearch fuzzySearch;
    @JsonIgnore
    private String tableName;

    private void validateSortBy() {
        if (sortBy == null || sortBy.size() == 0) {
            return;
        }
        for (String sort : sortBy) {
            if (!sort.matches(SORT_REGEXP)) {
                throw new IllegalArgumentException(String.format("排序字段%s不合法", sort));
            }
        }
    }

    public String createSelectSql(String tableName) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(tableName).append(" where 1=1 ");
        if (preConditions != null && preConditions.size() > 0) {
            sql.append(" and ( ");
            sql.append(Condition.createWhereClauses(preConditions));
            sql.append(" ) ");
        }
        if (conditions != null && conditions.size() > 0) {
            sql.append(" and ( ");
            sql.append(Condition.createWhereClauses(conditions));
            sql.append(" ) ");
        }

        if (fuzzySearch != null) {
            sql.append(" and ( ");
            sql.append(fuzzySearch.createFuzzyClause());
            sql.append(" ) ");
        }

        if (sortBy != null && sortBy.size() > 0) {
            StringBuilder orderBySql = new StringBuilder();
            orderBySql.append(" order by ");
            for (String sort : sortBy) {
                if (sort.startsWith("-")) {
                    orderBySql.append(sort.substring(1)).append(" desc,");
                } else {
                    orderBySql.append(sort).append(" asc,");
                }
            }
            orderBySql.deleteCharAt(orderBySql.length() - 1);
            if (!SqlUtil.isValidOrderBySql(orderBySql.toString())) {
                throw new IllegalArgumentException("order by语句不合法");
            }
            sql.append(orderBySql);
        }
        return sql.toString();
    }


    public void validate() {
        if (tableName == null || tableName.isEmpty()) {
            tableName = SqlUtil.getTableName(SqlUtil.getClass(className));
        }
        if (conditions != null) {
            for (Condition condition : conditions) {
                condition.validate();
            }
        }
        if (preConditions != null) {
            for (Condition condition : preConditions) {
                condition.validate();
            }
        }
        if (sortBy != null) validateSortBy();
        if (fuzzySearch != null) fuzzySearch.validate();
    }

    public void setClassName(String className) {
        this.className = className;
        if (this.tableName == null || this.tableName.isEmpty()) {
            this.tableName = SqlUtil.getTableName(SqlUtil.getClass(this.className));
        }
    }
}
