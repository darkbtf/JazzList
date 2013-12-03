package com.taipeihot.jazzlist;

import java.util.ArrayList;
import java.sql.Date;

import com.taipeihot.jazzlist.adapter.TodoListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.CategoryTable;
import com.taipeihot.jazzlist.table.TodoTable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

 @SuppressLint("NewApi")
public class CategoryFragment extends Fragment {

	View view;
	ArrayList<Todo> todoList=new ArrayList<Todo>();
	TodoListAdapter todoListAdapter;
	Category currentCategory;
	ExpandableListView listView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_category, null);
    	currentCategory = Data.getCategories().get(0);
    	todoList = currentCategory.getTodos();
    	todoListAdapter = new TodoListAdapter(this.getActivity(), todoList);
    	listView = (ExpandableListView) view.findViewById(R.id.todoList);
    	listView.setAdapter(todoListAdapter);
    	Button addButton = (Button) view.findViewById(R.id.category_addtodo_btn);
    	addButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				addTodo();
			}
    		
    	});
    	return view;
    }
    
    public void changeCategory(long categoryId) {
    	currentCategory=CategoryTable.find(categoryId);
    	todoList = CategoryTable.find(categoryId).getTodos(); 
    	todoListAdapter = new TodoListAdapter(this.getActivity(), todoList);
    	listView.setAdapter(todoListAdapter);
    	//todoListAdapter.notifyDataSetChanged();
    }
    
    private void addTodo() {
        EditText todoName = (EditText) view.findViewById(R.id.category_todoinput);
        if (todoName.getText().toString() != "") {
            //System.out.println(categoryName.getText().toString());
        	todoName.setText("");
        	long todoId = currentCategory.addTodo(todoName.getText().toString());
        	todoList.add(TodoTable.find(todoId));
            todoListAdapter.notifyDataSetChanged();
        }
    }
}
