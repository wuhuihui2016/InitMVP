package com.whh.initmvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.model.Weather;
import com.whh.initmvp.presenter.WeatherPresenter;
import com.whh.initmvp.view.WeatherView;

/**
 * Created by wuhuihui on 2019/5/16.
 * 加载天气信息
 */

public class WeatherActivity extends BaseActivity{

    private TextView showData;
    private Button requestData;

    private WeatherPresenter weatherPresenter = new WeatherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showData = (TextView) findViewById(R.id.showData);
        requestData = (Button) findViewById(R.id.requestData);
        requestData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherPresenter.getWeather(); //开始请求数据
            }
        });

        weatherPresenter.onCreate(); //启动Presenter,订阅View
        //刷新UI,显示数据
        weatherPresenter.attachView(new WeatherView() {
            @Override
            public void onSuccess(Weather weather) {
                showData.setText("\n\n\ngetTime:\n" + weather.getTime()
                        + "\n\n\ngetCityInfo:\n" + weather.getCityInfo()
                        + "\n\n\ngetData:\n" + weather.getData()
                );

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
        weatherPresenter.onDestory(); //取消view的引用，避免内存泄漏
    }
}
