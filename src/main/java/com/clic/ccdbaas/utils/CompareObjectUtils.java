package com.clic.ccdbaas.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CompareObjectUtils {

    /**
     * 比较新老对象的差别
     *
     * @param initialObject 旧对象
     * @param updateObject  新对象
     * @return prepareUpdateList 哪个字段有更新 内容更新成什么
     */
    public static Map<String, Object> compareObject(Object initialObject, Object updateObject) throws IllegalAccessException {
        Map<String, Object> prepareUpdateMap = new HashMap<>();
        if (initialObject != null) {
            Map<String, Object> initialObjectMap = changeClassValueToMap(initialObject);
            Map<String, Object> updateObjectMap = changeClassValueToMap(updateObject);
            if (initialObjectMap != null && !initialObjectMap.isEmpty()) {
                for (Map.Entry<String, Object> entry : initialObjectMap.entrySet()) {
                    Object initialObjectValue = entry.getValue();
                    Object updateObjectValue = updateObjectMap.get(entry.getKey());
                    if (!StringUtils.isEmpty(updateObjectValue) && !updateObjectValue.equals(initialObjectValue)) {
                        prepareUpdateMap.put(entry.getKey(), "initial" + initialObjectValue + "*update" + updateObjectValue);
                    }
                }
            }
        }
        return prepareUpdateMap;
    }

    /**
     * 将类对象转换成Map
     *
     * @param entity 原对象
     * @return
     * @throws IllegalAccessException 类型转换时报错
     */
    public static Map<String, Object> changeClassValueToMap(Object entity) throws IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            resultMap.put(name, field.get(entity));
        }
        return resultMap;
    }

    /**
     * 比较两个对象内容是否相同
     *
     * @param originalObj 原来对象
     * @param newObj      新对象
     * @return
     */
    public static boolean equals(Object originalObj, Object newObj) {
        if (originalObj != null) {
            Map<String, Object> initialObjectMap = JSONObject.parseObject(JSONObject.toJSONString(originalObj), Map.class);
            Map<String, Object> updateObjectMap = JSONObject.parseObject(JSONObject.toJSONString(newObj), Map.class);
            if (initialObjectMap != null && !initialObjectMap.isEmpty()) {
                for (Map.Entry<String, Object> entry : initialObjectMap.entrySet()) {
                    Object initialObjectValue = entry.getValue();
                    Object updateObjectValue = updateObjectMap.get(entry.getKey());
                    if (!StringUtils.isEmpty(updateObjectValue) && !updateObjectValue.equals(initialObjectValue))
                        return false;
                }
            }
        }
        return true;
    }
}
