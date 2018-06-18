package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
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

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.VideoListAdapter;
import com.example.bibingwei.view.bean.Music;
import com.example.bibingwei.view.bean.Video;
import com.example.bibingwei.view.network.Network;

import java.util.ArrayList;
import java.util.List;

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

    private volatile static Video_Fragment fragment;

    public static Video_Fragment newInstance() {
        if (fragment == null){
            synchronized (Video_Fragment.class){
                if (fragment == null){
                    fragment = new Video_Fragment();
                }
            }
        }
        return fragment;
    }

    public Video_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_fragment, container, false);
        ButterKnife.bind(this,view);
        videoListRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        videoListRecyclerView.setAdapter(mVideoListAdapter);
        mSwipeRefreshLayout.setRefreshing(true);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mVideoListAdapter.setClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

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
                            mVideoList.add(video.getItemList().get(i));
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                        mVideoListAdapter.setData(mVideoList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.i("ggg", "accept: "+ throwable.toString());
                        Toast.makeText(getActivity(),throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}
