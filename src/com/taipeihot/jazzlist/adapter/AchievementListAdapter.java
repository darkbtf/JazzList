package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.User;
 
public class AchievementListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<Achievement> achivItems;
     
    public AchievementListAdapter(Context context, ArrayList<Category> categoryItems){
        this.context = context;
        this.achivItems = categoryItems;
    }
 
    @Override
    public int getCount() {
        return achivItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
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
        Achievement achievement=(Achievement)getItem(position);
        ImageView achiveImage = (ImageView) convertView.findViewById(R.id.achive_image);
        TextView achivTitle = (TextView) convertView.findViewById(R.id.achive_title);

        if(achievement.done()){  
        	achiveImage.setImageResource(achivItems.get(position).getIcon());        
        	achivTitle.setText(achivItems.get(position).getTitle());
        }
        // displaying count
        // check whether it set visible or not
        
         
        return convertView;
    }
 
}