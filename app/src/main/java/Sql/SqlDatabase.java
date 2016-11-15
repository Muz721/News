package Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/7.
 */

public class SqlDatabase extends SQLiteOpenHelper {
    public SqlDatabase(Context context) {
        super(context,SqlInfo.DB_NAME,null,SqlInfo.DB_VERSION);//创建数据库  //上下传参、数据库名、游标工厂、版本

    }



    @Override
    public void onCreate(SQLiteDatabase db) {//创建数据库
//String sql="create table ss(_SUMMARY text,_ICON text,_STAMP text,_TITLE text,_NID text,_LINK text,_TYPE text)";
        String sql="create table %1$s(%2$s text,%3$s text,%4$s text,%5$s text,%6$s text,%7$s text,%8$s text)";
        String fomart=String.format(sql,SqlInfo.TABLE_NAME,SqlInfo._SUMMARY,SqlInfo._ICON,SqlInfo._STAMP,SqlInfo._TITLE,SqlInfo._NID,SqlInfo._LINK,SqlInfo._TYPE);
       db.execSQL(fomart);//创建数据库
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//更新数据库

    }
}
