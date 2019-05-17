package com.whh.initmvp.manager;

import android.content.Context;

import com.whh.initmvp.model.AppVersion;
import com.whh.initmvp.model.Weather;
import com.whh.initmvp.retrofit.RetrofitHelper;
import com.whh.initmvp.retrofit.RetrofitService;

import io.reactivex.Observable;

/**
 * Created by wuhuihui on 2019/5/17.
 * DataManager：数据处理管理器，用于处理多个API Observable
 */

public class DataManager {

    private RetrofitService retrofitService;

    public DataManager(Context context) {
        this.retrofitService = RetrofitHelper.getInstance(context).getServer();
    }

    /**
     * 获取AppVersion
     * @param currentVersion
     * @param type
     * @return
     */
    public Observable<AppVersion> getAppVersion(String currentVersion, String type) {
        return retrofitService.getAppVersion(currentVersion, type);
    }


    /**
     * 获取Weather
     * @return
     */
    public Observable<Weather> getWeather() {
        return retrofitService.getWeather();
    }

}
