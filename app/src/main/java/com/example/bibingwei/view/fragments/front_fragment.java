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
public class front_fragment extends Fragment {

    public static front_fragment newInstance() {
        
        Bundle args = new Bundle();
        
        front_fragment fragment = new front_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public front_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_front_fragment, container, false);
    }

}