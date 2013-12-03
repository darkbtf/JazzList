package com.taipeihot.jazzlist.adapter;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.model.Comment;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Status;

public class StatusListAdapter extends BaseExpandableListAdapter{

	ArrayList< Status > statusItems;
	Context context;
	public StatusListAdapter(Context context,ArrayList<Status> statusItems){
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
		ImageView userPhoto=(ImageView)convertView.findViewById(R.id.comment_user_photo);
		TextView userName=(TextView)convertView.findViewById(R.id.comment_user_name);
		TextView likeCount=(TextView)convertView.findViewById(R.id.comment_like_count);
		TextView commentContent=(TextView)convertView.findViewById(R.id.comment_content);
		TextView commentTime=(TextView)convertView.findViewById(R.id.comment_time);
		ImageButton imageButton=(ImageButton)convertView.findViewById(R.id.comment_like_btn);
		final Comment comment=(Comment)getChild(groupPosition,childPosition);
		imageButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				comment.incScore();
			}
			
		});
		commentTime.setText(comment.getTimeString());
		userName.setText(comment.getNickname());
		commentContent.setText(comment.getContent());
		likeCount.setText(Integer.toString((comment.getScore())));
		imageButton.setFocusable(false);

		int photoNum=comment.photoNumber()%15+1;
		userPhoto.setBackgroundResource(
				context.getResources()
				.getIdentifier(
						"user_photo_"+Integer
						.toString(photoNum),
						"drawable", context.getPackageName()));
		
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

		final Status status=statusItems.get(groupPosition);
		TextView userName=(TextView)convertView.findViewById(R.id.status_user_name);
		ImageView userPhoto=(ImageView)convertView.findViewById(R.id.state_user_photo);
		TextView todoName=(TextView)convertView.findViewById(R.id.status_todo_name);
		TextView todoTime=(TextView)convertView.findViewById(R.id.status_todo_time);
		TextView likeCount=(TextView)convertView.findViewById(R.id.status_like_count);
		TextView commentCount=(TextView)convertView.findViewById(R.id.status_comment_count);
		TextView statusCategory=(TextView)convertView.findViewById(R.id.status_category_name);
		ImageButton likeBtn=(ImageButton)convertView.findViewById(R.id.status_like_graph);
		ImageButton commentBtn=(ImageButton)convertView.findViewById(R.id.status_comment_graph);
		
		likeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				status.incScore();
				notifyDataSetChanged();
			}
			
		});
		statusCategory.setText(status.getCategory());
		final AlertDialog commentDialog;
        LayoutInflater inflater = LayoutInflater.from(convertView.getContext());
		final View alertView = inflater.inflate(R.layout.comment_dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(convertView.getContext());
        builder.setView(alertView)
        .setPositiveButton("Comment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	EditText commentText = (EditText) alertView.findViewById(R.id.comment_text);
            	status.addComment(commentText.getText().toString());
            	notifyDataSetChanged();
            }
        })
        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        commentDialog = builder.create();
        
        commentBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
		        commentDialog.show();
			}
        	
        });
		
		likeBtn.setFocusable(false);
		commentBtn.setFocusable(false);
		userName.setText(status.getNickname());
		todoName.setText(status.getTitle());
		todoTime.setText(status.getDeadline());
		likeCount.setText(Integer.toString(status.getScore()));
		commentCount.setText(Integer.toString(status.getComments().size()));
		int photoNum=status.photoNumber()%15+1;
		userPhoto.setBackgroundResource(
				context.getResources()
				.getIdentifier(
						"user_photo_"+Integer
						.toString(photoNum),
						"drawable", context.getPackageName()));
		
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
