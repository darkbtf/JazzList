package com.taipeihot.jazzlist;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.Table;
import com.taipeihot.jazzlist.table.TodoTable;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialComponent();
        //new Table(this);
        
        //databaseSample();
        socket_connect();
        //Table.close();
    }
    private Button btn_connect,btn_send;
    private EditText ed;
    private  TextView tv1,tv2;
    static private String str1="nothing",str2="nothing";
    public static Handler receiveServer = null;
    private void initialComponent(){
    	btn_connect = (Button) findViewById(R.id.btn_connect);
    	btn_send = (Button) findViewById(R.id.btn_send);
        tv1 = (TextView) findViewById(R.id.servermsg);
        tv2 = (TextView) findViewById(R.id.clientmsg);
        ed = (EditText) findViewById(R.id.et_input);
    }
    //Thread thread;
    private void socket_connect(){
        setAction();
    }

    private void setAction()  {
    	btn_connect.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			SocketHelper.start();
    		}
    	});  
    	btn_send.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			String str = ed.getText().toString();
    			sendMessage(new String[]{str});
    		}
    	});
    	Thread t = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			while(true){
	    			final String cmd=SocketHelper.getMessage();
	    			tv1.post(new Runnable() {
	    				@Override
	            	    public void run() {
	            	    	tv1.setText(cmd);
	            	    } 
	            	});
	    			try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	});
    	t.start();
    }
    public Boolean sendMessage(String[] messages){
    	return SocketHelper.sendMessage(messages);
	}
	public Boolean sendMessage(byte[] byteStream){
		return SocketHelper.sendMessage(byteStream);
	}
   
    private void databaseSample(){
    	Todo todo1 = new Todo("haha","not done yet");
        Todo todo2 = new Todo("QQ","Done!");
        TodoTable.insert(todo1);
        for(Todo t:TodoTable.All())
        	Log.e("All","id="+t.id()+" content="+t.content+" stauts="+t.status);
        TodoTable.insert(todo2);
        for(Todo t:TodoTable.All())
        	Log.e("All","id="+t.id()+" content="+t.content+" stauts="+t.status);
        
        for(Todo t:TodoTable.where("content = ? and _id <=6", new String[]{"QQ"}))
        	Log.e("where","id="+t.id()+" content="+t.content+" stauts="+t.status);
        for(Todo t:TodoTable.where("content = \"QQ\" and _id <=6"))
        	Log.e("where","id="+t.id()+" content="+t.content+" stauts="+t.status);
        
        Todo t = TodoTable.find(2);
        Log.e("Before","id="+t.id()+" content="+t.content+" stauts="+t.status);
        t.content += " change!";
        TodoTable.update(t);
        t = TodoTable.find(2);
        Log.e("After","id="+t.id()+" content="+t.content+" stauts="+t.status);
    }
    
    @Override
    protected void onStop() {
    	SocketHelper.close();
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
