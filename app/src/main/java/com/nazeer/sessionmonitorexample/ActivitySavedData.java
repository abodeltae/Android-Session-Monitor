package com.nazeer.sessionmonitorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nazeer.sessionmonitor.ScreenReportItem;
import com.nazeer.sessionmonitor.SessionEntry;
import com.nazeer.sessionmonitor.SessionMonitorManager;

import java.util.ArrayList;

public class ActivitySavedData extends AppCompatActivity {

    private SessionMonitorManager sessionMonitor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_saved_data);
        sessionMonitor=SessionMonitorManager.getInstance();
        ArrayList<String> entrynames = sessionMonitor.getEntryNames();
        ArrayList<ScreenReportItem> report = sessionMonitor.getReportForAllclasses();
        ArrayList<String> types = sessionMonitor.getTypes();
        long activitiesDuration = sessionMonitor.getTotalDurationForType("activity");
        ArrayList<SessionEntry> entries = sessionMonitor.getEntries();

        Log.w("","");
    }
}
