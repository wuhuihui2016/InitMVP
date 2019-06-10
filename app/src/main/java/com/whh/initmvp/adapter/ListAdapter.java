package com.whh.initmvp.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.whh.initmvp.R;
import com.whh.initmvp.model.EventMessage;

import java.util.List;

/**
 * Created by wuhuihui on 2019/6/10.
 */

public class ListAdapter extends BaseQuickAdapter<EventMessage, BaseViewHolder>{


    public ListAdapter(@LayoutRes int layoutResId, @Nullable List<EventMessage> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventMessage item) {
        helper.setText(R.id.msg, item.getMessage());
    }

}
