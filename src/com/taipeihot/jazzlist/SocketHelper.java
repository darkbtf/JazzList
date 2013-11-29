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
	static public boolean connecting = true;
	static private Thread getMessageToBuffer;

    static private BufferedInputStream in = null;
    static private BufferedOutputStream out = null;
    static public boolean start(){
    	connecting=true;
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
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			        }
				}catch(IOException e){
					close();
					System.out.println("getMessage Error");
				}
			}
		});
		if(connecting){
			try {
				if(socket != null && socket.isConnected())return true;
				CommunicateHelper.logined=false;
				socket=new Socket(Parameter.SERVER_IP,Parameter.port);
				in = new BufferedInputStream(socket.getInputStream());
				out = new BufferedOutputStream(socket.getOutputStream());
				getMessageToBuffer.start();
			}catch (Exception e) {
				close();
				e.printStackTrace();
			}
			return true;
		}
		return false;
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
			close();
			return Util.errorReport("sendMessage fail");
		}
		return true;
	}
    
    static public String getMessage(){
		while(messages.isEmpty()){
			while(!Util.parseByte(bufferInput, messages)){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return messages.poll();
	}
    static void close(){
    	try {
	    	if(in != null)in.close();
	    	if(out != null)out.close();
	    	if(socket!=null && socket.isConnected())socket.close();
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	finally{
    		connecting=false;
    		in=null;out=null;socket=null;
    	}
    }
}
