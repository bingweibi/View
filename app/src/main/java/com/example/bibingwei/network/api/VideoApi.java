package com.example.bibingwei.network.api;

import com.example.bibingwei.bean.Video;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * http://baobab.kaiyanapp.com/api/v4/discovery/hot
 */
public interface VideoApi {
    @GET("api/v4/discovery/hot")
    Observable<Video> getVideoInfo();
}
