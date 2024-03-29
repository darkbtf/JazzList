package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;
import com.taipeihot.jazzlist.fight.FightData;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.jazzmon.FightActivity;
 
public class ActionListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<Action> actionItems;
     
    public ActionListAdapter(Context context, ArrayList<Action> actionItems){
        this.context = context;
        this.actionItems = actionItems;
    }
 
    @Override
    public int getCount() {
        return actionItems.size();
    }
 
    @Override
    public Object getItem(int position) {
    	Util.errorReport(position+"");
        return actionItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.action_grid_item, null);
        }
        final Action action=(Action)getItem(position);
        ImageView actionImage = (ImageView) convertView.findViewById(R.id.actionImage);
        actionImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});

        int photoNum=action.getImageId();
        String type="skill_";
        final int res = context.getResources()
				.getIdentifier(
						type+Integer
						.toString(photoNum),
						"drawable", context.getPackageName());
        if(!action.exist())type+="h_";
    		actionImage.setImageResource(res);
    		actionImage.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				((FightActivity) context).setAction(action, res);
    			}
    		});
        Util.errorReport(action.getDescription()+"QQQQQQQ");
        
        // displaying count
        // check whether it set visible or not
        
         
        return convertView;
    }
 
}