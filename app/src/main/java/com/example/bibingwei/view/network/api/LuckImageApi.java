package com.example.bibingwei.view.network.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 */
public interface LuckImageApi {
    @GET("data/福利/10/{page}")
    Observable<com.example.bibingwei.view.bean.LuckImage> getImage(@Path("page") int page);
}
