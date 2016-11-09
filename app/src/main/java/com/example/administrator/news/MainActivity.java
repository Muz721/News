package com.example.administrator.news;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.adapter.NewsAdapter;
import com.example.administrator.entic.News;
import com.example.administrator.entic.OnLoadLister;
import com.example.administrator.fragment.CenterFragment;
import com.example.administrator.fragment.EnterFragment;
import com.example.administrator.fragment.FavoriteFragment;

/**
 * Created by Administrator on 2016/10/27.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener ,OnLoadLister,AdapterView.OnItemClickListener{
    Button mBtn_menu;
    Button mBtn_user;
    DrawerLayout mDrawerLayout;
    ImageView mImg_right_user;
    LinearLayout mLilt_lift_news;
    TextView mTxt_right_enter;
    LinearLayout mLine_lift_favorite;

    //Button mBtn_right_register;
    //ListView mListView;
//public static final String PATH="http://118.244.212.82:9092/newsClient/path/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.activity_main);
        initview();
        //DrawerLayout
        initNetwork();
        //initAdapter();
       // initData();
        //ArrayList<String> mList = null;
//        TestAdapter testAdapter=new TestAdapter(this);
//        testAdapter.setData(m);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
      // CenterFragment fragment= (CenterFragment) getSupportFragmentManager().findFragmentById(R.id.frag_center);
        CenterFragment fragment=new CenterFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragLayout,fragment);
        fragmentTransaction.commit();



    }

    private void initData() {
        News news=new News();

    }

    private void initAdapter() {
        NewsAdapter adapter=new NewsAdapter(this);
        //mListView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    private void initNetwork() {
//        OkHttpClient client = new OkHttpClient();
//        Request build = new Request.Builder().url(PATH).build();
//        Call call = client.newCall(build);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                ResponseBody body = response.body();
//                final String string=body.string();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Gson gson=new Gson();
//                        Type type=new TypeToken<MainInfo>(){}.getType();
//                        MainInfo mainInfo = gson.fromJson(string, type);
//                        ArrayList<NewsInfo> data=mainInfo.getData();
//                        for (NewsInfo newsInfo : data) {
//
//                        }
//                    }
//                });
//            }
//        });

    }

    private void initview() {
        mBtn_menu= (Button) findViewById(R.id.btn_menu);
        mBtn_user= (Button) findViewById(R.id.btn_main_user);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        //mListView= (ListView) findViewById(R.id.list_news);
        mBtn_user.setOnClickListener(this);
        mBtn_menu.setOnClickListener(this);

        mImg_right_user= (ImageView) findViewById(R.id.img_right_user);
        mImg_right_user.setOnClickListener(this);
        mTxt_right_enter= (TextView) findViewById(R.id.txt_right_enter);
        mTxt_right_enter.setOnClickListener(this);
//        mBtn_right_register= (Button) findViewById(R.id.btn_register);
//        mBtn_right_register.setOnClickListener(this);


        mLilt_lift_news= (LinearLayout) findViewById(R.id.line_lift_menu_news);
        mLine_lift_favorite= (LinearLayout) findViewById(R.id.line_lift_menu_favorite);
mLilt_lift_news.setOnClickListener(this);
        mLine_lift_favorite.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_user:

                if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)==true) {
mDrawerLayout.closeDrawer(Gravity.RIGHT);
                }else {
              //  startActivity(new Intent(MainActivity.this,NewsActivity.class));
                mDrawerLayout.openDrawer(Gravity.RIGHT);}
                break;
            case R.id.btn_menu:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)==true) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                }else {
                mDrawerLayout.openDrawer(Gravity.LEFT);}
                break;
//            case R.id.btn_register:
//                FragmentTransaction fragmentRegister = getSupportFragmentManager().beginTransaction();
//                fragmentRegister.replace(R.id.fragLayout,new RegisterFragment());
//                fragmentRegister.commit();
//                mDrawerLayout.closeDrawer(Gravity.RIGHT);
//                break;

            case R.id.img_right_user:
            case R.id.txt_right_enter:
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragLayout,new EnterFragment());
                fragmentTransaction.commit();
                mDrawerLayout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.line_lift_menu_news:
                FragmentTransaction fragmentNews = getSupportFragmentManager().beginTransaction();
                fragmentNews.replace(R.id.fragLayout,new CenterFragment());
                fragmentNews.commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            case R.id.line_lift_menu_favorite:
                FragmentTransaction fragmentFavorite=getSupportFragmentManager().beginTransaction();
                fragmentFavorite.replace(R.id.fragLayout,new FavoriteFragment());
                fragmentFavorite.commit();
                mDrawerLayout.closeDrawer(Gravity.LEFT);
                break;
            //case R.id.btn_register_register:

        }
    }

    @Override
    public void lister(String s) {

       // Intent intent=new Intent
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
