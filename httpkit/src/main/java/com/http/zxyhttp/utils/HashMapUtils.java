package com.http.zxyhttp.utils;

import com.http.zxyhttp.BuildConfig;
import com.http.zxyhttp.OkHttpConfig;

import java.util.HashMap;
import java.util.Map;

public class HashMapUtils {
    //zxy java单例模式模板，静态内部类

    private HashMapUtils() {
    }

    public static HashMapUtils instance() {
        return SingleInnerHolder.instance;
    }

    private static class SingleInnerHolder {
        private static HashMapUtils instance = new HashMapUtils();

    }

    //获取access_key 的Map
    public Map<String, Object> reqeustMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("timestamp", System.currentTimeMillis() / 1000 + "");
        map.put("access_key", BuildConfig.isDebug ? OkHttpConfig.access_key_DEBUG : OkHttpConfig.access_key_RESEASE);
        return map;
    }


}
