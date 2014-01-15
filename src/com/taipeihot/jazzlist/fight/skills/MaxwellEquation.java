package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class MaxwellEquation extends FightAction {

	public MaxwellEquation(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}

	@Override
	public String getMessage(Player player1, Player player2) {
		return player2.getNickname() + " took a electromagnetics final exam from " + player1.getNickname() + ".";
	}

}
