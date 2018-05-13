package com.example.bibingwei.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibingwei.view.R;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class luck_fragment extends Fragment {

    public static luck_fragment newInstance() {

        Bundle args = new Bundle();

        luck_fragment fragment = new luck_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public luck_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_luck_fragment, container, false);
    }

}
