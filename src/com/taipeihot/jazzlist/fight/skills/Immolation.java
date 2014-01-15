package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class Immolation extends FightAction {

	public Immolation(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		// TODO Auto-generated method stub
		return player1.getNickname() + " just burnt himself, attack and heat increased.";
	}

}
