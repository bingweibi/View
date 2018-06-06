package com.example.bibingwei.view.network;

import com.example.bibingwei.view.bean.OtherReading;
import com.example.bibingwei.view.network.api.LuckImageApi;
import com.example.bibingwei.view.network.api.OtherApi;
import com.example.bibingwei.view.network.api.ZhiHuApi;
import com.example.bibingwei.view.network.api.ZhiHuDetailApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author bibingwei
 */
public class Network {

    private static ZhiHuApi zhiHuApi;
    private static ZhiHuDetailApi zhiHuDetailApi;
    private static OtherApi otherApi;
    private static LuckImageApi luckImageApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static ZhiHuApi getZhiHuApi(){
        if (zhiHuApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://news-at.zhihu.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhiHuApi = retrofit.create(ZhiHuApi.class);
        }
        return zhiHuApi;
    }

    public static ZhiHuDetailApi getZhiHuDetailApi(){
        if ( zhiHuDetailApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://news-at.zhihu.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhiHuDetailApi = retrofit.create(ZhiHuDetailApi.class);
        }
        return zhiHuDetailApi;
    }

    public static OtherApi getOtherApi(){
        if (otherApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://v.juhe.cn/toutiao/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            otherApi = retrofit.create(OtherApi.class);
        }
        return otherApi;
    }

    public static LuckImageApi getLuckImageApi(){
        if (luckImageApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://gank.io/api/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            luckImageApi = retrofit.create(LuckImageApi.class);
        }
        return luckImageApi;
    }
}
