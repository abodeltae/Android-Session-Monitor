package com.nazeer.sessionmonitor;

import android.content.Context;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionMonitor {
    private static SessionMonitor instance;
    private Context context;
    private SessionMonitor(){

    }
    public void init(Context context){

    }
    public static SessionMonitor getInstance(){
        return instance;
    }

}
