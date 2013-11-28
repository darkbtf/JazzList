package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;

public class UpdateCommand implements Command{
	public boolean exec(){
		Util.errorReport("in UpdateCommand");
		String cmd = SocketHelper.getMessage();
		Util.errorReport(cmd);
		return true;
	}
}
