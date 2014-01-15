package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;

import com.taipeihot.jazzlist.fight.items.GreatHealingPotion;
import com.taipeihot.jazzlist.fight.items.HealingPotion;
import com.taipeihot.jazzlist.fight.items.MasterHealingPotion;
import com.taipeihot.jazzlist.fight.skills.*;

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
		
		itemList.add(new HealingPotion(null, null));
		itemList.add(new GreatHealingPotion(null, null));
		itemList.add(new MasterHealingPotion(null, null));
	}

	public String getSelfAnimation(int moveId) {
		if (moveId > 0) return skillList.get(moveId - 1).getSelfAnimation();
		else return itemList.get(-moveId - 1).getSelfAnimation();
	}
	
	public String getOpponentAnimation(int moveId) {
		if (moveId > 0) return skillList.get(moveId - 1).getOpponentAnimation();
		else return itemList.get(-moveId - 1).getOpponentAnimation();
	}
}