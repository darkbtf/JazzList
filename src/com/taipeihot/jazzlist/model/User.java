package com.taipeihot.jazzlist.model;

import com.taipeihot.jazzlist.table.UserTable;

public class User {
	private long _id=0;
	private String account;
	private String password;
	private String nickname;
	private int idOfServer=0;
	
	public User(String account, String password){
		this.account = account;
		this.password = password;
		this.nickname = account;
	}
	
	public User(String account, String password, String nickname){
		this.account = account;
		this.password = password;
		this.nickname = nickname;
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id=_id;}
	
	public String getAccount(){return account;}
	public void setAccount(String account){this.account=account;}
	
	public String getNickname(){return nickname;}
	public void setNickname(String nickname){this.nickname=nickname;}
	
	/***************************** For Database ********************************/
	public User(){}
	public User(int _id,String account,String password,String nickname, int idOfServer){
		this._id=_id;
		this.account=account;
		this.password = password;
		this.nickname = nickname;
		this.idOfServer = idOfServer;
	}
	public int save(){return UserTable.update(this);}
	public String getPassword(){return password;}
	public int getIdOfServer(){return idOfServer;}
	
}
