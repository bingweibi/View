package com.example.bibingwei.event;

import com.example.bibingwei.view.bean.Video;

import java.util.List;

/**
 * @author bibingwei
 */
public class VideoEvent {

    public final List<Video.ItemListBeanX> videoList;

    public VideoEvent(List<Video.ItemListBeanX> videoList) {
        this.videoList = videoList;
    }

    public List<Video.ItemListBeanX> getVideoList() {
        return videoList;
    }
}
