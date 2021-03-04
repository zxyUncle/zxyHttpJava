package com.http.zxyhttp;



import com.http.zxyhttp.bean.BaseBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by zxy on 2021/2/27 0027 18:44
 * ******************************************
 * * 接口
 * https://blog.csdn.net/fayangzhou/article/details/80859318?spm=1001.2014.3001.5501
 * ******************************************
 */
public interface OkHttpApi {

    //**********************登录**************************************
    /**
     * 发送验证码
     *
     * @return
     */
    @POST("passport/login/mobile/action-send-captcha")
    Observable<BaseBean<Object>> getPhoneSMS(@Body RequestBody body, @QueryMap Map<String, Object> querMap);
    /**
     * 登录状态续约
     *
     * @return
     */
    @POST("passport/token/action-refresh")
    Observable<BaseBean<Object>> RefreshToken(@Body RequestBody body, @QueryMap Map<String, Object> querMap);

//    @POST("wxarticle/chapters/json1")
//    @Multipart
//    Observable<BaseBean<ArrayList<Object>>> getFail1(@Body RequestBody body, @PartMap Map<String, RequestBody> map);


    //DELETE请求，删除，Path形式
//    @DELETE("logindelete/{uphone}")
//    Call<User> getLoginDelete(@Path("uphone") String uphone);

}
