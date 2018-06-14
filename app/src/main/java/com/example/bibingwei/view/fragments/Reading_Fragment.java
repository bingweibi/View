package com.example.bibingwei.view.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibingwei.view.R;

import qiu.niorgai.StatusBarCompat;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Reading_Fragment extends Fragment {

    private ViewPager readingViewPager;
    private Fragment zhihuFragment,androidFragment,iosFragment,frontFragment,jokerFragment;
    private BottomNavigationView mBottomNavigationView;
    private volatile  static Reading_Fragment fragment;

    public static Reading_Fragment newInstance() {
        if (fragment == null){
            synchronized (Reading_Fragment.class){
                if (fragment == null){
                    fragment = new Reading_Fragment();
                }
            }
        }
        return fragment;
    }

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
        readingViewPager = view.findViewById(R.id.reading_viewPager);
        readingViewPager.addOnPageChangeListener(mOnPageChangeListener);
        mBottomNavigationView = view.findViewById(R.id.reading_container);
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        zhihuFragment = ZhiHu_Fragment.newInstance();
        androidFragment = Top_Fragment.newInstance();
        iosFragment = Tiyu_Fragment.newInstance();
        frontFragment = Yule_Fragment.newInstance();
        jokerFragment = Keji_Fragment.newInstance();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        readingViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return zhihuFragment;
                    case 1:
                        return androidFragment;
                    case 2:
                        return iosFragment;
                    case 3:
                        return frontFragment;
                    case 4:
                        return jokerFragment;
                        default:
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        readingViewPager.setOffscreenPageLimit(5);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            readingViewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };
}
