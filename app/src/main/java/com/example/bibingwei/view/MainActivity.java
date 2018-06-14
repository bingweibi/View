package com.example.bibingwei.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bibingwei.view.fragments.Luck_Fragment;
import com.example.bibingwei.view.fragments.Music_Fragment;
import com.example.bibingwei.view.fragments.Reading_Fragment;
import com.example.bibingwei.view.fragments.Video_fragment;

import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private Fragment readingFragment,luckFragment,musicFragment,videoFragment;
    private BottomNavigationView navigation;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //透明状态栏
        StatusBarCompat.translucentStatusBar(this);
        //SDK >= 21时, 取消状态栏的阴影
        StatusBarCompat.translucentStatusBar(this,false);
        titleView = findViewById(R.id.title);
        mViewPager = findViewById(R.id.fragment_container);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        readingFragment = Reading_Fragment.newInstance();
        luckFragment = Luck_Fragment.newInstance();
        musicFragment = Music_Fragment.newInstance();
        videoFragment = Video_fragment.newInstance();
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
            titleView.setText(navigation.getMenu().getItem(position).getTitle());
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
