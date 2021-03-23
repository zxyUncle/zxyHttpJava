package com.http.zxyhttp.interceptro;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zxy on 2021/2/27 0027 18:02
 * ******************************************
 * * 请求头过滤器
 * ******************************************
 */
public class HeaderInterceptoer implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("token", "")
                .build();
        return chain.proceed(request);
    }

}

