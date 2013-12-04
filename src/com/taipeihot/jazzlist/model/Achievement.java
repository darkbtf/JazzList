package com.taipeihot.jazzlist.model;

public class Achievement {
	protected long _id=0;
	protected int icon;
	protected AchivType type;
	protected int need; // differ from different type achievement
	public boolean done(){
		int value = Data.getAchievementParameter(type);
		return need <= value;
	}
}