package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.adapter.TodoListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.CategoryTable;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

@SuppressLint("NewApi")
public class CategoryFragment extends Fragment {

	View view;
	ArrayList<Todo> todoList=new ArrayList<Todo>();
	TodoListAdapter todoListAdapter;
	Category currentCategory;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	view = inflater.inflate(R.layout.fragment_category, null);
    	todoList = new ArrayList<Todo>();
    	todoListAdapter = new TodoListAdapter(this.getActivity(), todoList);
    	ExpandableListView listView = (ExpandableListView) view.findViewById(R.id.todoList);
    	listView.setAdapter(todoListAdapter);
    	return view;
    }
    
    public void changeCategory(int categoryId) {
    	currentCategory=CategoryTable.find(categoryId);
    	todoList = CategoryTable.find(categoryId).getTodos(); 
    	todoListAdapter.notifyDataSetChanged();
    }
    
    public void addTodo() {
        EditText todoName = (EditText) view.findViewById(R.id.category_todoinput);
        if (todoName.getText().toString() != "") {
            //System.out.println(categoryName.getText().toString());
        	currentCategory.addTodo(todoName.getText().toString());
            todoListAdapter.notifyDataSetChanged();
        }
    }
}
