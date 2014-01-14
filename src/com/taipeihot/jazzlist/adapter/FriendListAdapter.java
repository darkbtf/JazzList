package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;
import com.taipeihot.jazzlist.Util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.TimelineActivity;
import com.taipeihot.jazzlist.jazzmon.FightActivity;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;
 
public class FriendListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<User> userItems;
    private boolean fightOnly;
	AlertDialog.Builder builder;
    private int toFightId;
     
    public FriendListAdapter(Context context, ArrayList<User> categoryItems, boolean fightOnly){
        this.context = context;
        this.userItems = categoryItems;
        this.fightOnly = fightOnly;
        builder = new AlertDialog.Builder(context);
    	builder
    	.setPositiveButton("Fight!",  new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {
    			//Util.errorReport(userItems.get(toFightId).getRealId() + "");
    			CommunicateHelper.inviteFight(userItems.get(toFightId).getRealId());
    		}
    		
    	})
    	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {

    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {
    			
    		}
    		
    	}); 
    	
    }
 
    @Override
    public int getCount() {
        return userItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return userItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public void showInviteBox() {
 	
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.friend_list_item, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.friend_user_icon);
        TextView textTitle = (TextView) convertView.findViewById(R.id.friend_user_name);
        User user=userItems.get(position);
        textTitle.setText(user.getNickname());
        int photoNum=user.photoNumber()%15+1;
        String s="user_photo_"+Integer.toString(photoNum);
		int resid=context.getResources().getIdentifier(s, "drawable", context.getPackageName());
		imgIcon.setBackgroundResource(resid);
         
        // displaying count
        // check whether it set visible or not

		
		ImageButton statusButton = (ImageButton) convertView.findViewById(R.id.friend_status_button);
		statusButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, TimelineActivity.class);
				intent.putExtra("userId", userItems.get(position).photoNumber());
				context.startActivity(intent);
			}
		});
		if (fightOnly) {
			statusButton.setVisibility(View.GONE);
		}
		
		ImageButton fightButton = (ImageButton) convertView.findViewById(R.id.friend_fight_button);
		fightButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				builder.setTitle("Fight?");
				toFightId = position;
				
				AlertDialog dialog = builder.create();
				dialog.show();
			}
		});
		
        return convertView;
    }
 
}