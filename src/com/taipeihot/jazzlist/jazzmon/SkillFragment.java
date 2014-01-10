package com.taipeihot.jazzlist.jazzmon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.taipeihot.jazzlist.R;

 @SuppressLint("NewApi")
public class SkillFragment extends Fragment {

	View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_skill, null);
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
    	} else if (skillSetId == 1) {
    		layout.setBackground(getResources().getDrawable(R.drawable.watertree));	
    	} else {
    		layout.setBackground(getResources().getDrawable(R.drawable.thundertree));
    	}
    }
    
}
