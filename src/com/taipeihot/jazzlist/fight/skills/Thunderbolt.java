package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class Thunderbolt implements FightAction {

	public final int MP_COST = 0;
	
	@Override
	public void exec(Player player1, Player player2) {
		player1.addMp();
		
	}

}
