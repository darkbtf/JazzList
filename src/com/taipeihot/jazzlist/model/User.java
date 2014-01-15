package com.taipeihot.jazzlist.model;


public class User {
	private String nickname;
	private int real_id=0; // id in server
	private String facebook_id="";
	
	public User(String nickname,int real_id,String facebook_id){
		this.nickname = nickname;
		this.real_id = real_id;
		this.facebook_id = facebook_id;
	}
	
	public String getNickname(){return nickname;}
	public void setNickname(String nickname){this.nickname=nickname;}
	
	public int photoNumber(){return real_id;}
	public int getRealId() { return real_id; }
	public String getFacebookId(){return facebook_id;}
}
