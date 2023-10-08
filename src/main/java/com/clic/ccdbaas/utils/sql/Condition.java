package com.clic.ccdbaas.utils.sql;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

@Data
public class Condition {
    private static final Map<String, Integer> AllowedSearchType = new HashMap<String, Integer>() {{
        put("in", -1);
        put("not in", -1);
        put("between", 2);
        put("not between", 2);
        put("like", 1);
        put("not like", 1);
        put("startWith", 1);
        put("endWith", 1);
        put("is null", 0);
        put("is not null", 0);
        put("repeat", 0);
        put(">", 1);
        put("<", 1);
        put("<>", 1);
        put(">=", 1);
        put("<=", 1);
        put("!=", 1);
        put("=", 1);
        put("regexp", 1);
        put("instr", 1);
    }};
    private static final String ConditionTypeRegexp = "^(and|or)$";
    //age name address
    private String domainName;
    //String Integer Date
    private String domainType;
    //in between like > < <> >= <=
    private String searchType;
    //[value1,value2,value3]
    private List<?> values;
    //and or not
    private String conditionType;
    
    public static String createWhereClauses(List<Condition> conditions) {
        if (conditions == null || conditions.isEmpty()) return "";
        //第一个条件必须是and
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < conditions.size(); i++) {
            Condition condition = conditions.get(i);
            String searchType = condition.getSearchType();
            String conditionType = condition.getConditionType();
            String domainName = condition.getDomainName();
            String domainType = condition.getDomainType();
            if (condition.getValues() == null || condition.getValues().size() == 0) {
                throw new IllegalArgumentException("条件值不能为空");
            }

            if (domainType == null) {
                domainType = condition.getValues().get(0).getClass().getSimpleName();
            }

            List<?> values = condition.getValues();
            for (Object value : values) {
                if (value.getClass().equals(String.class)) {
                    SqlUtil.filterKeyword((String) value);
                }
            }

            for (Object value : values) {
                if (!value.getClass().getSimpleName().equals(domainType)) {
                    throw new IllegalArgumentException("条件值类型不匹配");
                }
            }

            if (i != 0) {
                switch (conditionType) {
                    case "or":
                        sql.append(" or ");
                        break;
                    case "not":
                        sql.append(" and not ( ");
                        break;
                    case "or not":
                        sql.append(" or not ( ");
                        break;
                    default:
                        sql.append(" and ");
                        break;
                }
            }

            if (searchType.equals("in") || searchType.equals("not in")) {
                if (condition.getValues().size() == 0) {
                    throw new IllegalArgumentException("in条件值不能为空");
                }
                sql.append(domainName).append(" ").append(searchType).append(" (");
                for (Object value : condition.getValues()) {
                    if (value instanceof String || value instanceof Date) {
                        sql.append("'").append(value).append("',");
                    } else {
                        sql.append(value).append(",");
                    }
                }
                sql.deleteCharAt(sql.length() - 1);
                sql.append(")");
            } else if (searchType.equals("between") || searchType.equals("not between")) {
                if (condition.getValues().size() != 2) {
                    throw new IllegalArgumentException("between条件值个数必须为2");
                }
                sql.append(domainName).append(" ").append(searchType).append(" ");
                Object firstValue = condition.getValues().get(0);
                Object secondValue = condition.getValues().get(1);
                if (firstValue instanceof String || firstValue instanceof Date) {
                    sql.append("'").append(firstValue).append("'");
                } else {
                    sql.append(firstValue);
                }
                sql.append(" and ");
                if (secondValue instanceof String || secondValue instanceof Date) {
                    sql.append("'").append(secondValue).append("'");
                } else {
                    sql.append(secondValue);
                }
            } else if (searchType.equals("like") || searchType.equals("not like")) {
                if (!domainType.equals("String")) {
                    throw new IllegalArgumentException("like条件值类型必须为String");
                }
                sql.append(domainName).append(" ").append(searchType).append(" '%").append(condition.getValues().get(0)).append("%'");
            } else if (searchType.equals("startWith")) {
                if (!domainType.equals("String")) {
                    throw new IllegalArgumentException("起始于条件值类型必须为String");
                }
                sql.append(domainName).append(" like '").append(condition.getValues().get(0)).append("%'");
            } else if (searchType.equals("endWith")) {
                if (!domainType.equals("String")) {
                    throw new IllegalArgumentException("结束于条件值类型必须为String");
                }
                sql.append(domainName).append(" like '%").append(condition.getValues().get(0)).append("'");
            } else if (searchType.equals("is null") || searchType.equals("is not null")) {
                sql.append(domainName).append(" ").append(searchType);
            } else if (searchType.equals(">") || searchType.equals("<") || searchType.equals("<>") || searchType.equals(">=") || searchType.equals("<=") || searchType.equals("=") || searchType.equals("!=")) {
                //> < <> >= <=
                if (condition.getValues().get(0) instanceof String) {
                    sql.append(domainName).append(" ").append(searchType).append(" '").append(condition.getValues().get(0)).append("'");
                } else {
                    sql.append(domainName).append(" ").append(searchType).append(" ").append(condition.getValues().get(0));
                }
            } else if (searchType.equals("regexp")) {
                sql.append(domainName).append(" ").append(searchType).append(" '").append(condition.getValues().get(0)).append("'");
                if (!domainType.equals("String")) {
                    throw new IllegalArgumentException("regexp条件值类型必须为String");
                }
            } else if (searchType.equals("instr")) {
                sql.append("instr('").append(condition.getValues().get(0)).append("',").append(domainName).append(")").append(" > 0");
                if (!domainType.equals("String")) {
                    throw new IllegalArgumentException("instr条件值类型必须为String");
                }
            } else if (searchType.equals("repeat")) {
                StringBuilder subSql = new StringBuilder("select ");
                subSql.append(domainName).append(" from ").append(condition.getValues().get(0)).append(" group by ").append(domainName).append(" having count(*) > 1");
                sql.append(domainName).append(" in (").append(subSql).append(")");
            } else {
                throw new IllegalArgumentException("不支持的查询类型");
            }

            if (conditionType.equals("not") || conditionType.equals("or not")) {
                sql.append(" ) ");
            }
        }
        sql.append(" ");
        return sql.toString();
    }

    //改写为成员函数
    public void validate() {
        if (StringUtils.isEmpty(this.searchType)) {
            throw new IllegalArgumentException("查询类型searchType不能为空");
        }
        if (!AllowedSearchType.containsKey(this.searchType)) {
            throw new IllegalArgumentException(String.format("不支持的查询类型searchType:%s", this.searchType));
        }

        if (StringUtils.isEmpty(this.conditionType) || !Pattern.matches(ConditionTypeRegexp, this.conditionType)) {
            this.conditionType = "and";
        }

        if (StringUtils.isEmpty(this.domainName)) {
            throw new IllegalArgumentException("域名domainName不能为空");
        }
        if (!Pattern.matches(SqlUtil.DomainNameRegexp, this.domainName)) {
            throw new IllegalArgumentException(String.format("不合法的域名domainName:%s", this.domainName));
        }

        if (this.values == null) {
            this.values = new ArrayList<>();
        }

        //校验values数量，如多余则去除多余，少则报异常
        if (AllowedSearchType.get(this.searchType) == -1) {
            if (this.values.isEmpty()) {
                throw new IllegalArgumentException(String.format("查询类型:%s,值不能为空", this.searchType));
            }
        } else {
            if (this.values.size() != AllowedSearchType.get(this.searchType)) {
                if (this.values.size() > AllowedSearchType.get(this.searchType)) {
                    this.values.subList(AllowedSearchType.get(this.searchType), this.values.size()).clear();
                } else {
                    throw new IllegalArgumentException(String.format("查询类型:%s,值数量不足", this.searchType));
                }
            }
        }

        //若传了domainType，则values的类型必须都是domainType。否则校验values的类型是否相同，不相同则报异常
        if (AllowedSearchType.get(this.searchType) != 0) {
            if (this.domainType != null) {
                for (Object value : this.values) {
                    if (!value.getClass().getSimpleName().equals(this.domainType)) {
                        throw new IllegalArgumentException(String.format("查询类型:%s,值类型不匹配", this.domainType));
                    }
                }
            } else {
                String domainType = this.values.get(0).getClass().getSimpleName();
                for (Object value : this.values) {
                    if (!value.getClass().getSimpleName().equals(domainType)) {
                        throw new IllegalArgumentException(String.format("查询类型:%s,值类型不匹配", this.domainType));
                    }
                }
                this.domainType = domainType;
            }
        }
    }
}