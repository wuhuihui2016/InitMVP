package com.whh.initmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by wuhuihui on 2019/5/18.
 * 发送粘性事件给EventBus2Activity
 * 接收EventBus2Activity发来的普通事件
 */
public class EventBusActivity extends BaseActivity {

    private TextView showData1, showData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("发送事件", R.layout.activity_eventbus);

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this); //注册事件,不可重复注册事件

        showData1 = (TextView) findViewById(R.id.showData1);
        showData1.setText("1111EventBusActivity");
        showData2 = (TextView) findViewById(R.id.showData2);

        Button send = (Button) findViewById(R.id.send);
        send.setText("1111跳转页面");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new EventMessage(2, "11111发来粘性事件~")); //发送粘性事件
                startActivity(new Intent(activity, EventBus2Activity.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEventMessage(EventMessage eMsg) {
        if (eMsg.getType() == 1) showData2.setText(eMsg.getMessage());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this); //注销事件
    }
}
