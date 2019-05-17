package com.whh.initmvp;

import android.app.Application;
import android.content.Context;

import com.whh.initmvp.common.ContantUtils;


/**
 * Created by Administrator on 2019/5/16.
 */

public class MyApplication extends Application {
    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        ContantUtils.setCompositeDisposable();
    }

    public static Context getInstance(){
        return context;
    }
}
