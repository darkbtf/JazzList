package com.taipeihot.jazzlist.fight;

public class FightData {
	static boolean fighting = false;
	static Player me;
	static Player opponent;
	
	static public boolean isFighting() { return fighting; }
	
	static public void setMe(Player player) { me = player; }
	static public void setOpponent(Player player) { opponent = player; }
	
}