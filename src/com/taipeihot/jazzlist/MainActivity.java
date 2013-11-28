package com.taipeihot.jazzlist;

import java.sql.Date;

import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.Table;
import com.taipeihot.jazzlist.table.TodoTable;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Table(this);
        //databaseSample();
        //socket_connect();
        Table.close();
    }
    /*
    private boolean isNetworkAvailable(){
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
    
    static private int updatePeriod = 20000; 
    private void socket_connect(){
    	Thread thread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			while(true){
    				if(isNetworkAvailable())
    					UpdateHelper.start();
    				try {
						Thread.sleep(updatePeriod);// what if the last update not finished yet?
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	});
    	thread.start();
        
    }*/
    
    private void databaseSample(){
    	Todo todo1 = new Todo("haha",1,true,new Date(System.currentTimeMillis()),"not done yet");
        Todo todo2 = new Todo("QQ",1,false,new Date(System.currentTimeMillis()),"meow");
        TodoTable.insert(todo1);
        TodoTable.insert(todo2);
        for(Todo t:TodoTable.All())
        	Log.e("All","id="+t.getId()+" title="+t.getTitle()+" deadline="+t.getDeadline());
        
        /*for(Todo t:TodoTable.where("content = ? and _id <=6", new String[]{"QQ"}))
        	Log.e("where","id="+t.id()+" content="+t.content+" stauts="+t.status);
        for(Todo t:TodoTable.where("content = \"QQ\" and _id <=6"))
        	Log.e("where","id="+t.id()+" content="+t.content+" stauts="+t.status);
        
        Todo t = TodoTable.find(2);
        Log.e("Before","id="+t.id()+" content="+t.content+" stauts="+t.status);
        t.content += " change!";
        TodoTable.update(t);
        t = TodoTable.find(2);
        Log.e("After","id="+t.id()+" content="+t.content+" stauts="+t.status);*/
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
