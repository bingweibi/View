package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.MusicSong;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 * https://music-api-jwzcyzizya.now.sh/api/get/song/qq?id=001z1OuX41cBAx
 */
public interface MusicSongApi {
    @GET("get/song/qq?id={id}")
    Observable<MusicSong> getMusic(@Path("id") String id );
}
