package com.whh.irisking.arouter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/arouter/ARouterResultActivity")
public class ARouterResultActivity extends Activity {

    @Autowired()
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arouter);

        TextView receiveMsg = (TextView) findViewById(R.id.receiveMsg);
        receiveMsg.setText(key);

    }
}
