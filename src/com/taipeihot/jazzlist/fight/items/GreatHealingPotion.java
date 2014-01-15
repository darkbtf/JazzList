package com.taipeihot.jazzlist.fight.items;

import com.taipeihot.jazzlist.fight.FightAction;
import com.taipeihot.jazzlist.fight.Player;

public class GreatHealingPotion extends FightAction {
	
	public GreatHealingPotion(String selfAnime, String opponentAnime) {
		super(selfAnime, opponentAnime);
	}
	
	@Override
	public String getMessage(Player player1, Player player2) {
		return player1.getNickname() + " took Great Healing Potion and regained some HP.";
	}

}
