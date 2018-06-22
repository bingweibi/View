package com.example.bibingwei.view.fragments;


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

import com.example.bibingwei.view.R;

/**
 * A simple {@link Fragment} subclass.
 * @author bibingwei
 */
public class Reading_Fragment extends Fragment {

    private ViewPager readingViewPager;
    private Fragment zhihuFragment, topFragment, tiyuFragment, yuleFragment, kejiFragment;
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
        Log.i("------", "readingFragment onCreate: ");
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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d("------", "ReadingFragment onResume: ");
        zhihuFragment = ZhiHu_Fragment.newInstance();
        topFragment = Top_Fragment.newInstance();
        tiyuFragment = Tiyu_Fragment.newInstance();
        yuleFragment = Yule_Fragment.newInstance();
        kejiFragment = Keji_Fragment.newInstance();
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
        readingViewPager.setOffscreenPageLimit(5);
        Log.i("------", "readingFragment onPageSelected: " + readingViewPager.getCurrentItem());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("------", "readingFragment onStop: ");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("------", "readingFragment onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("------", "readingFragment onDestroy: ");
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
