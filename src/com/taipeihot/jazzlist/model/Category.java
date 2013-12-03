package com.taipeihot.jazzlist.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.table.CategoryTable;
import com.taipeihot.jazzlist.table.TodoTable;

public class Category {
	private long _id=0;
	private String title;
	private int icon;
	private int count = 0;
	private int real_id = 0;
	
	public Category(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}
	
	public Category(String title, int icon, int count) {
		this.title = title;
		this.icon = icon;
		this.count = count;
	}
	
	public long addTodo(String title,long deadline) { // TODO: not title only?
		Todo a = new Todo(title,getId(),true,deadline,title+" s description");
		CommunicateHelper.addTodo(a);
		return TodoTable.insert(a);
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id = _id;}
	
	public String getTitle() {return this.title;}
	public void setTitle(String title) {this.title = title;}
	
	public int getIcon() {return this.icon;}
	public void setIcon(int icon) {this.icon = icon;}
	
	public int getCount() {return this.count;}
	public void setCount(int count) {this.count = count;}
	
	public ArrayList<Todo> getTodos(){
		ArrayList<Todo> ret =TodoTable.where("category_id = "+_id);
		Collections.sort(ret);
		return ret;
	}
	
	/***************************** For Database ********************************/
	public Category(){}
	public Category(int _id,String title,int icon,int count,int real_id) {
		this._id = _id;
		this.title = title;
		this.icon = icon;
		this.count = count;
		this.real_id = real_id;
	}
	public int save(){return CategoryTable.update(this);}
	
	public int getRealId(){return real_id;}
	public void setRealId(int real_id){this.real_id = real_id;}
}