package com.taipeihot.jazzlist.fight;

public class FightData {
	
	final static int NONE = 0;
	final static int INVITED = 1;
	final static int STARTED = 2;
	final static int IDLE = 3;
	final static int DONE = 4;
	final static int UPDATED = 5;
	final static int ENDED = 6;
	
	static int status = 0;
	static int inviterId;
	static String inviterNickname;
	static int first;
	static Player me;
	static Player opponent; 
	
	static public boolean isInvited() { return status == INVITED; }
	static public boolean isStarted() { return status == STARTED; }
	static public boolean isIdle() { return status == IDLE; }
	static public boolean isDone() { return status == DONE; }
	static public boolean isUpdated() { return status == UPDATED; }
	static public boolean isEnded() { return status == ENDED; }

	static public int getInviterId() { return inviterId; }
	static public String getInviterNickname() { return inviterNickname; }
	static public Player getMe() { return me; }
	static public Player getOpponent() { return opponent ; }
	
	static public void reset() { status = 0; }
	static public void setInvited() { status = INVITED; }
	static public void setStarted() { status = STARTED; }
	static public void setIdle() { status = IDLE; }
	static public void setDone() { status = DONE; }
	public static void setUpdated() { status = UPDATED; }
	static public void setEnded() { status = ENDED; }
	
	static public void setInviterId(int inv) { inviterId = inv; } 
	static public void setInviterNickname(String inv) { inviterNickname = inv; } 
	static public void setMe(Player player) { me = player; }
	static public void setOpponent(Player player) { opponent = player; }
	static public void setFirst(int fst) { first = fst; }
	
}