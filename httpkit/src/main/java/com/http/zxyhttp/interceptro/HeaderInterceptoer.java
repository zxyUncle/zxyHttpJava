package com.http.zxyhttp.interceptro;

import com.http.zxyhttp.OkHttpApi;
import com.http.zxyhttp.OkHttpConfig;
import com.http.zxyhttp.OkHttpService;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.utils.BodyUtils;
import com.http.zxyhttp.utils.HashMapUtils;
import com.http.zxyhttp.utils.NetWorkListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
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

