package com.example.bibingwei.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.bibingwei.view.fragments.Luck_Fragment;
import com.example.bibingwei.view.fragments.Music_Fragment;
import com.example.bibingwei.view.fragments.Reading_Fragment;
import com.example.bibingwei.view.fragments.Video_Fragment;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container) ViewPager mViewPager;
    private Fragment readingFragment,luckFragment,musicFragment,videoFragment;
    @BindView(R.id.navigation) BottomNavigationView navigation;
    @BindView(R.id.title) TextView titleView;
    private FragmentPagerAdapter mPagerAdapter;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private int temp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(this,false);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        readingFragment = Reading_Fragment.newInstance();
        luckFragment = Luck_Fragment.newInstance();
        musicFragment = Music_Fragment.newInstance();
        videoFragment = Video_Fragment.newInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == 3){
            Log.i("------", "CurrentItem: " + mViewPager.getCurrentItem());
            temp = 3;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onResume() {
        super.onResume();

        mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        };
        mViewPager.setAdapter(mPagerAdapter);
        //防止频繁的销毁视图
        mViewPager.setOffscreenPageLimit(4);

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        if (temp != 0){
            mViewPager.setCurrentItem(temp);
            mOnPageChangeListener.onPageSelected(temp);
        }

        Log.i("------", "getCurrentItem: " + mViewPager.getCurrentItem());

        mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                titleView.setText(navigation.getMenu().getItem(position).getTitle());
                navigation.getMenu().getItem(position).setChecked(true);
                Log.i("------", "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mViewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };
}
