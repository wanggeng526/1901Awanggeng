package com.example.yurisa.zhoumozuoye1_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yurisa.zhoumozuoye1_1.RecMvp.MainView;
import com.example.yurisa.zhoumozuoye1_1.RecMvp.Presenter;
import com.example.yurisa.zhoumozuoye1_1.adapter.RecAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView {

    private Toolbar mMytoolbar;
    private RecyclerView mMyrec;
    private ArrayList<Bean.BodyBean.ResultBean> beans;
    private RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Presenter presenter = new Presenter(this);
        presenter.getData();
    }

    private void initView() {
        mMytoolbar = (Toolbar) findViewById(R.id.mytoolbar);
        mMytoolbar.setTitle("");
        setSupportActionBar(mMytoolbar);
        mMyrec = (RecyclerView) findViewById(R.id.myrec);
        mMyrec.setLayoutManager(new LinearLayoutManager(this));
        mMyrec.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        beans = new ArrayList<>();
        recAdapter = new RecAdapter(this, beans);
        mMyrec.setAdapter(recAdapter);

        recAdapter.setOnClick(new RecAdapter.OnClick() {
            @Override
            public void onClick(int pos) {
                EventBus.getDefault().postSticky(beans.get(pos));
                startActivity(new Intent(MainActivity.this,XiangQingActivity.class));
            }
        });
    }

    @Override
    public void getData(Bean bean) {
        beans.addAll(bean.getBody().getResult());
        recAdapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
    }
}
