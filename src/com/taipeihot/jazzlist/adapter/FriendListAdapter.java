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
import com.taipeihot.jazzlist.model.User;
 
public class FriendListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<User> userItems;
     
    public FriendListAdapter(Context context, ArrayList<User> categoryItems){
        this.context = context;
        this.userItems = categoryItems;
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
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.friend_list_item, null);
        }
          
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.friend_user_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.friend_user_name);
       
        txtTitle.setText(userItems.get(position).getNickname());
         
        // displaying count
        // check whether it set visible or not

         
        return convertView;
    }
 
}