package com.example.ibnsina.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class sqlexample extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clientdata.db";
    private static final String TABLE_NAME = "client";
    private static final String COLUMN1 = "ID";
    private static final String COLUMN2 = "FIRST_NAME";
    private static final String COLUMN3 = "LAST_NAME";
    private static final String COLUMN4 = "EMAIL";

    public sqlexample(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String quary;
        quary = "CREATE TABLE " +TABLE_NAME+ "(" +COLUMN1+ " INTEGER PRIMARY KEY, " +COLUMN2+ " TEXT, " +COLUMN3+ " TEXT, " +COLUMN4+ " TEXT "+ ")";

        db.execSQL(quary);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }

    public boolean AddToTable(String id,String f_name,String l_name,String email)
    {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1,id);
        contentValues.put(COLUMN2,f_name);
        contentValues.put(COLUMN3,l_name);
        contentValues.put(COLUMN4,email);
        long checker = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(checker == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    public Cursor viewdata()
    {
        SQLiteDatabase sqLiteDatabase= getReadableDatabase();
        Cursor ref;
        ref = sqLiteDatabase.rawQuery("SELECT *FROM "+TABLE_NAME,null);
        return ref;
    }

    public boolean updatetade(String id,String f_name,String l_name,String email)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN1,id);
        contentValues.put(COLUMN2,f_name);
        contentValues.put(COLUMN3,l_name);
        contentValues.put(COLUMN4,email);

        sqLiteDatabase.update(TABLE_NAME,contentValues,"ID = ?",new String[]{ id } );
        return true;
    }
    public int deleted(String id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME,"ID = ?",new String[]{ id } );

    }
}
