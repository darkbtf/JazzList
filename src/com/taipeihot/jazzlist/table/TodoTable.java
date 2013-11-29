package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;
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
		columns.add(new ColumnElement("title","TINYTEXT NOT NULL"));
		columns.add(new ColumnElement("category_id","SMALLINT NOT NULL"));
		columns.add(new ColumnElement("status","TINYINT NOT NULL"));
		columns.add(new ColumnElement("deadline","DATETIME NOT NULL"));
		columns.add(new ColumnElement("user_id","INTEGER NOT NULL"));
		columns.add(new ColumnElement("description","TEXT NOT NULL"));
		columns.add(new ColumnElement("belong_id","INTEGER NOT NULL"));
		columns.add(new ColumnElement("real_id","INTEGER NOT NULL"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	
	static public long insert(Todo todo){
		if(todo.getId() != 0){
			Util.errorReport("Insert todo, You can't insert todo with id!=0, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(todo);
		long todo_id = db.insert(tableName, null, values);
		todo.setId(todo_id);
		return todo_id;
	}
	
	static public int update(Todo todo){
		if(todo.getId() == 0){
			Util.errorReport("Update todo, You can't update todo with id=0, use insert instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(todo);
		return db.update(tableName, values, "_id = ?", new String[]{todo.getId()+""});
	}
	
	static public void delete(Todo todo){
		if(todo.getId() == 0){
			Util.errorReport("delete todo, You can't delete todo with id=0.");
			return;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{todo.getId()+""});
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
				c.getInt(2),
				c.getShort(3),
				c.getLong(4),
				c.getInt(5),
				c.getString(6),
				c.getInt(7),
				c.getInt(8)
				);
	}

	static private ContentValues makeValue(Todo todo){
		ContentValues values = new ContentValues();
		values.put("title", todo.getTitle());
		values.put("category_id", todo.getCategoryId());
		values.put("status", todo.getStatus());
		values.put("deadline", todo.getDeadlineLong());
		values.put("user_id", todo.getUserId());
		values.put("description", todo.getDescription());
		values.put("belong_id", todo.getBelongId());
		values.put("real_id", todo.getRealId());
		return values;
	}
}
