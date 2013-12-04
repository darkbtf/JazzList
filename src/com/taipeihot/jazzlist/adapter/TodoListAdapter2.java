package com.taipeihot.jazzlist.adapter;
 
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipeihot.jazzlist.MainActivity;
import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Category;
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
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.todo_list_big_item, null);
        }
        Todo todo=todoItems.get(position);
        TextView todoName = (TextView) convertView.findViewById(R.id.big_todo_name);
        TextView todoTime = (TextView) convertView.findViewById(R.id.big_todo_time);
        Button b=(Button)convertView.findViewById(R.id.big_setting_button);
		b.setFocusable(false);
		b.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	((MainActivity)context).toSetting(todoItems.get(position).getId());
		    }
		});
        todoName.setText(todo.getTitle());
        todoTime.setText(todo.getDeadlineString());
        
         
        return convertView;
    }
 
}