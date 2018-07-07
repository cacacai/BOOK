package com.example.administrator.book1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {


    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    //账号密码
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button adminlogin;
    private Button userlogin;
    private Button register;
    private CheckBox rememberPass;
    //创建数据库
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



        //pref = PreferenceManager.getDefaultSharedPreferences(this);
        usernameEdit = (EditText) findViewById(R.id.username_EditText);
        passwordEdit = (EditText) findViewById(R.id.password_EditText);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        adminlogin = (Button) findViewById(R.id.admin_login);
        userlogin = (Button) findViewById(R.id.user_login);
        register = (Button) findViewById(R.id.register);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        //rememberPass.setOnClickListener(this);

        db = SQLiteDatabase.openOrCreateDatabase(Login.this.getFilesDir().toString() + "/test.dbs", null);
        //跳转注册界面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Login.this, Register.class);
                startActivity(intent);
            }
        });


        rememberPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                //账号是admin  密码123456，管理员登录成功
                if (username.equals("admin") || password.equals("123456")) {
                        Intent intent = new Intent();
                        intent.setClass(Login.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
//                    new AlertDialog.Builder(Login.this).setTitle("正确")
//                            .setMessage("成功登陆")
//                            .show();
                    Toast.makeText(Login.this , "成功登陆" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        userlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (username.equals("") || password.equals("")) {
                    //弹出消息框
                    new AlertDialog.Builder(Login.this).setTitle("错误")
                            .setMessage("账号或密码不能为空").setPositiveButton("确定", null).show();
                } else {
                    if (isUserinfo(username, password)) {
                        Intent intent = new Intent();
                        intent.setClass(Login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        //弹出消息框
                        new AlertDialog.Builder(Login.this).setTitle("错误")
                                .setMessage("账号或密码错误").setPositiveButton("确定", null).show();
                    }
                }

            }
        });
    }


    //判断输入用户是否正确
    public Boolean isUserinfo(String name, String pwd) {
        try {
            String str = "select * from tb_user where name =? and password = ?";
            Cursor cursor = db.rawQuery(str, new String[]{name, pwd});
            if (cursor.getCount() <= 0) {
//                new AlertDialog.Builder(Login.this).setTitle("错误")
//                        .setMessage("账号或密码错误！").setPositiveButton("确定", null)
//                        .show();
                return false;
            }
            else {
                new AlertDialog.Builder(Login.this).setTitle("正确")
                        .setMessage("成功登陆").setPositiveButton("确定", null)
                        .show();
                return true;
            }

        } catch (SQLiteException e) {
            db.execSQL("create table tb_user(name varchar(30)primary key,password varchar(30))");
        }
        return false;
    }
}