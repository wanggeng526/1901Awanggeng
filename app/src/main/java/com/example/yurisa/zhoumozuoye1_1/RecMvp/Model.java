package com.example.yurisa.zhoumozuoye1_1.RecMvp;

import android.util.Log;

import com.example.yurisa.zhoumozuoye1_1.ApiService;
import com.example.yurisa.zhoumozuoye1_1.Bean;
import com.example.yurisa.zhoumozuoye1_1.XiangQingBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yurisa on 2019/9/20.
 */

public class Model {
    public void getData(final CallBack callBack) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        apiService.getData().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        callBack.Success(bean);
                        Log.i("成功", "onNext: "+bean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.Faild(e.getMessage());
                        Log.i("失败", "onNext: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public interface CallBack{
        void Success(Bean bean);
        void Faild(String str);
    }


}
