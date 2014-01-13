package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.taipeihot.jazzlist.fight.FightData;
import com.taipeihot.jazzlist.jazzmon.FightActivity;
import com.taipeihot.jazzlist.model.AchievementType;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;


public class MainActivity extends BaseActivity {

	ArrayList<Todo> todoList;
	Fragment contentFragment;
	Fragment settingFragment;
	Fragment friendFragment;
	Fragment menuFragment;
	Thread fightListener;
	
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

        getSlidingMenu().setOnClosedListener(new OnClosedListener(){

			@Override
			public void onClosed() {
				toFriendList();
			}
        	
        });
    	setTitle(Data.getCategories().get(0).getTitle());
    	initData();
    	fightListener = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					//Util.errorReport("guava");
					if (FightData.isStarted()) {
						FightData.setPrepared();
						Util.errorReport("started");
						Intent intent = new Intent(MainActivity.this, FightActivity.class);
						startActivity(intent);
					} else if (FightData.isInvited()) {
						Util.errorReport("invited");
						int userId = FightData.getInviterId();
						String nickname = FightData.getInviterNickname();
						showFightInvitationBox(userId, nickname);
						FightData.reset();
					}
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
    		
    	});
    	fightListener.start();
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
    	Bundle args = new Bundle();
        args.putLong("todoId", todoId);
        settingFragment = new SettingFragment();
        settingFragment.setArguments(args);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, settingFragment)
        .commit();    
        getSlidingMenu().showSecondaryMenu();
    }

    
    public void toFriendList() {
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, friendFragment)
        .commit();    	
    }
    
    public void showFightInvitationBox(final int userId, final String nickname) {
    	final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    	builder
    	.setPositiveButton("Fight!",  new DialogInterface.OnClickListener() {
    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {
    			Util.errorReport("wtf");
				CommunicateHelper.replyFight(userId, true);
    		}
    		
    	})
    	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {

    		@Override
    		public void onClick(DialogInterface arg0, int arg1) {
				CommunicateHelper.replyFight(userId, false);
    		}
    		
    	});
    	//Looper.prepare();
    	this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
		    	Dialog dialog = builder.create();
		    	dialog.setTitle(nickname + " wanna have a fight with you A__A");
		    	dialog.show();
			}
    	});
    }

    
}
