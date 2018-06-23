package com.example.bibingwei.model;

import android.support.v4.app.Fragment;

import com.example.bibingwei.controller.fragments.Keji_Fragment;
import com.example.bibingwei.controller.fragments.Luck_Fragment;
import com.example.bibingwei.controller.fragments.Music_Fragment;
import com.example.bibingwei.controller.fragments.Reading_Fragment;
import com.example.bibingwei.controller.fragments.Tiyu_Fragment;
import com.example.bibingwei.controller.fragments.Top_Fragment;
import com.example.bibingwei.controller.fragments.Video_Fragment;
import com.example.bibingwei.controller.fragments.Yule_Fragment;
import com.example.bibingwei.controller.fragments.ZhiHu_Fragment;

/**
 * @author bibingwei
 * 采用单例模式获得实例
 */
public class Singleton {

    private volatile static Keji_Fragment keji_fragment;
    private volatile static ZhiHu_Fragment zhiHu_fragment;
    private volatile static Luck_Fragment luck_fragment;
    private volatile static Music_Fragment music_fragment;
    private volatile static Reading_Fragment reading_fragment;
    private volatile static Tiyu_Fragment tiyu_fragment;
    private volatile static Top_Fragment top_fragment;
    private volatile static Video_Fragment video_fragment;

    public static Keji_Fragment newKejiInstance() {
        if (keji_fragment == null){
            synchronized (Keji_Fragment.class){
                if (keji_fragment == null){
                    keji_fragment = new Keji_Fragment();
                }
            }
        }
        return keji_fragment;
    }

    public static Luck_Fragment newLuckInstance() {
        if (luck_fragment == null){
            synchronized (Luck_Fragment.class){
                if (luck_fragment == null){
                    luck_fragment = new Luck_Fragment();
                }
            }
        }
        return luck_fragment;
    }

    public static Music_Fragment newMusicInstance() {
        if (music_fragment == null){
            synchronized (Music_Fragment.class){
                if (music_fragment == null){
                    music_fragment = new Music_Fragment();
                }
            }
        }
        return music_fragment;
    }

    public static Reading_Fragment newReadingInstance() {
        if (reading_fragment == null){
            synchronized (Reading_Fragment.class){
                if (reading_fragment == null){
                    reading_fragment = new Reading_Fragment();
                }
            }
        }
        return reading_fragment;
    }

    public static Tiyu_Fragment newTiyuInstance() {
        if (tiyu_fragment == null){
            synchronized (Tiyu_Fragment.class){
                if (tiyu_fragment == null){
                    tiyu_fragment = new Tiyu_Fragment();
                }
            }
        }
        return tiyu_fragment;
    }

    public static Top_Fragment newTopInstance() {
        if (top_fragment == null){
            synchronized (Top_Fragment.class){
                if (top_fragment == null){
                    top_fragment = new Top_Fragment();
                }
            }
        }
        return top_fragment;
    }

    public static Video_Fragment newVideoInstance() {
        if (video_fragment == null){
            synchronized (Video_Fragment.class){
                if (video_fragment == null){
                    video_fragment = new Video_Fragment();
                }
            }
        }
        return video_fragment;
    }

    private volatile  static Yule_Fragment yule_fragment;

    public static Yule_Fragment newYuleInstance() {
        if (yule_fragment == null){
            synchronized (Yule_Fragment.class){
                if (yule_fragment == null){
                    yule_fragment = new Yule_Fragment();
                }
            }
        }
        return yule_fragment;
    }

    public static ZhiHu_Fragment newZhihuInstance() {
        if (zhiHu_fragment == null){
            synchronized (ZhiHu_Fragment.class){
                if (zhiHu_fragment == null){
                    zhiHu_fragment = new ZhiHu_Fragment();
                }
            }
        }
        return zhiHu_fragment;
    }
}
