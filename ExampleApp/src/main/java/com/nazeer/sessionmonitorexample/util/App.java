package com.nazeer.sessionmonitorexample.util;

import android.app.Application;

import com.nazeer.sessionmonitor.SessionMonitorManager;

/**
 * Created by nazeer on 5/20/16.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SessionMonitorManager.init(this);
    }
}
