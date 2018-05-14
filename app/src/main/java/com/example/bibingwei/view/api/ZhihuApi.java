package com.example.bibingwei.view.api;

import com.example.bibingwei.view.bean.ZhiHu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * ttp://news-at.zhihu.com/api/4/news/latest
 */
public interface ZhihuApi {

    @GET("api/4/news/latest")
    Call<ZhiHu> getStoriesInfo();
}
