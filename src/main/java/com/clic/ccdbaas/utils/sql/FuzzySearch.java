package com.clic.ccdbaas.utils.sql;

import lombok.Data;

import java.util.List;
import java.util.regex.Pattern;

@Data
public class FuzzySearch {
    String value;
    List<String> searchedFields;
    String className;

    public String createFuzzyClause() {
        if (searchedFields == null || searchedFields.isEmpty()) {
            throw new IllegalArgumentException("查询字段列表searchedFields不能为空");
        }

        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("查询值value不能为空");
        }
        SqlUtil.filterKeyword(value);


        StringBuilder fields = new StringBuilder();
        for (String field : searchedFields) {
            if (field == null || field.isEmpty()) {
                throw new IllegalArgumentException("查询字段field不能为空");
            }
            fields.append(field).append(",");
        }
        if (!SqlUtil.isValidOrderBySql(fields.toString())) {
            throw new IllegalArgumentException("模糊字段不合法");
        }
        fields.deleteCharAt(fields.length() - 1);

        String sql = "concat_ws(' '," +
                fields +
                ") like '%" + value + "%'";

        return sql;
    }

    public void validate() {
        if (searchedFields == null || searchedFields.isEmpty()) {
            throw new IllegalArgumentException("查询字段列表searchedFields不能为空");
        }

        if (value == null) {
            throw new IllegalArgumentException("查询值value不能为空");
        }

        for (String field : searchedFields) {
            if (!Pattern.matches(SqlUtil.DomainNameRegexp, field)) {
                throw new IllegalArgumentException("查询字段field不合法");
            }
        }
    }
}