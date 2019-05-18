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
        observable.subscribeOn(Schedulers.io()) //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果
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

    /**
     * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程.
     多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.

     作者：Season_zlc
     链接：https://www.jianshu.com/p/8818b98c44e2
     来源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     */

}
