package com.example.bibingwei.controller.fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import com.example.bibingwei.event.VideoEvent;
import com.example.bibingwei.controller.R;
import com.example.bibingwei.controller.activity.VideoDetail;
import com.example.bibingwei.controller.adapter.VideoListAdapter;
import com.example.bibingwei.bean.Video;
import com.example.bibingwei.model.network.Network;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Video_Fragment extends Fragment {

    @BindView(R.id.videoList)RecyclerView videoListRecyclerView;
    @BindView(R.id.swipeRefreshLayout)SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Video.ItemListBeanX> mVideoList = new ArrayList<>();
    private VideoListAdapter mVideoListAdapter = new VideoListAdapter();
    private Context mContext;
    private boolean isVisible;

    public Video_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        isVisible = isVisibleToUser;
        if (isVisible){
            initData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_fragment, container, false);
        ButterKnife.bind(this,view);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setDistanceToTriggerSync(250);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        videoListRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        videoListRecyclerView.setAdapter(mVideoListAdapter);
        mSwipeRefreshLayout.setRefreshing(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (isVisible){
            initData();
        }

        mVideoListAdapter.setClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                EventBus.getDefault().postSticky(new VideoEvent(mVideoList));
                Objects.requireNonNull(getActivity()).startActivityForResult(new Intent(getActivity(),VideoDetail.class).putExtra("position",position),3);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void initData() {

        Network.getVideoApi(getContext())
                .getVideoInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Video>() {
                    @Override
                    public void accept(Video video) {
                        for (int i=2;i<video.getItemList().size();i++){
                            if(video.getItemList().get(i).getData().getCover() == null){
                                continue;
                            }
                            mVideoList.add(video.getItemList().get(i));
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                        mContext = getContext();
                        mVideoListAdapter.setData(mVideoList,mContext);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getActivity(),throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
