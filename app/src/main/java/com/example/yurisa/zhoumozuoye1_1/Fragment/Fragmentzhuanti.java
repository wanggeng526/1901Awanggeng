package com.example.yurisa.zhoumozuoye1_1.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yurisa.zhoumozuoye1_1.R;

/**
 * Created by yurisa on 2019/9/20.
 */

public class Fragmentzhuanti extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(container.getContext(), R.layout.fragment3,null);
    }
}
