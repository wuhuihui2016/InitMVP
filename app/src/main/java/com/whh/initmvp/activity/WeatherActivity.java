package com.whh.initmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.whh.baselib.activity.BaseActivity;
import com.whh.baselib.utils.SystemUtils;
import com.whh.initmvp.R;
import com.whh.initmvp.model.Weather;
import com.whh.initmvp.presenter.WeatherPresenter;
import com.whh.initmvp.view.WeatherView;

/**
 * Created by wuhuihui on 2019/5/16.
 * 加载天气信息 因为免费地多次测试，导致IP被封，天气接口访问失败
 */

public class WeatherActivity extends BaseActivity {

    private TextView showData;

    private WeatherPresenter weatherPresenter = new WeatherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("加载天气信息", R.layout.activity_main);

        showWeather(); //显示天气信息

        initEventBus(); //EventBus事件

        initGlide(); //Glide
    }


    /**
     * 显示天气信息
     */
    private void showWeather() {
        findViewById(R.id.loadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemUtils.isNetworkAvailable(activity)) {

                    showData = (TextView) findViewById(R.id.showData);

                    weatherPresenter.onCreate(); //启动Presenter,订阅View
                    weatherPresenter.getWeather(); //开始请求数据
                    //刷新UI,显示数据
                    weatherPresenter.attachView(new WeatherView() {
                        @Override
                        public void onSuccess(Weather weather) {
                            showData.setText("getTime:\n" + weather.getTime()
                                    + "\n\n\ngetCityInfo:\n" + weather.getCityInfo()
                                    + "\n\n\ngetData:\n" + weather.getData()
                            );

                        }

                        @Override
                        public void onError(String error) {
                            showData.setText(error);
                        }
                    });
                } else
                    Toast.makeText(getApplicationContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
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
        weatherPresenter.onDestory(); //取消view的引用，避免内存泄漏
    }
}
