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
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.User;
 
public class CategoryListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<Category> categoryItems;
     
    public CategoryListAdapter(Context context, ArrayList<Category> categoryItems){
        this.context = context;
        this.categoryItems = categoryItems;
    }
 
    @Override
    public int getCount() {
        return categoryItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return categoryItems.get(position);
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
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }
          
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        TextView txtCount = (TextView) convertView.findViewById(R.id.counter);
          
        imgIcon.setImageResource(categoryItems.get(position).getIcon());        
        txtTitle.setText(categoryItems.get(position).getTitle());
         
        // displaying count
        // check whether it set visible or not
        if(categoryItems.get(position).getCount() != 0){
            txtCount.setText(categoryItems.get(position).getCount());
        }else{
            // hide the counter view
            txtCount.setVisibility(View.GONE);
        }
         
        return convertView;
    }
 
}