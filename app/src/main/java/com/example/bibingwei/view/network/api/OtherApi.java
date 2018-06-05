package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.OtherReading;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author bibingwei
 * http://v.juhe.cn/toutiao/index?type=top&key=67f32c45c280e80ff8b1efb217d0ddc2
 */
public interface OtherApi {

    @GET("index?type={type}&key=67f32c45c280e80ff8b1efb217d0ddc2")
    Observable<OtherReading> getData(@Path("type") String top);
}
