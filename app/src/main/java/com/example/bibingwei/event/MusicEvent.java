package com.example.bibingwei.event;

import com.example.bibingwei.view.bean.Music;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @author bibingwei
 * 传递music播放列表
 */
public class MusicEvent {
    private final List<Music.SongListBean> musicList;

    public MusicEvent(List<Music.SongListBean> musicList) {
        this.musicList = musicList;
    }

    public List<Music.SongListBean> getMusicList() {
        return musicList;
    }
}
