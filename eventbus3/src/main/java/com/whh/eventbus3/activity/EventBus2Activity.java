package com.whh.eventbus3.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.whh.baselib.activity.BaseActivity;
import com.whh.eventbus3.R;
import com.whh.eventbus3.model.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by wuhuihui on 2019/5/18.
 * 发送普通事件给EventBusActivity
 * 接收EventBusActivity发来的粘性事件
 */
public class EventBus2Activity extends BaseActivity {

    private TextView showData1, showData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("粘性事件", R.layout.activity_eventbus);

        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this); //注册事件,不可重复注册事件

        showData1 = (TextView) findViewById(R.id.showData1);
        showData1.setText("2222EventBus2Activity");
        showData2 = (TextView) findViewById(R.id.showData2);
        Button send = (Button) findViewById(R.id.send);
        send.setText("2222发送消息");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventMessage(1, "2222eventBus上线啦~~"));
                finish();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.ASYNC, sticky = true)
    public void getEventMessage(EventMessage eMsg) {
        if (eMsg.getType() == 2) showData2.setText(eMsg.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this); //注销事件
    }
}
