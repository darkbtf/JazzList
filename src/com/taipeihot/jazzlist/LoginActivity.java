package com.taipeihot.jazzlist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.taipeihot.jazzlist.model.Data;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        writeAccount();
        readAccount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    public void login(View view) {
        // TODO: implement login
        System.out.println("login");
        Intent intent = new Intent();
        //intent.setClass(LoginActivity.this, MainActivity.class);
        intent.setClass(LoginActivity.this, LeftAndRightActivity.class);
        System.out.println("meow");
        startActivity(intent);
        System.out.println("meow2");
    }

    public void registerLater(View view) {
        Intent intent = new Intent();
        //intent.setClass(LoginActivity.this, MainActivity.class);
        intent.setClass(LoginActivity.this, LeftAndRightActivity.class);
        startActivity(intent);
    }

    private void writeAccount(){
        SharedPreferences pre = getSharedPreferences("loginValue",MODE_PRIVATE);
        String account = "david942j@gmail.com";
        String nickname = "david";
        String password = "Hue Nguyen";
        pre.edit().putString("account", account).putString("nickname",nickname).putString("password",Util.MD5(password)).commit();
    }
    private void readAccount(){
        SharedPreferences sp = getSharedPreferences("loginValue", MODE_PRIVATE);
        String account = sp.getString("account", null);
        String nickname = sp.getString("nickname", null);
        String password = sp.getString("password", null);
        Data.setAccount(account,nickname,password);
    }
}
