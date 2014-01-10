package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.taipeihot.jazzlist.model.AchievementType;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;


public class MainActivity extends BaseActivity {

	ArrayList<Todo> todoList;
	Fragment contentFragment;
	Fragment settingFragment;
	Fragment friendFragment;
	Fragment menuFragment;
	
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

        setContentView(R.layout.content_frame);

        menuFragment = new MenuFragment();
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, menuFragment).commit();
        
        contentFragment = new CategoryFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, contentFragment)
        .commit();

        friendFragment = new FriendListFragment();
        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, friendFragment)
        .commit();

        //getSlidingMenu().showMenu();
        //getSlidingMenu().showSecondaryMenu();
        //getSlidingMenu().toggle();
        getSlidingMenu().setOnClosedListener(new OnClosedListener(){

			@Override
			public void onClosed() {
				toFriendList();
			}
        	
        });
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
    	Data.character_info_sp = getSharedPreferences("character_information",MODE_PRIVATE);
    	Data.initCharacterInfo();
    }

    public void toSetting(long todoId) {
    	//System.out.println("meowmeowjiao");
    	Bundle args = new Bundle();
        args.putLong("todoId", todoId);
        settingFragment = new SettingFragment();
        settingFragment.setArguments(args);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, settingFragment)
        .commit();    
        getSlidingMenu().showSecondaryMenu();
        //((SettingFragment) settingFragment).setTodo(todoId);
    }

    
    public void toFriendList() {
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, friendFragment)
        .commit();    	
    }
    
}
