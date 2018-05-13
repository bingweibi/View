package com.example.bibingwei.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.bibingwei.view.fragments.luck_fragment;
import com.example.bibingwei.view.fragments.music_fragment;
import com.example.bibingwei.view.fragments.reading_fragment;
import com.example.bibingwei.view.fragments.video_fragment;

/**
 * @author bibingwei
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Fragment readingFragment,luckFragment,musicFragment,videoFragment;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.fragment_container);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        readingFragment = reading_fragment.newInstance();
        luckFragment = luck_fragment.newInstance();
        musicFragment = music_fragment.newInstance();
        videoFragment = video_fragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        return readingFragment;
                    case 1:
                        return luckFragment;
                    case 2:
                        return musicFragment;
                    case 3:
                        return videoFragment;
                        default:
                }
                return null;
            }

            @Override
            public int getCount() {
                return 4;
            }


        });
        //防止频繁的销毁视图
        mViewPager.setOffscreenPageLimit(4);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            navigation.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mViewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };
}
