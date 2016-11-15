package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.eneity.LogInfo;
import com.example.administrator.news.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/10.
 */

public class LogAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<LogInfo> mList;
    public LogAdapter(Context context){
        this.mContext=context;
    }
    public void setLog(ArrayList<LogInfo> list){
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
            holder=new Holder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.log_adapter,parent,false);
            holder.time= (TextView) convertView.findViewById(R.id.txt_log_time);
            holder.address= (TextView) convertView.findViewById(R.id.txt_log_address);
            holder.device= (TextView) convertView.findViewById(R.id.txt_log_device);
            convertView.setTag(holder);
        }else {
            holder= (Holder) convertView.getTag();
        }
        holder.time.setText(mList.get(position).getTime());
        holder.address.setText(mList.get(position).getAddress());
        if(mList.get(position).getDevice() == 0){
            holder.device.setText("手机登陆");
        }else if(mList.get(position).getDevice()==1){
            holder.device.setText("电脑登陆");
        }
        //holder.device.setText(mList.get(position).getDevice());
        return convertView;
    }
    public class Holder{
        TextView time;
        TextView address;
        TextView device;
    }
}
