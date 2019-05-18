package com.whh.initmvp.common;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public class ContantUtils {

    public static String APPVERSION_BASE_URL = "http://ios.mobile.che-by.com/";
    public static String WEATHER_BASE_URL = "http://t.weather.sojson.com/";
    public static String GITHUBSER_BASE_URL = "https://api.github.com/users/";

    public static CompositeDisposable compositeDisposable;

    public static void setCompositeDisposable() {
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 取消所有订阅
     */
    public static void compositeDisposableClear() {
        compositeDisposable.clear();
    }

}
