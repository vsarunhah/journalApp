package com.android.varun.journalentry.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.android.varun.journalentry.MainActivity;
import com.android.varun.journalentry.R;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyDB {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public MyDB (Context c)
    {
        dbHelper = new DBHelper(c);
    }

    public void open(){

        db = dbHelper.getWritableDatabase();
    }

    public void close(){

        db.close();
    }

    public long insertEntry(String title, String mood, String detail, String date){

        ContentValues cv = new ContentValues();
        cv.put(constants.TITLE_NAME, title);
        cv.put(constants.DATE_NAME, date);
        cv.put(constants.MOOD, mood);
        cv.put(constants.DETAIL_NAME, detail);
        return db.insert(constants.TABLE_NAME, null, cv);
    }

    public Cursor getEntries(){
        //Show what from table. null = eveything
        return dbHelper.getReadableDatabase().query(constants.TABLE_NAME, null, null, null, null, null, null);
    }


    public int updateEntry(String title, String mood, String detail, String date, int id) {
        ContentValues cv = new ContentValues();
        cv.put(constants.TITLE_NAME, title);
        cv.put(constants.DATE_NAME, date);
        cv.put(constants.MOOD, mood);
        cv.put(constants.DETAIL_NAME, detail);

        return db.update(constants.TABLE_NAME, cv, "_id=" + id, null);
    }

    public int deleteEntry (int id){
        return db.delete(constants.TABLE_NAME, "_id=" + id, null);
    }
    public int deleteAll (String tableName){
        return db.delete(tableName, null, null);
    }
}
