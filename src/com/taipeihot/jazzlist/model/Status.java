package com.taipeihot.jazzlist.model;

import java.util.ArrayList;

public class Status {
	//private long _id=0;
	private String nickname;
	private Todo todo;
	private int score = 0; //number of like 
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public Status(String nickname, Todo todo, ArrayList<Comment>comments){
		this.nickname = nickname;
		this.todo = todo;
		this.comments = comments;
	}
	
	public Status(String nickname, Todo todo){
		this.nickname = nickname;
		this.todo = todo;
		this.comments = new ArrayList<Comment>();
	}
	
	public String getTitle(){return todo.getTitle();}
	
	public String getDeadline(){return todo.getDeadlineString();}
	
	public String getNickname(){return nickname;}
	
	public int getScore(){return score;}
	public void incScore(){score++;}
	public void decScore(){score--;}
	
	public ArrayList<Comment> getComments(){
		return comments;
	}
	
	public void addComments(Comment c){
		comments.add(c);
	}
	
	/***************************** For Database ********************************/
	public Status(){}
	
}
