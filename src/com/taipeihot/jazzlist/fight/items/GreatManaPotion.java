package com.taipeihot.jazzlist.fight.items;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class GreatManaPotion extends FightAction {
	
	public GreatManaPotion(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		return player1.getNickname() + " took Great Mana Potion and regained some MP.";
	}

}
