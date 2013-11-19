package com.taipeihot.jazzlist;

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
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new Table(this);
        
        //databaseSample();
        socket_connect();
        //Table.close();
    }
    private Button btn_connect,btn_send;
    private EditText ed;
    private TextView tv1,tv2;
    static Socket socket=null;
    private final String SERVER_IP = "140.112.18.198";
    private final int SERVERPORT = 8765;
    private PrintWriter out;
    public BufferedReader in;
    static private String str1="nothing",str2="nothing";
    public static Handler receiveServer = null;
 
    //Thread thread;
    private void socket_connect(){
    	btn_connect = (Button) findViewById(R.id.btn_connect);
    	btn_send = (Button) findViewById(R.id.btn_send);
        tv1 = (TextView) findViewById(R.id.servermsg);
        tv2 = (TextView) findViewById(R.id.clientmsg);
        ed = (EditText) findViewById(R.id.et_input);
        setAction();
    }
    
    private void setAction()  {
		Log.i("OAQ","fuyyyyyyyyyyyyyyyyyyyyck");
    	btn_connect.setOnClickListener(new OnClickListener() {
    		public void onClick(View arg0) {
    				Thread t = new thread();
    				t.start();
    	            /*try {
    	            	//先讓他等待個 3 秒
    	            	t.sleep(3000);
    	            } catch (InterruptedException e) {}*/
    				/*Log.i("OAQ","fuck");
    				Socket socket=new Socket(SERVER_IP,SERVERPORT);
    				socket.sleep(5000);
    				//socket = new Socket(SERVER_IP, SERVERPORT);
    				if(socket.isConnected())
    					Log.i("OAQ","connect");//out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8")),true);
    				else Log.i("OAQ","no connect");*/
    			/*catch (UnknownHostException e) {
    				Log.i("OAQ","ddddddddddddddddddd");
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			} catch (IOException e) {
    				Log.i("OAQ","qqqqqqqqqqqqqqqqqqq");
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}*/
    		}
    	});  
    	btn_send.setOnClickListener(new OnClickListener(){
    		public void onClick(View v){
    			String str = ed.getText().toString();
    			out.println(str);
    			out.flush();
    		}
    	});
    }
    class thread extends Thread{
        public void run() {
         try{
         System.out.println("Waitting to connect......");
         String server="140.112.18.198";
         int servPort=8765;
         Socket socket=new Socket(server,servPort);
         InputStream in=socket.getInputStream();
         OutputStream out=socket.getOutputStream();
         System.out.println("Connected!!");
         byte[] rebyte = new byte[18];
         in.read(rebyte);
         str2 ="(Client端)接收的文字:"+ new String(new String(rebyte));
         String str = "android client string";
         str1 = "(Client端)傳送的文字:"+str;
         byte[] sendstr = new byte[21];
         System.arraycopy(str.getBytes(), 0, sendstr, 0, str.length());
         out.write(sendstr);
         }catch(Exception e)
         {
          System.out.println("Error: "+e.getMessage());
         }
         }
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
    
    /*@Override
    protected void onStop() {
        thread.stop();
        out.close();
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        super.onStop();
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
