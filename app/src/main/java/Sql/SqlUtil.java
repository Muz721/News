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
        SQLiteDatabase database = mSql.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SqlInfo._SUMMARY,summary);

        values.put(SqlInfo._ICON,icon);
        values.put(SqlInfo._STAMP,stamp);
        values.put(SqlInfo._TITLE,title);
        values.put(SqlInfo._NID,nid);
        values.put(SqlInfo._LINK,link);
        values.put(SqlInfo._TYPE,type);
        database.insert(SqlInfo.TABLE_NAME,null,values);
    }
public  ArrayList<NewsInfo> query(){
    SQLiteDatabase database =mSql.getReadableDatabase();
    Log.e("---------------","kkkkkkk"+database);
        ArrayList<NewsInfo> favorite=new ArrayList<>();

       mCursor = database.query(SqlInfo.TABLE_NAME, null, null, null, null, null, null);
    while (mCursor.moveToNext()) {
        String summary = mCursor.getString(mCursor.getColumnIndex("summary"));
        String icon = mCursor.getString(mCursor.getColumnIndex("icon"));
        String stamp = mCursor.getString(mCursor.getColumnIndex("stamp"));
        String title = mCursor.getString(mCursor.getColumnIndex("title"));
        String nid = mCursor.getString(mCursor.getColumnIndex("nid"));
        String link = mCursor.getString(mCursor.getColumnIndex("link"));
        String type=mCursor.getString(mCursor.getColumnIndex("type"));

        favorite.add(new NewsInfo(summary,icon,stamp,title,nid,link,type));
    }
return favorite;
  }

}
