package com.finder.stotomas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;


public class SqliteDatabase extends SQLiteOpenHelper {

    private static final int    DATABASE_VERSION =	1;
    private static final String DATABASE_NAME = "PERSONADB";
    private final static String TABLE_NAME ="PERSONA";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    private static final String COLUMN_URL = "URL";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE	TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME+ " TEXT," + " INTEGER," + COLUMN_DESCRIPTION+ " TEXT," + COLUMN_URL+ " TEXT);";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Persona> listPersona(){
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<Persona> listPersona = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String desc = cursor.getString(2);
                String url = cursor.getString(3);
                listPersona.add(new Persona(id,name,desc,url));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listPersona;
    }

    public  Persona getPersona(int id){
        String sql = "select * from " + TABLE_NAME+" where "+COLUMN_ID+"	= ?";
        SQLiteDatabase db = this.getReadableDatabase();

        Persona mPersona = new Persona();

        Cursor cursor=db.rawQuery(sql, new String[] { String.valueOf(id)});
        if(cursor.moveToFirst()){
            do{
                mPersona.setName(cursor.getString(1));
                mPersona.setDescription(cursor.getString(2));
                mPersona.setUrl( cursor.getString(3));

            }while (cursor.moveToNext());
        }
        cursor.close();
        return mPersona;
    }

    public void newPersona(Persona mPersona){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, mPersona.getName());
        values.put(COLUMN_DESCRIPTION, mPersona.getDescription());
        values.put(COLUMN_URL, mPersona.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
    }

    public  void updatePersona(Persona mPersona, int id){
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME, mPersona.getName());
        values.put(COLUMN_DESCRIPTION, mPersona.getDescription());
        values.put(COLUMN_URL, mPersona.getUrl());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_NAME, values, COLUMN_ID+"="+ id,null);
    }

    public void deletePersona(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }


}
