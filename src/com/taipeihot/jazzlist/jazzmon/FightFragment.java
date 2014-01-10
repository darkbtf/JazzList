package com.taipeihot.jazzlist.jazzmon;

import java.util.ArrayList;

import com.taipeihot.jazzlist.R;
import com.taipeihot.jazzlist.adapter.FriendListAdapter;
import com.taipeihot.jazzlist.model.Data;
import com.taipeihot.jazzlist.model.User;

import android.content.Intent;
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

public class FightFragment extends Fragment {
	View view;
	ArrayList<User> friends;
	FriendListAdapter friendListAdapter;
	ListView friendListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_fight, null);
        friends=Data.getFriends();
        friendListAdapter = new FriendListAdapter(this.getActivity(), friends, true);
        friendListView = (ListView) view.findViewById(R.id.friendList);
        friendListView.setAdapter(friendListAdapter);
        return view;
    }

}
