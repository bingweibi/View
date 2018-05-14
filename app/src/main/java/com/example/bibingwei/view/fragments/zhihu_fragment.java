package com.example.bibingwei.view.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.ZhihuAdapter;
import com.example.bibingwei.view.api.ZhihuApi;
import com.example.bibingwei.view.bean.ZhiHu;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class zhihu_fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ZhihuAdapter mZhihuAdapter;
    private List<ZhiHu.StoriesBean> mZhiHuList;

    public static zhihu_fragment newInstance() {

        Bundle args = new Bundle();

        zhihu_fragment fragment = new zhihu_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public zhihu_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_zhihu_fragment, container, false);
        mRecyclerView = mView.findViewById(R.id.zhiHu_fragment_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhihuApi api = retrofit.create(ZhihuApi.class);
        Call<ZhiHu> call = api.getStoriesInfo();
        call.enqueue(new Callback<ZhiHu>() {
            @Override
            public void onResponse(Call<ZhiHu> call, Response<ZhiHu> response) {
                mZhiHuList = response.body().getStories();
                mZhihuAdapter = new ZhihuAdapter(mZhiHuList);
                mZhihuAdapter.notifyDataSetChanged();
                mRecyclerView.setAdapter(mZhihuAdapter);
            }

            @Override
            public void onFailure(Call<ZhiHu> call, Throwable t) {
                Toast.makeText(getContext(),"抱歉,网络好像不给力！",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
