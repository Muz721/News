package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.eneity.NewsInfo;
import com.example.administrator.news.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */

public class NewsAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<NewsInfo> mNewsInfo;
    public  NewsAdapter(Context context){
        this.mContext=context;
    }



    public void setData(ArrayList<NewsInfo> newsInfo){
        this.mNewsInfo=newsInfo;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mNewsInfo==null?0:mNewsInfo.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holdle holdle=null;
        if (null ==convertView ) {
            holdle=new Holdle();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.news_adapter,parent,false);
            holdle.mImg= (ImageView) convertView.findViewById(R.id.img_new);
            holdle.ti= (TextView) convertView.findViewById(R.id.txt_biaoti);
            holdle.news= (TextView) convertView.findViewById(R.id.txt_neirong);
            holdle.time= (TextView) convertView.findViewById(R.id.txt_time);

            convertView.setTag(holdle);
        }else{
            holdle= (Holdle) convertView.getTag();
        }
//        holdle.mImg.setImageDrawable(mJx.get(position).g);
        holdle.ti.setText(mNewsInfo.get(position).getTitle());
        holdle.news.setText(mNewsInfo.get(position).getSummary());
        holdle.time.setText(mNewsInfo.get(position).getStamp());
        Glide.with(mContext).load(mNewsInfo.get(position).getIcon()).into(holdle.mImg);
//        holdle.link.setText(mNewsInfo.get(position).getLink());
        return convertView;
    }
//    public void flish (ArrayList<NewsInfo> jx){
//        this.mNewsInfo=jx;
//        this.notifyDataSetChanged();
//    }
    static class Holdle{
        ImageView mImg;
        TextView ti;
        TextView news;
        TextView time;
    TextView link;
    }

    }

