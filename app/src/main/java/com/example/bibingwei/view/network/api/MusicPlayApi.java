package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.MusicPlay;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author bibingwei
 * http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.song.play&songid=569080829
 */
public interface MusicPlayApi {
    @GET("ting?")
    Observable<MusicPlay> getMusicUrl(@QueryMap Map<String,String> params);
}
