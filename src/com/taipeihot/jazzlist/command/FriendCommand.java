package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;

public class FriendCommand implements Command{
	public boolean exec(){
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("add")){
			String nickname = SocketHelper.getMessage();
			int real_id = Integer.valueOf(SocketHelper.getMessage());
			Data.friends.add(new User(nickname,real_id));
		}
		return true;
	}
}
