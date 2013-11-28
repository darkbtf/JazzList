package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.User;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class UserTable extends Table{
	static private String tableName = "";
	static public String createSQL, insertSQL, selectSQL, updateSQL,deleteSQL,dropSQL;
	static private ArrayList<ColumnElement>columns = new ArrayList<ColumnElement>();
	public UserTable(){
		tableName="user";
		columns.add(new ColumnElement("account","VARCHAR(20) NOT NULL"));
		columns.add(new ColumnElement("password","VARCHAR(20)"));
		columns.add(new ColumnElement("nickname","VARCHAR(20) NOT NULL"));
		columns.add(new ColumnElement("idOfServer","INTEGER NOT NULL"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	
	static public long insert(User a){
		if(a.getId() != 0){
			Util.errorReport("Insert user, You can't insert with id!=0, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(a);
		
		long todo_id = db.insert(tableName, null, values);
		a.setId(todo_id);
		return todo_id;
	}
	
	static public int update(User a){
		if(a.getId() == 0){
			Util.errorReport("Update user, You can't update with id=0, use insert instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(a);
		return db.update(tableName, values, "_id = ?", new String[]{a.getId()+""});
	}
	
	static public void delete(User a){
		if(a.getId() == 0){
			Util.errorReport("delete user, You can't delete with id=0.");
			return;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{a.getId()+""});
	}
	
	static public User find(long id){
		SQLiteDatabase db = con.getReadableDatabase();
		String sql = selectSQL + "WHERE _id = " + id;
		Cursor c = db.rawQuery(sql, null);
		if(c!=null)c.moveToFirst();
		else{
			Util.errorReport("Find user, No such instance found.");
			return null;
		}
		return instance(c);
	}
	
	static public ArrayList<User> All(){
		ArrayList<User>ret = new ArrayList<User>();
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
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<User> where(String format, String[] params){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<User>ret = new ArrayList<User>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, params, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<User> where(String format){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<User>ret = new ArrayList<User>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, new String[]{}, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	static private User instance(Cursor c) {
		return new User(
				c.getInt(0),
				c.getString(1),
				c.getString(2),
				c.getString(3),
				c.getInt(4)
				);
	}

	static private ContentValues makeValue(User a){
		ContentValues values = new ContentValues();
		values.put("account", a.getAccount());
		values.put("password", a.getPassword());
		values.put("nickname", a.getNickname());
		values.put("idOfServer", a.getIdOfServer());
		return values;
	}
}
