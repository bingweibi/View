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
public class video_fragment extends Fragment {

    public static video_fragment newInstance() {
        
        Bundle args = new Bundle();
        
        video_fragment fragment = new video_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public video_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_fragment, container, false);
    }

}
