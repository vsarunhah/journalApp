package com.android.varun.journalentry.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Varun on 6/17/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    //    Create table query - SQLite
    private static final String CREATE_TABLE = "create table " +
            constants.TABLE_NAME  + " (" +
            constants.KEY_ID + " integer primary key autoincrement, " +
            constants.TITLE_NAME + " text not null, " +
            constants.MOOD + " text not null, " +
            constants.DETAIL_NAME + " text not null, " +
            constants.DATE_NAME + " text not null) " ;



    public DBHelper(Context c) {
        super(c, constants.DATABASE_NAME, null, constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + constants.TABLE_NAME);
        onCreate(db);
    }
}
