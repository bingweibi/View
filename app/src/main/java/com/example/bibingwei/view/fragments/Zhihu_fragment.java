package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.ZhihuAdapter;
import com.example.bibingwei.view.bean.ZhiHu;
import com.example.bibingwei.view.network.Network;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Zhihu_fragment extends Fragment {

    @BindView(R.id.zhiHu_fragment_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.zhihuSwipeRefreshLayout)SwipeRefreshLayout mSwipeRefreshLayout;

    private ZhihuAdapter mZhihuAdapter = new ZhihuAdapter();

    public static Zhihu_fragment newInstance() {

        Bundle args = new Bundle();

        Zhihu_fragment fragment = new Zhihu_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_zhihu_fragment, container, false);
        ButterKnife.bind(this,mView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mRecyclerView.setAdapter(mZhihuAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setEnabled(false);
        return mView;
    }

    @SuppressLint("CheckResult")
    private void initData(){
        Network.getZhihuApi()
                .getStoriesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhiHu>() {
                    @Override
                    public void accept(ZhiHu zhiHu) throws Exception {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mZhihuAdapter.setData(zhiHu.getStories());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
