package com.example.administrator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.adapter.NewsAdapter;
import com.example.administrator.eneity.NewsInfo;
import com.example.administrator.entic.OnLoadLister;
import com.example.administrator.news.NewsActivity;
import com.example.administrator.news.R;

import java.util.ArrayList;

import Sql.SqlUtil;

/**
 * Created by Administrator on 2016/11/8.
 */

public class FavoriteFragment extends Fragment implements OnLoadLister,AdapterView.OnItemClickListener{
    ListView mList;
 ArrayList<NewsInfo> mData;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.comment,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mList= (ListView) view.findViewById(R.id.list_comment);
        mList.setOnItemClickListener(this);

        NewsAdapter adapter=new NewsAdapter(getActivity());
        SqlUtil sqlUtil=new SqlUtil(getActivity());
mData=sqlUtil.query();
        Log.e("============",""+mData);
        //sc = SqlUtil.query();
        adapter.setData(mData);

        mList.setAdapter(adapter);
        adapter.notifyDataSetChanged();




    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url=mData.get(position-1).getLink();//获取网址
        Intent intent=new Intent(getActivity(),NewsActivity.class);//要跳转界面

        intent.putExtra("url",url);//携带数据跳转
        startActivity(intent);//开始跳转
    }


    @Override
    public void lister(String s) {
    }
}
