package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;

public class FriendCommand implements Command{
	public boolean exec(){
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("add")){
			int real_id = Integer.valueOf(SocketHelper.getMessage());
			if(real_id==0){
				Data.setFriendUpdating(true);
				return true;
			}
			String nickname = SocketHelper.getMessage();
			Data.friends.add(new User(nickname,real_id));
		}
		return true;
	}
}
