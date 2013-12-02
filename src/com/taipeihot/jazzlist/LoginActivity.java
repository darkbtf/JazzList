package com.taipeihot.jazzlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.table.Table;

public class LoginActivity extends Activity {

    AlertDialog registerDialog;
    View alertView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initRegisterDialog();
        writeAccount();
        readAccount();
        new Table(this);
        connect_to_server();
    }

    private void initRegisterDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        alertView = inflater.inflate(R.layout.register_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertView)
        .setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
        		System.out.println("errrrr");
            	EditText accountText = (EditText) alertView.findViewById(R.id.register_account_text);
            	EditText passwordText = (EditText) alertView.findViewById(R.id.register_password_text);
            	EditText confirmText = (EditText) alertView.findViewById(R.id.register_confirm_text);
            	String accountStr = accountText.getText().toString();
            	String passwordStr = passwordText.getText().toString();
            	String confirmStr = confirmText.getText().toString();
            	if (!confirmStr.equals(passwordStr)) {
            		errorDialog("incorrect confirm password");
            		System.out.println("errrrr");
            	} else {
            		if (!isNetworkAvailable()) {
                		errorDialog("No Internet connection, try again later.");
            		} else {
            			boolean result = Data.register(accountStr, passwordStr);
            			if (result == true) {
            				Data.login(accountStr, passwordStr);
            			} else {
            				errorDialog("Dulplicated account name.");
            			}
            		}
            		// TODO: galagala
            	}
            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        registerDialog = builder.create();
        //registerDialog = new AlertDialog(this);
        //registerDialog.setContentView(R.layout.register_dialog);
        //Window dialogWindow = registerDialog.getWindow();
        // WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        //lp.copyFrom(registerDialog.getWindow().getAttributes());
        //lp.y = 100;
        //lp.height = 600;
        //dialogWindow.setAttributes(lp);
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
        //intent.setClass(LoginActivity.this, MainActivity.class);
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        registerDialog.getWindow().setLayout(800, 1000);
        registerDialog.show();
        //Window dialogWindow = registerDialog.getWindow();
        /*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(registerDialog.getWindow().getAttributes());
        lp.y = 100;
        lp.height = 1000;
        registerDialog.getWindow().setAttributes(lp);*/
        //Intent intent = new Intent();
        //intent.setClass(LoginActivity.this, MainActivity.class);
        //intent.setClass(LoginActivity.this, MainActivity.class);
        //startActivity(intent);
        return;

    }

    public void registerLater(View view) {
        Intent intent = new Intent();
        //intent.setClass(LoginActivity.this, MainActivity.class);
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void writeAccount(){
        SharedPreferences pre = getSharedPreferences("loginValue",MODE_PRIVATE);
        String account = "david942jizz@gmail.com";
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
    private void connect_to_server(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                if(isNetworkAvailable())
                    CommunicateHelper.start();
            }
        });
        thread.start();
    }
    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    
    public void errorDialog(String errorMessage) {
		Dialog err = new Dialog(alertView.getContext());
		err.setTitle(errorMessage);
		err.show();
    }
}
