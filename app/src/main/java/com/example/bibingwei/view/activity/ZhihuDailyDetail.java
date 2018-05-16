package com.example.bibingwei.view.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bibingwei.view.R;
import com.githang.statusbar.StatusBarCompat;

/**
 * @author bibingwei
 */
public class ZhihuDailyDetail extends AppCompatActivity {

    private int zhihuDailyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_daily_detail);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#3F51B5"));
        zhihuDailyID = getIntent().getIntExtra("storiesId",0);
    }
}
