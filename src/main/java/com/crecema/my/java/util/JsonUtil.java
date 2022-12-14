package com.crecema.my.java.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import java.util.*;

public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtil() {}

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T toObject(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> kClass, Class<V> vClass) {
        try {
            MapType mapType = OBJECT_MAPPER.getTypeFactory().constructMapType(Map.class, kClass, vClass);
            return OBJECT_MAPPER.readValue(json, mapType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new LinkedHashMap<>();
        }
    }

    public static <E> List<E> toList(String json, Class<E> eClass) {
        try {
            CollectionType collectionType = OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, eClass);
            return OBJECT_MAPPER.readValue(json, collectionType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> E[] toArray(String json, Class<E> eClass) {
        try {
            ArrayType arrayType = OBJECT_MAPPER.getTypeFactory().constructArrayType(eClass);
            return OBJECT_MAPPER.readValue(json, arrayType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return (E[]) new Object[0] ;
        }
    }

}
