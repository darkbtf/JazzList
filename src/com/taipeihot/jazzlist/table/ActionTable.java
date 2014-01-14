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
			/* rate% Hp_consume Mp_consume Name description number object_id level_limit*/
			/*level 1*/
			insert(new Action(120,0,0,"Ember","little damage, +1 heat on your opponent",999,1,1));
			insert(new Action(120,0,0,"Bless","little heal",999,2,1));
			insert(new Action(120,0,0,"Thunderbolt","little damage",999,3,1));
			/*level 3*/
			insert(new Action(120,0,16,"Immolation","+2 heat on yourself",999,4,3));
			insert(new Action(120,0,0,"Mana Stream","regain some MP",999,5,3));
			insert(new Action(120,20,10,"Static Field","add 20% on attack value",999,6,3));
			/*level 5*/ 
			insert(new Action(120,0,30,"Karma Blast","release all heat on you and your oppoenet and make a great damage on your opponent",999,7,5));
			insert(new Action(120,0,30,"Frostnova","your opponent can't move in 2 round",999,8,5));
			insert(new Action(120,0,30,"Maxwell Equation","Kill your opponent directly in a probablity",999,9,5));
			
			/*Items: rate% Hp_consume Mp_consume Name description number object_id money*/
			insert(new Action(120,0,0,"Health Potion","Resume 25 HP",40,-1,20));
			insert(new Action(120,0,0,"Mana Potion","Resume 25 MP",10,-2,30));
			insert(new Action(120,0,0,"Omni Potion","Resume 30 HP and 30 MP",0,-3,100));
			insert(new Action(120,0,0,"Great Health Potion","Resume half of your HP",40,-4,200));
			insert(new Action(120,0,0,"Great Mana Potion","Resume half of your MP",0,-5,300));
			insert(new Action(120,0,0,"Great Omni Potion","Resume half of your HP and MP",50,-6,800));
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
				c.getString(4),
				c.getString(5),
				c.getInt(6),
				c.getInt(7),
				c.getInt(8)
				);
	}

	static private ContentValues makeValue(Action a){
		ContentValues values = new ContentValues();
		values.put("rate", a.getRate());
		values.put("hp_consume", a.getHpConsume());
		values.put("mp_consume", a.getMpConsume());
		values.put("name", a.getName());
		values.put("description", a.getDescription());
		values.put("number", a.getNumber());
		values.put("object_id", a.getObjectId());
		values.put("level_limit", a.getLevelLimit());
		return values;
	}
}
