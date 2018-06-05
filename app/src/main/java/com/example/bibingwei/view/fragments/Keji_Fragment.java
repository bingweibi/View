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
public class Keji_Fragment extends Fragment {

    public static Keji_Fragment newInstance() {

        Bundle args = new Bundle();

        Keji_Fragment fragment = new Keji_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Keji_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keji_fragment, container, false);
    }

}
