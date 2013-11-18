package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.model.Todo;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

public class TodoTable extends Table{
	static private String tableName = "";
	static public String createSQL, insertSQL, selectSQL, updateSQL,deleteSQL,dropSQL;
	static private ArrayList<ColumnElement>columns = new ArrayList<ColumnElement>();
	public TodoTable(){
		tableName="todo";
		columns.add(new ColumnElement("content","TINYTEXT NOT NULL"));
		columns.add(new ColumnElement("status","VARCHAR(20)"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	
	static public long insert(Todo todo){
		if(todo.id() != 0){
			Log.e("Insert todo","You can't insert todo with id, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(todo);
		
		long todo_id = db.insert(tableName, null, values);
		todo.setId(todo_id);
		return todo_id;
	}
	
	static public int update(Todo todo){
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(todo);
		return db.update(tableName, values, "_id = ?", new String[]{todo.id()+""});
	}
	
	static public void delete(Todo todo){
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{todo.id()+""});
	}
	
	static public Todo find(long id){
		SQLiteDatabase db = con.getReadableDatabase();
		String sql = selectSQL + "WHERE _id = " + id;
		Cursor c = db.rawQuery(sql, null);
		if(c!=null)c.moveToFirst();
		else{
			Log.e("Todo Find","Error: No such instance found.");
			return null;
		}
		return instance(c);
	}
	
	static public ArrayList<Todo> All(){
		ArrayList<Todo>ret = new ArrayList<Todo>();
		SQLiteDatabase db = con.getReadableDatabase();
		String sql = selectSQL;
		Cursor c = db.rawQuery(sql, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Todo> where(String format, String[] params){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Todo>ret = new ArrayList<Todo>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, params, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Todo> where(String format){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Todo>ret = new ArrayList<Todo>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, new String[]{}, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	static private Todo instance(Cursor c) {
		return new Todo(
				c.getInt(0),
				c.getString(1),
				c.getString(2)
				);
	}

	static private ContentValues makeValue(Todo todo){
		ContentValues values = new ContentValues();
		values.put("content", todo.content);
		values.put("status", todo.status);
		return values;
	}
}
