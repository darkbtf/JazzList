package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.taipeihot.jazzlist.fight.ActionManager;
import com.taipeihot.jazzlist.CommunicateHelper;
import android.widget.TextView;

public class FightActivity extends Activity {
	
	// in ms
	final int ANIMATION_INTERVAL = 2800;
	
	static ActionManager actionManager = new ActionManager();
	ActionListAdapter actionListAdapter;
	ArrayList<Action> actionItems=new ArrayList<Action>();
	ItemListAdapter itemListAdapter;
	ArrayList<Action> itemItems=new ArrayList<Action>();
	Player me;
	Player opponent;
	Thread fightThread;
	private int actionToUse = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fight);
		actionItems=Data.getAvailableSkills();
		itemItems=Data.getItems();
		
		final ImageView opponentImage = (ImageView)findViewById(R.id.fight_enemy_animation);

		// Start the animation (looped playback by default).
		final ImageView selfImage = (ImageView)findViewById(R.id.fight_self_animation);

		
		final GridView skillList=(GridView)findViewById(R.id.skill_gridview);
		actionListAdapter = new ActionListAdapter(this, actionItems);
		skillList.setAdapter(actionListAdapter);
		itemListAdapter = new ItemListAdapter(this, itemItems);
		
		TextView skillTab = (TextView) findViewById(R.id.skills_tab);
		skillTab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				skillList.setAdapter(actionListAdapter);
			}
		});

		TextView itemTab = (TextView) findViewById(R.id.items_tab);
		itemTab.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				skillList.setAdapter(itemListAdapter);
			}
		});
		
		Button actionButton = (Button) findViewById(R.id.action_button);
		actionButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CommunicateHelper.actionFight(actionToUse);
				FightData.setDone();
			}
			
		});
	
		fightThread = new Thread(new Runnable(){

			@Override
			public void run() {
				while (true) {
					Util.errorReport("banana");					
					if (FightData.isPrepared()) {
						me = FightData.getMe();
						opponent = FightData.getOpponent();
						FightData.setIdle();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								((ProgressBar) findViewById(R.id.fight_enemy_hp)).setMax(opponent.getHp());
								((ProgressBar) findViewById(R.id.fight_enemy_mp)).setMax(opponent.getMp());
								((ProgressBar) findViewById(R.id.fight_self_mp)).setMax(me.getMp());
								((ProgressBar) findViewById(R.id.fight_self_hp)).setMax(me.getHp());
								((ProgressBar) findViewById(R.id.fight_enemy_hp)).setProgress(opponent.getHp());
								((ProgressBar) findViewById(R.id.fight_self_hp)).setProgress(me.getHp());
								((ProgressBar) findViewById(R.id.fight_enemy_mp)).setProgress(opponent.getMp());
								((ProgressBar) findViewById(R.id.fight_self_mp)).setProgress(me.getMp());
							}
						});
					} else if (FightData.isUpdated()) {
						me = FightData.getMe();
						opponent = FightData.getOpponent();
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								((ProgressBar) findViewById(R.id.fight_enemy_hp)).setProgress(opponent.getHp());
								((ProgressBar) findViewById(R.id.fight_self_hp)).setProgress(me.getHp());
								((ProgressBar) findViewById(R.id.fight_enemy_mp)).setProgress(opponent.getMp());
								((ProgressBar) findViewById(R.id.fight_self_mp)).setProgress(me.getMp());
							}
						});
						final String selfOnselfAnime = actionManager.getSelfAnimation(me.getMove());
						final String selfOnOpponentAnime = actionManager.getOpponentAnimation(me.getMove());
						final String opponentOnSelfAnime = actionManager.getOpponentAnimation(opponent.getMove());
						final String opponentOnOpponentAnime = actionManager.getSelfAnimation(opponent.getMove());
						
						Runnable myMove = new Runnable() {
							@Override
							public void run() {
								if (selfOnselfAnime != null) {
									int res = getResources().getIdentifier(selfOnselfAnime, "drawable", getPackageName());
									selfImage.setBackgroundResource(res);
									AnimationDrawable selfAnimation = (AnimationDrawable) selfImage.getBackground();
									selfAnimation.stop();
									selfAnimation.start();
								}
								if (selfOnOpponentAnime != null) {
									int res = getResources().getIdentifier(selfOnOpponentAnime, "drawable", getPackageName());
									opponentImage.setBackgroundResource(res);
									final AnimationDrawable opponentAnimation = (AnimationDrawable) opponentImage.getBackground();
									opponentAnimation.stop();
									opponentAnimation.start();
									
								}
								TextView fightConsole = (TextView) findViewById(R.id.fight_console);
								fightConsole.setText(actionManager.getMessage(me, opponent, me.getMove()));
							}
						};
						

						Runnable opponentMove = new Runnable() {

							@Override
							public void run() {
								if (opponentOnSelfAnime != null) {
									int res = getResources().getIdentifier(opponentOnSelfAnime, "drawable", getPackageName());
									selfImage.setBackgroundResource(res);
									AnimationDrawable selfAnimation = (AnimationDrawable) selfImage.getBackground();
									selfAnimation.stop();
									selfAnimation.start();
								}
								if (opponentOnOpponentAnime != null) {
									int res = getResources().getIdentifier(opponentOnOpponentAnime, "drawable", getPackageName());
									opponentImage.setBackgroundResource(res);
									final AnimationDrawable opponentAnimation = (AnimationDrawable) opponentImage.getBackground();
									opponentAnimation.stop();
									opponentAnimation.start();
									
								}
								TextView fightConsole = (TextView) findViewById(R.id.fight_console);
								fightConsole.setText(actionManager.getMessage(opponent, me, opponent.getMove()));
							}
						};	
						
						if (FightData.getLastFirst() > 0) {
							runOnUiThread(myMove);
							try {
								Thread.sleep(ANIMATION_INTERVAL);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							runOnUiThread(opponentMove);
						} else {
							runOnUiThread(opponentMove);
							try {
								Thread.sleep(ANIMATION_INTERVAL);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							runOnUiThread(myMove);
						}
						
						if (FightData.getFirst() != 0) FightData.setIdle();
						else {
							CommunicateHelper.actionFight(0);
							FightData.setDone();
						}
						
					} else if (FightData.isEnded()) {
						FightData.reset();
						Intent intent = new Intent(FightActivity.this, MainActivity.class);
						startActivity(intent);
						break;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		});
	}
	
	public void showMessage(String msg) {
		TextView msgBox = (TextView) findViewById(R.id.fight_console);
		msgBox.setText(msg);
	}

    public void onResume() {
    	super.onResume();
    	fightThread.start();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fight, menu);
		return true;
	}

	public void setAction(Action action, int res) {
		ImageView actionImg = (ImageView) findViewById(R.id.action_img);
		actionImg.setImageResource(res);
		
		TextView actionTitle = (TextView) findViewById(R.id.action_title);
		actionTitle.setText(action.getName());
		
		TextView actionDescription = (TextView) findViewById(R.id.action_description);
		actionDescription.setText(action.getDescription());
		
		actionToUse = action.getObjectId();
		
		if (action.canUseInFight(FightData.getMe().getMp())) {
			Button fightButton = (Button) findViewById(R.id.action_button);
			fightButton.setEnabled(true);
		} else {
			Button fightButton = (Button) findViewById(R.id.action_button);
			fightButton.setEnabled(false);
			
		}
	}

}
