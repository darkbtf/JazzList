package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.adapter.StatusListAdapter;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;

public class TimelineActivity extends Activity {

	ExpandableListView statusListView;
	StatusListAdapter statusListAdapter;
	ArrayList<Status> statusList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        statusListView = (ExpandableListView) findViewById(R.id.status_list);
        statusList = Data.getStatus();
        statusListAdapter = new StatusListAdapter(this, statusList);
        statusListView.setAdapter(statusListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

}
