package com.example.yurisa.zhoumozuoye1_1.xiangqingmvp;

import android.util.Log;

import com.example.yurisa.zhoumozuoye1_1.RecMvp.Model;
import com.example.yurisa.zhoumozuoye1_1.XiangQingBean;

/**
 * Created by yurisa on 2019/9/22.
 */

public class XiangQingPresenter implements XiangQingModel.XiangQingCallBack{
    private XiangQingView xiangQingView;
    private XiangQingModel xiangQingModel;
    public XiangQingPresenter(XiangQingView xiangQingView) {
        this.xiangQingView = xiangQingView;
        this.xiangQingModel = new XiangQingModel();
    }

    public void getXiangQingData(int num) {

        xiangQingModel.getXiangQingData(this,num);
        Log.i("getXiangQingData", "getXiangQingData: "+num);
    }

    @Override
    public void XinagQingSuccess(XiangQingBean xiangQingBean) {
        Log.i("xqcg", "XinagQingSuccess: "+xiangQingBean.toString());
        xiangQingView.getXiangQing(xiangQingBean);

    }

    @Override
    public void XinagQingFaild(String str) {

    }
}
