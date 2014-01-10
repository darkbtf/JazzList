package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar.OnNavigationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.adapter.AchievementListAdapter;
import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;

@SuppressLint("NewApi")
public class AchievementFragment extends Fragment implements OnNavigationListener{

	View view;
	AchievementListAdapter achievementListAdapter;
	ArrayList<Achievement> achievItems=new ArrayList<Achievement>();
	
	
    CategoryListAdapter categoryListAdapter;
    int toDeleteItemId;
    ArrayList<Category> categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_achievement, null);
        
		achievItems=Data.getAchievements();
	
		Util.errorReport("meow " + achievItems.size());
		
		GridView achievGridView=(GridView)view.findViewById(R.id.achive_grid_view);
		achievementListAdapter = new AchievementListAdapter(this.getActivity(), achievItems);
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(achievementListAdapter);
        swingBottomInAnimationAdapter.setAbsListView(achievGridView);
        swingBottomInAnimationAdapter.setInitialDelayMillis(300);
        achievGridView.setAdapter(swingBottomInAnimationAdapter);
		return view;
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
