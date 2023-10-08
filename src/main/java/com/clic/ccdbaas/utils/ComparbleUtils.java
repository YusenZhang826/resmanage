package com.clic.ccdbaas.utils;

import com.clic.ccdbaas.model.ComparbleResult;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author chenjianhua
 * @version 1.0
 * @date 2023/5/6 15:25
 * @email chenjianhua@bmsoft.com.cn
 */
public class ComparbleUtils {
    /**
     * 返回所有字段
     *
     * @param target
     * @return
     */
    public static Map<String, Field> getFields(Object target) {
        if (target == null) {
            return Collections.EMPTY_MAP;
        }
        Field[] fields = target.getClass().getDeclaredFields();
        Map<String, Field> rsMap = new HashMap<>(fields.length);
        Stream.of(fields).forEach(field -> {
            field.setAccessible(true);
            rsMap.put(field.getName(), field);
        });
        return rsMap;
    }

    /**
     * 比较两个对象属性值是否相同,如果不同返回修改过的属性信息集合,包括：字段名,原始数据值，新值，更改类型
     *
     * @param source 原始对象
     * @param target 新对象
     * @return ArrayList<ComparbleResult>  变化后的数据集合
     */
    public static ArrayList<ComparbleResult> compareInstance(Object source, Object target) {
        ArrayList<ComparbleResult> compareResultList = new ArrayList<>();
        try {
            // 获取字段集合
            Map<String, Field> fileds_source = getFields(source);
            Map<String, Field> fields_target = getFields(target);
            // 先遍历source集合，处理两种情况：
            // source中有的,target中没有的->字段被删除
            // source中有的,target中有但是内容变化->字段内容被更新
            for (Field field : fileds_source.values()) {
                ComparbleResult comparbleResult = new ComparbleResult();
                Object v1 = field.get(source);

                if("resid".equals(field.getName())||"resId".equals(field.getName())){
                    continue;
                }
                // source中有的,target中没有的->字段被删除
                if (!fields_target.containsKey(field.getName())) {
                     /*   comparbleResult.setFieldName(field.getName());
                        comparbleResult.setHanderType("delete");
                        compareResultList.add(comparbleResult);*/
                    continue;
                }
                Object v2 = fields_target.get(field.getName()).get(target);
                comparbleResult.setFieldName(field.getName());
                comparbleResult.setFieldContent(v1);
                comparbleResult.setNewFieldContent(v2);
                if (v2 == null) {
                    continue;
                }

                if (v1 == null && v2 != null) {
                    comparbleResult.setHanderType("update");
                    compareResultList.add(comparbleResult);
                    continue;
                }
                // source中有的,target中有但是内容变化->字段内容被更新
                if (!v1.equals(v2)) {
                    comparbleResult.setHanderType("update");
                    compareResultList.add(comparbleResult);
                    continue;
                }
            }
            // 遍历target集合，处理一种情况
            // source中没有,target有的->新增字段
            for (Field field : fields_target.values()) {
                ComparbleResult comparbleResult = new ComparbleResult();

                if (!fileds_source.containsKey(field.getName())) {
                    comparbleResult.setFieldName(field.getName());
                    comparbleResult.setHanderType("add");
                    compareResultList.add(comparbleResult);
                    continue;
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return compareResultList;
    }


}
