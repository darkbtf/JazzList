package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.command.CommandManager;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Comment;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;
import com.taipeihot.jazzlist.model.Todo;

public class CommunicateHelper {
	static CommandManager cmdMgr = new CommandManager();
	//public static boolean logined = false;
	static Thread msgThread = null;
	
	static public boolean addFriend(String account){
		return sendMessage(new String[]{"friend","add",account});
	}
	public static void register(String account, String password) {
		SocketHelper.sendMessage(new String[]{"register",account,Util.MD5(password)});
	}

	public static void login(String account, String password) {
		SocketHelper.sendMessage(new String[]{"login",account,Util.MD5(password)});
	}

	public static void updateStatus() {
		SocketHelper.sendMessage(new String[]{"status","get",Data.lastUpdateStatusTime+""});
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
		V.add(a.getRandId()+"");
		for(String s:V){
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}

	public static boolean updateTodo(Todo a) {
		if(!sendMessage(new String[]{"todo","update"}))return false;
		ArrayList<String> V = new ArrayList<String>();
		V.add(a.getId()+"");
		V.add(a.getTitle()+"");
		V.add(a.getCategoryId()+"");
		V.add(a.getStatus()+"");
		V.add(a.getDeadlineLong()+"");
		V.add(a.getUserId()+"");
		V.add(a.getDescription()+"");
		V.add(a.getBelongId()+"");
		V.add(a.getRandId()+"");
		for(String s:V){
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
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}
	
	public static boolean addComment(Comment a) {
		if(!sendMessage(new String[]{"comment","new"}));
		ArrayList<String> V = new ArrayList<String>();
		V.add(a.getStatusId()+"");
		V.add(a.getContent());
		V.add(a.getUserId()+"");
		for(String s:V){
			Util.errorReport(s);
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}
	public static boolean addComment(int status_id,String content) {
		if(!sendMessage(new String[]{"comment","new"}));
		ArrayList<String> V = new ArrayList<String>();
		V.add(status_id+"");
		V.add(content);
		for(String s:V){
			if(!sendMessage(new String[]{s}))
				return false;
		}
		return true;
	}

	public static void getFriends() {
		SocketHelper.sendMessage(new String[]{"friend","get"});
	}

	public static void getStatus() {
		SocketHelper.sendMessage(new String[]{"status","get"});
	}

	public static void updateStatusByInstance(Status s) {
		SocketHelper.sendMessage(new String[]{"status","update",s.getRealId()+"",s.getScore()+""});
	}
	public static void updateCommentByInstance(Comment c) {
		SocketHelper.sendMessage(new String[]{"comment","update",c.getRealId()+"",c.getScore()+""});
	}
	
	/******************************Do not edit here***************************/
	public static boolean start(){
    	if(!SocketHelper.start())return false;
    	msgThread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			//if(!trylogin())return;
    			/*Data.addCategory("Today");
    			Category c = new Category("meow",0);
    			c.addTodo("ohoh");
    			Data.addFriend("david942j@gmail.com");*/
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
		Data.loginWait();
		SocketHelper.close();
	}
	static private boolean trylogin(){
		if(Data.hasLogined())return true;
		if(!SocketHelper.connecting)return false;
		if(Data.account==null)return false;
		if(!sendMessage(new String[]{"login",Data.account,Data.encryptedPassword}))return false;
		String cmd = getMessage();
		if(cmd.equals("login")){
			if(cmdMgr.parseCmd("login")){
				Data.loginSuccess();
				return true;
			}
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
