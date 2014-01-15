package com.taipeihot.jazzlist;

import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.facebook.*;
import com.facebook.model.*;

import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.table.Table;

public class LoginActivity extends Activity {

    AlertDialog registerDialog;
    View alertView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initRegisterDialog();//Can be removed after release
        new Table(this);
        connect_to_server();
        loginWithFacebook();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode,resultCode, data);
    }
    private void loginWithFacebook(){
    	try{
    		Session.OpenRequest request = new Session.OpenRequest(this);
    		request.setPermissions(Arrays.asList("publish_actions","publish_stream"));
    	} catch (Exception e){
    		e.printStackTrace();
    	}
        Session.openActiveSession(this, true, new Session.StatusCallback() {
        	@SuppressWarnings("deprecation")
			@Override
			public void call(Session session, SessionState state,Exception exception) {
        		if (session.isOpened()) {
        			Util.errorReport(session.getAccessToken()); // get token
        			Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
        				// callback after Graph API response with user object
        				@Override
        				public void onCompleted(GraphUser user, Response response) {
        					if (user != null) {
        						Util.errorReport(user.getName());
        	                    Data.login(user.getId(),user.getName());
        	                    Data.setUser(user);
        	                    toMainActivity();
        					}
        				}
        			});
        		}
        		else Util.errorReport("session.isOpened() failed");
        	}
        });
    }
    private void initRegisterDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        alertView = inflater.inflate(R.layout.register_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(alertView)
        .setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
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
            				toMainActivity();
            			} else {
            				errorDialog("Dulplicated account name.");
            			}
            		}
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
    	EditText accountText = (EditText) findViewById(R.id.account_text);
    	EditText passwordText = (EditText) findViewById(R.id.password_text);
    	
    	String accountStr = accountText.getText().toString();
    	String passwordStr = passwordText.getText().toString();
    	if (Data.login(accountStr, passwordStr)) {
    		toMainActivity();
    	} else {
    		errorDialog("login failed");
    	}
    }

    private void toMainActivity() {
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