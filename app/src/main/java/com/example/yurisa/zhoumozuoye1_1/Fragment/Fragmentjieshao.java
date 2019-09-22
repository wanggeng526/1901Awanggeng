package com.example.yurisa.zhoumozuoye1_1.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yurisa.zhoumozuoye1_1.Bean;
import com.example.yurisa.zhoumozuoye1_1.R;
import com.example.yurisa.zhoumozuoye1_1.XiangQingActivity;

/**
 * Created by yurisa on 2019/9/20.
 */

public class Fragmentjieshao extends Fragment {

    private View view;
    /**
     * 导师介绍
     */
    private TextView mJieshao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(container.getContext(), R.layout.fargment1, null);


        return inflate;
    }

}
