package com.example.administrator.news;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.administrator.adapter.LogAdapter;
import com.example.administrator.eneity.LogInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import HttpInfo.HttpInfo;
import util.HttpUtils;
import util.OnladResponseListener;


/**
 * Created by Administrator on 2016/11/9.
 */

public class UserActivity extends AppCompatActivity implements OnladResponseListener,View.OnClickListener{
    RequestQueue mRequestQueue;
    ListView mList;
   ArrayList<LogInfo> mLog;
    ImageView mBack;
    Button mExit;
    TextView mId;
    String mUid;
    String mPortrait;
    String mIntegration;
    String mComnum;
    TextView mGration;
    ImageView mPortaithttp;
    TextView mComment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
//        Intent intent=this.getIntent();
//        String token=intent.getStringExtra("token");
        SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
        String token=preferences.getString("token","");
        Log.e("========","ssssssssssssssssssss"+token);
        mRequestQueue= Volley.newRequestQueue(this);
        new HttpUtils().logPost(HttpInfo.BASE_URI+HttpInfo.LOG,token, this,mRequestQueue);
        mList= (ListView) findViewById(R.id.list_log);
mBack= (ImageView) findViewById(R.id.img_user_back);
        mExit= (Button) findViewById(R.id.btn_user_exit);
        mId= (TextView) findViewById(R.id.txt_user_id);
        mGration= (TextView) findViewById(R.id.txt_user_integral_count);
        mPortaithttp= (ImageView) findViewById(R.id.img_user_user);
        mComment= (TextView) findViewById(R.id.txt_user_comment);
        mBack.setOnClickListener(this);
        mExit.setOnClickListener(this);
        mPortaithttp.setOnClickListener(this);


    }



    @Override
    public void getResponse(String message) {
        Log.e("------","message"+message);
        try {
        JSONObject object=new JSONObject(message);
           // JSONObject object=new JSONObject(message);
            String data=object.getString("data");
            JSONObject arr=new JSONObject(data);
            mUid=arr.getString("uid");
            mPortrait=arr.getString("portrait");
            mIntegration=arr.getString("integration");
            mComnum=arr.getString("comnum");
            Log.e("-----------","portrait"+mPortrait);
          String loginlog=arr.getString("loginlog");
            mId.setText(mUid);
            mGration.setText(mIntegration);
            Glide.with(this).load(mPortrait).into(mPortaithttp);
            mComment.setText(mComnum);
            SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putString("portration",mPortrait);
            edit.commit();
            // JSONArray array=new JSONArray(loginlog);

//            ArrayList<LoginLogInfo> ft=array.f
//            for (int i = 0; i <array.length() ; i++) {
//             JSONObject    jsobject=array.getJSONObject(i);
//                String time=jsobject.getString("time");
//                String address=jsobject.getString("address");
//                String device=jsobject.getString("device");

          //  }
            Gson gson=new Gson();
            Type type=new TypeToken<ArrayList<LogInfo>>(){}.getType();
                    //gson.fromJson(loginlog,new TypeToken<LoginLogInfo>(){}.getType());
           // mLog=loginLogInfo.getLog();
            mLog=gson.fromJson(loginlog,type);
//            for (LogInfo log:mLog) {
//
//            }
            LogAdapter logAdapter=new LogAdapter(this);
            logAdapter.setLog(mLog);
            mList.setAdapter(logAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_user_back:
                Intent inter=new Intent(UserActivity.this,MainActivity.class);
                startActivity(inter);
                break;
            case R.id.btn_user_exit:
                SharedPreferences prefernces=this.getSharedPreferences("user",MODE_PRIVATE);
                SharedPreferences.Editor edit = prefernces.edit();
                edit.putInt("status",-1);
                edit.putInt("result",-1);
                edit.commit();
                Intent intent=new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);

                break;
        }
    }
}
