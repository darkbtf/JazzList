package com.taipeihot.jazzlist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		try {
	        FileOutputStream out = openFileOutput("user.dat", Context.MODE_PRIVATE);
			String account = "david";
	        String password = "Hue Nguyen";
	        out.write(account.getBytes());
	        out.write("\n".getBytes());
	        out.write(password.getBytes());
	        out.write("\n".getBytes());
	        out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    private void read_from_file_test(){
    	try{
    		FileInputStream in = openFileInput("user.dat");
    		String account = Util.readline(in);
    		String password = Util.readline(in);
    		Util.errorReport("account: "+account+", password = "+password);
    	}
    	catch(FileNotFoundException e){
    		e.printStackTrace();
    	}
    }
}
