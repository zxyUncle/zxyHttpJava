package com.zxy.http;


import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.http.zxyhttp.OkHttpService;
import com.http.zxyhttp.bean.ArticleData;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.utils.NetWorkListener;

import java.util.ArrayList;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSMS(View view) {
//        Log.e("zxy",(0/0)+"");
        OkHttpService.getInstance().callBack(okHttpApi.getWXArticle(), new NetWorkListener<BaseBean<ArrayList<ArticleData>>>() {
            @Override
            public void onSucc(BaseBean<ArrayList<ArticleData>> bean) {

            }

            @Override
            public void onNetError(Throwable throwable) {

            }

            @Override
            public void onFail(BaseBean<ArrayList<ArticleData>> bean) {

            }
        });
    }
}