package com.nazeer.sessionmonitor;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.nazeer.sessionmonitor.models.ScreenReportItem;
import com.nazeer.sessionmonitor.models.SessionEntry;
import com.nazeer.sessionmonitor.util.DataBaseHelper;

import java.util.ArrayList;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionMonitorManager {
    private static SessionMonitorManager instance;
    private Application applicationContext;
    private static DataBaseHelper helper;
    private SessionMonitorManager(){}

    public static void init(Application applicationContext){
        helper=new DataBaseHelper(applicationContext);
        instance=new SessionMonitorManager();
        instance.applicationContext=applicationContext;


    }
    public static SessionMonitorManager getInstance(){
        if(instance==null){
            throw new RuntimeException("You must call the method init first");
        }
        return instance;
    }


     void addSessionEntry(SessionEntry entry){
        SQLiteDatabase database = helper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN,entry.getName());
        values.put(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN,entry.getStartTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN,entry.getEndTimeMillis());
        values.put(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,entry.getDurationMillis());
        values.put(DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN,entry.getType());
        database.insert(DataBaseHelper.SESSIONS_TABLE_NAME,null,values);
        database.close();
    }

    private SessionEntry parseSessionEntryFromCursor(Cursor cursor){
        SessionEntry entry=new SessionEntry();
        entry.setId(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ID_COLUMN)));
        entry.setName(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        entry.setStartTimeMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_START_TIME_MILLIS_COLUMN)));
        entry.setEndTimeMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_END_TIME_MILLIS_COLUMN)));
        entry.setDurationMillis(cursor.getLong(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN)));
        entry.setType(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN)));
        return entry;
    }

    /**
    * get a full list of the saved sessions
    * @return  an arraylist containing all the entries logged
    * */
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

    /**
     * gets the entries for the specified screen
     * @param screenName the name of the item you to get its entries
     * @return list of entries for the specified screen
     */

    public ArrayList<SessionEntry> getEntries(String screenName){
        screenName=DatabaseUtils.sqlEscapeString(screenName);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query="select * from "+ DataBaseHelper.SESSIONS_TABLE_NAME+
                " where "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN+" = "+screenName;
        Cursor cursor=database.rawQuery(query,null);
        ArrayList <SessionEntry> list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(parseSessionEntryFromCursor(cursor));
        }
        database.close();
        return list;
    }
    /**
     * get a list of all the saved screen names
     * @return arraylist of strings representing the screen names
     */
    public ArrayList<String > getScreenNames(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=" SELECT DISTINCT "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN+ " FROM "+DataBaseHelper.SESSIONS_TABLE_NAME;
        Cursor cursor=database.rawQuery(query,null);
        ArrayList<String>list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        }
        database.close();
        return list;
    }

    /**
     * get a list of all the saved screen types
     * @return arraylist of strings representing the screen types
     */
    public ArrayList<String>getTypes(){
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=" SELECT DISTINCT "+DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN+ " FROM "+DataBaseHelper.SESSIONS_TABLE_NAME;
        Cursor cursor=database.rawQuery(query,null);
        ArrayList<String>list=new ArrayList<>();
        while (cursor.moveToNext()){
            list.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN)));
        }
        database.close();
        return list;
    }

    /**
     * generates a report for the given screen
     * @param screenName name of the screen
     * @return ScreenReportItem
     */
    public ScreenReportItem getScreenReport(String screenName){
        ScreenReportItem item=new ScreenReportItem();
        item.setName(screenName);
        item.setSessionEntries(getEntries(screenName));
        item.setTotalDuration(getTotalDurationForClass(screenName));
        return item;
    }

    /**
     *  get the sum of duration for the entries with the specified screen name in milliseconds
     * @param screenName
     * @return long the sum of durations in milliseconds
     */
    public long getTotalDurationForClass(String screenName) {
        screenName=DatabaseUtils.sqlEscapeString(screenName);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=String.format("select sum(%s) as sumResult from %s where %s=%s",DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,
                DataBaseHelper.SESSIONS_TABLE_NAME,DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN,screenName);
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            long sum=cursor.getLong(cursor.getColumnIndex("sumResult"));
            return sum;
        }
        return 0;
    }

    /**
     * get the sum of durations for all screens with the given type in milliseconds
     * @param type
     * @return sum of durations or 0 if type not found
     */
    public long getTotalDurationForType(String type) {
        type=DatabaseUtils.sqlEscapeString(type);
        SQLiteDatabase database = helper.getWritableDatabase();
        String query=String.format("select sum(%s) as sumResult from %s where %s=%s",DataBaseHelper.SESSION_TABLE_DURATION_MILLIS_COLUMN,
                DataBaseHelper.SESSIONS_TABLE_NAME,DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN,type);
        Cursor cursor=database.rawQuery(query,null);
        if(cursor.moveToFirst()){
            long sum=cursor.getLong(cursor.getColumnIndex("sumResult"));
            return sum;
        }
        return 0;
    }

    /**
     * get the total duration of the monitored activities
     * @return sum durations im milliseconds
     */
    public long getTotalDurationForActivities(){
        return getTotalDurationForType("activity");
    }

    /**
     * get the total duration of the monitored fragments
     * @return sum durations im milliseconds
     */
    public long getTotalDurationForFragments(){
        return getTotalDurationForType("fragment");
    }
    /**
     * generates a report of the given screens
     * @param screenNames list of the wanted screen names
     * @return arraylist of ScreenReport for the given screen names
     */

    public ArrayList<ScreenReportItem> getReportForScreens(ArrayList<String>screenNames){
        ArrayList <ScreenReportItem>list=new ArrayList<>();
        for (int i = 0; i <screenNames.size() ; i++) {
            list.add(getScreenReport(screenNames.get(i)));
        }
        return list;
    }

    /**
     * generates a full report of all the screens logged and saved
     * @return
     */
    public ArrayList<ScreenReportItem> getReportForAllScreens(){
        ArrayList<String> classNames = getScreenNames();
        return getReportForScreens(classNames);
    }

    public ArrayList<ScreenReportItem> getReportForType(String type){
        ArrayList<String> classNames = getScreenNamesForType(type);
        return getReportForScreens(classNames);
    }
    /**
     * generates a full report of all the screens with type activity
     * @return
     */
    public ArrayList<ScreenReportItem>getReportForActivities(){
        return getReportForType("activity");
    }

    /**
     * generates a full report of all the screens with type fragment
     * @return
     */
    public ArrayList<ScreenReportItem>getReportForFragments(){
        return getReportForType("fragment");
    }


    /**
     * returns screen names that is of the same specified type
     * @param type
     * @return
     */
    public ArrayList<String> getScreenNamesForType(String type) {
        ArrayList <String>names=new ArrayList<>();
        type=DatabaseUtils.sqlEscapeString(type);
        String query ="select DISTINCT "+DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN + " from "+DataBaseHelper.SESSIONS_TABLE_NAME +
                " where "+DataBaseHelper.SESSION_TABLE_ClASS_TYPE_COLUMN +" = "+type;
        SQLiteDatabase database = helper.getReadableDatabase();
        Cursor cursor=database.rawQuery(query,null);
        while (cursor.moveToNext()){
            names.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.SESSION_TABLE_ClASS_NAME_COLUMN)));
        }
        return names;
    }

    /**
     * Deletes all saved data and logs
     */
    public void reset(){
        helper.reset();
    }




}
