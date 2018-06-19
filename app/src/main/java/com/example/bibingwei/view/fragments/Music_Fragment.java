package com.example.bibingwei.view.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bibingwei.view.activity.MusicDetail;
import com.example.bibingwei.event.MusicEvent;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.adapter.MusicListAdapter;
import com.example.bibingwei.view.bean.Music;
import com.example.bibingwei.view.bean.MusicPlay;
import com.example.bibingwei.view.network.Network;
import com.freedom.lauzy.playpauseviewlib.PlayPauseView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @BindView(R.id.songList)RecyclerView songListRecyclerView;
    @BindView(R.id.songImage)ImageView songSmallImage;
    @BindView(R.id.musicTitle)TextView songTitle;
    @BindView(R.id.singerName)TextView singName;
    @BindView(R.id.frontSong)ImageView frontSong;
    @BindView(R.id.play_pause_view)com.freedom.lauzy.playpauseviewlib.PlayPauseView mPlayPauseView;
    @BindView(R.id.nextSong)ImageView nextSong;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    private List<Music.SongListBean> musicList;
    private MusicListAdapter mMusicListAdapter = new MusicListAdapter();
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private String musicPlayUrl;
    private Map<String,String> params = new HashMap<>();
    private Context mContext ;
    //第几首歌曲
    private int musicPosition;
    //歌曲播放时间，暂时为int
    private int musicCurrentPlayPosition = 0;

    private volatile static Music_Fragment fragment;

    public static Music_Fragment newInstance() {
        if (fragment == null){
            synchronized (Music_Fragment.class){
                if (fragment == null){
                    fragment = new Music_Fragment();
                }
            }
        }
        return fragment;
    }
    public Music_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
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
        mSwipeRefreshLayout.setRefreshing(true);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mMusicListAdapter.setClickListener(new MusicListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                musicPosition = position;
                musicCurrentPlayPosition = 0;
                params.put("method","baidu.ting.song.play");
                params.put("songid",musicList.get(musicPosition).getSong_id());
                musicInfo();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                initData();
            }
        });

        mPlayPauseView.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                //播放音乐
                mMediaPlayer.seekTo(musicCurrentPlayPosition);
                mMediaPlayer.start();
            }

            @Override
            public void pause() {
                //音乐暂停
                mMediaPlayer.pause();
                musicCurrentPlayPosition = mMediaPlayer.getCurrentPosition();
            }
        });

        //上一曲
        frontSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPosition-1<0){
                    musicPosition = musicList.size()-1;
                }else {
                    --musicPosition;
                }
                params.put("method","baidu.ting.song.play");
                params.put("songid",musicList.get(musicPosition).getSong_id());
                musicInfo();
            }
        });

        //下一曲
        nextSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (musicPosition+1 == musicList.size()){
                    musicPosition = 0;
                }else {
                    ++musicPosition;
                }
                params.put("method","baidu.ting.song.play");
                params.put("songid",musicList.get(musicPosition).getSong_id());
                musicInfo();
            }
        });

        //点击跳转至musicDetailActivity
        songSmallImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MusicEvent(musicList));
                startActivity(new Intent(getContext(), MusicDetail.class)
                        .putExtra("position",musicPosition)
                        .putExtra("playPosition",musicCurrentPlayPosition));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMediaPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    @SuppressLint("CheckResult")
    private void musicInfo( ) {
        Network.getMusicPlayApi(mContext)
                .getMusicUrl(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicPlay>() {
                    @Override
                    public void accept(MusicPlay musicPlay) {
                        musicPlayUrl = musicPlay.getBitrate().getShow_link();
                        songTitle.setText(musicPlay.getSonginfo().getTitle());
                        singName.setText(musicPlay.getSonginfo().getAuthor());
                        Glide.with(Objects.requireNonNull(getContext())).load(musicPlay.getSonginfo().getPic_small()).into(songSmallImage);
                        //播放音乐
                        try {
                            mPlayPauseView.play();
                            mMediaPlayer.reset();
                            mMediaPlayer.setDataSource(musicPlayUrl);
                            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            mMediaPlayer.prepare();
                            mMediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getActivity(),"musicPlay正在抓紧修复",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void initData() {
        Network.getMusicApi(mContext)
                .getMusicList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Music>() {
                    @Override
                    public void accept(Music music) {
                        musicList = music.getSong_list();
                        mSwipeRefreshLayout.setRefreshing(false);
                        mMusicListAdapter.setData(musicList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getActivity(),"music正在抓紧修复",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
