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
public class joker_fragment extends Fragment {

    public static joker_fragment newInstance() {

        Bundle args = new Bundle();

        joker_fragment fragment = new joker_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public joker_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joker_fragment, container, false);
    }

}
