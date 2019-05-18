package com.whh.initmvp.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.model.AppVersion;
import com.whh.initmvp.presenter.AppVersionPresenter;
import com.whh.initmvp.view.AppVersionView;

import java.util.List;

/**
 * Created by wuhuihui on 2019/5/16.
 * 加载版本信息
 */

public class AppVersionActivity extends BaseActivity{

    private TextView showData;

    private AppVersionPresenter appVersionPresenter = new AppVersionPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        appVersionPresenter.onDestory(); //取消view的引用，避免内存泄漏
    }
}
