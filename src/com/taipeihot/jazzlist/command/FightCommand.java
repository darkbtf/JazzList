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
		//Util.errorReport("wtf");
		String cmd = SocketHelper.getMessage();
		if (cmd.equals("invite")) {
			Util.errorReport("receive accept!");
			FightData.setInvited();
			int id = Integer.valueOf(SocketHelper.getMessage());
			FightData.setInviterId(id);
			FightData.setInviterNickname(SocketHelper.getMessage());
		} else if (cmd.equals("start")) {
			FightData.setStarted();
			CommunicateHelper.startFight(Data.getLevel(), Data.getHp(), Data.getMp(), Data.getAttack(), Data.getDefense());
		} else if (cmd.equals("init")) {
			
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
			FightData.setStarted();
		} else if (cmd.equals("update")) {
			
			int first = Integer.valueOf(SocketHelper.getMessage());
			Player me = FightData.getMe();
			Player opponent = FightData.getOpponent();
			int moveId = Integer.valueOf(SocketHelper.getMessage());
			int HP = Integer.valueOf(SocketHelper.getMessage());
			me.setHp(HP);
			int MP = Integer.valueOf(SocketHelper.getMessage());
			me.setMp(MP);
			int attack = Integer.valueOf(SocketHelper.getMessage());
			me.setAttack(attack);
			int defense = Integer.valueOf(SocketHelper.getMessage());
			me.setDefense(defense);
			String stat = SocketHelper.getMessage();
			FightData.setFirst(first);
			moveId = Integer.valueOf(SocketHelper.getMessage());
			HP = Integer.valueOf(SocketHelper.getMessage());
			opponent.setHp(HP);
			MP = Integer.valueOf(SocketHelper.getMessage());
			opponent.setMp(MP);
			attack = Integer.valueOf(SocketHelper.getMessage());
			opponent.setAttack(attack);
			defense = Integer.valueOf(SocketHelper.getMessage());
			opponent.setDefense(defense);
			stat = SocketHelper.getMessage();
			FightData.setStarted();
			
		} else if (cmd.equals("end")) {
			FightData.setEnded();
		} else {
			return false;
		}
		return true;
	}
	
}
