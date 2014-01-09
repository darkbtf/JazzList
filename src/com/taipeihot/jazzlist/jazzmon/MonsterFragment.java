package com.taipeihot.jazzlist.jazzmon;

import com.taipeihot.jazzlist.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MonsterFragment extends Fragment {
	View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_monster, null);
        return view;
    }

}
