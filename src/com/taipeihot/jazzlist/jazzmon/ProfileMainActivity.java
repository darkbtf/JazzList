package com.taipeihot.jazzlist.jazzmon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.taipeihot.jazzlist.BaseActivity;
import com.taipeihot.jazzlist.CategoryFragment;
import com.taipeihot.jazzlist.MainActivity;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.fight.FightData;

public class ProfileMainActivity extends BaseActivity  {
	
	Fragment profileFragment;
	Fragment achievementFragment;
	Fragment monsterFragment;
	Thread waitFighting;
	
    public ProfileMainActivity() {
        super(R.string.left_and_right);
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.content_frame);

        achievementFragment = new AchievementFragment();
        setBehindContentView(R.layout.menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, achievementFragment).commit();
        
        profileFragment = new ProfileFragment();
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.content_frame, profileFragment)
        .commit();

        monsterFragment = new MonsterFragment();
        getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
        getSlidingMenu().setSecondaryShadowDrawable(R.drawable.shadowright);
        getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.menu_frame_two, monsterFragment)
        .commit();
		
	}
	
    public void waitFightingInit() {
    	waitFighting = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!FightData.isStarted()) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				Intent intent = new Intent(ProfileMainActivity.this, FightActivity.class);
				startActivity(intent);
			}
    		
    	});
    }

}
