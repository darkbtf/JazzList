package com.taipeihot.jazzlist;

import java.util.Date;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ActionBar.OnNavigationListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.TodoTable;

@SuppressLint("NewApi")
public class SettingFragment extends Fragment {

	View view;
    Todo todo;
    AlertDialog setTimeDialog;
	Date date;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	System.out.println("view created!");
        view= inflater.inflate(R.layout.fragment_setting, null);
        long todoId = getArguments().getLong("todoId");
    	todo = TodoTable.find(todoId);
    	if (view == null) System.out.println("gg"); 
        final EditText todoTitle = (EditText) view.findViewById(R.id.todo_title);
        todoTitle.setText(todo.getTitle());
        todoTitle.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				todo.setTitle(todoTitle.getText().toString());
				todo.save();
				((CategoryFragment) ((MainActivity) getActivity()).contentFragment).reload();
			}
        	
        });
        final EditText todoDescription = (EditText) view.findViewById(R.id.todo_description);
        todoDescription.setText(todo.getDescription());
        todoDescription.setOnFocusChangeListener(new OnFocusChangeListener(){

			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				System.out.println(todoDescription.getText().toString());
				todo.setDescription(todoDescription.getText().toString());
				todo.save();				
			}
        	
        });

    	//final Date d=new Date(0);
        final View alertView = inflater.inflate(R.layout.dialog_time, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(alertView)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	TimePicker tp = (TimePicker)setTimeDialog.findViewById(R.id.setTime_time);
            	int hour=tp.getCurrentHour();
            	int min=tp.getCurrentMinute();
            	DatePicker dp=(DatePicker)setTimeDialog.findViewById(R.id.setTime_date);
            	int day=dp.getDayOfMonth();
            	
            	Util.errorReport(""+hour+" "+min+" "+day);
            	Date d=new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth());
            	d.setHours(tp.getCurrentHour().intValue());
            	d.setMinutes(tp.getCurrentMinute().intValue());
            	((Button)view.findViewById(R.id.set_time_btn)).setText(Util.dateLongToString(d.getTime()));
            	todo.setDeadline(d.getTime());
            	todo.save();
				((CategoryFragment) ((MainActivity) getActivity()).contentFragment).reload();
            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        setTimeDialog = builder.create();
        
        Button setTimeButton = (Button) view.findViewById(R.id.set_time_btn);
        setTimeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				setTimeDialog.show();				
			}
        	
        });
        
        final CheckBox publicCheckBox = (CheckBox) view.findViewById(R.id.set_public_checkbox);
        publicCheckBox.setChecked(todo.isPublic());
        publicCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				System.out.println("jizzjizz");
				todo.setPublic(publicCheckBox.isChecked());
				todo.save();
			}
        	
        });
        return view;
    }


}
