package com.taipeihot.jazzlist.model;

public class Achievement {
	private int icon;
	private AchievementType type;
	private int need;
	private String title;
	private String description;
	public boolean done(){
		int value = Data.getAchievementParameter(type);
		return need <= value;
	}
	
	public Achievement(int icon, AchievementType type, int need, String title, String description){
		this.icon = icon;
		this.type = type;
		this.need = need;
		this.title = title;
		this.description = description;
	}
	
	public String getType(){return type.toString();}
	public int getNeed(){return need;}
	public int getIcon(){return icon;}
	public String getTitle(){return title;}
	public String getDescription(){return description;}
}