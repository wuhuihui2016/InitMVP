package com.whh.initmvp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.whh.initmvp.common.MessageEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wuhuihui on 2019/5/18.
 */

public class EventBusActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this); //注册事件

        EventBus.getDefault().post(new MessageEvent("新消息"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); //注销事件
    }
}
