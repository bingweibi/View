package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.MusicListAdapter;
import com.example.bibingwei.view.bean.Music;
import com.example.bibingwei.view.network.Network;
import com.freedom.lauzy.playpauseviewlib.PlayPauseView;

import java.util.List;
import java.util.Objects;

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
public class Music_Fragment extends Fragment {

    @BindView(R.id.musicBackground)ImageView musicImage;
    @BindView(R.id.songList)RecyclerView songListRecyclerView;
    @BindView(R.id.songImage)ImageView songSmallImage;
    @BindView(R.id.musicTitle)TextView songTitle;
    @BindView(R.id.singerName)TextView singName;
    @BindView(R.id.frontSong)ImageView frontSong;
    @BindView(R.id.play_pause_view)com.freedom.lauzy.playpauseviewlib.PlayPauseView mPlayPauseView;
    @BindView(R.id.nextSong)ImageView nextSong;

    private List<Music.SongListBean> musicList;
    private MusicListAdapter mMusicListAdapter = new MusicListAdapter();

    public static Music_Fragment newInstance() {
        return new Music_Fragment();
    }
    
    public Music_Fragment() {
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
        songListRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        songListRecyclerView.setAdapter(mMusicListAdapter);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (musicList == null){
            Glide.with(Objects.requireNonNull(getActivity())).load(R.drawable.music).into(musicImage);
        }else {
            Glide.with(Objects.requireNonNull(getActivity())).load(musicList.get(0).getPic_big()).into(musicImage);
        }

        mPlayPauseView.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                //播放音乐

            }

            @Override
            public void pause() {
                //音乐暂停
            }
        });

        //上一曲
        frontSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //下一曲
        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @SuppressLint("CheckResult")
    private void initData() {
        Network.getMusicApi(getContext())
                .getMusicList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Music>() {
                    @Override
                    public void accept(Music music) {
                        musicList = music.getSong_list();
                        mMusicListAdapter.setData(musicList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getActivity(),"music正在抓紧修复",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
