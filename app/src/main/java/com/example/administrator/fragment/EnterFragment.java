package com.example.administrator.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.administrator.news.R;

import org.json.JSONException;
import org.json.JSONObject;

import HttpInfo.HttpInfo;
import util.HttpUtils;
import util.OnladResponseListener;

/**
 * Created by Administrator on 2016/11/3.
 */

public class EnterFragment extends Fragment implements View.OnClickListener, OnladResponseListener {
    EditText mEt_nickame;
    EditText mEt_password;
    Button mBtn_register;
    Button mBtn_forgetPassword;
    Button mBtn_enter;
    DrawerLayout mDrawerLayout;
    RequestQueue mRequestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initer(view);

    }

    private void initer(View view) {
        mEt_nickame= (EditText) view.findViewById(R.id.et_enter_nickname);
        mEt_password= (EditText) view.findViewById(R.id.et_enter_password);
        mBtn_register= (Button) view.findViewById(R.id.btn_register);
        mBtn_forgetPassword= (Button) view.findViewById(R.id.btn_forgetPassword);
        mBtn_enter= (Button) view.findViewById(R.id.btn_enter);
        mEt_nickame.setOnClickListener(this);
        mEt_password.setOnClickListener(this);
        mBtn_register.setOnClickListener(this);
        mBtn_forgetPassword.setOnClickListener(this);
        mBtn_enter.setOnClickListener(this);
        mRequestQueue = Volley.newRequestQueue(getActivity());

        mDrawerLayout= (DrawerLayout) view.findViewById(R.id.drawerLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
            
                FragmentTransaction fragmentRegister = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentRegister.replace(R.id.fragLayout,new RegisterFragment());
                fragmentRegister.commit();
               // mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.btn_forgetPassword:
                break;
            case R.id.btn_enter:
                String nickName=mEt_nickame.getText().toString();
                String password=mEt_password.getText().toString();
                new HttpUtils().enterPost(HttpInfo.BASE_URI+HttpInfo.ENTER,nickName,password,this,mRequestQueue);

                break;
        }
    }


    @Override
    public void getResponse(String message) {
        Log.e("---=-=-=-=-=-=-=-=-=",message);
        try {
            JSONObject object=new JSONObject(message);
            int status=object.getInt("status");
            switch (status){
                case 0:
            String da=object.getString("data");
            JSONObject data=new JSONObject(da);
            int result=data.getInt("result");
            switch (result){
                case 0:
        Toast.makeText(getActivity(),"登录成功！",Toast.LENGTH_SHORT).show();
                    break;
                case -1:
                    Toast.makeText(getActivity(),"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(getActivity(),"限制登陆！",Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(getActivity(),"异地登陆等异常！",Toast.LENGTH_SHORT).show();
                    break;
            }
                    break;
                case -1:
                    Toast.makeText(getActivity(),"用户名或密码错误！",Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(getActivity(),"版本过低！",Toast.LENGTH_SHORT).show();
                    break;
                case -3:
                    Toast.makeText(getActivity(),"服务器拒绝访问！",Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
