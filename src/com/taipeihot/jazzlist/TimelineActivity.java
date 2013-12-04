package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.adapter.StatusListAdapter;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ExpandableListView;

public class TimelineActivity extends Activity {

	ExpandableListView statusListView;
	StatusListAdapter statusListAdapter;
	ArrayList<Status> statusList;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Timeline");
        setContentView(R.layout.activity_timeline);
        statusListView = (ExpandableListView) findViewById(R.id.status_list);
        statusList = Data.getStatus();
        Data.updateStatus();
        statusListAdapter = new StatusListAdapter(this, statusList);
        statusListView.setAdapter(statusListAdapter);
     
        //
        // Set expand
        Button updateButton = (Button) findViewById(R.id.update_status_btn);
        updateButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Data.updateStatus();
				statusListAdapter.notifyDataSetChanged();
			}
        	
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.timeline, menu);
        return true;
    }

}
