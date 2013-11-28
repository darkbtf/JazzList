package com.taipeihot.jazzlist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        write_to_file_test();
        read_from_file_test();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    
    public void login(View view) {
        // TODO: implement login
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
    	startActivity(intent);
    }

    public void registerLater(View view) {
    	Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
    	startActivity(intent);
    }
    
    private void write_to_file_test(){
		SharedPreferences pre = getSharedPreferences("loginValue",MODE_PRIVATE);
		String account = "david";
		String password = "Hue Nguyen";  
		pre.edit().putString("account", account).putString("password",Util.MD5(password)).commit();
    }
    private void read_from_file_test(){
		SharedPreferences sp = getSharedPreferences("loginValue", MODE_PRIVATE);  
        String account = sp.getString("account", null);  
        String password = sp.getString("password", null);
		Util.errorReport("account: "+account+", password = "+password);
    }
}
