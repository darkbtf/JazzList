package com.taipeihot.jazzlist.fight.skills;

import com.taipeihot.jazzlist.fight.Action;
import com.taipeihot.jazzlist.fight.Type;

abstract class Skill implements Action {
	
	public Type getType() {
		return Type.SKILL;
	}
}