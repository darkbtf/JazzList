package com.taipeihot.jazzlist.model;


public class User {
	private String nickname;
	private int user_id=0; // id in server
	
	public User(String nickname,int user_id){
		this.nickname = nickname;
		this.user_id = user_id;
	}
	
	public String getNickname(){return nickname;}
	public void setNickname(String nickname){this.nickname=nickname;}
	
	public int photoNumber(){return user_id;}
}
