package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;

public class StatusCommand implements Command{
	public boolean exec(){
		Util.errorReport("in StatusCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("new")){
			int real_id = Integer.valueOf(SocketHelper.getMessage());
			String nickname = SocketHelper.getMessage();
			String title = SocketHelper.getMessage();
			long deadline = Long.valueOf(SocketHelper.getMessage());
			int score = Integer.valueOf(SocketHelper.getMessage());
			Data.addStatus(new Status(nickname,title,deadline,score,real_id));
			return true;
		}
		return true;
	}
}
