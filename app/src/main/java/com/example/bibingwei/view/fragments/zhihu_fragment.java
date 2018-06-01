package com.example.bibingwei.view.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.activity.ZhihuDailyDetail;
import com.example.bibingwei.view.adapter.ZhihuAdapter;
import com.example.bibingwei.view.network.Network;
import com.example.bibingwei.view.network.api.ZhihuApi;
import com.example.bibingwei.view.bean.ZhiHu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class zhihu_fragment extends Fragment {

    @BindView(R.id.zhiHu_fragment_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.zhihuSwipeRefreshLayout)SwipeRefreshLayout mSwipeRefreshLayout;

    private ZhihuAdapter mZhihuAdapter = new ZhihuAdapter();
    private Context mContext;
    protected Disposable disposable;

    public static zhihu_fragment newInstance() {

        Bundle args = new Bundle();

        zhihu_fragment fragment = new zhihu_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initData(){
        mSwipeRefreshLayout.setRefreshing(true);
        disposable = Network.getZhihuApi()
                .getStoriesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ZhiHu.StoriesBean>>() {

                    @Override
                    public void accept(List<ZhiHu.StoriesBean> storiesBeans) throws Exception {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mZhihuAdapter = new ZhihuAdapter(getContext(),storiesBeans);
                        mRecyclerView.setAdapter(mZhihuAdapter);
                        mZhihuAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"数据加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mSwipeRefreshLayout.setRefreshing(true);
        View mView = inflater.inflate(R.layout.fragment_zhihu_fragment, container, false);
        ButterKnife.bind(this,mView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(mZhihuAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mContext = getContext();
    }

    @Override
    public void onResume() {
        super.onResume();

//            mZhihuAdapter.setOnItemClickListener(new ZhihuAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View view, int position) {
//                    Toast.makeText(mContext,"1111111",Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(mContext,ZhihuDailyDetail.class);
//                    intent.putExtra("storiesId",mZhiHuList.get(position).getId());
//                    startActivity(intent);
//                }
//            });
    }
}
