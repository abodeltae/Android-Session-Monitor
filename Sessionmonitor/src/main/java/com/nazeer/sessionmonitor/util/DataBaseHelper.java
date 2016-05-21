package com.nazeer.sessionmonitor.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nazeer on 18/05/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="MONITOR_DB";
    private static final int DATABASE_VERSION=2;
    private static  String buildSessionsTableQuery;
    public static final String SESSIONS_TABLE_NAME="SESSIONS_TABLE";
    public static final String SESSION_TABLE_ID_COLUMN="ID", SESSION_TABLE_ClASS_NAME_COLUMN ="NAME"
            , SESSION_TABLE_START_TIME_MILLIS_COLUMN ="StartTimeMILLIS", SESSION_TABLE_END_TIME_MILLIS_COLUMN ="ENDTimeMILLIS",
            SESSION_TABLE_DURATION_MILLIS_COLUMN="DurationMillis",SESSION_TABLE_ClASS_TYPE_COLUMN="ClassType";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        makeBuildSessionsTableQuery();
    }

    private void makeBuildSessionsTableQuery() {

        buildSessionsTableQuery="CREATE TABLE "+SESSIONS_TABLE_NAME +"( "+
                  SESSION_TABLE_ID_COLUMN+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , " +
                SESSION_TABLE_START_TIME_MILLIS_COLUMN +" INTEGER NOT NULL ," +
                SESSION_TABLE_END_TIME_MILLIS_COLUMN +" INTEGER NOT NULL , " +
                SESSION_TABLE_DURATION_MILLIS_COLUMN+ " INTEGER NOT NULL , "+
                SESSION_TABLE_ClASS_NAME_COLUMN+" TEXT NOT NULL , " +
                SESSION_TABLE_ClASS_TYPE_COLUMN+" TEXT "+
                ");";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(buildSessionsTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + SESSIONS_TABLE_NAME);
        onCreate(db);
    }

    public void reset(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + SESSIONS_TABLE_NAME);
        onCreate(db);
        db.close();
    }
}
