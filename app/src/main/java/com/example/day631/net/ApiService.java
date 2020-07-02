package com.example.day631.net;

import com.example.day631.bean.ProcBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String BASS="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Flowable<ProcBean>getProc();
}
