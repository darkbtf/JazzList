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

	public static void login(String facebookId, String nickname) {
		SocketHelper.sendMessage(new String[]{"login",facebookId,nickname});
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
	
	public static void inviteFight(int userId) {
		SocketHelper.sendMessage(new String[]{"fight", "invite", userId + ""});
	}
	
	public static void replyFight(int userId, boolean result) {
		Util.errorReport("QQPIE");
		SocketHelper.sendMessage(new String[]{"fight", (result ? "accept" : "reject"), userId + ""});
	}
	
	public static void startFight(int level, int HP, int MP, int attack, int defense) {
		SocketHelper.sendMessage(new String[]{"fight", "start", level + "", HP + "", MP + "", attack + "", defense + ""});
	}
	
	public static void actionFight(int objectId) {
		SocketHelper.sendMessage(new String[]{"fight", "action", objectId + ""});
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
	static private boolean sendMessage(String[] messages){
    	return SocketHelper.sendMessage(messages);
	}
	static private String getMessage(){
		return SocketHelper.getMessage();
	}
}
