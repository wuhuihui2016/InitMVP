package com.whh.initmvp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.whh.initmvp.common.ContantUtils;
import com.whh.initmvp.common.OkHttpUtils;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2019/5/16.
 */

public class MyApp extends Application {

    private static Context context;

    public static OkHttpClient okHtpClient;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        ContantUtils.setCompositeDisposable(); //获取网络请求订阅器

        //获取okHtpClient，使用log拦截器
        okHtpClient = OkHttpUtils.getInstance().getOkHttpClient();

        //初始化FaceBook调试器,可在Chrome调试网络请求,查看SharePreferences,数据库等
        Stetho.initializeWithDefaults(this);
    }

    public static Context getInstance() {
        return context;
    }
}
