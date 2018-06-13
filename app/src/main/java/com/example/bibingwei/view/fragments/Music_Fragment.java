package com.example.bibingwei.view.fragments;


import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.RandomMusicAlbum;
import com.example.bibingwei.view.network.Network;
import com.freedom.lauzy.playpauseviewlib.PlayPauseView;

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
public class Music_Fragment extends Fragment {

    @BindView(R.id.musicBackground)ImageView musicImage;
    @BindView(R.id.songList)RecyclerView songListRecyclerView;
    @BindView(R.id.songImage)ImageView songSmallImage;
    @BindView(R.id.musicTitle)TextView songTitle;
    @BindView(R.id.singerName)TextView singName;
    @BindView(R.id.frontSong)ImageView frontSong;
    @BindView(R.id.play_pause_view)com.freedom.lauzy.playpauseviewlib.PlayPauseView mPlayPauseView;
    @BindView(R.id.nextSong)ImageView nextSong;

    private List<RandomMusicAlbum.QqBean.AlbumListBean> musicList;

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
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

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
        Network.getRandomAlbumApi()
                .getMusicId()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RandomMusicAlbum>() {
                    @Override
                    public void accept(RandomMusicAlbum randomMusicAlbum) {
                        musicList = randomMusicAlbum.getQq().getAlbumList();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {
                        Toast.makeText(getContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
