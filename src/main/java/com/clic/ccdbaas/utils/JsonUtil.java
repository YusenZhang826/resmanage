package com.clic.ccdbaas.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public final class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T unMarshal(String jsonstr, Class<T> type) {
        try {
            return MAPPER.readValue(jsonstr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T unMarshal(String jsonstr, TypeReference<T> type) {
        try {
            return MAPPER.readValue(jsonstr, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> unMarshalMap(String jsonstr, Class<T> type) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(HashMap.class, String.class, type);
            return MAPPER.readValue(jsonstr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String marshal(Object srcObj) {
        try {
            return MAPPER.writeValueAsString(srcObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObjectMapper getMapper() {
        return MAPPER;
    }
}
