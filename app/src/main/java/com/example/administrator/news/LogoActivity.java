package com.example.administrator.news;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by Administrator on 2016/10/27.
 */

public class LogoActivity extends Activity {
    public static final String PREFS_NAME="config";
    public static final String IS_FIRST="fist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=this.getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        boolean flag=sharedPreferences.getBoolean(IS_FIRST,true);//判断是不是第一次进这个软件

        if (flag) {
            Intent intent=new Intent(LogoActivity.this,GuideActivity.class);
            startActivity(intent);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(IS_FIRST,false);
            editor.commit();
            this.finish();
                   // overridePendingTransition(R.anim.anim_guide,R.anim.anim_guangbo_into);

        }else {
            setContentView(R.layout.activity_logo);
//            ImageView imageView= (ImageView) findViewById(R.id.img_logo);
//            Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_logo);
//            imageView.startAnimation(animation);
//            animation.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    Intent intent=new Intent(LogoActivity.this,MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//            });
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(6000);//这个界面只停留3秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(LogoActivity.this,MainActivity.class);

                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }){}.start();
        }


    }
}
