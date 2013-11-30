package com.taipeihot.jazzlist.command;

import java.util.HashMap;
import java.util.Map;

import com.taipeihot.jazzlist.Util;

public class CommandManager {
	static private Map<String, Command> cmdMap = new HashMap<String, Command>();
	public CommandManager() {
		cmdMap.put("login",new LoginCommand());
		cmdMap.put("update",new UpdateCommand());
		cmdMap.put("friend", new FriendCommand());
	}

	public boolean parseCmd(String cmd) {
		Command cmdKind = cmdMap.get(cmd); 
		if(cmdKind == null){
			return Util.errorReport("Undefined Command: "+cmd);
		}
		return cmdKind.exec();
	}
}
