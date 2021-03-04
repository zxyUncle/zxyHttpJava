package com.http.zxyhttp;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.interceptro.HeaderInterceptoer;
import com.http.zxyhttp.interceptro.HttpLoggerInterceptor;
import com.http.zxyhttp.log.LogcatUitls;
import com.http.zxyhttp.ssl.SSLSocketClient;
import com.http.zxyhttp.utils.NetWorkListener;
import com.http.zxyhttp.utils.NetWorkUtils;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zxy on 2021/2/27 0027 17:22
 * ******************************************
 * * 网路请求类
 * ******************************************
 */
public class OkHttpService {
    private RxAppCompatActivity mContext;

    //zxy java单例模式模板，静态内部类
    private OkHttpService() {
    }

    public static OkHttpService getInstance() {
        return SingleInnerHolder.instance;
    }

    private static class SingleInnerHolder {
        private static OkHttpService instance = new OkHttpService();
    }

    public OkHttpApi apiService(Context mContext) {
        this.mContext = (RxAppCompatActivity) mContext;
        return getService(BuildConfig.DEBUG ? OkHttpConfig.HTTP_DEBUG_HOSTURL : OkHttpConfig.HTTP_RESEASE_HOSTURL, OkHttpApi.class);
    }

    private <A> A getService(String baseUrl, Class<A> service) {

        OkHttpClient clien = new OkHttpClient.Builder()
                //自定义请求头
                .addNetworkInterceptor(new HeaderInterceptoer())
                //请求体过滤
                .addInterceptor(new HttpLoggerInterceptor(mContext))
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(GsonConverterFactory.create())
                //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clien)
                .build();

        return retrofit.create(service);
    }

    /**
     * 显示Dialog 加载中动画的网路
     *
     * @param observable         Observable<T>
     * @param httpClickLenerlist HttpClickLenerlist<T>
     */
    @SuppressLint("CheckResult")
    public <T> void callBack(Observable<T> observable, NetWorkListener<T> httpClickLenerlist) {
        if (!NetWorkUtils.isNetAvailable(mContext)) {
            httpClickLenerlist.onFail(null);
        } else {
            observable.subscribeOn(Schedulers.io())
//                    .compose(mContext.bindToLifecycle())
                    .compose(mContext.bindUntilEvent(ActivityEvent.DESTROY))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(next -> {//请求的next对象
                        LogcatUitls.printJson(OkHttpConfig.HTTP_TAG, new Gson().toJson(next));
                        if (((BaseBean) next).getErrorCode() == OkHttpConfig.CODE_SUCC) {
                            httpClickLenerlist.onSucc(next);
                        } else {
                            if (((BaseBean) next).getErrorCode() == OkHttpConfig.CODE_TOKEN_INVALID) {
                                //todo Token过期，跳转到登录界面
                            } else {
                                httpClickLenerlist.onFail(next);
                            }

                        }

                    }, throwable -> {//error
                        //加载动画隐藏
                        LogcatUitls.printJson(OkHttpConfig.HTTP_TAG, new Gson().toJson(throwable));
                        httpClickLenerlist.onNetError(throwable);
                    }, () -> {//Complete
                        //加载动画隐藏

                    });
        }
    }

}
