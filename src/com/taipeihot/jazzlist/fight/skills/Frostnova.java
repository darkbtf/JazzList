package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class Frostnova extends FightAction {

	public Frostnova(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {

		return "A frostnova exploded under " + player2.getNickname() + "'s feet. " + player2.getNickname() + " can't move for 2 rounds.";
	}


}
