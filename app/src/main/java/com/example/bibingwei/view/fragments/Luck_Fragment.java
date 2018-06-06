package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.LuckImageAdapter;
import com.example.bibingwei.view.bean.LuckImage;
import com.example.bibingwei.view.network.Network;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import qiu.niorgai.StatusBarCompat;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Luck_Fragment extends Fragment {

    @BindView(R.id.luck_image_recyclerView)
    DiscreteScrollView mDiscreteScrollView;

    private LuckImageAdapter mLuckImageAdapter = new LuckImageAdapter();
    private List<LuckImage.ResultsBean> mResultsBeanList;
    private int num = 1;

    public static Luck_Fragment newInstance() {
        Luck_Fragment fragment = new Luck_Fragment();
        return fragment;
    }

    public Luck_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarCompat.setStatusBarColor(getActivity(), Color.parseColor("#3F51B5"));
        initData(num);
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

//        mDiscreteScrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
//            @Override
//            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
////                Toast.makeText(getContext(),""+adapterPosition,Toast.LENGTH_SHORT).show();
////                if (adapterPosition % 8 ==0 && adapterPosition>0){
////                    initData(++num);
////                }
//            }
//
//            @Override
//            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
//
//            }
//
//            @Override
//            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {
//
//            }
//        });
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
