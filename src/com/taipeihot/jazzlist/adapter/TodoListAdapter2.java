package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.taipeihot.jazzlist.MainActivity;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Todo;
 
public class TodoListAdapter2 extends BaseAdapter {
     
    private Context context;
    private ArrayList<Todo> todoItems;
     
    public TodoListAdapter2(Context context, ArrayList<Todo> todoItems){
        this.context = context;
        this.todoItems = todoItems;
    }
 
    @Override
    public int getCount() {
        return todoItems.size();
    }
 
    @Override
    public Object getItem(int position) {       
        return todoItems.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    	Todo todo=todoItems.get(position);
    	LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    	if(todo.isAlive()){
        	if(todo.isEmergency())convertView = mInflater.inflate(R.layout.todo_list_em_item, null);
        	else convertView=mInflater.inflate(R.layout.todo_list_item, null);
        }
        else convertView =mInflater.inflate(R.layout.todo_list_dead_item, null);
    	if(todo.isAlive()){

	        TextView todoName = (TextView) convertView.findViewById(R.id.big_todo_name);
	        TextView todoTime = (TextView) convertView.findViewById(R.id.big_todo_time);
	        todoName.setText(todo.getTitle());
	        todoTime.setText(todo.getDeadlineString());
	        Button b=(Button)convertView.findViewById(R.id.big_setting_button);
			b.setFocusable(false);
			b.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {
			    	((MainActivity)context).toSetting(todoItems.get(position).getId());
			    }
			});
    	}
    	else{
	        TextView todoName = (TextView) convertView.findViewById(R.id.dead_todo_name);
	        TextView todoTime = (TextView) convertView.findViewById(R.id.dead_todo_time);
	        todoName.setText(todo.getTitle());
	        todoTime.setText(todo.getDeadlineString());
	        Button b=(Button)convertView.findViewById(R.id.dead_setting_button);
			b.setFocusable(false);
			b.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {
			    	((MainActivity)context).toSetting(todoItems.get(position).getId());
			    }
			});
    	}
        
         
        return convertView;
    }
 
}