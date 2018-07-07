package com.example.administrator.book1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button bt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt5= findViewById(R.id.bt5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dh = new DbHelper();
                dh.setMyDatabaseHelper(new MyDatabaseHelper(MainActivity.this , "BookDB.db", null, 1));
                // 拿到返回的值
               BookInfo result = dh.SearchTb1("select name from Book where id=?", "id");
            }
        });
    }
}


