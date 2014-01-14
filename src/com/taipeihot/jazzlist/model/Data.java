package com.taipeihot.jazzlist.model;

import java.util.ArrayList;
import java.util.Collections;

import android.content.SharedPreferences;

import com.facebook.model.GraphUser;
import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.table.ActionTable;
import com.taipeihot.jazzlist.table.CategoryTable;
import com.taipeihot.jazzlist.fight.FightUtils;

public class Data {
	
	public static GraphUser user;
	public static ArrayList<User> friends = new ArrayList<User>();
	public static ArrayList<Status> status = new ArrayList<Status>();
	public static ArrayList<Category> categories = new ArrayList<Category>();
	private static boolean statusUpdating = false;
	private static boolean friendUpdating = false;
	private static int logined = 0;
	private static ArrayList<Achievement> achievements = new ArrayList<Achievement>();
	public static long lastUpdateStatusTime=0;
	public static SharedPreferences achiv_sp;
	public static SharedPreferences character_info_sp;
	
	private static void init(){
		achievements = new ArrayList<Achievement>();
	}
	
	/**************************** User Login and Register****************************/
	public static boolean login(String facebookId, String nickname){
		CommunicateHelper.login(facebookId,nickname);
		while(waittingLogin());
		return hasLogined();
	}
	public static boolean register(String account,String password){
		CommunicateHelper.register(account,password);
		while(waittingLogin());
		return hasLogined();
		//setAccount(account,account,Util.MD5(password));
	}
	public static void setUser(GraphUser user){
		Data.user = user;
	}
	public static boolean waittingLogin(){return logined==0;}
	public static boolean hasLogined(){return logined==1;}
	
	public static void loginWait(){logined = 0;}
	public static void loginSuccess(){init();logined = 1;}
	public static void loginFail(){logined = 2;}
	/************************** About Character information ************************/
	public static void initCharacterInfo(){
		Data.setCharacterInfo(CharacterInfo.character_id, 1);
		Data.setCharacterInfo(CharacterInfo.level, 1);
		Data.setCharacterInfo(CharacterInfo.exp, 0);
		Data.setCharacterInfo(CharacterInfo.money, 10000);
		Data.setCharacterInfo(CharacterInfo.attack, 100);
		Data.setCharacterInfo(CharacterInfo.defense, 100);
		Data.setCharacterInfo(CharacterInfo.skill_point, 10);
	}
	public static int getCharacterId(){return getCharacterInfo(CharacterInfo.character_id);}
	public static int getLevel(){return getCharacterInfo(CharacterInfo.level);}
	public static int getExp(){return getCharacterInfo(CharacterInfo.exp);}
	public static int getMoney(){return getCharacterInfo(CharacterInfo.money);}
	public static int getAttack(){return getCharacterInfo(CharacterInfo.attack);}
	public static int getDefense(){return getCharacterInfo(CharacterInfo.defense);}
	public static int getSkillPoint(){return getCharacterInfo(CharacterInfo.skill_point);}
	public static int getHp(){return FightUtils.calcHp(getLevel());}
	public static int getMp(){return FightUtils.calcMp(getLevel());}
	
	public static void incMoney(int v){
		setCharacterInfo(CharacterInfo.money, getCharacterInfo(CharacterInfo.money)+v);
	}
	public static void decSkillPoint(){
		setCharacterInfo(CharacterInfo.skill_point, getCharacterInfo(CharacterInfo.skill_point)-1);
	}
	private static int getCharacterInfo(CharacterInfo s) {
		return character_info_sp.getInt(s.toString(), -1);
	}
	private static void setCharacterInfo(CharacterInfo s, int v) {
		character_info_sp.edit().putInt(s.toString(), v).commit();
	}
	public static void incCharacterInfo(CharacterInfo s) {
		setCharacterInfo(s,getCharacterInfo(s)+1);
	}
	public static void incExp() {
		if(getCharacterInfo(CharacterInfo.level)==5)return;
		int now = getCharacterInfo(CharacterInfo.exp);
		if (now >= 49){
			now=0;
			incCharacterInfo(CharacterInfo.level);
			incCharacterInfo(CharacterInfo.skill_point);
		}
		else now++;
		setCharacterInfo(CharacterInfo.exp,now);
	}
	public static void resetSkillPoints() {
		setCharacterInfo(CharacterInfo.skill_point, getLevel());
		for(Action a:ActionTable.where("object_id>0")) {
			a.reset();
			a.save();
		}
	}
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
	
	public static void deleteCategory(long category_id){
		for(int i=0;i<categories.size();i++)
			if(categories.get(i).getId()==category_id){
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
	public static ArrayList<Status> getStatusById(int id){
		ArrayList<Status> ret = new ArrayList<Status>();
		for(Status s:realStatus)
			if(s.getVisible() && s.photoNumber() == id)
				ret.add(s);
		Collections.sort(ret);
		return ret;
	}
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
				break;
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
	/****************************For Skills and Items*******************************/
	public static ArrayList<Action> getAvailableSkills(){
		return ActionTable.where("object_id>0 and number>0");
	}
	public static ArrayList<Action> getFireSkills(){
		Util.errorReport("int Data.java geting fire skills");
		return ActionTable.where("object_id > 0 and object_id%3 ==1");
	}
	public static ArrayList<Action> getWaterSkills(){
		Util.errorReport("int Data.java geting water skills");
		return ActionTable.where("object_id > 0 and object_id%3 ==2");
	}
	public static ArrayList<Action> getThunderSkills(){
		Util.errorReport("int Data.java geting thunder skills");
		return ActionTable.where("object_id > 0 and object_id%3 ==0");
	}
	public static ArrayList<Action> getItems(){
		return ActionTable.where("object_id < 0");
	}
}
