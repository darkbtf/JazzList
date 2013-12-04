package com.taipeihot.jazzlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar.OnNavigationListener;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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
        return view;
    }


}
