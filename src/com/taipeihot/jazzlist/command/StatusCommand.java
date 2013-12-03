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
			if(real_id == 0){
				long lastUpdateStausTime=Long.valueOf(SocketHelper.getMessage());
				Data.doneStatusUpdate(lastUpdateStausTime);
				return true;
			}
			String nickname = SocketHelper.getMessage();
			String title = SocketHelper.getMessage();
			int user_id = Integer.valueOf(SocketHelper.getMessage());
			long deadline = Long.valueOf(SocketHelper.getMessage());
			int score = Integer.valueOf(SocketHelper.getMessage());
			String category = SocketHelper.getMessage();
			long updated_at = Long.valueOf(SocketHelper.getMessage());
			Data.addStatus(new Status(nickname,title,user_id,deadline,score,real_id,category,updated_at));
			return true;
		}
		else if(cmd.equals("update")){
			int real_id = Integer.valueOf(SocketHelper.getMessage());
			String nickname = SocketHelper.getMessage();
			String title = SocketHelper.getMessage();
			int user_id = Integer.valueOf(SocketHelper.getMessage());
			long deadline = Long.valueOf(SocketHelper.getMessage());
			int score = Integer.valueOf(SocketHelper.getMessage());
			String category = SocketHelper.getMessage();
			long updated_at = Long.valueOf(SocketHelper.getMessage());
			Data.updateStatusByRealId(real_id,new Status(nickname,title,user_id,deadline,score,real_id,category,updated_at));
		}
		return true;
	}
}
