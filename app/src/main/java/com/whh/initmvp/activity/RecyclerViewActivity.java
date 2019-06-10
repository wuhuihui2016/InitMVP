package com.whh.initmvp.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.whh.initmvp.R;
import com.whh.initmvp.adapter.ListAdapter;
import com.whh.initmvp.adapter.ListAdapter2;
import com.whh.initmvp.model.EventMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuihui on 2019/6/10.
 */

public class RecyclerViewActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private ListAdapter2 adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView("RecyclerView", R.layout.activity_recyclerview);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//        //简单加载
//        if (adapter == null) {
//            adapter = new ListAdapter(R.layout.item_layout, null);
//            recyclerView.setAdapter(adapter);
//        } else adapter.notifyDataSetChanged();
//        adapter.setNewData(initData());

        //复杂加载数据(多个不同item)
        if (adapter2 == null) {
            adapter2 = new ListAdapter2(activity);
            recyclerView.setAdapter(adapter2);
        } else adapter2.notifyDataSetChanged();
        adapter2.addList(initData());
    }

    /**
     * 初始化数据
     * @return
     */
    private List<EventMessage> initData() {
        List<EventMessage> eventMessages = new ArrayList<>();
        eventMessages.add(new EventMessage(0, "000"));
        eventMessages.add(new EventMessage(1, "111"));
        eventMessages.add(new EventMessage(2, "222"));
        eventMessages.add(new EventMessage(3, "333"));
        eventMessages.add(new EventMessage(4, "444"));
        return eventMessages;
    }
}
