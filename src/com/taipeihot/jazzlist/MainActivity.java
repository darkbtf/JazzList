package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.CategoryTable;


public class MainActivity extends BaseActivity {

	ArrayList<Todo> todoList;
	Fragment contentFragment;
	
    public MainActivity() {
        super(R.string.left_and_right);
    }

    private void loadData() {
    	// TODO: load todos & categories from the database
    }
    
    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("check point 0: ");
        super.onCreate(savedInstanceState);
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        setContentView(R.layout.content_frame);

        System.out.println("check point 1: ");
        contentFragment = new CategoryFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, contentFragment)
        .commit();
        System.out.println("check point 2: ");

        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, new FriendListFragment())
        .commit();

        //getSlidingMenu().showMenu();
        //getSlidingMenu().showSecondaryMenu();
        //getSlidingMenu().toggle();
        System.out.println("check point 3: ");
    }

    public void changeCategory(long categoryId) {
    	((CategoryFragment) contentFragment).changeCategory(categoryId);
    	getSlidingMenu().showContent();
    }
    
}
