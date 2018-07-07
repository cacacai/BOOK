package com.example.administrator.book1;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DbHelper {
    private String CREATE_BOOK = "create table Book("
            + "id integer primary key autoincrement,"
            + "auter text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    private MyDatabaseHelper myDatabaseHelper;//创建数据库
    SQLiteDatabase db;

    /**
     * 更新数据
     */
    public void UpdateTb(String sql_string) {
        db = myDatabaseHelper.getWritableDatabase();
        String sql = sql_string;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.i("err", "update failed");
        }
    }

    /**
     * 插入数据
     */
    public void InsertTb(String sql_string) {
        db = myDatabaseHelper.getWritableDatabase();
//        db.execSQL(CREATE_BOOK);
        String sql = sql_string;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.i("err", "insert failed");
        }
    }

    /**
     * 删除数据
     */
    public void DeleteTb(String sql_string) {
        db = myDatabaseHelper.getWritableDatabase();
        String sql = sql_string;
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.i("err", "delete failed");
        }
    }

    /**
     * 查询数据
     */
    public  ArrayList<BookInfo> SearchTb(String sql_string, String key){

        db = myDatabaseHelper.getWritableDatabase();
        ArrayList<BookInfo> arrayList = new ArrayList<BookInfo>();
        Cursor cursor = null;
//        cursor = db.rawQuery(sql_string, new String[]{key});
        cursor = db.rawQuery(sql_string , null);
        Log.e("DbHelper" , "数据数量 = " + cursor.getCount());
        if(cursor.moveToNext()){
            BookInfo info = new BookInfo();
            info.setId(cursor.getInt(0));
            info.setAuter(cursor.getString(1));
            info.setPrice(cursor.getDouble(2));
            info.setPages(cursor.getInt(3));
            info.setName(cursor.getString(4));
            arrayList.add(info);
        }
        cursor.close();
        return arrayList;
    }


    /**
     * 查询数据
     */
    public  BookInfo SearchTb1(String sql_string, String key){

        db = myDatabaseHelper.getWritableDatabase();
        Cursor cursor = null;
//        cursor = db.rawQuery(sql_string, new String[]{key});
        cursor = db.rawQuery(sql_string , null);
        Log.e("DbHelper" , "数据数量 = " + cursor.getCount());
        if(cursor.moveToNext()){
            BookInfo info = new BookInfo();
            info.setId(cursor.getInt(0));
            info.setAuter(cursor.getString(1));
            info.setPrice(cursor.getDouble(2));
            info.setPages(cursor.getInt(3));
            info.setName(cursor.getString(4));
            return info;
        }
        cursor.close();
        return null;
    }


    public MyDatabaseHelper getMyDatabaseHelper() {
        return myDatabaseHelper;
    }


    public void setMyDatabaseHelper(MyDatabaseHelper myDatabaseHelper) {
        this.myDatabaseHelper = myDatabaseHelper;
    }
}
