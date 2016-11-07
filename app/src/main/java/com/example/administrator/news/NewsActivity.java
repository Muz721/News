package com.example.administrator.news;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.administrator.entic.OnLoadLister;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsActivity extends BaseActivity implements OnLoadLister,View.OnClickListener{
    WebView mWebView;
ImageView mImg_favorite;
    PopupWindow mPopupWindow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        mWebView= (WebView) findViewById(R.id.web);
mImg_favorite= (ImageView) findViewById(R.id.img_popupWindow);
        mPopupWindow=new PopupWindow();
        View view=this.getLayoutInflater().inflate(R.layout.popupwindow,null);
        mPopupWindow.setContentView(view);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.commenttm));
       // mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow));
        mImg_favorite.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_popupWindow:
                mPopupWindow.showAsDropDown(mImg_favorite);
                break;
        }
    }
}
