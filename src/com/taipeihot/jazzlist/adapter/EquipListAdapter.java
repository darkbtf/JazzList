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

import com.taipeihot.jazzlist.CommunicateHelper;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.fight.FightData;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.model.Equipment;
 
public class EquipListAdapter extends BaseAdapter {
     
    private Context context;
    private ArrayList<Equipment> equipmentItems;
    public EquipListAdapter(Context context, ArrayList<Equipment> equipmentItems){
        this.context = context;
        this.equipmentItems = equipmentItems;
    }
 
    @Override
    public int getCount() {
        return equipmentItems.size();
    }
 
    @Override
    public Object getItem(int position) {
    	Util.errorReport(position+"");
        return equipmentItems.get(position);
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
            convertView = mInflater.inflate(R.layout.equip_grid_item, null);
        }
        final Equipment equipment=(Equipment)getItem(position);
        ImageView actionImage = (ImageView) convertView.findViewById(R.id.equip_image);

        int photoNum=equipment.getImageId();
        String type="equip_";
        if(!equipment.exist())type+="h_";
    	
        actionImage.setImageResource(
    			context.getResources()
    				.getIdentifier(
    					type+Integer
    					.toString(photoNum),
    					"drawable", context.getPackageName()));
    	
    	actionImage.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(context,equipment.getDescription() , Toast.LENGTH_SHORT).show();
				if(!equipment.exist())equipment.buy();
				else
				{
					equipment.wear();
				}
				
				notifyDataSetChanged();
			}
		});
        	
        
        // displaying count
        // check whether it set visible or not
        
         
        return convertView;
    }
 
}