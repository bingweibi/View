package com.example.bibingwei.controller.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.controller.R;
import com.example.bibingwei.controller.adapter.LuckImageAdapter;
import com.example.bibingwei.bean.LuckImage;
import com.example.bibingwei.model.network.Network;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
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
public class Luck_Fragment extends Fragment {

    @BindView(R.id.luck_image_recyclerView)
    DiscreteScrollView mDiscreteScrollView;

    private LuckImageAdapter mLuckImageAdapter = new LuckImageAdapter();
    private List<LuckImage.ResultsBean> mResultsBeanList = new ArrayList<>();
    private int num = 1;

    public Luck_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            initData(num);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_luck_fragment, container, false);
        ButterKnife.bind(this,mView);

        mDiscreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);
        mDiscreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
        .setMaxScale(1.00f)
        .setMinScale(0.88f)
        .setPivotX(Pivot.X.CENTER)
        .setPivotY(Pivot.Y.BOTTOM)
        .build());
        mDiscreteScrollView.setSlideOnFling(true);
        mDiscreteScrollView.setAdapter(mLuckImageAdapter);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mLuckImageAdapter.setLongClickListener(new LuckImageAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(),"不提供保存，谢谢！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void initData(int num) {
        Network.getLuckImageApi()
                .getImage(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LuckImage>() {
                    @Override
                    public void accept(LuckImage luckImage) {
                        mResultsBeanList = luckImage.getResults();
                        mLuckImageAdapter.setData(mResultsBeanList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
