package com.example.bibingwei.model.network.api;

import com.example.bibingwei.bean.ZhiHuDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 *
 */
public interface ZhiHuDetailApi {
    @GET("api/4/news/{id}")
    Observable<ZhiHuDetail> getZhiHuDetailInfo(@Path("id") int id);
}
