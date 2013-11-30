package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.command.CommandManager;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;

public class CommunicateHelper {
	static CommandManager cmdMgr = new CommandManager();
	static boolean logined = false;
	static Thread msgThread = null;
	
	static public boolean addFriend(String account){
		return sendMessage(new String[]{"friend","add",account});
	}

	public static boolean addTodo(Todo a) {
		if(!sendMessage(new String[]{"todo","new"}))return false;
		ArrayList<String> V = new ArrayList<String>();
		V.add(a.getId()+"");
		V.add(a.getTitle()+"");
		V.add(a.getCategoryId()+"");
		V.add(a.getStatus()+"");
		V.add(a.getDeadlineLong()+"");
		V.add(a.getUserId()+"");
		V.add(a.getDescription()+"");
		V.add(a.getBelongId()+"");
		V.add(a.getRealId()+"");
		for(String s:V){
			Util.errorReport(s);
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}

	public static boolean addCategory(Category a) {
		if(!sendMessage(new String[]{"category","new"}))return false;
		ArrayList<String> V = new ArrayList<String>();
		V.add(a.getId()+"");
		V.add(a.getTitle()+"");
		V.add(a.getIcon()+"");
		V.add(a.getCount()+"");
		V.add(a.getRealId()+"");
		for(String s:V){
			Util.errorReport(s);
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}
	/******************************Do not edit here***************************/
	static boolean start(){
    	if(!SocketHelper.start())return false;
    	msgThread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			if(!trylogin())return;
    			/*Data.addCategory("Today");
    			Category c = new Category("meow",0);
    			c.addTodo("ohoh");*/
    			Data.addFriend("david942j@gmail.com");
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
