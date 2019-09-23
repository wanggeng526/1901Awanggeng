package com.example.yurisa.zhoumozuoye1_3;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by yurisa on 2019/9/22.
 */

public class VpAdapter extends PagerAdapter{
    private ArrayList<Bean.ResultsBean> beans;

    public VpAdapter(ArrayList<Bean.ResultsBean> beans) {
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.vpitem, null);
        ImageView viewById = view.findViewById(R.id.vpimg);
        Glide.with(container.getContext()).load(beans.get(position).getUrl()).into(viewById);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
