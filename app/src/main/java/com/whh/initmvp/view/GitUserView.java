package com.whh.initmvp.view;

import com.whh.initmvp.model.GitUser;

/**
 * Created by wuhuihui on 2019/5/17.
 */

public interface GitUserView extends View{

    void onSuccess(GitUser gitUser);

    void onError(String error);

}
