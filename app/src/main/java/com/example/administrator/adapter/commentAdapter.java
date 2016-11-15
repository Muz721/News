package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.eneity.CommentCommentInfo;
import com.example.administrator.news.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/14.
 */

public class CommentAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<CommentCommentInfo> mList;
    public CommentAdapter(Context context){
        this.mContext=context;
    }
    public void setData(ArrayList<CommentCommentInfo> list){
        this.mList=list;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(null==convertView){
         holder = new Holder();

        convertView = LayoutInflater.from(mContext).inflate(R.layout.comment_adapter, parent, false);
        holder.uid= (TextView) convertView.findViewById(R.id.txt_news_comment_user);
            holder.portrait= (ImageView) convertView.findViewById(R.id.img_news_comment_user);
            holder.stamp= (TextView) convertView.findViewById(R.id.txt_news_comment_time);
            holder.content= (TextView) convertView.findViewById(R.id.txt_news_comment_comment);
            convertView.setTag(holder);
        }
        else {
            holder= (Holder) convertView.getTag();
        }
        holder.uid.setText(mList.get(position).getUid());
        holder.stamp.setText(mList.get(position).getStamp());
        holder.content.setText(mList.get(position).getContent());
        Glide.with(mContext).load(mList.get(position).getPortrait()).into(holder.portrait);
        return convertView;
    }
    public class Holder{

        TextView uid;
        ImageView portrait;
        TextView stamp;
        TextView content;
    }
}
