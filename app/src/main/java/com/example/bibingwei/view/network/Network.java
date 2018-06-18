package com.example.bibingwei.view.network;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;

import com.example.bibingwei.view.bean.Music;
import com.example.bibingwei.view.network.api.LuckImageApi;
import com.example.bibingwei.view.network.api.MusicApi;
import com.example.bibingwei.view.network.api.MusicPlayApi;
import com.example.bibingwei.view.network.api.OtherApi;
import com.example.bibingwei.view.network.api.VideoApi;
import com.example.bibingwei.view.network.api.ZhiHuApi;
import com.example.bibingwei.view.network.api.ZhiHuDetailApi;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static MusicApi musicApi;
    private static MusicPlayApi musicPlayApi;
    private static VideoApi videoApi;
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
        okHttpClient.connectTimeoutMillis();
        okHttpClient.readTimeoutMillis();
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

    public static MusicApi getMusicApi(Context context){
        if (musicApi == null ){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient(context))
                    .baseUrl("http://tingapi.ting.baidu.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            musicApi = retrofit.create(MusicApi.class);
        }
        return musicApi;
    }

    public static MusicPlayApi getMusicPlayApi(Context context){
        if (musicPlayApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(getOkHttpClient(context))
                    .baseUrl("http://tingapi.ting.baidu.com/v1/restserver/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            musicPlayApi = retrofit.create(MusicPlayApi.class);
        }
        return musicPlayApi;
    }

    public static VideoApi getVideoApi(Context context){
        if (videoApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://baobab.kaiyanapp.com/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            videoApi = retrofit.create(VideoApi.class);
        }
        return videoApi;
    }

    /**
     *  构造okhttp头部(music)
     *
     * */
    private static OkHttpClient getOkHttpClient(final Context context) {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .removeHeader("User-Agent")
                                .addHeader("User-Agent", WebSettings.getDefaultUserAgent(context))
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
        return httpClient;
    }
}
