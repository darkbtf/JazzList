package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import com.taipeihot.jazzlist.Util;
import com.taipeihot.jazzlist.model.Category;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class CategoryTable extends Table{
	static private String tableName = "";
	static public String createSQL, insertSQL, selectSQL, updateSQL,deleteSQL,dropSQL;
	static private ArrayList<ColumnElement>columns = new ArrayList<ColumnElement>();
	public CategoryTable(){
		tableName="category";
		columns.add(new ColumnElement("title","TINYTEXT NOT NULL"));
		columns.add(new ColumnElement("icon","INTEGER NOT NULL"));
		columns.add(new ColumnElement("count","INTEGER NOT NULL DEFAULT 0"));
		columns.add(new ColumnElement("real_id","INTEGER DEFAULT 0"));
		dropSQL = "DROP TABLE IF EXISTS "+tableName;
		createSQL = makeCreateSQL(tableName, columns);
		insertSQL = makeInsertSQL(tableName, columns);
		selectSQL = "select * from "+tableName+" ";
		updateSQL = makeUpdateSQL(tableName, columns);
	}
	
	static public long insert(Category a){
		if(a.getId() != 0){
			Util.errorReport("Insert Category, You can't insert with id!=0, use update instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values = makeValue(a);
		Util.errorReport(values.toString());
		long id = db.insert(tableName, null, values);
		a.setId(id);
		return id;
	}
	
	static public int update(Category a){
		if(a.getId() == 0){
			Util.errorReport("Update Category, You can't update with id=0, use insert instead.");
			return 0;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		ContentValues values=makeValue(a);
		return db.update(tableName, values, "_id = ?", new String[]{a.getId()+""});
	}
	
	static public void delete(Category a){
		if(a.getId() == 0){
			Util.errorReport("delete Category, You can't delete with id=0.");
			return;
		}
		SQLiteDatabase db = con.getWritableDatabase();
		db.delete(tableName,"_id = ?",new String[]{a.getId()+""});
	}
	
	static public Category find(long id){
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
	
	static public ArrayList<Category> All(){
		ArrayList<Category>ret = new ArrayList<Category>();
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
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Category> where(String format, String[] params){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Category>ret = new ArrayList<Category>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, params, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN) static public ArrayList<Category> where(String format){
		SQLiteDatabase db = con.getReadableDatabase();
		ArrayList<Category>ret = new ArrayList<Category>();
		Cursor c =
				db.query(true, tableName, new String[]{"*"}, format, new String[]{}, null, null, "_id", null, null);
		if(c.moveToFirst()){
			do{
				ret.add(instance(c));
			}while(c.moveToNext());
		}
		return ret;
	}
	
	static private Category instance(Cursor c) {
		return new Category(
				c.getInt(0),
				c.getString(1),
				c.getInt(2),
				c.getInt(3),
				c.getInt(4)
				);
	}

	static private ContentValues makeValue(Category a){
		ContentValues values = new ContentValues();
		values.put("title", a.getTitle());
		values.put("icon", a.getIcon());
		values.put("count", a.getCount());
		values.put("real_id",a.getRealId());
		return values;
	}
}
