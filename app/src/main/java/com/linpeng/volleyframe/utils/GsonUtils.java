package com.linpeng.volleyframe.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

/**
 * 》
 * 》Created on 15/10/24 下午7:13
 * 》
 */
public class GsonUtils {
    public static Gson gson;
    public static <T> T fromJson(String json, Class<T> clazz) {
        gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    public static String objectToString(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public static <T> T fromJson(JsonElement element, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(element, clazz);
    }

    public static <T> T fromJson(JsonElement element, Type typeOfT) {
        Gson gson = new Gson();
        return gson.fromJson(element, typeOfT);
    }


}
