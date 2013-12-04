package com.taipeihot.jazzlist.model;

import java.sql.Date;
import java.util.ArrayList;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.table.TodoTable;

public class Todo implements Comparable{
	private long _id=0;
	private String title;
	private long category_id;
	private boolean isPublic = false; // settings' default?
	private boolean alive=true;
	private long deadline; 
	private int user_id = 0;
	private String description;
	private long belong_id = 0;
	private int rand_id;
	
	public Todo(String title, long category_id, boolean isPublic, long deadline, String description){
		this.title = title;
		this.category_id = category_id;
		this.isPublic = isPublic;
		this.alive = true;
		this.deadline = deadline;
		// this.user_id = TODO: what's my ID QQ?
		this.description = description;
		this.rand_id = Util.rng58();
		// this.belong_id = TODO: what's my ID QQ?
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id=_id;}
	
	public String getTitle(){return title;}
	public void setTitle(String title){this.title=title;}
	
	public long getCategoryId(){return category_id;}
	public void setCategoryId(long category_id){this.category_id=category_id;}
	
	public boolean isPublic(){return isPublic;}
	public void setPublic(boolean isPublic){this.isPublic = isPublic;}
	
	public boolean isAlive(){return alive;}
	public void setAlive(boolean alive){this.alive=alive;}

	public String getDeadlineString(){
		if(deadline == 0)return "";
		return Util.dateLongToString(deadline);
	}
	public Date getDeadline(){return new Date(deadline);}
	public void setDeadline(Date deadline){this.deadline=deadline.getTime();}
	
	public String getDescription(){return description;}
	public void setDescription(String description){this.description=description;}
	
	public long getBelongId(){return belong_id;}
	public void setBelongId(long belong_id){this.belong_id=belong_id;}
	
	public ArrayList<Todo> getSubtodos(){
		if(_id == 0){
			Util.errorReport("cannot get subtodos because _id=0");
			return null;
		}
		return TodoTable.where("belong_id = "+_id);
	}
	public int subtodoCount(){
		return this.getSubtodos().size();
	}
	/***************************** For Database ********************************/
	public Todo(){}
	public Todo(int _id,String title,int category_id,short status, long deadline,
				int user_id, String description, int belong_id, int rand_id){
		this._id=_id;
		this.title=title;
		this.category_id = category_id;
		this.isPublic = ((status&2) ==2 );
		this.alive = ((status&1) ==1 );
		this.deadline = deadline;
		this.user_id = user_id;
		this.description = description;
		this.belong_id = belong_id;
		this.rand_id = rand_id;
	}
	public void save(){
		TodoTable.update(this);
		CommunicateHelper.updateTodo(this);
	}
	public short getStatus(){return (short) ((isPublic?2:0)+(alive?1:0));}
	public long getDeadlineLong(){return getDeadline().getTime();}
	public int getUserId(){return user_id;}
	public int getRandId(){return rand_id;}
	
	@Override
	public int compareTo(Object o){
		Todo A = (Todo)o;
		if(this.isAlive()!=A.isAlive()){
			if(this.isAlive())return -1;
			else return 1;
		}
		else{
			if(this.getDeadlineLong() > A.getDeadlineLong())return 1;
			else if(this.getDeadlineLong() == A.getDeadlineLong())return 0;
			return -1;
		}
	}
}
