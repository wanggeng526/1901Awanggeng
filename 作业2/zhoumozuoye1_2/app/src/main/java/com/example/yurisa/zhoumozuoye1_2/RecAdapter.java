package com.example.yurisa.zhoumozuoye1_2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.xts.greendaodemo.db.DatasBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yurisa on 2019/9/22.
 */

public class RecAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<DatasBean> beans;
    private DatasBeanDao baseApp = BaseApp.getInstance().getDaoSession().getDatasBeanDao();
    private TextView guanzhu;

    public RecAdapter(Context context, ArrayList<DatasBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.recitem, null);


        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(beans.get(position).getTitle());
        viewHolder.tv2.setText(beans.get(position).getAuthor());
        Glide.with(context).load(beans.get(position).getAvatar()).apply(new RequestOptions().circleCrop()).into(viewHolder.img);
        guanzhu = viewHolder.guanzhu;
        List<DatasBean> datasBeans = baseApp.loadAll();
        for (int i = 0; i < datasBeans.size(); i++) {
            if (datasBeans.get(i).getId().equals(beans.get(position).getId())){
                guanzhu.setText("取消");
            }
        }
        viewHolder.guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = viewHolder.guanzhu.getText().toString();
                if (s.equals("关注")){
                    baseApp.insertOrReplace(beans.get(position));
                    Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
                    viewHolder.guanzhu.setText("取消");

                    List<DatasBean> greenDaoBeans = baseApp.loadAll();
                    Log.i("---", "onClick: "+greenDaoBeans.toString());

                }
                if (s.equals("取消")){
                        DatasBean datasBean = beans.get(position);

                        baseApp.deleteByKey(datasBean.getId());
                        viewHolder.guanzhu.setText("关注");
                        Toast.makeText(context, "取消关注", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView tv;
        TextView tv2;
        TextView guanzhu;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.recimg);
            tv = itemView.findViewById(R.id.rectitle);
            tv2 = itemView.findViewById(R.id.recretitle);
            guanzhu = itemView.findViewById(R.id.guanzhu);
        }
    }

}
