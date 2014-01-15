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
			Util.errorReport("start command");
			FightData.setStarted();
			CommunicateHelper.startFight(Data.getLevel(), Data.getHp(), Data.getMp(), Data.getAttack(), Data.getDefense());
		} else if (cmd.equals("init")) {
			Util.errorReport("init command");
			
			int first = Integer.valueOf(SocketHelper.getMessage());
			int level = Integer.valueOf(SocketHelper.getMessage());
			int HP = Integer.valueOf(SocketHelper.getMessage());
			int MP = Integer.valueOf(SocketHelper.getMessage());
			int attack = Integer.valueOf(SocketHelper.getMessage());
			int defense = Integer.valueOf(SocketHelper.getMessage());
			FightData.setFirst(first);
			FightData.setMe(new Player(-1, level, HP, MP, attack, defense, 0, ""));
			FightData.getMe().setNickname("Paul Hsia");
			level = Integer.valueOf(SocketHelper.getMessage());
			HP = Integer.valueOf(SocketHelper.getMessage());
			MP = Integer.valueOf(SocketHelper.getMessage());
			attack = Integer.valueOf(SocketHelper.getMessage());
			defense = Integer.valueOf(SocketHelper.getMessage());
			int opponentId = Integer.valueOf(SocketHelper.getMessage());
			FightData.setOpponent(new Player(opponentId, level, HP, MP, attack, defense, 0, ""));
			FightData.getOpponent().setNickname("Po-Cheng Chu");
			FightData.setPrepared();
		} else if (cmd.equals("update")) {
			Util.errorReport("updated command");
			int first = Integer.valueOf(SocketHelper.getMessage());
			FightData.setFirst(first);
			int moveId = Integer.valueOf(SocketHelper.getMessage());
			int HP = Integer.valueOf(SocketHelper.getMessage());
			int MP = Integer.valueOf(SocketHelper.getMessage());
			int attack = Integer.valueOf(SocketHelper.getMessage());
			int defense = Integer.valueOf(SocketHelper.getMessage());
			String stat = SocketHelper.getMessage();
			Player me = new Player(-1, FightData.getMe().getLevel(), HP, MP, attack, defense, moveId, stat);
			FightData.setMe(me);
			FightData.getMe().setNickname("Paul Hsia");
			moveId = Integer.valueOf(SocketHelper.getMessage());
			HP = Integer.valueOf(SocketHelper.getMessage());
			MP = Integer.valueOf(SocketHelper.getMessage());
			attack = Integer.valueOf(SocketHelper.getMessage());
			defense = Integer.valueOf(SocketHelper.getMessage());
			stat = SocketHelper.getMessage();
			Player opponent = new Player(FightData.getOpponent().getRealId(), FightData.getOpponent().getLevel(), HP, MP, attack, defense, moveId, stat);
			FightData.setOpponent(opponent);
			FightData.getOpponent().setNickname("Po-Cheng Chu");
			FightData.setUpdated();
			
		} else if (cmd.equals("end")) {
			FightData.setResult(SocketHelper.getMessage().equals("true"));
			FightData.setEnded();
		} else {
			return false;
		}
		return true;
	}
	
}
