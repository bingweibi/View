package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.RandomMusicAlbum;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author bibingwei
 * 随机推荐的音乐
 * https://music-api-jwzcyzizya.now.sh/api/suggest/album/all?limit=10
 */
public interface RandomMusicAlbumApi {
    @GET("suggest/album/all?limit=10")
    Observable<RandomMusicAlbum> getMusicId();
}
