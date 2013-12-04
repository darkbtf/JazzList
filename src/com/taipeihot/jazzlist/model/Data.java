package com.taipeihot.jazzlist.model;

import java.util.ArrayList;
import java.util.Collections;

import android.content.SharedPreferences;

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
	private static ArrayList<Achievement> achievements = new ArrayList<Achievement>();
	public static long lastUpdateStatusTime=0;
	public static SharedPreferences achiv_sp;
	
	private static void init(){
		achievements = new ArrayList<Achievement>();
	}
	
	/**************************** User Login and Register****************************/
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
	public static boolean waittingLogin(){return logined==0;}
	public static boolean hasLogined(){return logined==1;}
	
	public static void loginWait(){logined = 0;}
	public static void loginSuccess(){init();logined = 1;}
	public static void loginFail(){logined = 2;}
	/**************************** About Friends*************************************/
	public static boolean addFriend(String account){
		return CommunicateHelper.addFriend(account);
	}
	
	public static ArrayList<User> getFriends() {
		friends = new ArrayList<User>();
		setFriendUpdating(true);
		CommunicateHelper.getFriends();
		while(getFriendUpdating());
		setAchievementParameter(AchievementType.friend_number,friends.size());
		return friends;
    }

	public static boolean getFriendUpdating() {return friendUpdating;}
	public static void setFriendUpdating(boolean b) {friendUpdating = b;}
	/**************************** About Category************************************/
	public static boolean addCategory(String title){
		Category c = new Category(title,R.drawable.ic_home);
		CategoryTable.insert(c);
		categories.add(c);
		return CommunicateHelper.addCategory(c);
	}
	
	public static void deleteCategory(int category_id){
		for(int i=0;i<categories.size();i++)
			if(categories.get(i).getRealId()==category_id){
				Category c = categories.get(i); 
				categories.remove(i);
				c.die();
				c.save();
				//TODO communicate server
				break;
			}
	}
	
	public static ArrayList<Category> getCategories() {
		if(categories.size() < 1){
			for(Category c:CategoryTable.All())
				if(c.getCount() >=0 )
					categories.add(c);
		}
		return categories;
    }
	
	/**************************** About Status*************************************/
	static private ArrayList<Status> realStatus = new ArrayList<Status>();
	public static void addStatus(Status s) {
		Util.errorReport(s.getTitle()+" meow "+s.getVisible());
		if(s.getVisible())status.add(s);
		realStatus.add(s);
	}
	public static ArrayList<Status> getStatus(){return status;}
	public static void updateStatus(){// MOST call with network
		setStatusUpdating();
		CommunicateHelper.updateStatus();
		Util.errorReport("updating status");
		while(getStatusUpdating());
	}
	public static void doneStatusUpdate(long lastUpdateStatusTime) {
		Collections.sort(status);//Magic sort.
		statusUpdating = false;
		Data.lastUpdateStatusTime = lastUpdateStatusTime;
		//TODO need write lastUpdateStatusTime to sp
	}
	
	public static void setStatusUpdating() {statusUpdating=true;}
	public static boolean getStatusUpdating(){return statusUpdating;}
	
	public static void updateComment(Comment comment) {
		for(Status s:status)
			if(s.getRealId() == comment.getStatusId()){
				s.updateComment(comment);
				break;
			}
	}
	
	public static void updateStatusByRealId(int real_id, Status newStatus) {
		for(Status s:realStatus)
			if(s.getRealId() == real_id){
				boolean a = s.getVisible();
				boolean b = newStatus.getVisible();
				s.clone(newStatus);
				if(a != b){
					if(newStatus.getVisible())
						status.add(s);
					else status.remove(s);
				}
			}
	}
	
	/****************************For Achievement *********************************/
	public static void addAchivements(Achievement a){
		achievements.add(a);
	}
	public static ArrayList<Achievement> getAchievements(){
		return achievements;
	}
	
	public static int getAchievementParameter(AchievementType s) {
		return achiv_sp.getInt(s.toString(), -1);
	}
	public static void setAchievementParameter(AchievementType s, int v) {
		achiv_sp.edit().putInt(s.toString(), v).commit();
	}
	public static void incAchievementParameter(AchievementType s) {
		setAchievementParameter(s,getAchievementParameter(s)+1);
	}
	public static void showAchievements(){
		for(Achievement a:getAchievements())
			Util.errorReport("Achievement:"+a.getType()+", need="+a.getNeed());
		for(AchievementType a:AchievementType.values())
			Util.errorReport("Parameter "+a.toString()+"="+getAchievementParameter(a));
	}
}
