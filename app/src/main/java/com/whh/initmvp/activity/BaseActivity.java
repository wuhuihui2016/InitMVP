package com.whh.initmvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.whh.initmvp.common.ContantUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public class BaseActivity extends Activity {

    protected Activity activity;
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        activity = this;
        context = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CompositeDisposable compositeDisposable = ContantUtils.compositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
