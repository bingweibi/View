package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.RandomMusic;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * 随机推荐的音乐
 * https://music-api-jwzcyzizya.now.sh/api/suggest/album/all?limit=10
 */
public interface RandomMusicApi {
    @GET("suggest/album/all?limit=10")
    Observable<RandomMusic> getMusicId();
}
