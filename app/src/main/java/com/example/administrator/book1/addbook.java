package com.example.administrator.book1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class addbook extends AppCompatActivity {
    private EditText bookname_EditText;
    private EditText id_EditText;
    private EditText auter_EditText;
    private EditText price_EditText;
    private EditText pages_EditText;
    private Button addBook_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        id_EditText = findViewById(R.id.id_EditText);
        bookname_EditText = findViewById(R.id.bookname_EditText);
        auter_EditText = findViewById(R.id.auter_EditText);
        price_EditText = findViewById(R.id.price_EditText);
        pages_EditText = findViewById(R.id.pages_EditText);
        addBook_Button = findViewById(R.id.addBook_Button);

        addBook_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = id_EditText.getText().toString();
                String bookname = bookname_EditText.getText().toString();
                String auter = auter_EditText.getText().toString();
                String price = price_EditText.getText().toString();
                String pages = pages_EditText.getText().toString();
                if (!(id.equals("") && bookname.equals("") && auter.equals("") && price.equals("")
                        && pages.equals(""))) {

                    if (addBook(id, bookname, auter, price, pages)) {
                        DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                //跳转到图书查询界面
                                Intent in = new Intent();
                                in.setClass(addbook.this, AdminActivity.class);
                                startActivity(in);
                            }
                        };
                        new AlertDialog.Builder(addbook.this).setTitle("添加成功").setMessage("添加成功").setPositiveButton("确定", ss).show();
                    } else {
                        new AlertDialog.Builder(addbook.this).setTitle("添加失败").setMessage("添加失败").setPositiveButton("确定", null);
                    }
                } else {
                    new AlertDialog.Builder(addbook.this).setTitle("内容不能为空").setMessage("内容不能为空").setPositiveButton("确定", null);
                }
            }
        });
    }

    //添加图书
    private Boolean addBook(String id,String bookname, String auter, String price, String pages) {

//        DbHelper dh = new DbHelper();
//        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AdminActivity.this , "BookDB.db", null, 1);
//        dh.setMyDatabaseHelper(myDatabaseHelper);
//        dh.InsertTb("insert into Book(auter , price , pages , name) values ('作者' , 100.5 ,500 , '我是书名')");

        String str = "insert into Book (auter , price , pages , name) values('" + auter + "'," + price + "," + pages + ",'"  + bookname+ "')";
        MyDatabaseHelper databaseHelper = new MyDatabaseHelper(this, "BookDB.db", null, 1);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        try{
            db.execSQL(str);
            return true;
        }catch (Exception e){
            db.execSQL("create table Book("
                    + "id integer primary key autoincrement,"
                    +"auter text,"
                    +"price real,"
                    +"pages integer,"
                    +"name text)");
        }
        return false;
    }
}
