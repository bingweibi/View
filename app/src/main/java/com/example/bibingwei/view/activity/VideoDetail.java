package com.example.bibingwei.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.bibingwei.event.VideoEvent;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.Video;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 */
public class VideoDetail extends AppCompatActivity {

    @BindView(R.id.videoPlay)PlayerView mVideoView;
    @BindView(R.id.videoDescription)TextView mTextView;

    private List<Video.ItemListBeanX> videoList;
    private Context mContext;
    private SimpleExoPlayer player;
    private Player.EventListener eventListener;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(this,false);
        mContext = getApplicationContext();
        position = getIntent().getIntExtra("position",0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(mContext,trackSelector);
        mVideoView.setPlayer(player);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();

        mVideoView.setOnTouchListener(new View.OnTouchListener(){

            float mPosY = 0,mCurPosY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        mPosY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        mCurPosY = event.getY();
                        if (mCurPosY - mPosY < 0 && Math.abs(mCurPosY - mPosY) > 50){
                            ++position;
                            playVideo(videoList.get(position));
                            mCurPosY = 0;
                            mPosY = 0;
                        }
                        break;
                    default:
                }
                return true;
            }
        });

        eventListener = new Player.EventListener() {

            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) { }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) { }

            @Override
            public void onLoadingChanged(boolean isLoading) { }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                if (playbackState == Player.STATE_ENDED) {
                    player.setPlayWhenReady(true);
                    player.seekTo(0);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) { }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) { }

            @Override
            public void onPlayerError(ExoPlaybackException error) { }

            @Override
            public void onPositionDiscontinuity(int reason) { }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) { }

            @Override
            public void onSeekProcessed() { }
        };

        playVideo(videoList.get(position));
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        player.release();
    }

    private void playVideo(Video.ItemListBeanX listBeanX) {

        // 生成加载媒体数据的DataSource实例。
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext,"View"),null);
        // MediaSource代表要播放的媒体。
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(listBeanX.getData().getPlayUrl()));
        //Prepare the player with the source.
        player.prepare(videoSource);
        //添加监听的listener
        player.addListener(eventListener);
        player.setPlayWhenReady(true);
        mTextView.setText(listBeanX.getData().getDescription());
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEvent(VideoEvent event){
        videoList = event.getVideoList();
    }
}
