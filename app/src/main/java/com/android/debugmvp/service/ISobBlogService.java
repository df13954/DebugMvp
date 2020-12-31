package com.android.debugmvp.service;

import com.android.debugmvp.bean.BaseResponse;
import com.android.debugmvp.bean.SobLoop;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author: 123
 * @date: 2020/12/31
 * @description sob blog
 */
public interface ISobBlogService {
    @GET("/portal/web_size_info/loop")
    Observable<BaseResponse<List<SobLoop>>> getSobLoop();
}
