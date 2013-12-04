package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.AchievementType;
import com.taipeihot.jazzlist.model.Data;

public class AchievementCommand implements Command{
	public boolean exec(){
		Util.errorReport("in AchievementCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("add")){
			int icon=Integer.valueOf(SocketHelper.getMessage());
			AchievementType type = AchievementType.valueOf(SocketHelper.getMessage());
			int need = Integer.valueOf(SocketHelper.getMessage());
			Data.addAchivements(new Achievement(icon,type,need));
		}
		else return false;
		return true;
	}
}
