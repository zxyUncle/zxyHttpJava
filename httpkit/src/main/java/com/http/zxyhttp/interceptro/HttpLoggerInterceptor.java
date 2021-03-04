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
                charset = contentType.charset(Charset.forName("UTF-8"));
                body = buffer.readString(charset);
                LogcatUitls.printPost(OkHttpConfig.HTTP_TAG, request.url().toString(), body);
            } else {
                LogcatUitls.printStirng(OkHttpConfig.HTTP_TAG, request.url().toString());
            }
            //临近过期刷新token
            if (false && !request.url().toString().contains("passport/token/action-refresh")) {
                refreshToken();
            }
        }
        return chain.proceed(request);
    }

    //临近过期刷新token
    private void refreshToken() {
        //组装请求参数
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("refresh_token", OkHttpConfig.refresh_token);
        //组装@body
        RequestBody requestBody = BodyUtils.bodyMap(hashMap);
        Map<String, Object> reqeustMap = HashMapUtils.instance().reqeustMap();
        OkHttpApi okHttpApi = OkHttpService.getInstance().apiService(mContext);
        OkHttpService.getInstance().callBack(okHttpApi.RefreshToken(requestBody, reqeustMap), new NetWorkListener<BaseBean<Object>>() {
            @Override
            public void onSucc(BaseBean<Object> bean) {
                //刷新toke成功-保存

            }

            @Override
            public void onNetError(Throwable throwable) {

            }

            @Override
            public void onFail(BaseBean<Object> bean) {

            }
        });
    }
}
