package com.taipeihot.jazzlist;


import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.TodoTable;

@SuppressLint("NewApi") public class MainActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<Category> categoryItems;
    private CategoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //mDrawerMenu = (RelativeLayout) findViewById(R.id.list_slidermenu);
        System.out.println("check point 0: layout done");

        /*
         * TODO: Load Category Items
         *       now simply use sample data
         */
        categoryItems = new ArrayList<Category>();
        categoryItems.add(new Category("Jizz", navMenuIcons.getResourceId(0, -1)));
        categoryItems.add(new Category("In", navMenuIcons.getResourceId(1, -1)));
        categoryItems.add(new Category("My", navMenuIcons.getResourceId(2, -1)));
        categoryItems.add(new Category("Pants", navMenuIcons.getResourceId(3, -1)));
        System.out.println("check point 1: category done");

        // Recycle the typed array
        navMenuIcons.recycle();
        System.out.println("check point 1.25: recycle done");
        System.out.println(findViewById(R.id.list_slidermenu));
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        if (mDrawerList == null) {
            System.out.println("gg!");
        } else {
            System.out.println("yeah!");
        }
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        System.out.println("check point 1.5: listener done");

        // setting the nav drawer list adapter
        adapter = new CategoryListAdapter(getApplicationContext(),
                categoryItems);
        mDrawerList.setAdapter(adapter);
        System.out.println("check point 2: adapter done");

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
                ){
            @Override
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        System.out.println("toggle done " + mDrawerLayout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
        System.out.println("QAQ");
    }
    private class SlideMenuClickListener implements
    ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
        case R.id.action_settings:
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("taipeihot", "main resumed!");
    }
    /*
    private boolean isNetworkAvailable(){
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
    
    static private int updatePeriod = 20000; 
    private void socket_connect(){
    	Thread thread = new Thread(new Runnable(){
    		@Override
    		public void run(){
    			while(true){
    				if(isNetworkAvailable())
    					UpdateHelper.start();
    				try {
						Thread.sleep(updatePeriod);// what if the last update not finished yet?
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	});
    	thread.start();
        
    }*/
 
    @Override
    protected void onStop() {
    	SocketHelper.close();
        super.onStop();
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        fragment = new CategoryFragment();

        if (fragment != null) {
            Log.e("taipeihot", navMenuTitles[position]);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }
}
