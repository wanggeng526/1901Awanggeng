package com.example.yurisa.zhoumozuoye1_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class MainActivity extends AppCompatActivity {

    private Toolbar mMytoolbar;
    private RecyclerView mMyrec;
    private ArrayList<DatasBean> beans;
    private RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        mMytoolbar.setTitle("");
        setSupportActionBar(mMytoolbar);
        mMyrec = (RecyclerView) findViewById(R.id.myrec);
        mMyrec.setLayoutManager(new LinearLayoutManager(this));
        beans = new ArrayList<>();
        getData();
        recAdapter = new RecAdapter(this, beans);
        mMyrec.setAdapter(recAdapter);

    }

    public void getData() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(ApiService.getUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
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
                        beans.addAll(bean.getDatas());
                        Log.i("====", "onNext: "+bean.toString());
                        recAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("====", "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    interface ApiService{
        String getUrl = "http://static.owspace.com/";
        @GET("?c=api&a=getList&page_id=0")
        Observable<Bean> getData();
    }
}
