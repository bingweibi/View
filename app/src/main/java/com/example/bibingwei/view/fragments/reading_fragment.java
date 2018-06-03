package com.example.bibingwei.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.bibingwei.view.R;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class reading_fragment extends Fragment {

    private ViewPager readingViewPager;
    private Fragment zhihuFragment,androidFragment,iosFragment,frontFragment,jokerFragment;
    private BottomNavigationView mBottomNavigationView;

    public static reading_fragment newInstance() {

        Bundle args = new Bundle();

        reading_fragment fragment = new reading_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    public reading_fragment() {
        // Required empty public constructor
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
        zhihuFragment = Zhihu_fragment.newInstance();
        androidFragment = android_fragment.newInstance();
        iosFragment = ios_fragment.newInstance();
        frontFragment = front_fragment.newInstance();
        jokerFragment = joker_fragment.newInstance();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        readingViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
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
