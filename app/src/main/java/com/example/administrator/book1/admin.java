package com.example.administrator.book1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class admin extends AppCompatActivity {
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);

        bt1.setOnClickListener(new View.OnClickListener()//查询
        {
            @Override
            public void onClick(View v) {
                DbHelper dh = new DbHelper();
                // 拿到返回的值
                BookInfo result = dh.SearchTb1("select name from Book where id=?", "*");
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {//增加
            @Override
            public void onClick(View v) {

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


    }}





