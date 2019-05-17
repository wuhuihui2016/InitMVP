package com.whh.initmvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.whh.initmvp.common.ContantUtils;
import com.whh.initmvp.model.AppVersion;
import com.whh.initmvp.manager.DataManager;
import com.whh.initmvp.view.AppVersionView;
import com.whh.initmvp.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wuhuihui on 2019/5/17.
 * AppVersionPresenter 处理AppVersion返回的数据，与View交互
 */

public class AppVersionPresenter implements Presenter {

    private final static String TAG = "AppVersionPresenter";

    private Context context;
    private DataManager manager;
    private CompositeDisposable compositeDisposable;
    private AppVersionView appVersionView;
    private AppVersion appVersion;

    public AppVersionPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        manager = new DataManager(context); //参数0标识为当前是请求版本信息数据
        compositeDisposable = ContantUtils.compositeDisposable;
        //RxJava1: mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onDestory() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose(); //解除订阅

            //RxJava1:CompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        appVersionView = (AppVersionView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getAppVersion(String currentVersion, String type) {
        Observable<AppVersion> observable = manager.getAppVersion(currentVersion, type);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppVersion>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AppVersion value) {
                        appVersion = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError:" + e.toString());
                        appVersionView.onError("请求失败:\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete!");
                        if (appVersionView != null) {
                            appVersionView.onSuccess(appVersion);
                            Log.i(TAG, "appVersion:" + appVersion.toString());
                        }
                    }
                });
    }

}
