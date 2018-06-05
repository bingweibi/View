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
public class Yule_Fragment extends Fragment {

    public static Yule_Fragment newInstance() {
        
        Bundle args = new Bundle();
        
        Yule_Fragment fragment = new Yule_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Yule_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yule_fragment, container, false);
    }

}
