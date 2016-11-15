package com.example.administrator.news;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.adapter.CommentAdapter;
import com.example.administrator.eneity.CommentCommentInfo;
import com.example.administrator.eneity.CommentInfo;
import com.example.administrator.fragment.CenterFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import HttpInfo.HttpInfo;
import me.maxwin.view.XListView;
import util.HttpUtils;
import util.OnladResponseListener;

import static com.example.administrator.fragment.CenterFragment.s;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CommentActivity extends AppCompatActivity implements OnladResponseListener,XListView.IXListViewListener{
    XListView mXListview;
    Handler mHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_comment);
        String nid = CenterFragment.mData.get(s).getNid();
        Log.e("---------------","nid========"+nid);
        mHandler=new Handler();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        SharedPreferences comment = getSharedPreferences("comment", MODE_PRIVATE);
        String cid = comment.getString("data", "");
        Log.e("----------","data====="+cid);
        new HttpUtils().commentPost(HttpInfo.BASE_URI+HttpInfo.COMMENT,nid,cid,this,requestQueue);
        mXListview= (XListView) findViewById(R.id.XList_comment);

        mXListview.setPullLoadEnable(true);
        mXListview.setPullRefreshEnable(true);
        mXListview.setXListViewListener(this);//监听


    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void getResponse(String message) {
Log.e("------------","message===="+message);
            Gson gson = new Gson();
        CommentInfo commentInfo=gson.fromJson(message,new TypeToken<CommentInfo>(){}.getType());
        ArrayList<CommentCommentInfo> info =commentInfo.getData();
        CommentAdapter adapter = new CommentAdapter(this);
        adapter.setData(info);
        Log.e("-------------","info"+info);
        mXListview.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
stop();
            }
        },200);
    }

    @Override
    public void onLoadMore() {
mHandler.postDelayed(new Runnable() {
    @Override
    public void run() {
stop();
    }
},200);
    }
    public void stop(){
        mXListview.stopLoadMore();
        mXListview.stopRefresh();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mXListview.setRefreshTime(simpleDateFormat.format(new Date(System.currentTimeMillis())));
    }
}
