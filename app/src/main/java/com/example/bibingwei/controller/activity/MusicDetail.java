package com.example.bibingwei.controller.activity;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bibingwei.event.MusicEvent;
import com.example.bibingwei.controller.R;
import com.example.bibingwei.bean.Music;
import com.example.bibingwei.bean.MusicPlay;
import com.example.bibingwei.model.network.Network;
import com.freedom.lauzy.playpauseviewlib.PlayPauseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.zhengken.lyricview.LyricView;
import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 *
 * MusicDetail
 */
public class MusicDetail extends AppCompatActivity {

    @BindView(R.id.musicDetailPic)ImageView musicDetailPic;
    @BindView(R.id.backMusicFragment)ImageView backMusicFragment;
    @BindView(R.id.musicName)TextView musicName;
    @BindView(R.id.musicAuthor)TextView musicAuthor;
    @BindView(R.id.musicWord)LyricView musicWord;
    @BindView(R.id.nowTime)TextView nowTime;
    @BindView(R.id.progressBar)ProgressBar mProgressBar;
    @BindView(R.id.totalTime)TextView totalTime;
    @BindView(R.id.previousMusic)ImageView previousMusic;
    @BindView(R.id.play_pause_view)PlayPauseView mPlayPauseView;
    @BindView(R.id.nextMusic)ImageView nextMusic;

    private int musicPosition;
    private int musicPlayPosition;
    private List<Music.SongListBean> musicList;
    private MediaPlayer mMediaPlayer = new MediaPlayer();
    private Map<String,String> params = new HashMap<>();
    private String musicPlayUrl;
    //private lyricfile

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_detail);

        ButterKnife.bind(this);
        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(this,false);
        musicPosition = getIntent().getIntExtra("position",0);
        musicPlayPosition = getIntent().getIntExtra("playPosition",0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        params.put("method","baidu.ting.song.play");
        params.put("songid",musicList.get(musicPosition).getSong_id());
        musicInfo();
        mPlayPauseView.setPlayPauseListener(new PlayPauseView.PlayPauseListener() {
            @Override
            public void play() {
                //播放音乐
                mMediaPlayer.seekTo(musicPlayPosition);
                mMediaPlayer.start();
            }

            @Override
            public void pause() {
                //音乐暂停
                mMediaPlayer.pause();
                musicPlayPosition = mMediaPlayer.getCurrentPosition();
            }
        });

        //上一曲
        previousMusic.setOnClickListener(new View.OnClickListener() {
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
        nextMusic.setOnClickListener(new View.OnClickListener() {
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
        backMusicFragment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMediaPlayer.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        MusicDetail.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            setResult(2);
            finish();
        }
        return false;
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(MusicEvent event){
        musicList = event.getMusicList();
    }

    @SuppressLint("CheckResult")
    private void musicInfo( ) {
        Network.getMusicPlayApi(MusicDetail.this)
                .getMusicUrl(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MusicPlay>() {
                    @Override
                    public void accept(MusicPlay musicPlay) {
                        musicPlayUrl = musicPlay.getBitrate().getShow_link();
                        musicName.setText(musicPlay.getSonginfo().getTitle());
                        musicAuthor.setText(musicPlay.getSonginfo().getAuthor());
                        Glide.with(MusicDetail.this).load(musicPlay.getSonginfo().getPic_big())
                                .apply(new RequestOptions().override(MusicDetail.this.getResources().getDisplayMetrics().widthPixels,MusicDetail.this.getResources().getDisplayMetrics().heightPixels))
                                .into(musicDetailPic);
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
                        Toast.makeText(MusicDetail.this,"musicDetail正在抓紧修复",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
