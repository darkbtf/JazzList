package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.fight.FightData;
import com.taipeihot.jazzlist.fight.Player;

public class FightCommand implements Command {

	@Override
	public boolean exec() {
		String cmd = SocketHelper.getMessage();
		if (cmd.equals("invite")) {
			
		} else if (cmd.equals("start")) {
			
		} else if (cmd.equals("init")) {
			int level = Integer.valueOf(SocketHelper.getMessage());
			int HP = Integer.valueOf(SocketHelper.getMessage());
			int MP = Integer.valueOf(SocketHelper.getMessage());
			int attack = Integer.valueOf(SocketHelper.getMessage());
			int defense = Integer.valueOf(SocketHelper.getMessage());
			FightData.setMe(new Player(level, HP, MP, attack, defense));
			level = Integer.valueOf(SocketHelper.getMessage());
			HP = Integer.valueOf(SocketHelper.getMessage());
			MP = Integer.valueOf(SocketHelper.getMessage());
			attack = Integer.valueOf(SocketHelper.getMessage());
			defense = Integer.valueOf(SocketHelper.getMessage());
			FightData.setOpponent(new Player(level, HP, MP, attack, defense));
		} else if (cmd.equals("update")) {
			
		} else if (cmd.equals("end")) {
			
		} else {
			return false;
		}
		return true;
	}
	
}
