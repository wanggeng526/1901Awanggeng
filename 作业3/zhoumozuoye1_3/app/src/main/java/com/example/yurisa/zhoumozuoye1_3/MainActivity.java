package com.example.yurisa.zhoumozuoye1_3;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mMyfr;
    private Frammentrec frammentrec;
    private Frammentvp frammentvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMyfr = (FrameLayout) findViewById(R.id.myfr);
        frammentrec = new Frammentrec();
        frammentvp = new Frammentvp();
        final FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.myfr,frammentrec).add(R.id.myfr,frammentvp).show(frammentrec).hide(frammentvp).commit();
        frammentrec.setFragOnClick(new Frammentrec.FragOnClick() {
            @Override
            public void fragOnClick(int pos) {
                manager.beginTransaction()
                        .show(frammentvp).hide(frammentrec).commit();
            }
         });
    }
}
