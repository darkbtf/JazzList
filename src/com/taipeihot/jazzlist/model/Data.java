package com.taipeihot.jazzlist.model;

import java.util.ArrayList;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.table.CategoryTable;

public class Data {
	public static String account = null;
	public static String nickname = null;
	public static String encryptedPassword = null;
	public static ArrayList<User> friends = new ArrayList<User>();
	public static ArrayList<Status> status = new ArrayList<Status>();
	public static ArrayList<Category> categories = new ArrayList<Category>();
	private static boolean statusUpdating = false;
	private static boolean friendUpdating = false;
	private static int logined = 0;
	
	public static boolean login(String account, String password){
		CommunicateHelper.login(account,password);
		while(waittingLogin());
		return hasLogined();
	}
	public static boolean register(String account,String password){
		CommunicateHelper.register(account,password);
		while(waittingLogin());
		return hasLogined();
		//setAccount(account,account,Util.MD5(password));
	}
	public static void setAccount(String a, String n, String password) {
		account = a;
		nickname = n;
		encryptedPassword = password;
	}
	public static boolean addFriend(String account){
		return CommunicateHelper.addFriend(account);
	}
	public static boolean addCategory(String title){
		Category c = new Category(title,R.drawable.ic_home);
		CategoryTable.insert(c);
		categories.add(c);
		return CommunicateHelper.addCategory(c);
	}
	public static ArrayList<Category> getCategories() {
		return categories=CategoryTable.All();
        //return categories;
    }
	public static ArrayList<User> getFriends() {
		setFriendUpdating(true);
		CommunicateHelper.getFriends();
		Util.errorReport("wait friends updating");
		while(getFriendUpdating());
		Util.errorReport("get friends!");
		return friends;
        //return categories;
    }
	public static void addStatus(Status s) {
		status.add(s);
		//TODO add to the front? Need a magic sort.
	}
	public static ArrayList<Status> getStatus(){
		return status;
	}
	public static void setStatusUpdating(boolean b) {
		if(b){
			statusUpdating=true;
		}
		else{
			statusUpdating=false;
			// TODO sort ArrayList 
		}
	}
	public static boolean getStatusUpdating(){return statusUpdating;}
	
	public static void setFriendUpdating(boolean b) {
		friendUpdating = b;
	}
	public static boolean getFriendUpdating() {
		return friendUpdating;
	}
	
	public static boolean hasLogined(){
		return logined==1;
	}
	
	public static boolean waittingLogin(){
		return logined==0;
	}
	public static void loginWait(){logined = 0;}
	public static void loginSuccess(){logined = 1;}
	public static void loginFail(){logined = 2;}
}
