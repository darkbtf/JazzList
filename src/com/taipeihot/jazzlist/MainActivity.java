package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.taipeihot.jazzlist.model.Achievement;
import com.taipeihot.jazzlist.model.AchievementType;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.CategoryTable;


public class MainActivity extends BaseActivity {

	ArrayList<Todo> todoList;
	Fragment contentFragment;
	
    public MainActivity() {
        super(R.string.left_and_right);
    }
    
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	if (Data.getCategories().size() < 1) {
        	Data.addCategory("Default");
        }
        super.onCreate(savedInstanceState);
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);

        setContentView(R.layout.content_frame);

        contentFragment = new CategoryFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, contentFragment)
        .commit();

        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, new FriendListFragment())
        .commit();

        //getSlidingMenu().showMenu();
        //getSlidingMenu().showSecondaryMenu();
        //getSlidingMenu().toggle();
    	setTitle(Data.getCategories().get(0).getTitle());
    	initData();
    }

    public void changeCategory(long categoryId) {
    	((CategoryFragment) contentFragment).changeCategory(categoryId);
    	getSlidingMenu().showContent();
    }
    
    private void initData(){
    	Data.achiv_sp = getSharedPreferences("achivement_parameter",MODE_PRIVATE);
    	for(AchievementType a:AchievementType.values())
			if(Data.getAchievementParameter(a)==-1)
				Data.setAchievementParameter(a,0);
    }
    
}
