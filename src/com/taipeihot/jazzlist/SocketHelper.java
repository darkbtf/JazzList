package com.taipeihot.jazzlist;

import android.annotation.TargetApi;
import android.os.Build;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class SocketHelper {
	static Socket socket=null;
	static Thread try_connect = null;
	static private Deque<Byte> bufferInput = new LinkedList<Byte>();
	static private Queue<String> messages = new LinkedList<String>();
	static private boolean hasNet = true;
	static private final String SERVER_IP = "140.112.18.198";
	static private final int SERVERPORT = 8766;
	static private Thread getMessageToBuffer;

    static private BufferedInputStream in = null;
    static private BufferedOutputStream out = null;
    static public void start(){

    	getMessageToBuffer=new Thread(new Runnable(){
			@TargetApi(Build.VERSION_CODES.GINGERBREAD)
			@Override
			public void run() {
		        byte[] b = new byte[1024];
				Integer length;
				try{
					while ((length = in.read(b)) > 0){
						for(int i=0;i<length;i++)
							bufferInput.add(new Byte(b[i]));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			        }
				}catch(IOException e){
					System.out.println("getMessage Error");
				}
			}
		});
    	
    	try_connect = new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					Util.errorReport("Waitting to connect......");
					if(hasNet){
						socket=new Socket(SERVER_IP,SERVERPORT);
						in = new BufferedInputStream(socket.getInputStream());
						out = new BufferedOutputStream(socket.getOutputStream());
						getMessageToBuffer.start();
					}
					else{
						Util.errorReport("No Internet");
					}
				}catch(Exception e){
					System.out.println("Error: "+e.getMessage());
				}
			}
		});
    	try_connect.start();
    }
    static public Boolean sendMessage(String[] messages){
		for(String str: messages)
			if(!sendMessage(str.getBytes()))
				return false;
		return true;
	}
    static public Boolean sendMessage(byte[] byteStream){
		byte[] length = Util.intToByteArray(byteStream.length);
		try {
			out.write(length);
			out.write(byteStream);
			out.flush();
		} catch (IOException e) {
			return Util.errorReport("sendMessage fail");
		}
		return true;
	}
    
    static public String getMessage(){
    	Util.errorReport("getting message");
		while(messages.isEmpty()){
			Util.errorReport("isempty");
			while(!Util.parseByte(bufferInput, messages)){
				Util.errorReport("trying parsing");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}Util.errorReport("get msg");
		return messages.poll();
	}
    static void close(){
    	try {
	    	if(in != null){
	    		in.close();
	    	}
	    	if(out != null){
	    		out.close();
	    	}
	    	if(socket!=null && socket.isConnected()){
	    		socket.close();
	    	}
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
