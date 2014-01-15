package com.taipeihot.jazzlist.fight;

public class FightUtils {
	
	public static final int HP_SLOPE = 37;
	public static final int HP_INTERCEPT = 100;
	public static final int MP_SLOPE = 27;
	public static final int MP_INTERCEPT = 50;
	
	public static int calcHp(int level) { return level * HP_SLOPE + HP_INTERCEPT; } 
	public static int calcMp(int level) { return level * MP_SLOPE + MP_INTERCEPT; } 
	
	public static int calcDamage(Player player1, Player player2, int baseDamage) {
		double ratio = (double) (player1.getAttack()) / (double) (player2.getAttack());
		ratio *= (1 + Math.random() * 0.1 - 0.05);
		return (int) Math.ceil(baseDamage * ratio);
	}
	
	public static int calcHpDiff(Player player) {
		return player.getPrevious().getHp() - player.getHp();
	}
	
	public static int calcMpDiff(Player player) {
		return player.getPrevious().getMp() - player.getMp();
	}
}