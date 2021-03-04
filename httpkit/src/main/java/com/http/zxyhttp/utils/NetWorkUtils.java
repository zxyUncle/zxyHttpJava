package com.http.zxyhttp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkUtils {
    /**
     * JsonUtil
     * 判断是否在Wifi下
     *
     * @param mContext Context
     * @return Boolean
     */
    public static boolean isWifi(Context mContext) {
        Object systemService = mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null
                && activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI
        ) {
            return true;
        }
        return false;
    }

    /**
     * 是否有网路
     *
     * @param context Context
     * @return Boolean
     */
    public static boolean isNetAvailable(Context context) {
        boolean isAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
