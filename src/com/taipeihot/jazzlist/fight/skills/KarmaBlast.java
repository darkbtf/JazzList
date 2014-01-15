package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class KarmaBlast extends FightAction {

	public KarmaBlast(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		return player1.getNickname() + " smashed " + player2.getNickname() + "'s face with KARMA BLAST!!! ·~¤O¤ÞÃz!!!";
	}
}
