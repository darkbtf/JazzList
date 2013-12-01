package com.taipeihot.jazzlist.model;

import java.util.ArrayList;
import java.sql.Date;

import com.taipeihot.jazzlist.Util;

public class Status {
	//private long _id=0;
	private String nickname;
	private String title;
	private long deadline;
	private int score = 0; //number of like 
	private boolean visible = true;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	public Status(String nickname, Todo todo, ArrayList<Comment>comments){//TODO remove
		this.nickname = nickname;
		this.title = todo.getTitle();
		this.deadline = todo.getDeadlineLong();
		this.comments = comments;
	}
	
	public Status(String nickname, Todo todo){//TODO remove
		this.nickname = nickname;
		this.title = todo.getTitle();
		this.deadline = todo.getDeadlineLong();
		this.comments = new ArrayList<Comment>();
	}
	
	public Status(String nickname,String title,long deadline){
		this.nickname = nickname;
		this.title = title;
		this.deadline=deadline;
	}
	
	public Status(String nickname,String title,Date deadline){
		this.nickname = nickname;
		this.title = title;
		this.deadline=deadline.getTime();
	}
	
	public String getTitle(){return title;}
	
	public String getDeadline(){return Util.dateLongToString(deadline);}
	
	public String getNickname(){return nickname;}
	
	public int getScore(){return score;}
	public void incScore(){score++;}
	public void decScore(){score--;}
	
	public boolean getVisible(){return visible;}
	public void setVisible(boolean visible){this.visible = visible;}
	public ArrayList<Comment> getComments(){
		return comments;
	}
	
	public void addComments(Comment c){
		comments.add(c);
	}
}
