package com.taipeihot.jazzlist.model;

import java.util.ArrayList;
import java.sql.Date;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.Util;

public class Status implements Comparable{
	//private long _id=0;
	private String nickname;
	private String title;
	private int user_id;
	private long deadline;
	private int score = 0; //number of like 
	private boolean visible = true;
	private int real_id;
	private String category;
	private long updated_at;
	private ArrayList<Comment> comments = new ArrayList<Comment>();
		
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
	
	public String getDeadline(){
		if(deadline==0)return "";
		return Util.dateLongToString(deadline);
	}
	public long getDeadlineLong(){return deadline;}
	
	public String getNickname(){return nickname;}
	
	public String getCategory(){return category;}
	public int getScore(){return score;}
	public void incScore(){
		score++;
		Data.incAchievementParameter(AchievementType.like_number);
		update();
	}
	public void decScore(){score--;}
	
	public boolean getVisible(){return visible;}
	public void setVisible(boolean visible){this.visible = visible;}
	public ArrayList<Comment> getComments(){
		return comments;
	}
	
	public void addComment(Comment c){
		c.setStatusId(real_id);
		//comments.add(c);  // no need this
		CommunicateHelper.addComment(c);
		Data.updateStatus();
	}
	
	public void addComment(String c){
		//comments.add(c);  // no need this
		Data.incAchievementParameter(AchievementType.comment_number);
		CommunicateHelper.addComment(real_id,c);
		Data.updateStatus();
	}
	
	public int photoNumber(){return user_id;}
	
	/***************************For Database****************************/
	/*public Status(String nickname, String title, int user_id, long deadline, int score, int real_id, String category,long updated_at){
		this.nickname = nickname;
		this.title = title;
		this.user_id = user_id;
		this.deadline = deadline;
		this.score = score;
		this.real_id = real_id;
		this.category = category;
		this.updated_at = updated_at;
	}*/
	public Status(String nickname, String title, int user_id, long deadline, int score, boolean visible, int real_id,String category,long updated_at){
		this.nickname = nickname;
		this.title = title;
		this.user_id = user_id;
		this.deadline = deadline;
		this.score = score;
		this.visible = visible;
		this.real_id = real_id;
		this.category = category;
		this.updated_at = updated_at;
	}
	public int getRealId(){return real_id;}

	public void updateComment(Comment comment) {
		comments.add(comment);
	}
	public long getUpdatedAt(){return updated_at;}
	public void clone(Status newStatus) { // clone data except comments;
		this.title = newStatus.getTitle();
		this.deadline = newStatus.getDeadlineLong();
		this.score = newStatus.getScore();
		this.visible = newStatus.getVisible();
		this.category = newStatus.getCategory();
		this.updated_at = newStatus.getUpdatedAt();
	}
	
	private void update(){
		CommunicateHelper.updateStatusByInstance(this);
	}
	
	@Override
	public int compareTo(Object o){
		long a =this.updated_at, b =  ((Status)o).updated_at;
		if(a < b)return 1;
		if(a==b)return 0;
		return -1; 
	}
}
