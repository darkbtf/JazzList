package com.taipeihot.jazzlist.model;

import com.taipeihot.jazzlist.Util;

public class Comment {
	//private long _id=0;
	private String nickname;
	private String content;
	private int user_id;
	private int status_id;
	private int real_id;
	private int score = 0; // number of like
	private long time = 0;
	
	public Comment(String nickname,String content,int score){
		this.nickname = nickname;
		this.content = content;
		this.score = score;
	}
	public Comment(String nickname, String content){
		this.nickname = nickname;
		this.content = content;
		this.score = 0;
	}
	public String getNickname(){return nickname;}
	
	public String getContent(){return content;}
	
	public int getUserId() {return user_id;}
	
	public int photoNumber(){return user_id;}
	
	public int getStatusId(){return status_id;}
	public void setStatusId(int status_id){this.status_id = status_id;}
	
	public int getScore(){return score;}
	
	public void incScore(){score++;}
	public void decScore(){score--;}
	
	public String getTimeString(){return Util.dateLongToString(time); }
	
	/***********************For database*******************************/
	public Comment(String nickname, String content, int user_id, int status_id, int real_id, int score,long time){
		this.nickname = nickname;
		this.content = content;
		this.user_id = user_id;
		this.status_id = status_id;
		this.real_id = real_id;
		this.score = score;
		this.time = time;
	}
	
	public int getRealId(){return real_id;}

}
