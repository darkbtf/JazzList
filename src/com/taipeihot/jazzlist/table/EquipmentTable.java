package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Equipment;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

public class EquipmentTable extends Table{
	static private String tableName = "";
	static public String createSQL, insertSQL, selectSQL, updateSQL,deleteSQL,dropSQL;
	static private ArrayList<ColumnElement>columns = new ArrayList<ColumnElement>();
	public EquipmentTable(){
		tableName="equipment";
		columns.add(new ColumnElement("hp_change","TINYINT NOT NULL"));
		columns.add(new ColumnElement("mp_change","INT NOT NULL"));
		columns.add(new ColumnElement("attack_change","INT NOT NULL"));
		columns.add(new ColumnElement("defense_change","INT NOT NULL"));
		columns.add(new ColumnElement("money","INT NOT NULL"));
		columns.add(new ColumnElement("at","INT NOT NULL"));
		columns.add(new ColumnElement("name","TINYTEXT NOT NULL"));
		columns.add(new ColumnElement("description","TEXT NOT NULL"));
		columns.add(new ColumnElement("type","INTEGER NOT NULL"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	static public void init(){
		if(All().size() == 0){
			/*hp mp attack defense money at name description type*/
			insert(new Equipment(35,0,0,0,300,1,"Head","Increase your base HP",1));
			insert(new Equipment(0,0,15,0,500,1,"Sword","Increase your base attack",2));
			insert(new Equipment(0,0,0,15,300,-1,"Gloves","Increase your base defense",2));
			insert(new Equipment(0,35,0,0,300,1,"Boots","Increase your base MP",3));
		}
	}
	static public long insert(Equipment a){
		if(a.getId() != 0){
			Util.errorReport("Insert euip, You can't insert action with id!=0, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(a);
		long id = db.insert(tableName, null, values);
		a.setId(id);
		return id;
	}
	
	static public int update(Equipment a){
		if(a.getId() == 0){
			Util.errorReport("Update euip, You can't update action with id=0, use insert instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(a);
		return db.update(tableName, values, "_id = ?", new String[]{a.getId()+""});
	}
	
	static public void delete(Equipment a){
		if(a.getId() == 0){
			Util.errorReport("delete euip, You can't delete action with id=0.");
			return;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{a.getId()+""});
	}
	
	static public Equipment find(long id){
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
	
	static public ArrayList<Equipment> All(){
		ArrayList<Equipment>ret = new ArrayList<Equipment>();
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
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Equipment> where(String format, String[] params){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Equipment>ret = new ArrayList<Equipment>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, params, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Equipment> where(String format){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Equipment>ret = new ArrayList<Equipment>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, new String[]{}, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	static private Equipment instance(Cursor c) {
		return new Equipment(
				c.getInt(0),
				c.getInt(1),
				c.getInt(2),
				c.getInt(3),
				c.getInt(4),
				c.getInt(5),
				c.getInt(6),
				c.getString(7),
				c.getString(8),
				c.getInt(9)
				);
	}

	static private ContentValues makeValue(Equipment a){
		ContentValues values = new ContentValues();
		values.put("hp_change", a.getHpChange());
		values.put("mp_change", a.getMpChange());
		values.put("attack_change", a.getAttackChange());
		values.put("defense_change", a.getDefenseChange());
		values.put("money", a.getMoney());
		values.put("at", a.getAt());
		values.put("name", a.getName());
		values.put("description", a.getDescription());
		values.put("type", a.getType());
		return values;
	}
}
