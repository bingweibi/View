package com.example.bibingwei.controller.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.util.GetDateUtil;
import com.example.bibingwei.controller.R;
import com.example.bibingwei.controller.activity.ZhihuDailyDetail;
import com.example.bibingwei.controller.adapter.ZhiHuAdapter;
import com.example.bibingwei.bean.ZhiHu;
import com.example.bibingwei.model.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class ZhiHu_Fragment extends Fragment {

    @BindView(R.id.reading_fragment_recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.readingSwipeRefreshLayout)SwipeRefreshLayout mSwipeRefreshLayout;

    private ZhiHuAdapter mZhiHuAdapter = new ZhiHuAdapter();
    private List<ZhiHu.StoriesBean> mStoriesBeanList;
    private int beforeDate = -1;
    private String oldDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_zhihu_fragment, container, false);
        ButterKnife.bind(this,mView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mRecyclerView.setAdapter(mZhiHuAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setDistanceToTriggerSync(250);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        if (mStoriesBeanList == null){
            mSwipeRefreshLayout.setRefreshing(true);
            initData();
        }
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mZhiHuAdapter.setClickListener(new ZhiHuAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), ZhihuDailyDetail.class);
                intent.putExtra("storyId",mStoriesBeanList.get(position).getId());
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (! mRecyclerView.canScrollVertically(1)){
                    oldDate = new GetDateUtil().getDate(beforeDate);
                    --beforeDate;
                    getOldData();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @SuppressLint("CheckResult")
    private void initData(){
        Network.getZhiHuApi()
                .getStoriesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhiHu>() {
                    @Override
                    public void accept(ZhiHu zhiHu) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mStoriesBeanList = zhiHu.getStories();
                        mZhiHuAdapter.setData(mStoriesBeanList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void getOldData(){
        Network.getZhiHuApi()
                .getOldStoriesInfo(oldDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhiHu>(){
                    @Override
                    public void accept(ZhiHu zhiHu){
                        mStoriesBeanList.addAll(zhiHu.getStories());
                        mZhiHuAdapter.setData(mStoriesBeanList);
                    }
                },new Consumer<Throwable>(){

                    @Override
                    public void accept(Throwable throwable){
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
