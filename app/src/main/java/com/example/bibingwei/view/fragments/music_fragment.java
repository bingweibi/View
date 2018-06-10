package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.RandomMusic;
import com.example.bibingwei.view.bean.RandomMusic.QqBean;
import com.example.bibingwei.view.network.Network;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class music_fragment extends Fragment {
    @BindView(R.id.musicBackground) ImageView musicBackground;
    @BindView(R.id.musicFengmian)ImageButton musicFengMian;
    private List<RandomMusic.QqBean.AlbumListBean> musicList;

    public static music_fragment newInstance() {
        
        Bundle args = new Bundle();
        
        music_fragment fragment = new music_fragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    public music_fragment() {
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
        View mView = inflater.inflate(R.layout.fragment_music_fragment, container, false);
        ButterKnife.bind(this,mView);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MediaPlayer mediaPlayer = new MediaPlayer();

        Glide.with(this).load(R.drawable.test)
                .apply(bitmapTransform(new BlurTransformation(10, 3)))
                .into(musicBackground);
    }

    @SuppressLint("CheckResult")
    private void initData() {
        Network.getRandomMusicApi()
                .getMusicId()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RandomMusic>() {
                    @Override
                    public void accept(RandomMusic randomMusic) {
                        musicList = randomMusic.getQq().getAlbumList();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
