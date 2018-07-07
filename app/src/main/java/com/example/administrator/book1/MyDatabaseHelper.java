package com.example.administrator.book1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    //数据库名称
    private static final String DB_NAME = "MyDB.db";
    //    表名
    private static final String DB_TABLE = "bookinfo";
    //    声明SQLite对象
    private SQLiteDatabase db;
    public static final String CREATE_BOOK = "create table Book("
            + "id integer primary key autoincrement,"
            +"auter text,"
            +"price real,"
            +"pages integer,"
            +"name text)";


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        this.db=db;
        Log.e("database" , "创建表成功");
//        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    //    插入
    public void insert(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    //    查询
    public BookInfo cheak(String sql_string, String key) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = null;
        // db.rawQuery("select name from *** where id=?", new String[]{"1"});
        cursor = db.rawQuery(sql_string, new String[]{key});
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

    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(DB_TABLE, null, null, null, null, null, null);
        return cursor;
    }

    //    关闭数据库
    public void close() {
        if (db != null)
            db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){


    }


}
