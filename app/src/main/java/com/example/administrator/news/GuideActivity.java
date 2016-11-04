package com.example.administrator.news;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.administrator.adapter.GuideAdapter;

/**
 * Created by Administrator on 2016/10/27.
 */

public class GuideActivity extends Activity {
ViewPager mVp;
    ImageView[] mImg;
    ImageView[] mRes;
    int[] mId={R.drawable.welcome,R.drawable.wy,R.drawable.bd,R.drawable.small};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mVp= (ViewPager) findViewById(R.id.vp_guide);
        mImg=new ImageView[4];
        mRes=new ImageView[4];
        mImg[0]= (ImageView) findViewById(R.id.img_guide_0);
        mImg[1]= (ImageView) findViewById(R.id.img_guide_1);
        mImg[2]= (ImageView) findViewById(R.id.img_guide_2);
        mImg[3]= (ImageView) findViewById(R.id.img_guide_3);
        mImg[0].setImageResource(R.drawable.adware_style_selected);
        for (int i = 0; i <4 ; i++) {
            mRes[i]=new ImageView(this);
            mRes[i].setImageResource(mId[i]);
        }
initAdapter();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent=new Intent(GuideActivity.this,LogoActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//        }).start();
//ImageView imageView= (ImageView) findViewById(R.id.img_guide_3);
//
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_guide);
//        imageView.startAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                Intent intent=new Intent(GuideActivity.this,LogoActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
    }

    private void initAdapter() {
        GuideAdapter guideAdapter=new GuideAdapter(mRes);//导入适配器
        mVp.setAdapter(guideAdapter);
        guideAdapter.notifyDataSetChanged();//刷新适配器
        mVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i <mImg.length ; i++) {
                    mImg[i].setImageResource(R.drawable.adware_style_default);//让所有原点为灰色     如果不设置这个，则三个都查看过会让原点都变成绿色
                }
mImg[position].setImageResource(R.drawable.adware_style_selected);//让当前的原点为绿色
                if (position==3) {
new Thread(new Runnable() {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(GuideActivity.this,LogoActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.anim_guangbo_into,R.anim.anim_guide);

            }
        });
    }
}).start();
               // RelativeLayout r= (RelativeLayout) findViewById(R.id.rel_guide);
//        Animation animation = AnimationUtils.loadAnimation(GuideActivity.this, R.anim.anim_guide);
       // r.startAnimation(animation);
//        animation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }

//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
