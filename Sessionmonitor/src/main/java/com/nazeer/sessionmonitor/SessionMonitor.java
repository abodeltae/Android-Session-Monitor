package com.nazeer.sessionmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionMonitor {
    private static SessionMonitor instance;
    private Context context;
    private DataBaseHelper helper;
    private SessionMonitor(){

    }
    public void init(Context context){
        helper=new DataBaseHelper(context);

    }
    public static SessionMonitor getInstance(){
        return instance;
    }

    public void addSessionEntry(SessionEntry entry){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN,entry.getName());
        values.put(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN,entry.getStartTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN,entry.getEndTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,entry.getEndTimeMillis()-entry.getStartTimeMillis());
        database.insert(DataBaseHelper.SESSIONS_TABLE_NAME,null,values);
        database.close();
    }

    public SessionEntry parseSessionEntryFromCursor(Cursor cursor){
        SessionEntry entry=new SessionEntry();
        entry.setId(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ID_COLUMN)));
        entry.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        entry.setStartTimeMillis(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN)));
        entry.setEndTimeMillis(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN)));
        entry.setDurationMillis(cursor.getInt(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN)));
        return entry;
    }

    public ArrayList<SessionEntry> getEntries(){
        SQLiteDatabase database = helper.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from "+DataBaseHelper.SESSIONS_TABLE_NAME,null);
        ArrayList <SessionEntry> list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(parseSessionEntryFromCursor(cursor));
        }
        database.close();
        return list;
    }

    public ArrayList<SessionEntry> getEntries(String className){
        SQLiteDatabase database = helper.getWritableDatabase();
        String query="select * from "+ DataBaseHelper.SESSIONS_TABLE_NAME+
                " where "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN+" = "+className;
        Cursor cursor=database.rawQuery(query+DataBaseHelper.SESSIONS_TABLE_NAME,null);
        ArrayList <SessionEntry> list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(parseSessionEntryFromCursor(cursor));
        }
        database.close();
        return list;
    }



}
