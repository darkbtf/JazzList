package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.adapter.TreeListAdapter;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.model.Data;

 @SuppressLint("NewApi")
public class SkillFragment extends Fragment {

	View view;
	ArrayList<Action> fireSkills=Data.getFireSkills();
	ArrayList<Action> waterSkills=Data.getWaterSkills();
	ArrayList<Action> thunderSkills=Data.getThunderSkills();
	TreeListAdapter treeListAdapter;
	GridView skillTree;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_skill, null);
    	skillTree=(GridView)view.findViewById(R.id.tree_skill_tree);
    	treeListAdapter=new TreeListAdapter(this.getActivity(),fireSkills);
    	skillTree.setAdapter(treeListAdapter);
    	
    	ImageButton fireButton = (ImageButton) view.findViewById(R.id.fireSkillBtn);
    	fireButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				switchSkillSet(0);
			}
    	});
    	ImageButton waterButton = (ImageButton) view.findViewById(R.id.waterSkillBtn);
    	waterButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				switchSkillSet(1);
			}
    	});
    	ImageButton thunderButton = (ImageButton) view.findViewById(R.id.thunderSkillBtn);
    	thunderButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				switchSkillSet(2);
			}
    	});
    	
    	return view;
    }
    
    public void switchSkillSet(int skillSetId) {
    	LinearLayout layout = ((LinearLayout) view.findViewById(R.id.layout_skilltree));
    	
    	if (skillSetId == 0) {
    		layout.setBackground(getResources().getDrawable(R.drawable.firetree));
    		treeListAdapter=new TreeListAdapter(this.getActivity(),fireSkills);
    		skillTree.setAdapter(treeListAdapter);
    	} else if (skillSetId == 1) {
    		layout.setBackground(getResources().getDrawable(R.drawable.watertree));	
    		treeListAdapter=new TreeListAdapter(this.getActivity(),waterSkills);
        	skillTree.setAdapter(treeListAdapter);
    	} else {
    		layout.setBackground(getResources().getDrawable(R.drawable.thundertree));
    		treeListAdapter=new TreeListAdapter(this.getActivity(),thunderSkills);
        	skillTree.setAdapter(treeListAdapter);
    	}
    }
    
}
