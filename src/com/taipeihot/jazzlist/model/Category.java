package com.taipeihot.jazzlist.model;

import java.util.ArrayList;

import com.taipeihot.jazzlist.table.CategoryTable;
import com.taipeihot.jazzlist.table.TodoTable;

public class Category {
	private long _id=0;
	private String title;
	private int icon;
	private int count = 0;
	
	public Category(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}
	
	public Category(String title, int icon, int count) {
		this.title = title;
		this.icon = icon;
		this.count = count;
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
		return TodoTable.where("category_id = "+_id);
	}
	
	/***************************** For Database ********************************/
	public Category(){}
	public Category(int _id,String title,int icon,int count) {
		this._id = _id;
		this.title = title;
		this.icon = icon;
		this.count = count;
	}
	public int save(){return CategoryTable.update(this);}
}