package com.example.administrator.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.entic.OnLoadLister;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsActivity extends AppCompatActivity implements OnLoadLister{
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        mWebView= (WebView) findViewById(R.id.web);

        Log.e("TAG","--------------------------------------"+mWebView);
        Intent intent=this.getIntent();//启动当前活动意图
        String url=intent.getStringExtra("url");

       // Log.e("adasdddddddddddd","---------------------"+string);
        mWebView.loadUrl(url);
        //mWebView.loadUrl("http://mini.eastday.com/a/161009082150384.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                Log.e("TAG","--------------------------------------"+newProgress);
            }


        });

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("TAG","--------------------------------------"+url);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("TAG","-----------------onPageFinished---------------------"+url);

            }
        });
    }



    @Override
    public void onContentChanged() {
        super.onContentChanged();


    }

    @Override
    public void lister(String s) {

    }
}
