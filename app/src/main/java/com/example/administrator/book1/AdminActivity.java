package com.example.administrator.book1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AdminActivity extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;
    private TextView find_result_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);
        find_result_tv = (TextView)findViewById(R.id.find_result_tv);

        bt1.setOnClickListener(new View.OnClickListener()//查询
        {
            @Override
            public void onClick(View v) {
                DbHelper dh = new DbHelper();
                dh.setMyDatabaseHelper(new MyDatabaseHelper(AdminActivity.this , "BookDB.db", null, 1));
                // 拿到返回的值
                ArrayList<BookInfo> arrayList = dh.SearchTb("select * from Book", "id");
                for (BookInfo result : arrayList)
                {
                    find_result_tv.setText(find_result_tv.getText() + "作者：" + result.getAuter() + "  书本名称 = " + result.getName() + " 书本页数 = " + result.getPages() + "  书本价格 = " + result.getPrice() + "\n" );

                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {//增加
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this , addbook.class);
                startActivity(intent);
//                DbHelper dh = new DbHelper();
//                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AdminActivity.this , "BookDB.db", null, 1);
//                dh.setMyDatabaseHelper(myDatabaseHelper);
//                dh.InsertTb("insert into Book(auter , price , pages , name) values ('作者' , 100.5 ,500 , '我是书名')");

            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {//删除
            @Override
            public void onClick(View v) {

            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {//修改
            @Override
            public void onClick(View v) {

            }
        });

    }
}
