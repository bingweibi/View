package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.ZhiHu;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * http://news-at.zhihu.com/api/4/news/latest
 */
public interface ZhihuApi {

    @GET("api/4/news/latest")
    Observable<List<ZhiHu.StoriesBean>> getStoriesInfo();
}
