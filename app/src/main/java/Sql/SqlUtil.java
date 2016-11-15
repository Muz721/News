package Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.eneity.NewsInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/7.
 */

public class SqlUtil  {
    Context mContext;
   public static  SqlDatabase mSql;
    public static Cursor mCursor;
public SqlUtil(Context context){
    this.mContext=context;
    mSql=new SqlDatabase(context);
}
    public void collect(String summary,String icon,String stamp,String title,String nid,String link,String type){
        SQLiteDatabase database = mSql.getWritableDatabase();//插入数据

        ContentValues values = new ContentValues();//创建一个空的map集合值的默认的最大初始值为8
        values.put(SqlInfo._SUMMARY,summary);

        values.put(SqlInfo._ICON,icon);
        values.put(SqlInfo._STAMP,stamp);
        values.put(SqlInfo._TITLE,title);
        values.put(SqlInfo._NID,nid);
        values.put(SqlInfo._LINK,link);
        values.put(SqlInfo._TYPE,type);
        database.insert(SqlInfo.TABLE_NAME,null,values);//表名，如果值为空则为null,值//插入一行数据//因为数据库不允许插入完全空的集合
    }
public  ArrayList<NewsInfo> query(){
    SQLiteDatabase database =mSql.getReadableDatabase();//查询数据
    Log.e("---------------","kkkkkkk"+database);
        ArrayList<NewsInfo> favorite=new ArrayList<>();//用一个空集合来接受

       mCursor = database.query(SqlInfo.TABLE_NAME, null, null, null, null, null, null);//根据下标查询数据  //表名、要查询的列、查询条件、条件的值、分组、过滤、排序
    while (mCursor.moveToNext()) {//查询//一直符合条件的，直到没有符合条件的位置
        String summary = mCursor.getString(mCursor.getColumnIndex("summary"));//获取列的下标，根据对应的列名获取下标
        String icon = mCursor.getString(mCursor.getColumnIndex("icon"));
        String stamp = mCursor.getString(mCursor.getColumnIndex("stamp"));
        String title = mCursor.getString(mCursor.getColumnIndex("title"));
        String nid = mCursor.getString(mCursor.getColumnIndex("nid"));
        String link = mCursor.getString(mCursor.getColumnIndex("link"));
        String type=mCursor.getString(mCursor.getColumnIndex("type"));

        favorite.add(new NewsInfo(summary,icon,stamp,title,nid,link,type));//用集合来接收
    }
return favorite;//返回值
  }

}
