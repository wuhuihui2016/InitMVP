package com.whh.initmvp.Presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.whh.initmvp.Model.AppVersionModel;
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

    private DataManager manager;
    private CompositeDisposable compositeDisposable;
    private Context context;
    private AppVersionView appVersionView;
    private AppVersionModel appVersion;

    public AppVersionPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        manager = new DataManager(context);
        compositeDisposable = new CompositeDisposable();
        //RxJava1: mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose(); //解除订阅
            compositeDisposable.clear(); //取消所有订阅
            compositeDisposable = null;

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
        Observable<AppVersionModel> observable = manager.getAppVersion(currentVersion, type);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppVersionModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(AppVersionModel value) {
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
