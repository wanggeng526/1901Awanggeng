package com.example.yurisa.zhoumozuoye1_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by yurisa on 2019/9/22.
 */

public class Frammentvp extends Fragment {
    private View view;
    private ViewPager mMyvp;
    private ArrayList<Bean.ResultsBean> beans;
    private int pos;
    private VpAdapter vpAdapter;
    private TextView mVptv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(container.getContext(), R.layout.framgmentviewpager, null);

        initView(inflate);
        EventBus.getDefault().register(this);
        return inflate;
    }

    @Subscribe
    public void getBus(BusBean busBean) {
        if (busBean != null) {
            beans.addAll(busBean.getBeans());
            Log.i("----", "getBus: " + busBean.toString());
            vpAdapter.notifyDataSetChanged();
            pos = busBean.getPos();
            mMyvp.setCurrentItem(pos);

            mVptv.setText(pos+1+"/"+beans.size());
            Log.i("pos", "getBus: "+pos);
        }
    }

    private void initView(View inflate) {
        mVptv = (TextView) inflate.findViewById(R.id.vptv);
        mMyvp = (ViewPager) inflate.findViewById(R.id.myvp);
        beans = new ArrayList<>();
        vpAdapter = new VpAdapter(beans);
        mMyvp.setAdapter(vpAdapter);
        mMyvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mVptv.setText(position+1+"/"+beans.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
