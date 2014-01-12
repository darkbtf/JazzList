package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;

public class Player {
	/**
	 * @author DarkBtf
	 * */
	private int level;
	private int HP;
	private int MP;
	private int attack;
	private int defense;
	private ArrayList<Status> status;
	private Player opponent;
	
	/** === constructor === **/
	public Player() {
		
	}
	
	public Player(int level, int HP, int MP, int attack, int defense) {
		this.level = level;
		this.HP = HP;
		this.MP = MP;
		this.attack = attack;
		this.defense = defense;
	}
	
	/** === setters & getters === **/
	public int getLevel() { return level; }
	public int getHp() { return HP; }
	public int getMp() { return MP; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public ArrayList<Status> getStatus() { return status; }
	public Player getOpponent() { return opponent; }
	
	public void setLevel(int level) { this.level = level; }
	public void setHp(int HP) { this.HP = HP; }
	public void setMp(int MP) { this.MP = MP; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }
	public void setOpponent(Player player) { this.opponent = player; }
	
	public void addStatus(Status stat) { status.add(stat); }
	
	/** some utils **/
	public void addHp(int diff) { this.HP += diff; }
	public void addMp(int diff) { this.MP += diff; }
}