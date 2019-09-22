package com.example.yurisa.zhoumozuoye1_1.RecMvp;

import android.util.Log;

import com.example.yurisa.zhoumozuoye1_1.Bean;
import com.example.yurisa.zhoumozuoye1_1.XiangQingBean;
import com.example.yurisa.zhoumozuoye1_1.xiangqingmvp.XiangQingView;

/**
 * Created by yurisa on 2019/9/20.
 */

public class Presenter implements Model.CallBack{
    private MainView mainView;
    private Model model;
    public Presenter(MainView mainView) {
        this.mainView = mainView;
        this.model = new Model();
    }

    public void getData() {
        model.getData(this);
    }

    @Override
    public void Success(Bean bean) {
        mainView.getData(bean);
    }

    @Override
    public void Faild(String str) {
        mainView.showToast(str);
    }


}
