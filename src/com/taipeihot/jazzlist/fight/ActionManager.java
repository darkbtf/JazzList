package com.taipeihot.jazzlist.fight;

import java.util.ArrayList;
import com.taipeihot.jazzlist.fight.skills.*;

public class ActionManager {
	ArrayList<FightAction> SkillList = new ArrayList<FightAction>();
	ArrayList<FightAction> itemList = new ArrayList<FightAction>();
	
	public ActionManager() {
		SkillList.add(new Ember(null, "fire0"));
		SkillList.add(new Bless("water0", null));
		SkillList.add(new Thunderbolt(null, "thunder0"));
		SkillList.add(new Immolation("fire1", null));
		SkillList.add(new ManaStream("water1", null));
		SkillList.add(new StaticField("thunder1", null));
		SkillList.add(new KarmaBlast(null, "fire2"));
		SkillList.add(new Frostnova(null, "water2"));
		SkillList.add(new MaxwellEquation("thunder2_self", "thunder2_enemy"));
	}

	public String getSelfAnimation() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getOpponentAnimation() {
		// TODO Auto-generated method stub
		return null;
	}
}