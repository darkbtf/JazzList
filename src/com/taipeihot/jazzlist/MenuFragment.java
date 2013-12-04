package com.taipeihot.jazzlist;

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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.haarman.listviewanimations.swinginadapters.AnimationAdapter;
import com.haarman.listviewanimations.swinginadapters.prepared.SwingLeftInAnimationAdapter;
import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;

@SuppressLint("NewApi")
public class MenuFragment extends Fragment implements OnNavigationListener{

    CategoryListAdapter categoryListAdapter;
    View view;
    int toDeleteItemId;
    ArrayList<Category> categories;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragemt_menu, null);
        Button addButton = (Button) view.findViewById(R.id.category_add_btn);
        addButton.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        addCategory(view);
                    }
                });
        categories=Data.getCategories();
        
        categoryListAdapter = new CategoryListAdapter(this.getActivity(), categories);
        AnimationAdapter animAdapter=new SwingLeftInAnimationAdapter(categoryListAdapter);
        ListView categoryListView = (ListView) view.findViewById(R.id.category_list);
        animAdapter.setAbsListView(categoryListView);
        categoryListView.setAdapter(animAdapter);
        categoryListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
            	//System.out.println("now position = " + position);
            	Category category = categories.get(position);
            	getActivity().setTitle(category.getTitle());
            	((MainActivity) getActivity()).changeCategory(category.getId());
            }
        });
        
        
		final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder
		.setPositiveButton("Delete",  new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				Data.deleteCategory(categories.get(toDeleteItemId).getId());
	            categoryListAdapter.notifyDataSetChanged();
			}
			
		})
		.setNegativeButton("Back",  new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				
			}
			
		});
        
        categoryListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				toDeleteItemId = arg2;
				builder.setTitle("You're deleting \""+ categories.get(arg2).getTitle() +"\"");
				AlertDialog alertDialog = builder.create();
				alertDialog.show();
				return true;
			}
        	
        });
        Button timelineButton = (Button) view.findViewById(R.id.menu_status_btn);
        timelineButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), TimelineActivity.class);
				startActivity(intent);
			}
        });
        
        Button profileButton = (Button) view.findViewById(R.id.menu_profile_btn);
        profileButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), ProfileActivity.class);
				startActivity(intent);
			}
        	
        });
        return view;
    }

    private void addCategory(View view) {
        EditText categoryName = (EditText) view.findViewById(R.id.category_name);
        if (!categoryName.getText().toString().equals("")) {
            //System.out.println(categoryName.getText().toString());
            Data.addCategory(categoryName.getText().toString());
        	categoryName.setText("");
            categoryListAdapter.notifyDataSetChanged();
        }
    }
    
	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}
}
