package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.ZhiHu;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * http://news-at.zhihu.com/api/4/news/latest
 */
public interface ZhiHuApi {

    @GET("api/4/news/latest")
    Observable<ZhiHu> getStoriesInfo();
}
