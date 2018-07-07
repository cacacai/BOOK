package com.example.administrator.book1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register extends AppCompatActivity {

    private EditText username_EditText;
    private EditText password_EditText;
    private Button admin_login;
    private Button user_login;
    private Button register;
    SQLiteDatabase db;

    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        username_EditText = (EditText) findViewById(R.id.username_EditText);
        password_EditText = (EditText) findViewById(R.id.password_EditText);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username_EditText.getText().toString();
                String password = password_EditText.getText().toString();
                if (!(name.equals("") && password.equals(""))) {
                    if (addUser(name, password)) {
                        DialogInterface.OnClickListener ss = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                //跳转到登陆界面
                                Intent in = new Intent();
                                in.setClass(Register.this, Login.class);
                                startActivity(in);
                                //摧毁当前activity
                                finish();
                                //Register.this.onDestroy();
                            }
                        };
                        new AlertDialog.Builder(Register.this).setTitle("注册成功").setMessage("注册成功").setPositiveButton("确定", ss).show();
                    } else {
                        new AlertDialog.Builder(Register.this).setTitle("注册失败").setMessage("注册失败").setPositiveButton("确定", null);
                    }
                } else {
                    new AlertDialog.Builder(Register.this).setTitle("账号密码不能为空").setMessage("账号密码不能为空").setPositiveButton("确定", null);
                }
            }
        });
    }

    //添加账户
    private Boolean addUser(String name,String password) {
        String str = "insert into tb_user values(?,?)";
//        Login main = new Login();
        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()+"/test.dbs",null);
        Login.db = db;
        try{
            db.execSQL(str,new String[]{name,password});
            return true;
        }catch (Exception e){
            db.execSQL("create table tb_user(name varchar(30)primary key,password varchar(30))");
        }
        return false;
    }
}