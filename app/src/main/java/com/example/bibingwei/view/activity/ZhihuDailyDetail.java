package com.example.bibingwei.view.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bibingwei.util.HtmlUtil;
import com.example.bibingwei.view.R;
import com.example.bibingwei.view.bean.ZhiHuDetail;
import com.example.bibingwei.view.network.Network;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 */
public class ZhihuDailyDetail extends AppCompatActivity {

    @BindView(R.id.dailyContentImage) ImageView mImageView;
    @BindView(R.id.toolBar) android.support.v7.widget.Toolbar mToolbar;
    @BindView(R.id.comments) FloatingActionButton mFloatingActionButton;
    @BindView(R.id.contentText) WebView mWebView;

    private int zhihuDailyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhihu_daily_detail);
        ButterKnife.bind(this);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#3F51B5"));
        mToolbar.setNavigationIcon(R.drawable.back);
        setSupportActionBar(mToolbar);
        mWebView.setDrawingCacheEnabled(true);
        zhihuDailyID = getIntent().getIntExtra("storyId",0);
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void getData() {
        Network.getZhiHuDetailApi()
                .getZhiHuDetailInfo(zhihuDailyID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhiHuDetail>(){

                    @Override
                    public void accept(ZhiHuDetail zhiHuDetail) {
                        Glide.with(getApplicationContext()).load(zhiHuDetail.getImage()).into(mImageView);
                        mToolbar.setTitle(zhiHuDetail.getTitle());
                        String htmlData = HtmlUtil.createHtmlData(zhiHuDetail);
                        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getApplicationContext(),"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
