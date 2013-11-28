package com.taipeihot.jazzlist;

import com.taipeihot.jazzlist.command.CommandManager;
import com.taipeihot.jazzlist.model.Data;

public class CommunicateHelper {
	static CommandManager cmdMgr = new CommandManager();
	static boolean logined = false;
	static Thread msgThread = null;
	
	static public boolean addFriend(String account){
		return sendMessage(new String[]{"friend","add",account});
	}
	
	/******************************Do not edit here***************************/
	static boolean start(){
    	if(!SocketHelper.start())return false;
    	msgThread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			if(!trylogin())return;
    			sendMessage(new String[]{"hello meow"});
    			while(SocketHelper.connecting){
	    			String cmd=SocketHelper.getMessage();
	    			cmdMgr.parseCmd(cmd);
    			}
    		}
    	});
    	msgThread.start();
    	return true;
	}
	static public void close(){
		//if(msgThread != null && msgThread.isAlive())msgThread.
		logined=false;
		SocketHelper.close();
	}
	static private boolean trylogin(){
		if(logined)return true;
		if(!SocketHelper.connecting)return false;
		if(Data.account==null)return false;
		if(!sendMessage(new String[]{"login",Data.account,Data.encryptedPassword}))return false;
		String cmd = getMessage();
		if(cmd.equals("login")){
			if(cmdMgr.parseCmd("login"))return logined=true;
			else Util.errorReport("why login fail QQ?");
		}
		return false;
	}
	static private boolean sendMessage(String[] messages){
    	return SocketHelper.sendMessage(messages);
	}
	static private String getMessage(){
		return SocketHelper.getMessage();
	}
}
