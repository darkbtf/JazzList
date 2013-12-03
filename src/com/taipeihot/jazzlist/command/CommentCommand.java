package com.taipeihot.jazzlist.command;

import com.taipeihot.jazzlist.SocketHelper;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Comment;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;

public class CommentCommand implements Command{
	public boolean exec(){
		Util.errorReport("in CommentCommand");
		String cmd = SocketHelper.getMessage();
		if(cmd.equals("new")){
			String nickname = SocketHelper.getMessage();
			String content  = SocketHelper.getMessage();
			int user_id = Integer.valueOf(SocketHelper.getMessage());
			int status_id = Integer.valueOf(SocketHelper.getMessage());
			int real_id = Integer.valueOf(SocketHelper.getMessage());
			int score = Integer.valueOf(SocketHelper.getMessage());
			long time = Long.valueOf(SocketHelper.getMessage());
			Data.updateComment(new Comment(nickname,content,user_id,status_id,real_id,score,time));
			return true;
		}
		return true;
	}
}
