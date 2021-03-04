package com.zxy.http;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.http.zxyhttp.OkHttpConfig;
import com.http.zxyhttp.OkHttpService;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.utils.BodyUtils;
import com.http.zxyhttp.utils.HashMapUtils;
import com.http.zxyhttp.utils.NetWorkListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSMS(View view) {

        //请求路径的map
        Map<String, Object> reqeustMap = HashMapUtils.instance().reqeustMap();
        //组装请求参数
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("mobile_countrycode", "86");
        hashMap.put("mobile_number", "15669316960");
        //组装@body
        RequestBody requestBody = BodyUtils.bodyMap(hashMap);
        //执行网路请求
        OkHttpService.getInstance().callBack(okHttpApi.getPhoneSMS(requestBody, reqeustMap), new NetWorkListener<BaseBean<Object>>() {
            @Override
            public void onSucc(BaseBean<Object> bean) {

            }

            @Override
            public void onNetError(Throwable throwable) {

            }


            @Override
            public void onFail(BaseBean<Object> bean) {

            }

        });
        startActivity(new Intent(this,TestActivity.class));

    }
}