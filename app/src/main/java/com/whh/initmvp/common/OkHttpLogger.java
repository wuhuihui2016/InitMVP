package com.whh.initmvp.common;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wuhuihui on 2019/5/18.
 * OkHttpLogger 日志拦截器
 */

public class OkHttpLogger {

    private static final String TAG = "OkHttpLogger";
    public static final long DEFAULT_READ_TIMEOUT_MILLIS = 15 * 1000;
    public static final long DEFAULT_WRITE_TIMEOUT_MILLIS = 20 * 1000;
    public static final long DEFAULT_CONNECT_TIMEOUT_MILLIS = 20 * 1000;
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;

    private static volatile OkHttpLogger instance;
    private OkHttpClient okHttpClient;

    /**
     * 获取log拦截器，单例模式，仅获取一次，共APP全局使用
     */
    private OkHttpLogger() {
//      设置HttpLoggingInterceptor
        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        try {
                            Log.i(TAG, message);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(TAG, message);
                        }
                    }
                });


        //包含header、body数据
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        setlevel用来设置日志打印的级别，共包括了四个级别：NONE,BASIC,HEADER,BODY
//        BASEIC:请求/响应行
//        HEADER:请求/响应行 + 头
//        BODY:请求/响应行 + 头 + 体

//        初始化OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                //FaceBook 网络调试器，可在Chrome调试网络请求，查看SharePreferences,数据库等
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor).build();
    }

    public static OkHttpLogger getInstance() {
        if (instance == null) {
            synchronized (OkHttpLogger.class) {
                if (instance == null) {
                    instance = new OkHttpLogger();
                }
            }
        }
        return instance;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
