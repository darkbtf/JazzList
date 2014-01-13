package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.taipeihot.jazzlist.MainActivity;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.adapter.ItemListAdapter;
import com.taipeihot.jazzlist.adapter.ActionListAdapter;
import com.taipeihot.jazzlist.model.Action;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.fight.FightData;
import com.taipeihot.jazzlist.fight.Player;
import com.taipeihot.jazzlist.Util;

public class FightActivity extends Activity {
	ProgressBar myHpBar;
	ProgressBar myMpBar;
	ProgressBar oppoHpBar;
	ProgressBar oppoMpBar;
	ActionListAdapter actionListAdapter;
	ArrayList<Action> actionItems=new ArrayList<Action>();
	ItemListAdapter itemListAdapter;
	ArrayList<Action> itemItems=new ArrayList<Action>();
	Player me;
	Player opponent;
	Thread fightThread;
	private String menuName[]={"Skills","Items"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fight);
		actionItems=Data.getAvailableSkills();
		itemItems=Data.getItems();
		
		ImageView img = (ImageView)findViewById(R.id.fight_enemy_animation);
		img.setBackgroundResource(R.drawable.fire0);

		 // Get the background, which has been compiled to an AnimationDrawable object.
		AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

		 // Start the animation (looped playback by default).
		frameAnimation.start();
		ImageView img2 = (ImageView)findViewById(R.id.fight_self_animation);
		img2.setBackgroundResource(R.drawable.water2);

		 // Get the background, which has been compiled to an AnimationDrawable object.
		AnimationDrawable frameAnimation2 = (AnimationDrawable) img2.getBackground();

		 // Start the animation (looped playback by default).
		frameAnimation2.start();
		
		final GridView skillList=(GridView)findViewById(R.id.skill_gridview);
		actionListAdapter = new ActionListAdapter(this, actionItems);
		skillList.setAdapter(actionListAdapter);
		itemListAdapter = new ItemListAdapter(this, itemItems);
		
		
		ListView menuList=(ListView)findViewById(R.id.fight_menu);
		menuList.setAdapter(new ArrayAdapter<String>(
				this,android.R.layout.simple_list_item_1 , menuName));
		menuList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				if(position==0)
				{
					skillList.setAdapter(actionListAdapter);
				}
				else if(position==1)
				{
					skillList.setAdapter(itemListAdapter);
				}
			}
		});

		oppoHpBar = ((ProgressBar) findViewById(R.id.fight_enemy_hp));
		oppoMpBar = ((ProgressBar) findViewById(R.id.fight_enemy_mp));
		
		fightThread = new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {

					//Util.errorReport("gg");
					if (FightData.isStarted() || FightData.isUpdated()) {
						Util.errorReport("fuck");

						me = FightData.getMe();
						opponent = FightData.getOpponent();
						refreshBars();
						FightData.setIdle();
					} else if (FightData.isEnded()) {
						FightData.reset();
						Intent intent = new Intent(FightActivity.this, MainActivity.class);
						startActivity(intent);
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		fightThread.start();
		
		
	}
	
	public void refreshBars() {

		/*runOnUiThread*/
		findViewById(android.R.id.content).post(new Runnable() {
			public void run() {
				Util.errorReport("QQQQQQ");
				Util.errorReport(((ProgressBar) findViewById(R.id.fight_enemy_hp)).getMax() + "");
				oppoHpBar.setMax(opponent.getHp());
				Util.errorReport("QQQQQQ");
				oppoHpBar.setProgress(opponent.getHp());
				Util.errorReport("QQQQQQ");
				oppoMpBar.setMax(opponent.getMp());
				oppoMpBar.setProgress(opponent.getMp());
				((ProgressBar) findViewById(R.id.fight_self_hp)).setMax(me.getHp());
				((ProgressBar) findViewById(R.id.fight_self_hp)).setProgress(me.getHp());
				((ProgressBar) findViewById(R.id.fight_self_mp)).setMax(me.getMp());
				((ProgressBar) findViewById(R.id.fight_self_mp)).setProgress(me.getMp());
				//((ProgressBar) findViewById(R.id.fight_self_mp)).
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fight, menu);
		return true;
	}

}
