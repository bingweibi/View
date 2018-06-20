package com.example.bibingwei.network.api;

import com.example.bibingwei.bean.ZhiHu;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 * http://news-at.zhihu.com/api/4/news/latest
 * 过往消息:https://news-at.zhihu.com/api/4/news/before/20180619
 */
public interface ZhiHuApi {

    @GET("api/4/news/latest")
    Observable<ZhiHu> getStoriesInfo();

    @GET("api/4/news/before/{date}")
    Observable<ZhiHu> getOldStoriesInfo(@Path("date") String date);
}
