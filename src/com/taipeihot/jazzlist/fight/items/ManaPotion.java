package com.taipeihot.jazzlist.fight.items;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class ManaPotion extends FightAction {
	
	public ManaPotion(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		return player1.getNickname() + " took Mana Potion and regained some MP.";
	}

}
