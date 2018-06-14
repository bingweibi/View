package com.example.bibingwei.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibingwei.view.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Video_fragment extends Fragment {

    private volatile  static Video_fragment fragment;

    public static Video_fragment newInstance() {
        if (fragment == null){
            synchronized (Video_fragment.class){
                if (fragment == null){
                    fragment = new Video_fragment();
                }
            }
        }
        return fragment;
    }

    public Video_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_fragment, container, false);
    }

}
