package com.whh.initmvp.view;

import com.whh.initmvp.model.AppVersion;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public interface AppVersionView extends View{

    void onSuccess(AppVersion appVersion);

    void onError(String error);

}
