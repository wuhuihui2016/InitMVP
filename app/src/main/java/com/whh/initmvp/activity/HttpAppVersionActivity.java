package com.whh.initmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.whh.initmvp.R;
import com.whh.initmvp.common.SystemUtils;
import com.whh.initmvp.model.AppVersion;
import com.whh.initmvp.presenter.AppVersionPresenter;
import com.whh.initmvp.view.AppVersionView;

import java.util.List;

/**
 * Created by wuhuihui on 2019/5/16.
 * 加载版本信息
 */

public class HttpAppVersionActivity extends BaseActivity {

    private TextView showData;

    private AppVersionPresenter appVersionPresenter = new AppVersionPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("加载版本信息", R.layout.activity_main);

        showAppVersion(); //显示版本信息

        initEventBus(); //EventBus事件

        initGlide(); //Glide

    }

    /**
     * 显示版本信息
     */
    private void showAppVersion() {
        findViewById(R.id.loadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SystemUtils.isNetworkAvailable(activity)) {
                    showData = (TextView) findViewById(R.id.showData);

                    appVersionPresenter.onCreate(); //启动Presenter,订阅View
                    appVersionPresenter.getAppVersion("6.0", "android"); //开始请求数据
                    //刷新UI,显示数据
                    appVersionPresenter.attachView(new AppVersionView() {
                        @Override
                        public void onSuccess(AppVersion appVersion) {
                            showData.setText(appVersion.getLatestVersion()
                                    + "\n" + appVersion.getSize()
                                    + "\n" + appVersion.getUrl()
                                    + "\n\n新版本更新内容：\n"
                            );

                            List<String> list = appVersion.getFunctions();
                            for (int i = 0; i < list.size(); i++) {
                                showData.append(list.get(i) + "\n");
                            }
                        }

                        @Override
                        public void onError(String error) {
                            showData.setText(error);
                        }
                    });
                } else Toast.makeText(getApplicationContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * EventBus
     */
    private void initEventBus() {
        findViewById(R.id.eventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, EventBusActivity.class));
            }
        });
    }

    /**
     * Glide
     */
    private void initGlide() {
        findViewById(R.id.glide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, GlideActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appVersionPresenter.onDestory(); //取消view的引用，避免内存泄漏
    }
}
