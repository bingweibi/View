package com.example.bibingwei.controller.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibingwei.controller.R;
import com.example.bibingwei.model.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Reading_Fragment extends Fragment {

    private ViewPager readingViewPager;
    private Fragment zhihuFragment, topFragment, tiyuFragment, yuleFragment, kejiFragment;
    @BindView(R.id.reading_container) BottomNavigationView mBottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    public Reading_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reading, container, false);
        ButterKnife.bind(this,view);
        readingViewPager = view.findViewById(R.id.reading_viewPager);
        readingViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        zhihuFragment = Singleton.newZhihuInstance();
        topFragment = Singleton.newTopInstance();
        tiyuFragment = Singleton.newTiyuInstance();
        yuleFragment = Singleton.newYuleInstance();
        kejiFragment = Singleton.newKejiInstance();
    }

    @Override
    public void onResume() {
        super.onResume();

        readingViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return zhihuFragment;
                    case 1:
                        return topFragment;
                    case 2:
                        return tiyuFragment;
                    case 3:
                        return yuleFragment;
                    case 4:
                        return kejiFragment;
                    default:
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        readingViewPager.setOffscreenPageLimit(1);

        mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                readingViewPager.setCurrentItem(item.getOrder());
                return true;
            }
        };

        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mBottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
