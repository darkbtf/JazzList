package com.taipeihot.jazzlist;

import android.os.Bundle;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;

public class FacebookHelper {
	
	static boolean postCombat(int user_id) {
		Bundle params = new Bundle();
		Session session = Session.getActiveSession();
		GraphUser user=Data.getUser();
		User fri=Data.getFriendByRealId(user_id);
		params.putString("message", user.getName()+" combat "+fri.getNickname()+" in JazzList!");
		params.putString("tags", fri.getFacebookId());
		params.putString("place", "611250202243422");
		post(params);
		return true;
	}
	static boolean post(Bundle params) {
		Session session = Session.getActiveSession();
		if(session==null)return false;
		Request request = new Request(session, "me/feed", params, HttpMethod.POST, new Request.Callback() {
			
			@Override
			public void onCompleted(Response response) {
				Util.errorReport("complete post!");
				// TODO Auto-generated method stub
				
			}
		});
		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();
		return true;
	}
}
