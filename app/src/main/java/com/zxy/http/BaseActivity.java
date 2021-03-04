package com.zxy.http;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.http.zxyhttp.OkHttpApi;
import com.http.zxyhttp.OkHttpService;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by zxy on 2021/2/27 0027 23:01
 * ******************************************
 * *
 * ******************************************
 */
public class BaseActivity extends RxAppCompatActivity {
    OkHttpApi okHttpApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        okHttpApi = OkHttpService.getInstance().apiService(this);
    }
}
