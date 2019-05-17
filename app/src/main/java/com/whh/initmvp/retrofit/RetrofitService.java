package com.whh.initmvp.retrofit;


import com.whh.initmvp.Model.AppVersionModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wuhuihui on 2019/5/17.
 * RetrofitService：管理Retrofit的各种数据请求(post、get),包含请求api、请求参数
 */

public interface RetrofitService {

    //get请求
//    @GET("appversion-getAppVersion")
//    Observable<AppVersionModel> getAppVersion(@Query("currentVersion") String currentVersion,
//                               @Query("type") String type);

    //post请求
    @FormUrlEncoded
    @POST("appversion-getAppVersion")
    Observable<AppVersionModel> getAppVersion(@Field("currentVersion") String currentVersion,
                                              @Field("type") String type);
}
