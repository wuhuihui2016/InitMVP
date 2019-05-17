package com.whh.initmvp.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuhuihui on 2019/5/17.
 * RetrofitHelper：初始化Retrofit，设置请求API的baseUrl、gson解析方式、
 */

public class RetrofitHelper {
    private Context context;

    private static RetrofitHelper instance = null;
    private Retrofit retrofit = null;

    private RetrofitHelper(Context context) {
        this.context = context;
        initRetrofit();
    }

    public static RetrofitHelper getInstance(Context context) {
        if (instance == null) instance = new RetrofitHelper(context);
        return instance;
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://ios.mobile.che-by.com/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer(){
        return retrofit.create(RetrofitService.class);
    }

}
