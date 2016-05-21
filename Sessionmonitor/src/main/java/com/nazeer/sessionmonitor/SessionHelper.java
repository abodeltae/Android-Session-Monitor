package com.nazeer.sessionmonitor;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.nazeer.sessionmonitor.models.SessionEntry;

import java.util.Calendar;

/**
 * Created by nazeer on 19/05/16.
 */
public class SessionHelper {
    private String name, type;
    private long startTimeMillis,endTimeMillis,durationInMillis;
    private boolean isResumed=true,isVisible=true;
    public SessionHelper(Activity activity){
        name=activity.getClass().getName();
        type="activity";
    }
    public SessionHelper(Fragment fragment){
        name=fragment.getClass().getName();
        type="fragment";
    }
    public SessionHelper(android.app.Fragment fragment){
        name=fragment.getClass().getName();
        type="fragment";
    }
    public SessionHelper(String name, String type){
        this.name=name;
        this.type=type;
    }

    /**
     * should be used inside onResume method in the activity or fragment to be monitored
     */
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

    /**
     * should be used inside onStop method in the activity or fragment to be monitored
     */
    public void onStop(){
        isResumed=false;
        if(isVisible){
            addEntry();
        }

    }
    /**
     * should be used inside onResume method in the  fragment to be monitored
     */
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
