package com.example.administrator.news;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Administrator on 2016/11/2.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void intent(Class cls, int position) {
        // 携带数据跳转
        Intent intent = new Intent(BaseActivity.this, cls);
        // 携带数据
        intent.putExtra("position", position);
        // 跳转
        startActivity(intent);
    }
}
