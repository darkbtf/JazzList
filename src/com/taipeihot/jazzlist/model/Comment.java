package com.taipeihot.jazzlist.model;

public class Comment {
	//private long _id=0;
	private String nickname;
	private String content;
	private int score = 0; // number of like
	
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
	
	public int getScore(){return score;}
	
	public void incScore(){score++;}
	public void decScore(){score--;}
}
