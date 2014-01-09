package com.taipeihot.jazzlist.jazzmon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.taipeihot.jazzlist.R;

 @SuppressLint("NewApi")
public class SkillFragment extends Fragment {

	View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_skill, null);
    	return view;
    }
    
}
