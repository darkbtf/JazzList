package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Action;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

public class ActionTable extends Table{
	static private String tableName = "";
	static public String createSQL, insertSQL, selectSQL, updateSQL,deleteSQL,dropSQL;
	static private ArrayList<ColumnElement>columns = new ArrayList<ColumnElement>();
	public ActionTable(){
		tableName="action";
		columns.add(new ColumnElement("rate","TINYINT NOT NULL"));
		columns.add(new ColumnElement("hp_consume","INT NOT NULL"));
		columns.add(new ColumnElement("mp_consume","INT NOT NULL"));
		columns.add(new ColumnElement("hp_damage","INT NOT NULL"));
		columns.add(new ColumnElement("mp_damage","INT NOT NULL"));
		columns.add(new ColumnElement("description","TEXT NOT NULL"));
		columns.add(new ColumnElement("number","INTEGER NOT NULL"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	
	static public long insert(Action a){
		if(a.getId() != 0){
			Util.errorReport("Insert action, You can't insert action with id!=0, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(a);
		long id = db.insert(tableName, null, values);
		a.setId(id);
		return id;
	}
	
	static public int update(Action a){
		if(a.getId() == 0){
			Util.errorReport("Update action, You can't update action with id=0, use insert instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(a);
		return db.update(tableName, values, "_id = ?", new String[]{a.getId()+""});
	}
	
	static public void delete(Action a){
		if(a.getId() == 0){
			Util.errorReport("delete action, You can't delete action with id=0.");
			return;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{a.getId()+""});
	}
	
	static public Action find(long id){
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
	
	static public ArrayList<Action> All(){
		ArrayList<Action>ret = new ArrayList<Action>();
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
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Action> where(String format, String[] params){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Action>ret = new ArrayList<Action>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, params, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Action> where(String format){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Action>ret = new ArrayList<Action>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, new String[]{}, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	static private Action instance(Cursor c) {
		return new Action(
				c.getInt(0),
				c.getInt(1),
				c.getInt(2),
				c.getInt(3),
				c.getInt(4),
				c.getInt(5),
				c.getString(6),
				c.getInt(7)
				);
	}

	static private ContentValues makeValue(Action a){
		ContentValues values = new ContentValues();
		values.put("rate", a.getRate());
		values.put("hp_consume", a.getHpConsume());
		values.put("mp_consume", a.getMpConsume());
		values.put("hp_damage", a.getHpDamage());
		values.put("mp_damage", a.getMpDamage());
		values.put("description", a.getDescription());
		values.put("number", a.getNumber());
		return values;
	}
}
