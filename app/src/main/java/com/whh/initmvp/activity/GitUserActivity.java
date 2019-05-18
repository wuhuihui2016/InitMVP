package com.whh.initmvp.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.model.GitUser;
import com.whh.initmvp.presenter.GitUserPresenter;
import com.whh.initmvp.view.GitUserView;

/**
 * Created by wuhuihui on 2019/5/16.
 * https加载githubUser信息
 */

public class GitUserActivity extends BaseActivity {

    private TextView showData;

    private GitUserPresenter gitUserPresenter = new GitUserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showData = (TextView) findViewById(R.id.showData);

        gitUserPresenter.onCreate(); //启动Presenter,订阅View
        gitUserPresenter.getGitUser(); //开始请求数据
        //刷新UI,显示数据
        gitUserPresenter.attachView(new GitUserView() {
            @Override
            public void onSuccess(GitUser gitUser) {
                showData.setText(gitUser.toString());
            }

            @Override
            public void onError(String error) {
                showData.setText(error);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        gitUserPresenter.onDestory(); //取消view的引用，避免内存泄漏
    }
}
