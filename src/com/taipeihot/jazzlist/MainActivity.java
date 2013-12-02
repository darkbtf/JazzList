package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.taipeihot.jazzlist.model.Todo;


public class MainActivity extends BaseActivity {

	ArrayList<Todo> todoList;
	
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
        Fragment fragment = null;
        fragment = new CategoryFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, fragment)
        .commit();
        System.out.println("check point 2: ");

        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, new FriendListFragment())
        .commit();

        System.out.println("check point 3: ");
    }

    void changeCategory(int Categoryid) {
    	
    }
}
