package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;

class Player {
	/**
	 * @author DarkBtf
	 * */
	private int level;
	private int HP;
	private int MP;
	private ArrayList<Status> status;
	private Player opponent;
	
	/** === constructor === **/
	Player() {
		
	}
	
	Player(int level, int HP, int MP) {
		this.level = level;
		this.HP = HP;
		this.MP = MP;
	}
	
	/** === setters & getters === **/
	public int getLevel() { return level; }
	public int getHp() { return HP; }
	public int getMp() { return MP; }
	public ArrayList<Status> getStatus() { return status; }
	public Player getOpponent() { return opponent; }
	
	public void setLevel(int level) { this.level = level; }
	public void setHp(int HP) { this.HP = HP; }
	public void setMp(int MP) { this.MP = MP; }
	public void setOpponent(Player player) { this.opponent = player; }
	
	public void addStatus(Status stat) { status.add(stat); }
	
	/**  **/
}