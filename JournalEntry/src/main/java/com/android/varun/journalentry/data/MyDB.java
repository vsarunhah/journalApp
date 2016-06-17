package com.android.varun.journalentry.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.widget.Toast;

/**
 * Created by Varun on 6/17/16.
 */
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
}