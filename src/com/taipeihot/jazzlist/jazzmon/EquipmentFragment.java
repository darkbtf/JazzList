package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.adapter.ItemListAdapter;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.model.Data;

 @SuppressLint("NewApi")
public class EquipmentFragment extends Fragment {
	 ItemListAdapter itemListAdapter;
	 ArrayList<Action> itemItems;
	View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_equipment, null);
    	itemItems=Data.getItems();
    	GridView itemGrid=(GridView)view.findViewById(R.id.equip_item_gridview);
    	itemListAdapter=new ItemListAdapter(this.getActivity(),itemItems);
    	itemGrid.setAdapter(itemListAdapter);
    	return view;
    }
    
}
