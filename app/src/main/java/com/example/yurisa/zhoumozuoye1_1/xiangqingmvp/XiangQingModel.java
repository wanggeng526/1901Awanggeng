package com.example.yurisa.zhoumozuoye1_1.xiangqingmvp;

import android.util.Log;

import com.example.yurisa.zhoumozuoye1_1.ApiService;
import com.example.yurisa.zhoumozuoye1_1.Bean;
import com.example.yurisa.zhoumozuoye1_1.RecMvp.Model;
import com.example.yurisa.zhoumozuoye1_1.XiangQingBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yurisa on 2019/9/22.
 */

public class XiangQingModel {
    public void getXiangQingData(final XiangQingCallBack xiangQingCallBack,int num) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .build();
        ApiService apiService = build.create(ApiService.class);
        apiService.getXiangQing(num).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<XiangQingBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XiangQingBean xiangQingBean) {
                        Log.i("======", "onNext: "+xiangQingBean.toString());
                        xiangQingCallBack.XinagQingSuccess(xiangQingBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("======", "onNext: "+e.getMessage().toString());
                        xiangQingCallBack.XinagQingFaild(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface XiangQingCallBack{
        void XinagQingSuccess(XiangQingBean xiangQingBean);
        void XinagQingFaild(String str);
    }

}
