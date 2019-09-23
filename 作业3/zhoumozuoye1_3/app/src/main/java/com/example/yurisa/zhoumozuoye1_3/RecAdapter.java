package com.example.yurisa.zhoumozuoye1_3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by yurisa on 2019/9/22.
 */

public class RecAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<Bean.ResultsBean> beans;

    public RecAdapter(Context context, ArrayList<Bean.ResultsBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context,R.layout.recitem,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(beans.get(position).getUrl()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(position);
                EventBus.getDefault().post(new BusBean(beans,position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recitem);
        }
    }
    interface OnClick{
        void onClick(int pos);
    }
    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }
}
