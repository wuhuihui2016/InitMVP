package com.whh.initmvp.retrofit;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.whh.initmvp.MyApp;
import com.whh.initmvp.common.ContantUtils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuhuihui on 2019/5/17.
 * RetrofitHelper：初始化Retrofit，设置请求API的baseUrl、gson解析方式
 */

public class RetrofitHelper {
    private final String TAG = "RetrofitHelper";
    private Context context;

    private static RetrofitHelper instance = null;
    private Retrofit retrofit = null;

    private RetrofitHelper(Context context) {
        this.context = context;
        initRetrofit();
//        initWeatherRetrofit();
//        initGitUserRetrofit();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) instance = new RetrofitHelper(context);
        return instance;
    }

    private void initRetrofit() {
        Log.i(TAG, ContantUtils.APPVERSION_BASE_URL);
        retrofit = new Retrofit.Builder()
                .baseUrl(ContantUtils.APPVERSION_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MyApp.okHtpClient)
                .build();
    }

    /**
     * 访问天气的网络请求
     */
    private void initWeatherRetrofit() {
        Log.i(TAG, ContantUtils.WEATHER_BASE_URL);
        retrofit = new Retrofit.Builder()
                .baseUrl(ContantUtils.WEATHER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MyApp.okHtpClient)
                .build();
    }

    /**
     * GitUser https请求
     */
    private void initGitUserRetrofit() {
        Log.i(TAG, ContantUtils.GITHUBSER_BASE_URL);
        retrofit = new Retrofit.Builder()
                .baseUrl(ContantUtils.GITHUBSER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MyApp.okHtpClient)
                .build();
    }

    public RetrofitService getServer() {
        return retrofit.create(RetrofitService.class);
    }

}
