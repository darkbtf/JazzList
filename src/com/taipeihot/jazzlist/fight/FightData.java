package com.taipeihot.jazzlist.fight;

import com.taipeihot.jazzlist.Util;

public class FightData {
	
	final static int NONE = 0;
	final static int INVITED = 1;
	final static int STARTED = 2;
	final static int PREPARED = 3;
	final static int IDLE = 4;
	final static int DONE = 5;
	final static int UPDATED = 6;
	final static int ENDED = 7;
	
	static int status = 0;
	static int inviterId;
	static String inviterNickname;
	static int lastFirst;
	static int first;
	static Player me;
	static Player opponent; 

	static public boolean isNone() { return status == NONE; }
	static public boolean isInvited() { return status == INVITED; }
	static public boolean isStarted() { return status == STARTED; }
	static public boolean isPrepared() { return status == PREPARED; }
	static public boolean isIdle() { return status == IDLE; }
	static public boolean isDone() { return status == DONE; }
	static public boolean isUpdated() { return status == UPDATED; }
	static public boolean isEnded() { return status == ENDED; }

	static public int getInviterId() { return inviterId; }
	static public int getLastFirst() { return lastFirst; }
	static public int getFirst() { return first; }
	static public String getInviterNickname() { return inviterNickname; }
	static public Player getMe() { return me; }
	static public Player getOpponent() { return opponent ; }
	
	static public void reset() { Util.errorReport("reset");  status = 0; }
	static public void setInvited() { Util.errorReport("set invited"); status = INVITED; }
	static public void setStarted() { Util.errorReport("set started"); status = STARTED; }
	static public void setPrepared() { Util.errorReport("set prepared"); status = PREPARED; }
	static public void setIdle() { Util.errorReport("set idle"); status = IDLE; }
	static public void setDone() { Util.errorReport("set done"); status = DONE; }
	public static void setUpdated() { Util.errorReport("set updated"); status = UPDATED; }
	static public void setEnded() { Util.errorReport("set ended"); status = ENDED; }
	
	
	
	static public void setInviterId(int inv) { inviterId = inv; } 
	static public void setInviterNickname(String inv) { inviterNickname = inv; } 
	static public void setMe(Player player) { 
		player.setPrevious(me);
		me = player;
	}
	static public void setOpponent(Player player) {
		player.setPrevious(opponent);
		opponent = player; 
	}
	static public void setFirst(int fst) { 
		lastFirst = first;
		first = fst; 
	}
	
}