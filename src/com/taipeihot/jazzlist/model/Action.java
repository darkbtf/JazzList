package com.taipeihot.jazzlist.model;

public class Action {
	private long _id=0;
	private int rate;
	private int hp_consume;
	private int mp_consume;
	private int hp_damage;
	private int mp_damage;
	private String name;
	private String description;
	private int number;
	private int object_id;
	private int level_limit;
	
	public Action(int rate, int hp_consume, int mp_consume, int hp_damage, int mp_damage, String name, String description, int number, int object_id, int level_limit) {
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
		this.hp_damage = hp_damage;
		this.mp_damage = mp_damage;
		this.name = name;
		this.description = description;
		this.number = number;
		this.object_id = object_id;
		this.level_limit = level_limit;
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id=_id;}
	
	public double getRate(){return rate;}
	
	public int getHpConsume(){return hp_consume;}
	public int getMpConsume(){return mp_consume;}
	public int getHpDamage(){return hp_damage;}
	public int getMpDamage(){return mp_damage;}
	
	public String getName(){return name;}
	public String getDescription(){return description;}

	public int getNumber(){return number;}
	
	public int getObjectId(){return object_id;}
	
	public int getLevelLimit(){return level_limit;}

	public int getImageId(){return object_id>0?object_id:-object_id;}
	
	public boolean exist(){return number!=0;}
	/***************************** For Database ********************************/
	public Action(){}
	public Action(int _id,int rate,int hp_consume, int mp_consume, int hp_damage, int mp_damage, String name, String description, int number, int object_id, int level_limit){
		this._id=_id;
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
		this.hp_damage = hp_damage;
		this.mp_damage = mp_damage;
		this.name = name;
		this.description = description;
		this.number = number;
		this.object_id = object_id;
		this.level_limit = level_limit;
	}
}
