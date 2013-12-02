package com.taipeihot.jazzlist.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Todo;

public class TodoListAdapter extends BaseExpandableListAdapter{

	ArrayList< Todo > todoItem;
	Context context;
	public TodoListAdapter(Context context,ArrayList<Todo> todoItem){
		this.context=context;
		this.todoItem=todoItem;
	}
	@Override
	public Object getChild(int position, int childPosition) {
		// TODO Auto-generated method stub
		return todoItem.get(position)
				.getSubtodos()
				.get(childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			LayoutInflater infalInflater = (LayoutInflater) this.context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=infalInflater.inflate(R.layout.todo_list_small_item, null);
		}
		TextView t=(TextView)convertView.findViewById(R.id.small_todo_name);
		t.setText(todoItem.get(groupPosition).getSubtodos().get(childPosition).getTitle());
		t=(TextView)convertView.findViewById(R.id.small_todo_time);
		t.setText(todoItem.get(groupPosition)
				.getSubtodos().get(childPosition)
				.getDeadline().toString());
		Button b=(Button)convertView.findViewById(R.id.small_setting_button);
		b.setFocusable(false);
		b.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
		    	Toast.makeText(v.getContext(),"lala",Toast.LENGTH_SHORT).show();
		    }
		});
		return convertView;
	}

	@Override
	public int getChildrenCount(int position) {
		// TODO Auto-generated method stub
		return todoItem.get(position).getSubtodos().size();
	}

	@Override
	public Object getGroup(int position) {
		// TODO Auto-generated method stub
		
		return todoItem.get(position);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return todoItem.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			LayoutInflater infalInflater = (LayoutInflater) this.context
	                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView=infalInflater.inflate(R.layout.todo_list_big_item, null);
		}
		Todo todo=todoItem.get(groupPosition);
		if(todo.isAlive()){
			TextView t=(TextView)convertView.findViewById(R.id.big_todo_name);
			t.setText(todo.getTitle());
			t=(TextView)convertView.findViewById(R.id.big_todo_time);
			t.setText(todo.getDeadline().toString());
			Button b=(Button)convertView.findViewById(R.id.big_setting_button);
			b.setFocusable(false);
			b.setOnClickListener(new Button.OnClickListener() {
			    public void onClick(View v) {
			    	Toast.makeText(v.getContext(),"lala",Toast.LENGTH_SHORT).show();

			    }
			});

		}
		return convertView;
	}
	
	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return true;
	}
	

}
