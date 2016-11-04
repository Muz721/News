package com.example.administrator.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.news.R;

import org.json.JSONException;
import org.json.JSONObject;

import HttpInfo.HttpInfo;
import util.HttpUtils;
import util.OnladResponseListener;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2016/11/3.
 */

public class RegisterFragment extends Fragment implements OnladResponseListener,View.OnClickListener{
    EditText mEt_email;
    EditText mEt_nickname;
    EditText mEt_password;
    Button mButton_register;
    RequestQueue mRequestQueue;
    public static final String PREFS_NAME="config";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

mButton_register= (Button) view.findViewById(R.id.btn_register_register);
        mEt_email = (EditText) view.findViewById(R.id.et_email);
        mEt_nickname = (EditText) view.findViewById(R.id.et_nickname);
        mEt_password = (EditText) view.findViewById(R.id.et_password);
        mButton_register.setOnClickListener(this);
        mRequestQueue = Volley.newRequestQueue(getActivity());
    }

    @Override
    public void getResponse(String message) {
        Log.e("=====================",message);
//        String a="message";
        try {
        JSONObject object = new JSONObject(message);
            String data=object.getString("data");
            JSONObject arr=new JSONObject(data);
//            JSONObject data=arr.getJSONObject(1);
//            JSONObject data1=arr.getJSONObject(2);
            String token1=arr.getString("result");
            String token=arr.getString("token");
            String s=arr.getString("explain");
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token",token);
            editor.putString("explain",s);
            editor.putString("explain",token1);
        Log.e("fffffffffff","=============="+editor.putString("explain",token1));
        editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void onClick(View v) {

        String email=mEt_email.getText().toString();
        String nickname=mEt_nickname.getText().toString();
        String password=mEt_password.getText().toString();
        //new HttpUtils().connection(HttpInfo.BASE_URI+HttpInfo.REGITER+"ver=1&uid="+nickname+"&email="+email+"&pwd="+password,this,mRequestQueue);
new HttpUtils().connectionPost(HttpInfo.BASE_URI+HttpInfo.REGITER,nickname,email,password,this,mRequestQueue);

    }
}
