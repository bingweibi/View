package com.example.bibingwei.model.network.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 */
public interface LuckImageApi {
    @GET("data/福利/10/{page}")
    Observable<com.example.bibingwei.bean.LuckImage> getImage(@Path("page") int page);
}
