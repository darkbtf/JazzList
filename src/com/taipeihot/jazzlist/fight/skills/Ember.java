package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.FightUtils;
import com.taipeihot.jazzlist.fight.Player;

public class Ember extends FightAction {

	public Ember(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		int hpDiff = FightUtils.calcHpDiff(player2);
		return player1.getNickname() + " lit " + player2.getNickname() + " on fire and dealt " + hpDiff + " points of damage";
	}

}
