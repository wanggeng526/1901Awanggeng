package com.example.yurisa.zhoumozuoye1_1;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService{
        String getUrl = "https://api.yunxuekeji.cn/";
        @GET("yunxue_app_api/content/getData/30/66597/1/10")
        Observable<Bean> getData();

        @GET("yunxue_app_api/teacher/getTeacherPower/{num}")
        Observable<XiangQingBean> getXiangQing(@Path("num") int num);
    }