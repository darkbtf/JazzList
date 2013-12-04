package com.taipeihot.jazzlist.model;

public class Achievement {
	private int icon;
	private AchievementType type;
	private int need; // differ from different type achievement
	public boolean done(){
		int value = Data.getAchievementParameter(type);
		return need <= value;
	}
	
	public Achievement(int icon, AchievementType type, int need){
		this.icon = icon;
		this.type = type;
		this.need = need;
	}
	
	public String getType(){return type.toString();}
	public int getNeed(){return need;}
	public int getIcon(){return icon;}
}