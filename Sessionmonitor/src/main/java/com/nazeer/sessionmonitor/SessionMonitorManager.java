package com.nazeer.sessionmonitor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionMonitorManager {
    private static SessionMonitorManager instance;
    private Context context;
    private DataBaseHelper helper;
    private SessionMonitorManager(){

    }
    public void init(Context context){
        helper=new DataBaseHelper(context);

    }
    public static SessionMonitorManager getInstance(){
        return instance;
    }

    public void addSessionEntry(SessionEntry entry){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN,entry.getName());
        values.put(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN,entry.getStartTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN,entry.getEndTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,entry.getEndTimeMillis()-entry.getStartTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN,entry.getType());
        database.insert(DataBaseHelper.SESSIONS_TABLE_NAME,null,values);
        database.close();
    }

    public SessionEntry parseSessionEntryFromCursor(Cursor cursor){
        SessionEntry entry=new SessionEntry();
        entry.setId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ID_COLUMN)));
        entry.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        entry.setStartTimeMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN)));
        entry.setEndTimeMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN)));
        entry.setDurationMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN)));
        entry.setType(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN)));
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

    public ArrayList<String >getEntryNames(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=" SELECT DISTINCT "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN+ " FROM "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN;
        Cursor cursor=database.rawQuery(query,null);
        ArrayList<String>list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        }
        database.close();
        return list;
    }
    public ArrayList<String>getTypes(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=" SELECT DISTINCT "+DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN+ " FROM "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN;
        Cursor cursor=database.rawQuery(query,null);
        ArrayList<String>list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        }
        database.close();
        return list;
    }

    public ClassReportItem getClassReport(String className){
        ClassReportItem item=new ClassReportItem();
        item.setName(className);
        item.setSessionEntries(getEntries(className));
        item.setTotalDuration(getTotalDurationForClass(className));
        return item;
    }

    public long getTotalDurationForClass(String className) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=String.format("select sum(%s) from %s where %s=%s",DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,
                DataBaseHelper.SESSIONS_TABLE_NAME,DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN,className);
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            long sum=cursor.getLong(cursor.getColumnIndex("sum"));
            return sum;
        }
        return 0;
    }

    public long getTotalDurationForType(String type) {
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=String.format("select sum(%s) from %s where %s=%s",DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,
                DataBaseHelper.SESSIONS_TABLE_NAME,DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN,type);
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            long sum=cursor.getLong(cursor.getColumnIndex("sum"));
            return sum;
        }
        return 0;
    }


    public ArrayList<ClassReportItem> getReportForClasses(ArrayList<String>classNames){
        ArrayList <ClassReportItem>list=new ArrayList<>();
        for (int i = 0; i <classNames.size() ; i++) {
            list.add(getClassReport(classNames.get(i)));
        }
        return list;
    }

     public ArrayList<ClassReportItem> getReportForAllclasses(){
        ArrayList<String> classNames = getEntryNames();
        return getReportForClasses(classNames);
    }




}
