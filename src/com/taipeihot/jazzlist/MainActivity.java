package com.taipeihot.jazzlist;

import com.taipeihot.jazzlist.model.Todo;
import com.taipeihot.jazzlist.table.Table;
import com.taipeihot.jazzlist.table.TodoTable;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Table(this);
        Todo todo1 = new Todo("haha","not done yet");
        Todo todo2 = new Todo("QQ","Done!");
        TodoTable.insert(todo1);
        for(Todo t:TodoTable.All())
        	Log.v("OAO","xddd"+t.id()+t.content+t.status);
        TodoTable.insert(todo2);
        for(Todo t:TodoTable.All())
        	Log.v("OAO","xddd"+t.id()+t.content+t.status);
        Todo t1 = TodoTable.find(1);
        Log.v("meow","XDDDD"+t1.id()+t1.content+t1.status);
        Table.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
