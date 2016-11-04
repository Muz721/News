package com.example.administrator.entic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/31.
 */

public
abstract
class MyAdapter<T> extends BaseAdapter {
   ArrayList<T> mList;
    LayoutInflater mInflater;

    public MyAdapter(Context context){
        mInflater=LayoutInflater.from(context);
    }
    public void setDatas(ArrayList<T> list){
//        if (this.mList != null) {
//            this.mList.clear();
//        }
        this.mList=list;
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
        return setView(position,convertView,parent);
    }
    public abstract View setView(int position, View convertView, ViewGroup parent);



}
