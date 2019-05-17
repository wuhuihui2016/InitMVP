package com.whh.initmvp.presenter;

import android.content.Intent;

import com.whh.initmvp.view.View;

/**
 * Created by wuhuihui on 2019/5/17.
 * Presenter：基类方法用于Activity或Fragment在onDestory解绑View
 * 所有presenter的基类，子类实现其方法，
 */

public interface Presenter {

    void onCreate();

    void onStart();

    void onDestory();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}




