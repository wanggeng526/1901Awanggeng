package com.example.yurisa.zhoumozuoye1_3;

import java.util.ArrayList;

/**
 * Created by yurisa on 2019/9/22.
 */

public class BusBean {
    private ArrayList<Bean.ResultsBean> beans;
    private int pos;

    public BusBean(ArrayList<Bean.ResultsBean> beans, int pos) {
        this.beans = beans;
        this.pos = pos;
    }

    public ArrayList<Bean.ResultsBean> getBeans() {
        return beans;
    }

    public void setBeans(ArrayList<Bean.ResultsBean> beans) {
        this.beans = beans;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
