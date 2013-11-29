package com.taipeihot.jazzlist.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Comment;
import com.taipeihot.jazzlist.model.Status;

public class StatusListAdapter extends BaseExpandableListAdapter{

	ArrayList< Status > statusItems;
	Context context;
	StatusListAdapter(Context context,ArrayList<Status> statusItems){
		this.context=context;
		this.statusItems=statusItems;
	}
	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return statusItems.get(groupPosition)
				.getComments()
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
			convertView=infalInflater.inflate(R.layout.comment_list_item,parent,false);
		}
		TextView t=(TextView)convertView.findViewById(R.id.comment_user_name);
		t.setText(((Comment)getChild(groupPosition, childPosition)).getNickname());
		t=(TextView)convertView.findViewById(R.id.comment_like_count);
		t.setText(((Comment)getChild(groupPosition, childPosition)).getScore());
		Button b=(Button)convertView.findViewById(R.id.comment_like_btn);
		b.setFocusable(false);
		return convertView;
	}

	@Override
	public int getChildrenCount(int position) {
		// TODO Auto-generated method stub
		return statusItems.get(position).getComments().size();
	}

	@Override
	public Object getGroup(int position) {
		// TODO Auto-generated method stub
		
		return statusItems.get(position);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return statusItems.size();
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
			convertView=infalInflater.inflate(R.layout.status_list_item,parent,false);
		}
		Status status=statusItems.get(groupPosition);
		
		TextView t=(TextView)convertView.findViewById(R.id.status_user_name);
		t.setText(status.getScore());
		t=(TextView)convertView.findViewById(R.id.status_todo_name);
		t.setText(status.getTitle());
		t=(TextView)convertView.findViewById(R.id.status_todo_time);
		t.setText(status.getDeadline());
		t=(TextView)convertView.findViewById(R.id.status_like_count);
		t.setText(Integer.toString(status.getScore()));
		t=(TextView)convertView.findViewById(R.id.status_comment_count);
		t.setText(Integer.toString(status.getComments().size()));

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
