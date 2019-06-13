package com.whh.initmvp.receiver;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.whh.baselib.utils.SystemUtils;

/**
 * IntentService的使用
 * Created by wuhuihui on 2019/6/10.
 */
public class NetworkReceiver extends BroadcastReceiver {

    private static final String TAG = NetworkReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (SystemUtils.isNetworkAvailable(context)) {
            // upload data by start intentservice
            Intent serviceIntent = new Intent(context, NetworkService.class);
            context.startService(serviceIntent);
        }

    }

    public static class NetworkService extends IntentService {

        public NetworkService() {
            super("NetworkService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            //执行耗时操作
        }
    }
}