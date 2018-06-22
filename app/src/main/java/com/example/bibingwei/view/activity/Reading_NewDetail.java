package com.example.bibingwei.view.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.bibingwei.view.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import qiu.niorgai.StatusBarCompat;

/**
 * @author bibingwei
 */
public class Reading_NewDetail extends AppCompatActivity {

    @BindView(R.id.webView)WebView mWebView;

    private String newsAddress;
    private WebSettings mWebSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading__new_detail);
        ButterKnife.bind(this);

        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#3F51B5"));
        mWebView.setDrawingCacheEnabled(true);
        mWebSettings = mWebView.getSettings();
        newsAddress = getIntent().getStringExtra("newsAddress");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.loadUrl(newsAddress);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    protected void onDestroy() {

        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
