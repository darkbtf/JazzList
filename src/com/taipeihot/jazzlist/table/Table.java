package com.taipeihot.jazzlist.table;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Table {
	public static DatabaseHelper con; 
	public Table(){}
	public Table(Context context){
		new TodoTable();
		new CategoryTable();
		new ActionTable();
		new EquipmentTable();
		con = new DatabaseHelper(context);
		ActionTable.init(15);
		EquipmentTable.init();
	}
	protected String makeCreateSQL(String tableName,ArrayList<ColumnElement>columns){
		String ret = "CREATE TABLE IF NOT EXISTS "+tableName+" ( _id INTEGER primary key autoincrement ";
		for(ColumnElement c:columns)
			ret += ", "+c.name+" "+c.type;
		ret += ")";
		return ret;
	}
	protected String makeInsertSQL(String tableName,ArrayList<ColumnElement> columns){
		String ret = "insert into "+tableName+"( _id";
		for(ColumnElement c:columns)
			ret += ","+c.name;
		ret += ") VALUES (?";
		for(int i=0;i<columns.size();i++)
			ret += ",?";
		ret +=")";
		return ret;
	}
	static public void close(){
		SQLiteDatabase db = con.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
	protected String makeUpdateSQL(String tableName, ArrayList<ColumnElement>columns){
		String ret = "update "+tableName+" set _id=?";
		for(ColumnElement c:columns)
			ret += ","+c.name+"=? ";
		ret += "where _id=?";
		return ret;
	}
	class ColumnElement{
		String name;
		String type;
		public ColumnElement(){}
		public ColumnElement(String name,String type){
			this.name = name;
			this.type = type;
		}
	}
}
