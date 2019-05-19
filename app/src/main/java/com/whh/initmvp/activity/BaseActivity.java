package com.whh.initmvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.common.ContantUtils;
import com.whh.initmvp.common.SystemUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected Activity activity;
    protected Context context;
    protected String TAG;
    protected FrameLayout content_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initActivity();

    }

    /**
     * 初始化Activity
     */
    private void initActivity() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置竖屏
        setContentView(R.layout.activity_base_layout);

        context = this;
        activity = this;
        TAG = getLocalClassName(); //初始化常量

        Log.i(TAG, TAG + " is onCreated!");

    }

    /**
     * 设置中间内容布局
     *
     * @param title
     */
    protected void setTitle(String title) {

        ImageButton return_btn = (ImageButton) findViewById(R.id.return_btn);
        if (isOtherActivity()) {
            return_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else {
            return_btn.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(title)) {
            //设置当前界面的title
            TextView titleView = (TextView) findViewById(R.id.title);
            titleView.setText(title);
        } else {
            RelativeLayout top_layout = (RelativeLayout) findViewById(R.id.top_layout);
            top_layout.setVisibility(View.GONE);
        }
    }

    /**
     * 设置中间内容布局
     *
     * @param layoutID
     * @param title
     */
    protected void setContentView(String title, int layoutID) {

        setTitle(title);

        //加载中间布局
        content_layout = (FrameLayout) findViewById(R.id.content_layout);
        content_layout.removeAllViews();
        View view = LayoutInflater.from(this).inflate(layoutID, null);
        content_layout.addView(view);

    }

    /**
     * 设置界面右上角按钮的点击事件
     *
     * @param text
     * @param listener
     */
    protected void setRightBtnListener(CharSequence text, View.OnClickListener listener) {
        if (!TextUtils.isEmpty(text)) {
            TextView right_btn = (TextView) findViewById(R.id.right_btn);
            right_btn.setVisibility(View.VISIBLE);
            right_btn.setText(text);
            right_btn.setOnClickListener(listener);
            findViewById(R.id.right_imgbtn).setVisibility(View.GONE);
        }
    }

    /**
     * 设置界面右上角图片按钮的点击事件
     *
     * @param resId
     * @param listener
     */
    protected void setRightImgBtnListener(int resId, View.OnClickListener listener) {
        ImageView right_imgbtn = (ImageView) findViewById(R.id.right_imgbtn);
        right_imgbtn.setVisibility(View.VISIBLE);
        right_imgbtn.setImageResource(resId);
        right_imgbtn.setOnClickListener(listener);

        findViewById(R.id.right_btn).setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (SystemUtils.isFastDoubleClick()) {
            return;
        }
    }

    /**
     * 判断当前activity是不是非MainActivity
     *
     * @return
     */
    private boolean isOtherActivity() {
        return !TAG.contains("Http");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "lifecycle---" + TAG + " onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "lifecycle---" + TAG + " onDestroy");

        //清除所有订阅
        CompositeDisposable compositeDisposable = ContantUtils.compositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
