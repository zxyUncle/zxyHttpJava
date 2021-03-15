package com.http.zxyhttp.interceptro;


import com.http.zxyhttp.OkHttpApi;
import com.http.zxyhttp.OkHttpConfig;
import com.http.zxyhttp.OkHttpService;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.log.LogcatUitls;
import com.http.zxyhttp.utils.BodyUtils;
import com.http.zxyhttp.utils.HashMapUtils;
import com.http.zxyhttp.utils.NetWorkListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by zxy on 2021/2/27 0027 18:04
 * ******************************************
 * * 请求过滤器
 * ******************************************
 */
public class HttpLoggerInterceptor implements Interceptor {
    private RxAppCompatActivity mContext;

    public HttpLoggerInterceptor(RxAppCompatActivity mContext) {
        this.mContext = mContext;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!request.url().toString().isEmpty()) {
            if ("POST".equals(request.method())) {
                RequestBody requestBody = request.body();
                String body = "";
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = Charset.forName("UTF-8");
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(Charset.forName("UTF-8"));
                    body = buffer.readString(charset);
                }
                LogcatUitls.printPost(OkHttpConfig.HTTP_TAG, request.url().toString(), body);
            } else {
                LogcatUitls.printStirng(OkHttpConfig.HTTP_TAG, request.url().toString());
            }
        }
        Response response = chain.proceed(request);


        return chain.proceed(request);
    }
}
