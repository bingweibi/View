package com.example.bibingwei.view.network;

import com.example.bibingwei.view.network.api.ZhihuApi;

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

    private static ZhihuApi zhihuApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    public static ZhihuApi getZhihuApi(){
        if (zhihuApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://news-at.zhihu.com")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhihuApi = retrofit.create(ZhihuApi.class);
        }
        return zhihuApi;
    }
}
