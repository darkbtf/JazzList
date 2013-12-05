package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import com.haarman.listviewanimations.swinginadapters.prepared.SwingBottomInAnimationAdapter;
import com.taipeihot.jazzlist.adapter.AchievementListAdapter;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.Data;

public class ProfileActivity extends Activity {
	AchievementListAdapter achievementListAdapter;
	ArrayList<Achievement> achievItems=new ArrayList<Achievement>();
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		achievItems=Data.getAchievements();
		
        
		setContentView(R.layout.activity_achievement);
		GridView achievGridView=(GridView)findViewById(R.id.achive_grid_view);
		achievementListAdapter=new AchievementListAdapter(this, achievItems);
		SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(achievementListAdapter);
        swingBottomInAnimationAdapter.setAbsListView(achievGridView);
        swingBottomInAnimationAdapter.setInitialDelayMillis(300);
        achievGridView.setAdapter(swingBottomInAnimationAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.achievement, menu);
		return true;
	}

}
