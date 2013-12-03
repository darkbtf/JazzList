package com.taipeihot.jazzlist;

import java.util.ArrayList;

import com.taipeihot.jazzlist.adapter.CategoryListAdapter;
import com.taipeihot.jazzlist.adapter.FriendListAdapter;
import com.taipeihot.jazzlist.model.Category;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class FriendListFragment extends Fragment {
	View view;
	ArrayList<User> friends;
	FriendListAdapter friendListAdapter;
	ListView friendListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.activity_freind_list, null);
        Button addButton = (Button) view.findViewById(R.id.friend_add);
        addButton.setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        addFriend();
                    }
                });
        friends=Data.getFriends();
        friendListAdapter = new FriendListAdapter(this.getActivity(), friends);
        friendListView = (ListView) view.findViewById(R.id.friendList);
        friendListView.setAdapter(friendListAdapter);
        
        return view;
    }

    private void addFriend() {
        EditText friendName = (EditText) view.findViewById(R.id.friend_add_name);
        if (friendName.getText().toString() != "") {
        	friendName.setText("");
            //System.out.println(categoryName.getText().toString());
            Data.addFriend(friendName.getText().toString());
            friendListView.setAdapter(friendListAdapter);
            friendListAdapter.notifyDataSetChanged();
            System.out.println("meow add friend"); 
        }
    }
}
