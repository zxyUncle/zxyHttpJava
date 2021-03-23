package com.http.zxyhttp;

/**
 * Created by zxy on 2021/2/27 0027 17:35
 * ******************************************
 * * 網絡配置
 * ******************************************
 */
public class OkHttpConfig {
    //网路请求的配置
    public static int CODE_SUCC = 0;//成功的Code
    public static int CODE_TOKEN_INVALID = 40401;//Token过期
    public static String HTTP_DEBUG_HOSTURL = "https://www.wanandroid.com/";//测试服务器地址
    public static String HTTP_RESEASE_HOSTURL = "https://www.wanandroid.com/";//线上服务器地址
    public static String HTTP_TAG = "zxy"  ;       //网路请求数据显示的Logcat 日志过滤TAG

}
