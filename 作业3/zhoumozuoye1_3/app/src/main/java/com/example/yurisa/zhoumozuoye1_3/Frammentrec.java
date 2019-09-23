package com.example.yurisa.zhoumozuoye1_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by yurisa on 2019/9/22.
 */

public class Frammentrec extends Fragment{

    private RecyclerView rec;
    private ArrayList<Bean.ResultsBean> beans;
    private RecAdapter recAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(container.getContext(), R.layout.ftagmentrec, null);
        rec = inflate.findViewById(R.id.myrec);
        rec.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        beans = new ArrayList<>();
        getData();
        recAdapter = new RecAdapter(getActivity(), beans);
        rec.setAdapter(recAdapter);
        recAdapter.setOnClick(new RecAdapter.OnClick() {
            @Override
            public void onClick(int pos) {
                fragOnClick.fragOnClick(pos);

            }
        });
        return inflate;
    }

    public void getData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = build.create(ApiService.class);
        Observable<Bean> data = apiService.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        beans.addAll(bean.getResults());
                        recAdapter.notifyDataSetChanged();
                        Log.i("=====", "onNext: "+bean.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("=====", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    interface ApiService{
        String getUrl = "http://gank.io/api/";
        @GET("data/福利/20/1")
        Observable<Bean> getData();
    }
    interface FragOnClick{
        void fragOnClick(int pos);
    }
    FragOnClick fragOnClick;

    public void setFragOnClick(FragOnClick fragOnClick) {
        this.fragOnClick = fragOnClick;
    }
}
