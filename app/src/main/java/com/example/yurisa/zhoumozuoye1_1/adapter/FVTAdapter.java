package com.example.yurisa.zhoumozuoye1_1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yurisa on 2019/9/20.
 */

public class FVTAdapter extends FragmentPagerAdapter{
    private ArrayList<String> strings;
    private ArrayList<Fragment> list;

    public FVTAdapter(FragmentManager fm, ArrayList<String> strings, ArrayList<Fragment> list) {
        super(fm);
        this.strings = strings;
        this.list = list;
    }

    public FVTAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }
}
