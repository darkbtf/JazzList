package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Data;

public class LoginCommand implements Command{
	public boolean exec(){
		Util.errorReport("in LoginCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("success")){
			Data.loginSuccess();
		}
			//Util.errorReport("login success");
		else{
			Data.loginFail();
			return Util.errorReport("login fail"); // logout if receive this?
		}
		return true;
	}
}
