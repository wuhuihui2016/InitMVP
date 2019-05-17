package com.whh.initmvp.view;

import com.whh.initmvp.Model.AppVersionModel;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public interface AppVersionView extends View{

    void onSuccess(AppVersionModel appVersion);
    void onError(String error);

}
