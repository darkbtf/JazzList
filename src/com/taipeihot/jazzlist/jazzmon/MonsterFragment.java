package com.taipeihot.jazzlist.jazzmon;

import com.taipeihot.jazzlist.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonsterFragment extends Fragment {
	View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_monster, null);
        FragmentTabHost tabHost = (FragmentTabHost)view.findViewById(android.R.id.tabhost);

        tabHost.setup(getActivity(), getActivity().getSupportFragmentManager(), R.id.realtabcontent);

        //1
        tabHost.addTab(tabHost.newTabSpec("Equipments")
                              .setIndicator("Equipments"), 
                       EquipmentFragment.class, 
                       null);
        //2
        tabHost.addTab(tabHost.newTabSpec("Skills")
                              .setIndicator("Skills"), 
                       SkillFragment.class, 
                       null);
        //3
        tabHost.addTab(tabHost.newTabSpec("Fight!")
                              .setIndicator("Fight!"), 
                       FightFragment.class, 
                       null);
        return view;
    }

}
