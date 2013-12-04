package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;

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
import com.taipeihot.jazzlist.model.Category;
 
public class AchievementListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<Achievement> achivItems;
     
    public AchievementListAdapter(Context context, ArrayList<Achievement> achivItems){
        this.context = context;
        this.achivItems = achivItems;
    }
 
    @Override
    public int getCount() {
        return achivItems.size();
    }
 
    @Override
    public Object getItem(int position) {
    	Util.errorReport(position+"");
        return achivItems.get(position);
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
            convertView = mInflater.inflate(R.layout.achiv_grid_item, null);
        }
        final Achievement achievement=(Achievement)getItem(position);
        ImageView achiveImage = (ImageView) convertView.findViewById(R.id.achive_image);
        TextView achivTitle = (TextView) convertView.findViewById(R.id.achive_title);

        achivTitle.setText(achievement.getTitle());
        achiveImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
        if(achievement.done()){  
        	int photoNum=achievement.getIcon();

    		achiveImage.setBackgroundResource(
    				context.getResources()
    				.getIdentifier(
    						"achiv_"+Integer
    						.toString(photoNum),
    						"drawable", context.getPackageName()));
    		achiveImage.setOnClickListener(new OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				Toast.makeText(context,achievement.getDescription() , Toast.LENGTH_SHORT).show();
    			}
    		});
        	
        }
        else {
        	achiveImage.setBackgroundResource(R.drawable.achiv_default);
        }
        // displaying count
        // check whether it set visible or not
        
         
        return convertView;
    }
 
}