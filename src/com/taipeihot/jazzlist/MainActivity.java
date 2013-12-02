package com.taipeihot.jazzlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


public class MainActivity extends BaseActivity {

    public MainActivity() {
        super(R.string.left_and_right);
    }

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("check point 0: ");
        super.onCreate(savedInstanceState);
        getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
        getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        setContentView(R.layout.content_frame);
        //ListFragment

        System.out.println("check point 1: ");
        //getSupportFragmentManager()
        //.beginTransaction()
        //.replace(R.id.content_frame, new SampleListFragment())
        // .commit();
        Fragment fragment = null;
        //        fragmentTransaction.replace(R.id.content_frame, fragment);

        //setContentView(R.layout.content_frame);
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

}
