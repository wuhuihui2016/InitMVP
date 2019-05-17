package com.whh.initmvp.view;

import com.whh.initmvp.model.Weather;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public interface WeatherView extends View{

    void onSuccess(Weather weather);

    void onError(String error);

}
