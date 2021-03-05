package com.zxy.http;


import android.os.Bundle;
import android.view.View;


import com.http.zxyhttp.OkHttpService;
import com.http.zxyhttp.bean.ArticleData;
import com.http.zxyhttp.bean.BaseBean;
import com.http.zxyhttp.utils.NetWorkListener;



public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onSMS(View view) {
        OkHttpService.getInstance().callBack(okHttpApi.getWXArticle(), new NetWorkListener<BaseBean<ArticleData>>() {
            @Override
            public void onSucc(BaseBean<ArticleData> bean) {

            }

            @Override
            public void onNetError(Throwable throwable) {

            }

            @Override
            public void onFail(BaseBean<ArticleData> bean) {

            }
        });
    }
}