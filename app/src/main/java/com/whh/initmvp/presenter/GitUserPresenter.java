package com.whh.initmvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.whh.initmvp.common.ContantUtils;
import com.whh.initmvp.manager.DataManager;
import com.whh.initmvp.model.GitUser;
import com.whh.initmvp.view.GitUserView;
import com.whh.initmvp.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wuhuihui on 2019/5/18.
 * GitUserPresenter 处理GitUser返回的数据，与View交互
 */

public class GitUserPresenter implements Presenter {

    private final static String TAG = "GitUserPresenter";

    private Context context;
    private DataManager manager;
    private CompositeDisposable compositeDisposable;
    private GitUserView gitUserView;
    private GitUser gitUser;

    public GitUserPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        manager = new DataManager(context);
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
        gitUserView = (GitUserView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getGitUser() {
        Observable<GitUser> observable = manager.getGitUser();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GitUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GitUser value) {
                        gitUser = value;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Log.i(TAG, "onError:" + e.toString());
                        gitUserView.onError("请求失败:\n" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete!");
                        if (gitUserView != null) {
                            gitUserView.onSuccess(gitUser);
                            Log.i(TAG, "gitUser:" + gitUser.toString());
                        }
                    }
                });
    }

}
