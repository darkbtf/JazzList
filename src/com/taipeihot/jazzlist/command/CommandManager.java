package com.taipeihot.jazzlist.command;

import java.util.HashMap;
import java.util.Map;

import com.taipeihot.jazzlist.Util;

public class CommandManager {
	static private Map<String, Command> cmdMap = new HashMap<String, Command>();
	public CommandManager() {
		cmdMap.put("achievement", new AchievementCommand());
		cmdMap.put("login",new LoginCommand());
		cmdMap.put("status",new StatusCommand());
		cmdMap.put("friend", new FriendCommand());
		cmdMap.put("comment", new CommentCommand());
		cmdMap.put("fight", new FightCommand());
	}

	public boolean parseCmd(String cmd) {
		Command cmdKind = cmdMap.get(cmd); 
		if(cmdKind == null){
			return Util.errorReport("Undefined Command: "+cmd);
		}
		return cmdKind.exec();
	}
}
