package com.whh.initmvp.retrofit;


import com.whh.initmvp.model.AppVersion;
import com.whh.initmvp.model.Weather;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by wuhuihui on 2019/5/17.
 * RetrofitService：管理Retrofit的各种数据请求(post、get),包含请求api、请求参数
 */

public interface RetrofitService {

    //get请求
//    @GET("appversion-getAppVersion")
//    Observable<AppVersion> getAppVersion(@Query("currentVersion") String currentVersion,
//                               @Query("type") String type);

    //post请求
    @FormUrlEncoded
    @POST("appversion-getAppVersion")
    Observable<AppVersion> getAppVersion(@Field("currentVersion") String currentVersion,
                                         @Field("type") String type);
    //get请求
    @GET("api/weather/city/101010100")
    Observable<Weather> getWeather();
}
