package com.taipeihot.jazzlist.model;

public class Todo {
	private long _id=0;
	public String content;
	public String status;
	public Todo(){}
	public Todo(String content,String status){
		this.content=content;
		this.status=status;
	}
	public Todo(int _id,String content,String status){
		this._id=_id;
		this.content=content;
		this.status=status;
	}
	
	public long id(){return _id;}
	public void setId(long _id){this._id=_id;}
}
