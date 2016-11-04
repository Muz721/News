package com.example.administrator.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/27.
 */

public class GuideAdapter extends PagerAdapter{
ImageView[] mRes;

    public GuideAdapter(ImageView[] res){
        this.mRes=res;
    }
    @Override
    public int getCount() {
        return mRes==null?0:mRes.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=mRes[position];
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(mRes[position]);
    }
}
