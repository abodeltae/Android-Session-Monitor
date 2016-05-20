package com.nazeer.sessionmonitor;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.Calendar;

/**
 * Created by nazeer on 19/05/16.
 */
public class Session {
    private String name, type;
    private long startTimeMillis,endTimeMillis,durationInMillis;
    private boolean isResumed=true,isVisible=true;
    public Session(Activity activity){
        name=activity.getClass().getName();
        type="activity";
    }
    public Session (Fragment fragment){
        name=fragment.getClass().getName();
        type="fragment";
    }
    public Session (android.app.Fragment fragment){
        name=fragment.getClass().getName();
        type="fragment";
    }
    public Session(String name,String type){
        this.name=name;
        this.type=type;
    }
    public void onResume(){
        isResumed=true;
        if(isVisible){
           setStartTime();
        }

    }

    private void setStartTime() {
        Calendar calendar=Calendar.getInstance();
        startTimeMillis=calendar.getTimeInMillis();
    }

    public void onStop(){
        isResumed=false;
        if(isVisible){
            addEntry();
        }

    }
    public void onHiddenChanged(boolean hidden){
        if(hidden){
            isVisible=false;
            if(isResumed){
                addEntry();
            }
        }
        else{
            isVisible=true;
            if(isResumed){
                setStartTime();
            }
        }
    }

    private void addEntry() {
        Calendar calendar=Calendar.getInstance();
        endTimeMillis=calendar.getTimeInMillis();
        durationInMillis=endTimeMillis-startTimeMillis;
        SessionEntry entry=new SessionEntry();
        entry.setName(name);
        entry.setType(type);
        entry.setStartTimeMillis(startTimeMillis);
        entry.setEndTimeMillis(endTimeMillis);
        entry.setDurationMillis(durationInMillis);
        SessionMonitorManager manager=SessionMonitorManager.getInstance();
        if(manager!=null){
            manager.addSessionEntry(entry);
        }
    }
}
