package com.example.bibingwei.view.api;

import com.example.bibingwei.view.bean.ZhiHuDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 *
 */
public interface ZhihuDetailApi {
    @GET("api/4/news/{id}")
    Call<ZhiHuDetail> getZhiHuDetailInfo(@Path("id") int id);
}
