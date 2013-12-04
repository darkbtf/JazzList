package com.taipeihot.jazzlist;

import java.util.ArrayList;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.haarman.listviewanimations.itemmanipulation.OnDismissCallback;
import com.haarman.listviewanimations.itemmanipulation.SwipeDismissAdapter;
import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.taipeihot.jazzlist.adapter.TodoListAdapter2;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.CategoryTable;

 @SuppressLint("NewApi")
public class CategoryFragment extends Fragment implements OnDismissCallback{

	View view;
	ArrayList<Todo> todoList=new ArrayList<Todo>();
	public TodoListAdapter2 todoListAdapter2;
	Category currentCategory;
	ListView todoListView;
	AlertDialog setTimeDialog;
	AnimationAdapter animAdapter;
	SwipeDismissAdapter adapter;
	Date d=new Date(0);
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_category, null);
    	currentCategory = Data.getCategories().get(0);
    	
    	todoList = currentCategory.getTodos();
    	todoListAdapter2 = new TodoListAdapter2(this.getActivity(), todoList);
    	animAdapter=new SwingLeftInAnimationAdapter(todoListAdapter2);
    	
        todoListView = (ListView) view.findViewById(R.id.todoList);
        animAdapter.setAbsListView(todoListView);
        adapter = new SwipeDismissAdapter(animAdapter, this);
        adapter.setAbsListView(todoListView);
        todoListView.setAdapter(adapter);
        
    	
    	Button addButton = (Button) view.findViewById(R.id.category_addtodo_btn);
    	Button setTimeBtn = (Button) view.findViewById(R.id.category_settime_btn);
    	addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				addTodo();
			}
    		
    	});
    	setTimeBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				todoSetTime();
			}
    		
    	});
    	
    	

		final View alertView = inflater.inflate(R.layout.dialog_time, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(alertView)
        .setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	Util.errorReport("positive");
            	TimePicker tp = (TimePicker)setTimeDialog.findViewById(R.id.setTime_time);
            	int hour=tp.getCurrentHour();
            	int min=tp.getCurrentMinute();
            	DatePicker dp=(DatePicker)setTimeDialog.findViewById(R.id.setTime_date);
            	int day=dp.getDayOfMonth();
            	
            	Util.errorReport(""+hour+" "+min+" "+day);
            	d=new Date(dp.getYear()-1900,dp.getMonth(),dp.getDayOfMonth());
            	d.setHours(tp.getCurrentHour().intValue());
            	d.setMinutes(tp.getCurrentMinute().intValue());
            	((Button)view.findViewById(R.id.category_settime_btn)).setText(Util.dateLongToString(d.getTime()));
            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        setTimeDialog = builder.create();
    	
        todoListView.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				
				return true;
			}
        	
        });
    	return view;
    }
    
    public void changeCategory(long categoryId) {
    	currentCategory=CategoryTable.find(categoryId);
    	todoList = CategoryTable.find(categoryId).getTodos(); 
    	todoListAdapter2 = new TodoListAdapter2(this.getActivity(), todoList);
    	animAdapter=new SwingLeftInAnimationAdapter(todoListAdapter2);
        todoListView = (ListView) view.findViewById(R.id.todoList);
        animAdapter.setAbsListView(todoListView);
        adapter = new SwipeDismissAdapter(animAdapter, this);
        adapter.setAbsListView(todoListView);
        todoListView.setAdapter(adapter);

    	//todoListAdapter.notifyDataSetChanged();
    }
    
    private void addTodo() {
        EditText todoName = (EditText) view.findViewById(R.id.category_todoinput);
        if (todoName.getText().toString() != "") {
            //System.out.println(categoryName.getText().toString());
        	long todoId = currentCategory.addTodo(todoName.getText().toString(),d.getTime());
        	todoName.setText("");

        	reload();

        	/*todoList.add(TodoTable.find(todoId));
            todoListAdapter.notifyDataSetChanged();*/
        }
    }
    
    public void reload() {
    	todoList = currentCategory.getTodos();
		todoListAdapter2 = new TodoListAdapter2(this.getActivity(), todoList);
		AnimationAdapter animAdapter=new SwingLeftInAnimationAdapter(todoListAdapter2);
	    todoListView = (ListView) view.findViewById(R.id.todoList);
	    animAdapter.setAbsListView(todoListView);
	    SwipeDismissAdapter adapter = new SwipeDismissAdapter(animAdapter, this);
        adapter.setAbsListView(todoListView);
        todoListView.setAdapter(adapter);	
    }
    
    private void todoSetTime(){
    	setTimeDialog.show();
    }

	@Override
	public void onDismiss(AbsListView listView, int[] reverseSortedPositions) {
		// TODO Auto-generated method stub
		for (int position : reverseSortedPositions) {
			currentCategory.eraseTodo((int)todoList.get(position).getId());
        }
		reload(); 
	}

    
    
}
