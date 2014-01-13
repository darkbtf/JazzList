package com.taipeihot.jazzlist.model;

import com.taipeihot.jazzlist.table.ActionTable;

public class Action {
	private long _id=0;
	private int rate;
	private int hp_consume;
	private int mp_consume;
	private String name;
	private String description;
	private int number;
	private int object_id;
	private int level_limit;
	
	public Action(int rate, int hp_consume, int mp_consume, String name, String description, int number, int object_id, int level_limit) {
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
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
	
	public String getName(){return name;}
	public String getDescription(){return description;}

	public int getNumber(){return number;}
	
	public int getObjectId(){return object_id;}
	
	public int getLevelLimit(){return level_limit;}

	public int getImageId(){return object_id>0?object_id:-object_id;}
	
	public boolean exist(){return number!=0;}
	
	public int getMoney(){return level_limit;}
	
	public boolean buy() {
		if(Data.getMoney() < level_limit)
			return false;
		Data.incMoney(-level_limit);
		number++;
		save();
		return true;
	}
	
	public boolean learn() {
		if(Data.getSkillPoint()<=0)return false;
		if(object_id < 0)return false;
		if(object_id > 3 && ActionTable.where("object_id = "+(object_id-3)).get(0).getNumber() <=0)return false;
		number = 999;
		save();
		return true;
	}
	public boolean canUseInFight(int mp) {
		return mp>=mp_consume && number>0;
	}
	/***************************** For Database ********************************/
	public Action(){}
	public Action(int _id,int rate,int hp_consume, int mp_consume, String name, String description, int number, int object_id, int level_limit){
		this._id=_id;
		this.rate = rate;
		this.hp_consume = hp_consume;
		this.mp_consume = mp_consume;
		this.name = name;
		this.description = description;
		this.number = number;
		this.object_id = object_id;
		this.level_limit = level_limit;
	}
	public void save(){ActionTable.update(this);}
}
