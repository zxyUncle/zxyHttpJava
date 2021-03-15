package com.http.zxyhttp;



import com.http.zxyhttp.bean.ArticleData;
import com.http.zxyhttp.bean.BaseBean;


import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by zxy on 2021/2/27 0027 18:44
 * ******************************************
 * * 接口
 * ******************************************
 */
public interface OkHttpApi {

    //**********************登录**************************************
    /**
     * @return
     */
    @GET("wxarticle/chapters/json")
    Observable<BaseBean<ArrayList<ArticleData>>> getWXArticle();

}
