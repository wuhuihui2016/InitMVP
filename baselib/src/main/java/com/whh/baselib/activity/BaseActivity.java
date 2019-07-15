package com.whh.baselib.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
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

import com.whh.baselib.R;
import com.whh.baselib.utils.FileUtil;
import com.whh.baselib.utils.SystemUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected Activity activity;
    protected Context context;
    protected String TAG;
    protected FrameLayout content_layout;
    
    public static String DEFAULT_COVERAGE_FILE_PATH = Environment.getExternalStorageDirectory()+"/";

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

        requestPermission(
                new String[]{
                        "android.permission.WRITE_EXTERNAL_STORAGE",
                        "android.permission.READ_EXTERNAL_STORAGE",
                }
        );

    }

    private final int PERMISSION_REQUEST_CODE = 2000;
    @SuppressLint("WrongConstant")
    private void requestPermission(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int resultCode = 0;
            for (String str : permissions) {
                resultCode = checkSelfPermission(str);
                if (resultCode != 0) {
                    if (resultCode == PackageManager.PERMISSION_DENIED) {
                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                        break;
                    }
                }
            }
        }
        Log.i("wuhuihui", "BaseActivity.java BaseActivity requestPermission :");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限通过
            } else {
                // 权限拒绝
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    // 禁止后不再询问了！
                } else {
                    // 用户此次选择了禁止权限
                }
            }
            return;
        }
        Log.i("wuhuihui", "BaseActivity.java BaseActivity onRequestPermissionsResult :");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        FileUtil.createFile(DEFAULT_COVERAGE_FILE_PATH, "initMVp_coverage.ec");
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
        generateCoverageFile();
        Log.i(TAG, "lifecycle---" + TAG + " onDestroy");

    }

    /**
     * 生成executionData
     */
    public void generateCoverageFile() {

        OutputStream out = null;

        try {
            out = new FileOutputStream(DEFAULT_COVERAGE_FILE_PATH + "/initmvp_coverage.ec", false);
            Object agent = Class.forName("org.jacoco.agent.rt.RT").getMethod("getAgent").invoke(null);
            // 这里之下就统计不到了
            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class).invoke(agent, false));

            Log.i("wuhuihui", "BaseActivity.java BaseActivity generateCoverageFile write success");
        } catch (Exception e) {
            Log.i("wuhuihui", "BaseActivity.java BaseActivity generateCoverageFile Exception:" + e.toString());

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
