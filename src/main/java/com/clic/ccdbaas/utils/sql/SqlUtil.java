package com.clic.ccdbaas.utils.sql;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.clic.ccdbaas.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * sql操作工具类
 *
 * @author ruoyi
 */
public class SqlUtil {
    public static final String DomainNameRegexp = "^[a-zA-Z0-9_]+$";
    /**
     * 定义常用的 sql关键字
     */
    public static String SQL_REGEX = "and |extractvalue|updatexml|exec |insert |select |delete |update |drop |count |chr |mid |master |truncate |char |declare |or |+|user()";
    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            throw new IllegalArgumentException("参数不符合规范，不能进行查询");
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }

    /**
     * SQL关键字检查
     */
    public static void filterKeyword(String value) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        String[] sqlKeywords = StringUtils.split(SQL_REGEX, "\\|");
        for (String sqlKeyword : sqlKeywords) {
            if (StringUtils.indexOfIgnoreCase(value, sqlKeyword) > -1) {
                throw new IllegalArgumentException("参数存在SQL注入风险");
            }
        }
    }

    public static void fillCriteriaByInstance(Object instance, Object Criteria) {
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(instance);
                if (value != null) {
                    String criteriaName = field.getName();
                    String sqlMethod = "EqualTo";
                    if (value.getClass().equals(String.class)) {
                        String strValue = (String) value;
                        if (strValue.startsWith("!")) {
                            sqlMethod = "NotEqualTo";
                            value = strValue.substring(1);
                        } else if (strValue.startsWith("#")) {
                            sqlMethod = "Like";
                            value = "%" + strValue.substring(1) + "%";
                        }
                    }
                    //enum
                    if (value.getClass().isEnum()) {
                        value = value.toString();
                    }

                    String criteriaMethod = "and" + criteriaName.substring(0, 1).toUpperCase() + criteriaName.substring(1) + sqlMethod;

                    Criteria.getClass().getMethod(criteriaMethod, value.getClass()).invoke(Criteria, value);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException ignore) {
            }
        }
    }

    //mybatis-plus的模糊查询，支持全字段或指定字段查询
    public static <T> QueryWrapper<T> fuzzySearchFullField(Class<T> clazz, String value, List<String> searchedFields) {
        //mybatis-plus的模糊查询，支持全字段或指定字段查询
        if (searchedFields != null && searchedFields.isEmpty()) {
            throw new IllegalArgumentException("searchedFields can not be empty");
        }

        //根据clazz，创建类型为String的属性名集合
        Set<String> clazzStringFields = new HashSet<>();
        Field[] clazzDeclaredFields = clazz.getDeclaredFields();
        for (Field field : clazzDeclaredFields) {
            //仅限String类型的字段，不含TableField注解为不exist的字段
            if (field.getType().getSimpleName().equals("String")) {
                TableField tableField = field.getAnnotation(TableField.class);
                if (tableField == null || tableField.exist())
                    clazzStringFields.add(field.getName());
            }
        }

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (searchedFields == null) {
            searchedFields = new ArrayList<>(clazzStringFields);
        } else {
            //判断传入的字段是不是clazz的字段
            for (String field : searchedFields) {
                if (!clazzStringFields.contains(field)) {
                    throw new IllegalArgumentException("field " + field + "不存在或不是能够模糊查询的字段");
                }
            }
        }
        for (String field : searchedFields) {
            queryWrapper.or().like(field, value);
        }
        return queryWrapper;
    }

    public static <T> String getTableName(Class<T> clazz) {
        //return value of @TableName annotation
        if (clazz.getAnnotation(com.baomidou.mybatisplus.annotation.TableName.class) == null && clazz.getAnnotation(javax.persistence.Table.class) == null)
            throw new IllegalArgumentException("class " + clazz.getName() + " 必须有 @TableName 或 @Table 注解来获得表名");
        if (clazz.getAnnotation(com.baomidou.mybatisplus.annotation.TableName.class) != null) {
            return clazz.getAnnotation(com.baomidou.mybatisplus.annotation.TableName.class).value();
        } else {
            return clazz.getAnnotation(javax.persistence.Table.class).name();
        }
    }

    public static Class getClass(String className) {
        Class clazz;
        try {
            clazz = Class.forName("com.clic.ccdbaas.entity." + className);
        } catch (Exception e) {
            throw new RuntimeException("获取目标类型失败，请正确填写ClassName");
        }
        return clazz;
    }
}
