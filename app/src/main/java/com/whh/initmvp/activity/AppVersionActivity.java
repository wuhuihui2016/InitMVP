package com.whh.initmvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button requestData;

    private AppVersionPresenter appVersionPresenter = new AppVersionPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showData = (TextView) findViewById(R.id.showData);
        requestData = (Button) findViewById(R.id.requestData);
        requestData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appVersionPresenter.getAppVersion("6.0", "android"); //开始请求数据
            }
        });

        appVersionPresenter.onCreate(); //启动Presenter,订阅View
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

                requestData.setVisibility(View.GONE);
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
