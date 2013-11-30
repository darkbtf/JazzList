package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;

public class LoginCommand implements Command{
	public boolean exec(){
		Util.errorReport("in LoginCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("success")){
			CommunicateHelper.logined=true;
		}
			//Util.errorReport("login success");
		else return Util.errorReport("login fail"); // logout if receive this?
		return true;
	}
}
