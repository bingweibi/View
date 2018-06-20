package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.activity.Reading_NewDetail;
import com.example.bibingwei.view.adapter.ReadingOtherAdapter;
import com.example.bibingwei.bean.OtherReading;
import com.example.bibingwei.network.Network;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Tiyu_Fragment extends Fragment {

    @BindView(R.id.reading_fragment_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.readingSwipeRefreshLayout)SwipeRefreshLayout mSwipeRefreshLayout;

    private ReadingOtherAdapter mReadingOtherAdapter = new ReadingOtherAdapter();
    private List<OtherReading.ResultBean.DataBean> mDataBeans;
    private Map<String, String> params = new HashMap<>();
    private String newsDetail;

    private volatile static Tiyu_Fragment fragment;

    public static Tiyu_Fragment newInstance() {
        if (fragment == null){
            synchronized (Tiyu_Fragment.class){
                if (fragment == null){
                    fragment = new Tiyu_Fragment();
                }
            }
        }
        return fragment;
    }

    public Tiyu_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        params.put("type","tiyu");
        params.put("key","67f32c45c280e80ff8b1efb217d0ddc2");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_top_fragment, container, false);
        ButterKnife.bind(this,mView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mRecyclerView.setAdapter(mReadingOtherAdapter);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        mSwipeRefreshLayout.setDistanceToTriggerSync(250);
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        if (mDataBeans == null){
            mSwipeRefreshLayout.setRefreshing(true);
        }

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mReadingOtherAdapter.setClickListener(new ReadingOtherAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                newsDetail = mDataBeans.get(position).getUrl();
                startActivity(new Intent(getActivity(), Reading_NewDetail.class).putExtra("newsAddress",newsDetail));
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
    private void initData(){
        Network.getOtherApi()
                .getData(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OtherReading>() {
                    @Override
                    public void accept(OtherReading otherReading) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mDataBeans = otherReading.getResult().getData();
                        mReadingOtherAdapter.setData(mDataBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getContext(),"跑跑跑不动了",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
