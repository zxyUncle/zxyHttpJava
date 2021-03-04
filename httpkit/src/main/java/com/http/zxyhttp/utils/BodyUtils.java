package com.http.zxyhttp.utils;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BodyUtils {
    /**
     * 组装body数据
     * @param map
     * @return
     */
    public static RequestBody bodyMap(Map<String, Object> map) {
        String json = new Gson().toJson(map);
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }
}
