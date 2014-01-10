package com.taipeihot.jazzlist.fight.items;

import com.taipeihot.jazzlist.fight.Action;
import com.taipeihot.jazzlist.fight.Type;

abstract class Item implements Action {
	
	public Type getType() {
		return Type.ITEM;
	}
}