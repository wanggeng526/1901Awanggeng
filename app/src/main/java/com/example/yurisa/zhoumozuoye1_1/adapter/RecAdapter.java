package com.example.yurisa.zhoumozuoye1_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yurisa.zhoumozuoye1_1.Bean;
import com.example.yurisa.zhoumozuoye1_1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurisa on 2019/9/20.
 */

public class RecAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<Bean.BodyBean.ResultBean> beans;

    public RecAdapter(Context context, ArrayList<Bean.BodyBean.ResultBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(context, R.layout.recitem,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.name.setText(beans.get(position).getTeacherName());
        viewHolder.title.setText(beans.get(position).getTitle());
        List<?> teacherType = beans.get(position).getTeacherType();

        if (teacherType.size()==0){
            viewHolder.jineng.setText("暂无");
        }else if (teacherType.size()==1){
            String[] split = teacherType.get(0).toString().split("=");
            String substring = split[1].substring(0, split[1].length() - 1);
            viewHolder.jineng.setText("#"+substring+"#");
        }else {
            String[] split = teacherType.get(0).toString().split("=");
            String substring = split[1].substring(0, split[1].length() - 1);
            String[] split1 = teacherType.get(1).toString().split("=");
            String substring2 = split1[1].substring(0, split1[1].length() - 1);
            viewHolder.jineng.setText("#"+substring+"#"+"   "+"#"+substring2+"#");
        }
        Glide.with(context).load(beans.get(position).getTeacherPic()).apply(new RequestOptions().circleCrop()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name;
        TextView title;
        TextView jineng;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recimg);
            name = itemView.findViewById(R.id.teachername);
            title = itemView.findViewById(R.id.teachertitle);
            jineng = itemView.findViewById(R.id.teacherjineng);
        }
    }
    public interface OnClick{
        void onClick(int pos);
    }
    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }


}
