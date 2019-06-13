package com.whh.initmvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.whh.initmvp.utils.ContantUtils;
import com.whh.initmvp.manager.DataManager;
import com.whh.initmvp.model.Weather;
import com.whh.initmvp.view.View;
import com.whh.initmvp.view.WeatherView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wuhuihui on 2019/5/17.
 * WeatherPresenter 处理Weather返回的数据，与View交互
 */

public class WeatherPresenter implements Presenter {

    private final static String TAG = "WeatherPresenter";

    private Context context;
    private DataManager manager;
    private CompositeDisposable compositeDisposable;
    private WeatherView weatherView;
    private Weather weather;

    public WeatherPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        manager = new DataManager(context); //参数1标识为当前是请求天气信息数据
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
        weatherView = (WeatherView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getWeather() {
        Observable<Weather> observable = manager.getWeather();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Weather value) {
                        weather = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError:" + e.toString());
                        weatherView.onError("请求失败:\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete!");
                        if (weatherView != null) {
                            weatherView.onSuccess(weather);
                            Log.i(TAG, "weather:" + weather.toString());
                        }
                    }
                });
    }

}
