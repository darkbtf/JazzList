package com.taipeihot.jazzlist;

import android.os.Bundle;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.taipeihot.jazzlist.model.Data;

public class FacebookHelper {
	
	static boolean postCombat(int user_id) {
		Bundle params = new Bundle();
		Session session = Session.getActiveSession();
		GraphUser user=Data.getUser();
		params.putString("message", Dat)
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
