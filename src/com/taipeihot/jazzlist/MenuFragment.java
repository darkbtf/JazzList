package com.taipeihot.jazzlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.model.Data;

public class MenuFragment extends Fragment {

    CategoryListAdapter categoryListAdapter;
    View view;
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
        categoryListAdapter = new CategoryListAdapter(this.getActivity(), Data.getCategories());
        ListView categoryListView = (ListView) view.findViewById(R.id.category_list);
        categoryListView.setAdapter(categoryListAdapter);
        categoryListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {

                // TODO: change category
            }
        });
        return view;
    }

    private void addCategory(View view) {
        EditText categoryName = (EditText) view.findViewById(R.id.category_name);
        if (categoryName.getText().toString() != "") {
            //System.out.println(categoryName.getText().toString());
            Data.addCategory(categoryName.getText().toString());
            categoryListAdapter.notifyDataSetChanged();
        }
    }
}
