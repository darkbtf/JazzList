package com.taipeihot.jazzlist.table;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "jazzListDB";
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(TodoTable.createSQL);
		db.execSQL(CategoryTable.createSQL);
		db.execSQL(ActionTable.createSQL);
		db.execSQL(EquipmentTable.createSQL);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL(TodoTable.dropSQL);
		db.execSQL(CategoryTable.dropSQL);
		db.execSQL(ActionTable.dropSQL);
		db.execSQL(EquipmentTable.dropSQL);
		onCreate(db);
	}
}
