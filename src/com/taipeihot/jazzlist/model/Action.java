package com.taipeihot.jazzlist.model;

public class Action {
	private long _id=0;
	private int rate;
	private int hp_consume;
	private int mp_consume;
	private int hp_damage;
	private int mp_damage;
	private String description;
	private int number;
	
	public Action(int rate, int hp_consume, int mp_consume, int hp_damage, int mp_damage, String description, int number) {
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
		this.hp_damage = hp_damage;
		this.mp_damage = mp_damage;
		this.description = description;
		this.number = number;
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id=_id;}
	
	public int getActionId(){return (int)getId();}
	
	public double getRate(){return rate;}
	
	public int getHpConsume(){return hp_consume;}
	public int getMpConsume(){return mp_consume;}
	public int getHpDamage(){return hp_damage;}
	public int getMpDamage(){return mp_damage;}
	
	public String getDescription(){return description;}

	public int getNumber(){return number;}
	
	/***************************** For Database ********************************/
	public Action(){}
	public Action(int _id,int rate,int hp_consume, int mp_consume, int hp_damage, int mp_damage, String description, int number){
		this._id=_id;
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
		this.hp_damage = hp_damage;
		this.mp_damage = mp_damage;
		this.description = description;
		this.number = number;
	}
}
