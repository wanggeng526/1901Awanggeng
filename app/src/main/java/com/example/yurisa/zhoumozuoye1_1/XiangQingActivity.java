package com.example.yurisa.zhoumozuoye1_1;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yurisa.zhoumozuoye1_1.Fragment.Fragmentjieshao;
import com.example.yurisa.zhoumozuoye1_1.Fragment.Fragmentkecheng;
import com.example.yurisa.zhoumozuoye1_1.Fragment.Fragmentzhuanti;
import com.example.yurisa.zhoumozuoye1_1.adapter.FVTAdapter;
import com.example.yurisa.zhoumozuoye1_1.xiangqingmvp.XiangQingPresenter;
import com.example.yurisa.zhoumozuoye1_1.xiangqingmvp.XiangQingView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class XiangQingActivity extends AppCompatActivity implements XiangQingView {

    private ImageView mRecimg;
    /**
     * 华健
     */
    private TextView mTeachername;
    /**
     * 石化大学长者
     */
    private TextView mTeachertitle;
    /**
     * #人力资源#
     */
    private TextView mTeacherjineng;
    private TabLayout mMytab;
    private ViewPager mMyvp;
    private int id = 80;
    private List<XiangQingBean.BodyBean.ResultBean> result = new ArrayList<>();
    private XiangQingPresenter xiangQingPresenter;
    private Bean.BodyBean.ResultBean sendbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        xiangQingPresenter = new XiangQingPresenter(this);

        initView();
        EventBus.getDefault().register(this);


    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getBus(Bean.BodyBean.ResultBean bean){

        id = bean.getID();
        xiangQingPresenter.getXiangQingData(id);
        Log.i("id+++++", "getBus: "+id);
        Log.i("name+++++", "getBus: "+ bean.getTeacherName());
        mTeachername.setText(bean.getTeacherName());
        mTeachertitle.setText(bean.getTitle());

        List<?> teacherType = bean.getTeacherType();
        if (teacherType.size()==0){
            mTeacherjineng.setText("暂无");
        }else if (teacherType.size()==1){
            String[] split = teacherType.get(0).toString().split("=");
            String substring = split[1].substring(0, split[1].length() - 1);
            mTeacherjineng.setText("#"+substring+"#");
        }else {
            String[] split = teacherType.get(0).toString().split("=");
            String substring = split[1].substring(0, split[1].length() - 1);
            String[] split1 = teacherType.get(1).toString().split("=");
            String substring2 = split1[1].substring(0, split1[1].length() - 1);
            mTeacherjineng.setText("#"+substring+"#"+"   "+"#"+substring2+"#");
        }
        Glide.with(this).load(bean.getTeacherPic()).apply(new RequestOptions().circleCrop()).into(mRecimg);

    }




    private void initView() {

        mRecimg = (ImageView) findViewById(R.id.recimg);
        mTeachername = (TextView) findViewById(R.id.teachername);
        mTeachertitle = (TextView) findViewById(R.id.teachertitle);
        mTeacherjineng = (TextView) findViewById(R.id.teacherjineng);
        mMytab = (TabLayout) findViewById(R.id.mytab);
        mMyvp = (ViewPager) findViewById(R.id.myvp);

    }

    private void initTab() {

       if (result!=null){
           if (result.size()==1){
               initTab_1();
           } else if(result.size()==2){
               initTab_2();
           }else if (result.size()==3){
               initTab_3();
           }
       }
    }

    private void initTab_1() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragmentjieshao());
        ArrayList<String> list = new ArrayList<>();
        list.add(result.get(0).getDescription());
        FVTAdapter fvtAdapter = new FVTAdapter(getSupportFragmentManager(), list, fragments);
        mMyvp.setAdapter(fvtAdapter);
        mMytab.setupWithViewPager(mMyvp);
    }

    private void initTab_3() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragmentjieshao());
        fragments.add(new Fragmentkecheng());
        fragments.add(new Fragmentzhuanti());
        ArrayList<String> list = new ArrayList<>();
        list.add(result.get(0).getDescription());
        list.add(result.get(1).getDescription());
        list.add(result.get(2).getDescription());
        FVTAdapter fvtAdapter = new FVTAdapter(getSupportFragmentManager(), list, fragments);
        mMyvp.setAdapter(fvtAdapter);
        mMytab.setupWithViewPager(mMyvp);
    }

    private void initTab_2() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragmentjieshao());
        fragments.add(new Fragmentzhuanti());
        ArrayList<String> list = new ArrayList<>();
        list.add(result.get(0).getDescription());
        list.add(result.get(1).getDescription());
        FVTAdapter fvtAdapter = new FVTAdapter(getSupportFragmentManager(), list, fragments);
        mMyvp.setAdapter(fvtAdapter);
        mMytab.setupWithViewPager(mMyvp);

    }

    @Override
    public void getXiangQing(XiangQingBean xiangQingBean) {
        List<XiangQingBean.BodyBean.ResultBean> resultlist = xiangQingBean.getBody().getResult();
        Log.i("get", "getXiangQing: "+resultlist.size());
        result.addAll(resultlist);
        initTab();
    }

}
