package com.example.bibingwei.view.network.api;

import com.example.bibingwei.view.bean.OtherReading;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author bibingwei
 * http://v.juhe.cn/toutiao/index?type=top&key=67f32c45c280e80ff8b1efb217d0ddc2
 */
public interface OtherApi {

    @GET("index?")
    Observable<OtherReading> getData(@QueryMap Map<String,String> params);
}
