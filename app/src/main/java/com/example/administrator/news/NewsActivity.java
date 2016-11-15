package com.example.administrator.news;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.entic.OnLoadLister;
import com.example.administrator.fragment.CenterFragment;

import org.json.JSONException;
import org.json.JSONObject;

import HttpInfo.HttpInfo;
import Sql.SqlUtil;
import util.HttpUtils;
import util.OnladResponseListener;

import static com.example.administrator.fragment.CenterFragment.s;

/**
 * Created by Administrator on 2016/11/1.
 */

public class NewsActivity extends BaseActivity implements OnLoadLister,View.OnClickListener, OnladResponseListener{
    WebView mWebView;
ImageView mImg_favorite;
    PopupWindow mPopupWindow;
ImageView mImg__back;
    TextView mTxt_comment;
    RequestQueue mRequestQueue;
    LinearLayout mLine_commnetNumber;
    String mCid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        mWebView= (WebView) findViewById(R.id.web);
mImg_favorite= (ImageView) findViewById(R.id.img_popupWindow);
        mImg__back= (ImageView) findViewById(R.id.img_news_back);
        mTxt_comment= (TextView) findViewById(R.id.txt_news_comment);
        mLine_commnetNumber= (LinearLayout) findViewById(R.id.Line_news_comment_title);
         mRequestQueue = Volley.newRequestQueue(this);
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
        mImg__back.setOnClickListener(this);
        mLine_commnetNumber.setOnClickListener(this);

        Log.e("TAG","--------------------------------------"+mWebView);
        Intent intent=this.getIntent();//启动当前活动意图
        String url=intent.getStringExtra("url");
       // Log.e("adasdddddddddddd","---------------------"+string);
        mWebView.loadUrl(url);
        //mWebView.loadUrl("http://mini.eastday.com/a/161009082150384.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        String nid = CenterFragment.mData.get(CenterFragment.s).getNid();
        new HttpUtils().commentNumberPost(HttpInfo.BASE_URI+HttpInfo.COMMENT_NUMBER,nid,this,mRequestQueue);
//        SharedPreferences sharedPreferences = getSharedPreferences("comment", MODE_PRIVATE);
//        String comment = sharedPreferences.getString("data", "");
        Log.e("----------------","comment==="+mCid);
        mTxt_comment.setText(mCid);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebChromeClient( new WebChromeClient(){
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

                //String ss=news.toString();


                SqlUtil sqlUtil=new SqlUtil(this);
                String summary = CenterFragment.mData.get(s).getSummary();//获取所要插入的数据
                String icon = CenterFragment.mData.get(s).getIcon();
                String stamp = CenterFragment.mData.get(s).getStamp();
                String title = CenterFragment.mData.get(s).getTitle();
                String nid = CenterFragment.mData.get(s).getNid();
                String link = CenterFragment.mData.get(s).getLink();
                String type = CenterFragment.mData.get(s).getType();
                sqlUtil.collect(summary,icon,stamp,title,nid,link,type);//插入数据

//                String s = mData.oString();
//                try {
//                    JSONObject object=new JSONObject(s);
//                    String summary = object.getString("summary");
//                    String icon = object.getString("icon");
//                    String stamp = object.getString("stamp");
//                    String title = object.getString("title");
//                    String nid = object.getString("nid");
//                    String link = object.getString("link");
//                    String type = object.getString("type");



                break;
            case R.id.img_news_back:
                this.setResult(-1);
                finish();
                break;
            case R.id.Line_news_comment_title:
        //nid = CenterFragment.mData.get(CenterFragment.s).getNid();
Intent intent=new Intent(NewsActivity.this,CommentActivity.class);
                startActivity(intent);
        }
    }

    @Override
    public void getResponse(String message) {
        Log.e("------------------","message============"+message);
        try {
            JSONObject object = new JSONObject(message);
            String data = object.getString("data");
            SharedPreferences preferences = getSharedPreferences("comment",MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("data",data);
            Log.e("-------------","data===="+data);
            edit.commit();
            mCid = preferences.getString("data","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
