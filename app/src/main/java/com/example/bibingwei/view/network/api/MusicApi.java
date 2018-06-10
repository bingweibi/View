package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.Music;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 * https://music-api-jwzcyzizya.now.sh/api/get/song/qq?id=001z1OuX41cBAx
 */
public interface MusicApi {
    @GET("get/song/qq?id={id}")
    Observable<Music> getMusic(@Path("id") int id );
}
