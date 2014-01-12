package com.taipeihot.jazzlist.fight;

public interface FightAction {
	/** == player1 perform action on player2 == **/
	void exec(Player player1, Player player2);
}