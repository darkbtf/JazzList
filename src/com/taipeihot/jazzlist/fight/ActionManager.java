package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;

import com.taipeihot.jazzlist.fight.items.GreatHealingPotion;
import com.taipeihot.jazzlist.fight.items.GreatManaPotion;
import com.taipeihot.jazzlist.fight.items.GreatOmniPotion;
import com.taipeihot.jazzlist.fight.items.HealingPotion;
import com.taipeihot.jazzlist.fight.items.ManaPotion;
import com.taipeihot.jazzlist.fight.items.OmniPotion;
import com.taipeihot.jazzlist.fight.skills.Bless;
import com.taipeihot.jazzlist.fight.skills.Ember;
import com.taipeihot.jazzlist.fight.skills.Frostnova;
import com.taipeihot.jazzlist.fight.skills.Immolation;
import com.taipeihot.jazzlist.fight.skills.KarmaBlast;
import com.taipeihot.jazzlist.fight.skills.ManaStream;
import com.taipeihot.jazzlist.fight.skills.MaxwellEquation;
import com.taipeihot.jazzlist.fight.skills.StaticField;
import com.taipeihot.jazzlist.fight.skills.Thunderbolt;

public class ActionManager {
	ArrayList<FightAction> skillList = new ArrayList<FightAction>();
	ArrayList<FightAction> itemList = new ArrayList<FightAction>();
	
	public ActionManager() {
		skillList.add(new Ember(null, "fire0"));
		skillList.add(new Bless("water0", null));
		skillList.add(new Thunderbolt(null, "thunder0"));
		skillList.add(new Immolation("fire1", null));
		skillList.add(new ManaStream("water1", null));
		skillList.add(new StaticField("thunder1", null));
		skillList.add(new KarmaBlast(null, "fire2"));
		skillList.add(new Frostnova(null, "water2"));
		skillList.add(new MaxwellEquation("thunder2_self", "thunder2_enemy"));
		
		itemList.add(new HealingPotion("red_potion", null));
		itemList.add(new ManaPotion("red_potion", null));
		itemList.add(new OmniPotion("red_potion", null));		
		itemList.add(new GreatHealingPotion("red_potion", null));
		itemList.add(new GreatManaPotion("red_potion", null));
		itemList.add(new GreatOmniPotion("red_potion", null));
	}

	public String getSelfAnimation(int moveId) {
		if (moveId > 0) return skillList.get(moveId - 1).getSelfAnimation();
		else if (moveId < 0) return itemList.get(-moveId - 1).getSelfAnimation();
		else return null;
	}
	
	public String getOpponentAnimation(int moveId) {
		if (moveId > 0) return skillList.get(moveId - 1).getOpponentAnimation();
		else if (moveId < 0) return itemList.get(-moveId - 1).getOpponentAnimation();
		else return null;
	}
	
	public String getMessage(Player player1, Player player2, int moveId) {
		if (moveId > 0) return skillList.get(moveId - 1).getMessage(player1, player2);
		else if (moveId < 0) return itemList.get(-moveId - 1).getMessage(player1, player2);
		else return null;
	}
}