package com.example.bibingwei.network.api;

import com.example.bibingwei.bean.Music;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author bibingwei
 *
 * 获取每天的热歌榜数据
 * http://tingapi.ting.baidu.com/v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=2&size=10&offset=0
 */
public interface MusicApi {
    @GET("v1/restserver/ting?format=json&calback=&from=webapp_music&method=baidu.ting.billboard.billList&type=2&size=20&offset=0")
    Observable<Music> getMusicList();
}
