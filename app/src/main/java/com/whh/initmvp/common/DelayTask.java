package com.whh.initmvp.common;

/**
 * Created by wuhuihui on 2017/5/22.
 */

import android.os.AsyncTask;

/**
 * 延时操作
 */
public class DelayTask extends AsyncTask<Void, Void, Void> {

    private long time;//延时时间
    private ICallBack callBack;//操作回调

    public DelayTask(long time, ICallBack callBack) {
        this.time = time;
        this.callBack = callBack;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        callBack.deal();
    }

    public interface ICallBack {
        void deal();
    }

}
