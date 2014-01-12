package com.taipeihot.jazzlist.jazzmon;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

import com.taipeihot.jazzlist.R;

public class FightActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fight);
		ImageView img = (ImageView)findViewById(R.id.fight_enemy_animation);
		img.setBackgroundResource(R.drawable.water2);

		 // Get the background, which has been compiled to an AnimationDrawable object.
		AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();

		 // Start the animation (looped playback by default).
		frameAnimation.start();
		ImageView img2 = (ImageView)findViewById(R.id.fight_self_animation);
		img2.setBackgroundResource(R.drawable.fire2);

		 // Get the background, which has been compiled to an AnimationDrawable object.
		AnimationDrawable frameAnimation2 = (AnimationDrawable) img2.getBackground();

		 // Start the animation (looped playback by default).
		frameAnimation2.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fight, menu);
		return true;
	}

}
