package com.taipeihot.jazzlist.fight;

public class FightData {
	
	final static int IDLE = 0;
	final static int INVITED = 1;
	final static int STARTED = 2;
	
	static int status = 0;
	static int inviterId;
	static String inviterNickname;
	static int first;
	static Player me;
	static Player opponent; 
	
	static public boolean isInvited() { return status == INVITED; }
	static public boolean isStarted() { return status == STARTED; }

	static public int getInviterId() { return inviterId; }
	static public String getInviterNickname() { return inviterNickname; }
	
	static public void setInvited(boolean inv) { status = (inv ? INVITED : 0); }
	static public void setStarted(boolean fight) { status = (fight ? STARTED : 0); }
	
	static public void setInviterId(int inv) { inviterId = inv; } 
	static public void setInviterNickname(String inv) { inviterNickname = inv; } 
	static public void setMe(Player player) { me = player; }
	static public void setOpponent(Player player) { opponent = player; }
	static public void setFirst(int fst) { first = fst; }
	
}