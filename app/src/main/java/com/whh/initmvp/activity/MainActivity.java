package com.whh.initmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.whh.baselib.activity.BaseActivity;
import com.whh.baselib.utils.SystemUtils;
import com.whh.initmvp.R;
import com.whh.initmvp.model.GitUser;
import com.whh.initmvp.presenter.GitUserPresenter;
import com.whh.initmvp.utils.ContantUtils;
import com.whh.initmvp.view.GitUserView;

import io.reactivex.disposables.CompositeDisposable;

import static com.whh.initmvp.R.id.initRecyclerView;

/**
 * Created by wuhuihui on 2019/5/16.
 * https加载githubUser信息
 */
public class MainActivity extends BaseActivity {

    private TextView showData;

    private GitUserPresenter gitUserPresenter = new GitUserPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("https请求加载", R.layout.activity_main);

        showGitUser(); //显示gitUser

        initEventBus(); //EventBus事件

        initGlide(); //Glide

        initRecyclerView(); //initRecyclerView

//        initARouter(); //初始化ARouter

    }

    /**
     * 显示gitUser
     */
    private void showGitUser() {
        findViewById(R.id.loadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemUtils.isNetworkAvailable(activity)) {
                    showData = (TextView) findViewById(R.id.showData);
//                    showData.setMovementMethod(ScrollingMovementMethod.getInstance()); //设置可滑动
                    showData.setMovementMethod(LinkMovementMethod.getInstance()); //设置链接

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
                } else
                    Toast.makeText(getApplicationContext(), "当前网络不可用", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * EventBus
     */
    private void initEventBus() {
        findViewById(R.id.eventBus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, EventBusActivity.class));
            }
        });
    }

    /**
     * Glide
     */
    private void initGlide() {
        findViewById(R.id.glide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, GlideActivity.class));
            }
        });
    }

    /**
     * RecyclerView
     */
    private void initRecyclerView() {
        findViewById(initRecyclerView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, RecyclerViewActivity.class));
            }
        });
    }

    /**
     * ARouter
     */
//    private void initARouter() {
//        ARouter.getInstance().inject(this); //注入到当前页面
//
//        findViewById(R.id.arouter).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/arouter/ARouterResultActivity").withString("key", "ARouter20190708 14:46").navigation();
//            }
//        });
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        gitUserPresenter.onDestory(); //取消view的引用，避免内存泄漏

        //清除所有订阅
        CompositeDisposable compositeDisposable = ContantUtils.compositeDisposable;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }
}
