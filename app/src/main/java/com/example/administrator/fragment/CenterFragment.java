package com.example.administrator.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.administrator.adapter.NewsAdapter;
import com.example.administrator.eneity.MainInfo;
import com.example.administrator.eneity.NewsInfo;
import com.example.administrator.entic.News;
import com.example.administrator.entic.OnLoadLister;
import com.example.administrator.entic.TestAdapter;
import com.example.administrator.news.NewsActivity;
import com.example.administrator.news.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.maxwin.view.XListView;

/**
 * Created by Administrator on 2016/10/31.
 */

public class CenterFragment extends Fragment implements XListView.IXListViewListener,OnLoadLister,AdapterView.OnItemClickListener{
XListView mxListView;
    Handler mHandler;
    public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
//    ArrayList<String>mList;
    ArrayList<NewsInfo> mData;
    TestAdapter mAdapter;
    Gson mGson;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {//在这里写对应的界面
        return inflater.inflate(R.layout.center, container,false);//相应的界面

    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mHandler=new Handler();
        mxListView= (XListView) view.findViewById(R.id.xlit_center_news);//绑定相应的ID
        News news=new News();//调过来
        news.setOnLoadLister(this);//显示子条目内容
        news.execute(PATH);//网址

        mxListView.setPullLoadEnable(true);//上拉加载
        mxListView.setPullRefreshEnable(true);//下拉刷新
        mxListView.setXListViewListener(this);//监听
mxListView.setOnItemClickListener(this);


    }

    @Override
    public void onRefresh() {
mHandler.postDelayed(new Runnable() {
    @Override
    public void run() {
stop();

    }
},2000);
    }
    public void stop(){
        mxListView.stopLoadMore();
        mxListView.stopRefresh();

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mxListView.setRefreshTime(format.format(new Date(System.currentTimeMillis())));
    }

    @Override
    public void onLoadMore() {
mHandler.postDelayed(new Runnable() {
    @Override
    public void run() {
//        for (int i = 0; i < 5; i++) {
//            //mList.add("i=="+i);
//        }
       // mAdapter.setDa(mData);
//        mAdapter.notifyDataSetChanged();
        stop();
    }
},2000);
    }

    @Override
    public void lister(String s) {
        Gson gson=new Gson();//解析
//                        Type type=new TypeToken<NewsInfo>(){}.getType();
                        MainInfo mainInfo = gson.fromJson(s,new  TypeToken<MainInfo>(){}.getType());
                        mData=mainInfo.getData();
        NewsAdapter adapter=new NewsAdapter(getActivity());
        adapter.setData(mData);
        mxListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url=mData.get(position-1).getLink();//获取网址
        Intent intent=new Intent(getActivity(),NewsActivity.class);//要跳转界面

        intent.putExtra("url",url);//携带数据跳转
        startActivity(intent);//开始跳转
    }
}
