package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.fight.FightData;
import com.taipeihot.jazzlist.fight.Player;
import com.taipeihot.jazzlist.model.Data;

public class FightCommand implements Command {

	@Override
	public boolean exec() {
		String cmd = SocketHelper.getMessage();
		if (cmd.equals("invite")) {
			FightData.setInvited(true);
			int id = Integer.valueOf(SocketHelper.getMessage());
			FightData.setInviterId(id);
			FightData.setInviterNickname(SocketHelper.getMessage());
		} else if (cmd.equals("start")) {
			CommunicateHelper.startFight(Data.getLevel(), Data.getHp(), Data.getMp(), Data.getAttack(), Data.getDefense());
		} else if (cmd.equals("init")) {
			Util.errorReport("INIT!");
			
			int first = Integer.valueOf(SocketHelper.getMessage());
			int level = Integer.valueOf(SocketHelper.getMessage());
			int HP = Integer.valueOf(SocketHelper.getMessage());
			int MP = Integer.valueOf(SocketHelper.getMessage());
			int attack = Integer.valueOf(SocketHelper.getMessage());
			int defense = Integer.valueOf(SocketHelper.getMessage());
			FightData.setFirst(first);
			FightData.setMe(new Player(level, HP, MP, attack, defense));
			level = Integer.valueOf(SocketHelper.getMessage());
			HP = Integer.valueOf(SocketHelper.getMessage());
			MP = Integer.valueOf(SocketHelper.getMessage());
			attack = Integer.valueOf(SocketHelper.getMessage());
			defense = Integer.valueOf(SocketHelper.getMessage());
			FightData.setOpponent(new Player(level, HP, MP, attack, defense));
			FightData.setStarted(true);
		} else if (cmd.equals("update")) {
			
		} else if (cmd.equals("end")) {
			
		} else {
			return false;
		}
		return true;
	}
	
}
