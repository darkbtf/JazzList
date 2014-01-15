package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;

public class Player {
	/**
	 * @author DarkBtf
	 * */
	private String nickname;
	private int level;
	private int HP;
	private int MP;
	private int attack;
	private int defense;
	private int move;
	private ArrayList<Status> status;
	private Player opponent;
	private Player prev;
	private String stat;
	private int realId;
	
	/** === constructor === **/
	public Player() {
		
	}
	
	public Player(int realId, int level, int HP, int MP, int attack, int defense, int move, String stat) {
		this.realId = realId;
		this.level = level;
		this.HP = HP;
		this.MP = MP;
		this.attack = attack;
		this.defense = defense;
		this.move = move;
		this.stat = stat;
		this.prev = null;
	}
	
	/** === setters & getters === **/
	public String getNickname() { return nickname; }
	public int getLevel() { return level; }
	public int getHp() { return HP; }
	public int getMp() { return MP; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public int getMove() { return move; }
	public int getRealId() { return realId; }
	public String getStat() { return stat; }
	public ArrayList<Status> getStatus() { return status; }
	public Player getOpponent() { return opponent; }
	public Player getPrevious() { return prev; }

	public void setNickname(String nickname) { this.nickname = nickname; }
	public void setRealId(int realId) { this.realId = realId; }
	public void setLevel(int level) { this.level = level; }
	public void setHp(int HP) { this.HP = HP; }
	public void setMp(int MP) { this.MP = MP; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }
	public void setOpponent(Player player) { this.opponent = player; }
	public void setMove(int move) { this.move = move; }
	public void setStat(String stat) { this.stat = stat; }
	public void setPrevious(Player prev) { this.prev = prev; }
	
	public void addStatus(Status stat) { status.add(stat); }
	
	/** some utils **/
	public void addHp(int diff) { this.HP += diff; }
	public void addMp(int diff) { this.MP += diff; }
	
	public void output() {
		Util.errorReport(nickname + " " + level + " " + HP + " " + MP);
		
	}	
}