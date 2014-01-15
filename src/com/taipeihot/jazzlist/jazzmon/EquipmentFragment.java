package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.adapter.EquipItemListAdapter;
import com.taipeihot.jazzlist.adapter.EquipListAdapter;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Equipment;

 @SuppressLint("NewApi")
public class EquipmentFragment extends Fragment {
	 EquipItemListAdapter equaipItemListAdapter;
	 ArrayList<Action> itemItems;
	 View view;
	 EquipListAdapter equipListAdapter;
	 ArrayList<Equipment> headEquips=Data.getHeadEquipments();
	 ArrayList<Equipment> handEquips=Data.getHandEquipments();
	 ArrayList<Equipment> feetEquips=Data.getFeetEquipments();
	 Equipment head;
	 Equipment hand;
	 Equipment feet;
	 ImageView headImg;
	 ImageView handImg;
	 ImageView feetImg;
	 private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_equipment, null);
    	headImg=(ImageView)view.findViewById(R.id.equip_head);
   	 	handImg=(ImageView)view.findViewById(R.id.equip_hand);
   	 	feetImg=(ImageView)view.findViewById(R.id.equip_feet);
   	 	context=this.getActivity();
    	init();
    	itemItems=Data.getItems();
    	
    	GridView itemGrid=(GridView)view.findViewById(R.id.equip_item_gridview);
    	/*equaipItemListAdapter=new EquipItemListAdapter(this.getActivity(),itemItems);
    	itemGrid.setAdapter(equaipItemListAdapter);*/
    	equipListAdapter=new EquipListAdapter(this,handEquips,"hand");
    	itemGrid.setAdapter(equipListAdapter);
    	
    	return view;
    }
    public void init()
    {
    	head=Data.getHeadWear();
    	hand=Data.getHandWear();
    	feet=Data.getFeetWear();
    	String type="equip_";
        if(head!=null)setImage(headImg,type+head.getImageId());
        else setImage(headImg,"defualt");
    	if(hand!=null)setImage(handImg,type+hand.getImageId());
    	else setImage(handImg,"defualt");
    	if(feet!=null)setImage(feetImg,type+feet.getImageId());
    	else setImage(feetImg,"defualt");
    	TextView skillPoint=(TextView)view.findViewById(R.id.equipPointNum);
    	skillPoint.setText(Data.getSkillPoint()+"");
    	TextView moneyText=(TextView)view.findViewById(R.id.equip_money_num);
    	moneyText.setText(Data.getMoney()+"");
    	ProgressBar expPro=(ProgressBar)view.findViewById(R.id.equip_exp_bar);
    	expPro.setProgress(Data.getExp());
    }
    private void setImage(ImageView img,String photoName)
    {
    	Util.errorReport(photoName);
    	img.setImageResource(
    		getResources()
				.getIdentifier(
					photoName,
					"drawable", context.getPackageName()));
    }
    
}
