package com.taipeihot.jazzlist;

import com.taipeihot.jazzlist.command.CommandManager;

public class UpdateHelper {
	static CommandManager cmdMgr = new CommandManager();
	static boolean start(){
    	if(!SocketHelper.start())return false;
    	Thread msgThread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			if(!trylogin())return;
    			while(SocketHelper.hasNet){
	    			String cmd=SocketHelper.getMessage();
	    			cmdMgr.parseCmd(cmd);
    			}
    		}
    	});
    	msgThread.start();
    	return true;
	}
	static private boolean trylogin(){
		for(int i=0;i<20;i++){
			if(!SocketHelper.hasNet)return false;
			if(!sendMessage(new String[]{"login","david942j","Hue Nguyen"}))return false;
			String cmd = getMessage();
			if(cmd.equals("login")){
				if(cmdMgr.parseCmd("login"))return true;
				else Util.errorReport("why login fail QQ?");
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	static private boolean sendMessage(String[] messages){
    	return SocketHelper.sendMessage(messages);
	}
	static private String getMessage(){
		return SocketHelper.getMessage();
	}
}
