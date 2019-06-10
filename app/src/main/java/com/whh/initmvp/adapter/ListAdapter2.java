package com.whh.initmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whh.initmvp.R;
import com.whh.initmvp.model.EventMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhuihui on 2019/6/10.
 */

public class ListAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_ONE,
        ITEM_TYPE_TWO
    }

    private Context context;
    private LayoutInflater layoutInflater;
    private List<EventMessage> eventMessages = new ArrayList<>();

    public ListAdapter2(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void addList(List<EventMessage> list) {
        eventMessages.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE.ITEM_TYPE_ONE.ordinal()) {
            return new OneHolder(layoutInflater.inflate(R.layout.item_layout, parent, false));
        } else {
            return new TwoHolder(layoutInflater.inflate(R.layout.item_layout2, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        EventMessage eventMessage = eventMessages.get(position);
        if (holder instanceof OneHolder) {
            ((OneHolder) holder).msg.setText(eventMessage.getMessage());
        } else if (holder instanceof TwoHolder) {
            ((TwoHolder) holder).type.setText(eventMessage.getType() + "");
            ((TwoHolder) holder).msg.setText(eventMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return eventMessages == null ? 0 : eventMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_ONE.ordinal() : ITEM_TYPE.ITEM_TYPE_TWO.ordinal();
    }

    public static class OneHolder extends RecyclerView.ViewHolder {
        public TextView msg;

        OneHolder(View view) {
            super(view);

            msg = (TextView) view.findViewById(R.id.msg);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("OneHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }

    public static class TwoHolder extends RecyclerView.ViewHolder {
        public TextView type;
        public TextView msg;


        TwoHolder(View view) {
            super(view);

            type = (TextView) view.findViewById(R.id.type);
            msg = (TextView) view.findViewById(R.id.msg);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TwoHolder", "onClick--> position = " + getPosition());
                }
            });
        }
    }
}
