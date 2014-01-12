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
		columns.add(new ColumnElement("name","TINYTEXT NOT NULL"));
		columns.add(new ColumnElement("description","TEXT NOT NULL"));
		columns.add(new ColumnElement("number","INTEGER NOT NULL"));
		columns.add(new ColumnElement("object_id","INT NOT NULL"));
		columns.add(new ColumnElement("level_limit","INT NOT NULL"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	static public void init(int should){
		if(All().size() != should){
			/* rate% hp_consume mp_consume hp_damage, mp_damage Name description number object_id level_limit*/
			/*level 1*/
			insert(new Action(120,0,0,10,0,"Ember","little damage, +1 heat on your opponent",0,1,1));
			insert(new Action(120,-8,0,0,0,"Bless","little heal",0,2,1));
			insert(new Action(120,0,0,10,0,"Thunderbolt","damage!",0,3,1));
			/*level 3*/
			insert(new Action(120,0,0,10,0,"Fire2","mewo meow",0,4,3));
			insert(new Action(120,0,0,10,0,"Water2","little heal",0,5,3));
			insert(new Action(120,0,0,10,0,"Thunder2","QQzzThunder",0,6,3));
			/*level 5*/ 
			insert(new Action(120,0,0,10,0,"Fire3","Fire 3",0,7,5));
			insert(new Action(120,0,0,10,0,"Water3","Water3 ~~",0,8,5));
			insert(new Action(120,0,0,10,0,"Maxwell","Monster Kill",0,9,5));
			
			/*Items: rate% hp_consume mp_consume hp_damage, mp_damage Name description number object_id money*/
			insert(new Action(120,-8,-8,0,0,"Healing Potion","Resume HP and MP",0,-1,20));
			insert(new Action(120,-20,-20,0,0,"Great Healing Potion","Resume more HP and MP",0,-1,30));
			insert(new Action(120,-500,-500,0,0,"Max Healing Potion","Totally Resume HP and MP",0,-1,100));
		}
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
				c.getString(7),
				c.getInt(8),
				c.getInt(9),
				c.getInt(10)
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
		values.put("object_id", a.getObjectId());
		values.put("level_limit", a.getLevelLimit());
		return values;
	}
}
