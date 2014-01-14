package com.taipeihot.jazzlist.model;

import com.taipeihot.jazzlist.table.EquipmentTable;

public class Equipment {
	private long _id=0;
	private int hp_change;
	private int mp_change;
	private int attack_change;
	private int defense_change;
	private int money;
	private int at;
	private String name;
	private String description;
	private int type;
	
	public Equipment(int hp_change, int mp_change, int attack_change, int defense_change, int money, int at, String name, String description, int type) {
		this.hp_change = hp_change;
		this.mp_change = mp_change;
		this.attack_change = attack_change;
		this.defense_change = defense_change;
		this.money = money;
		this.at = at;
		this.name = name;
		this.description = description;
		this.type = type;
	}
	public long getId(){return _id;}
	public void setId(long _id){this._id=_id;}
	
	public int getHpChange(){return hp_change;}
	public int getMpChange(){return mp_change;}
	public int getAttackChange(){return attack_change;}
	public int getDefenseChange(){return defense_change;}
	public int getAt(){return at;}

	public String getName(){return name;}
	public String getDescription(){return description;}

	public int getImageId(){return (int)_id;}
	
	public int getMoney(){return money;}
	public int getType() {return type;}
	public void setType(int type){this.type=type;}
	public boolean exist(){return !(at==-1);}
	public boolean buy() {
		if(Data.getMoney() < money)
			return false;
		Data.incMoney(-money);
		at=0;
		save();
		return true;
	}
	public void remove(){
		if(at!=1)return;
		at=0;
		save();
	}
	public void wear() {
		if(at!=0)return;
		for(Equipment e:EquipmentTable.where("type = ? and where = 1",new String[]{type+""})){
			e.setType(0);
			e.save();
		}
		at=1;
		save();
	}
	/***************************** For Database ********************************/
	public Equipment(){}
	public Equipment(int _id, int hp_change, int mp_change, int attack_change, int defense_change, int money, int at, String name, String description, int type){
		this._id=_id;
		this.hp_change = hp_change;
		this.mp_change = mp_change;
		this.attack_change = attack_change;
		this.defense_change = defense_change;
		this.money = money;
		this.at = at;
		this.name = name;
		this.description = description;
		this.type = type;
	}
	public void save(){EquipmentTable.update(this);}
}
