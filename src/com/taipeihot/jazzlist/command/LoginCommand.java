package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;

public class LoginCommand implements Command{
	public boolean exec(){
		Util.errorReport("in LoginCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("success"))
			Util.errorReport("login success");
		else return Util.errorReport("login fail");
		return true;
	}
}
