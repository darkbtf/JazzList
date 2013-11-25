package com.taipeihot.jazzlist;

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
        initialComponent();
        //new Table(this);
        
        //databaseSample();
        socket_connect();
        //Table.close();
    }
    private Button btn_connect,btn_send;
    private EditText ed;
    private  TextView tv1,tv2;
    public static Handler receiveServer = null;
    private void initialComponent(){
    	btn_connect = (Button) findViewById(R.id.btn_connect);
    	btn_send = (Button) findViewById(R.id.btn_send);
        tv1 = (TextView) findViewById(R.id.servermsg);
        tv2 = (TextView) findViewById(R.id.clientmsg);
        ed = (EditText) findViewById(R.id.et_input);
    }
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
        
    }

    /*private void setAction()  {
    	btn_send.setEnabled(false);
    	btn_connect.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    			SocketHelper.start();
    			btn_send.setEnabled(true);
    			arg0.setEnabled(false);
    		}
    	});  
    	btn_send.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			String str = ed.getText().toString();
    			tv2.setText(str);
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
    }*/
    
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
