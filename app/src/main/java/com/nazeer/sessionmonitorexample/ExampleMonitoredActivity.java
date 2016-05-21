package com.nazeer.sessionmonitorexample;

import android.os.Bundle;

import com.nazeer.sessionmonitor.monitored_screens.MonitoredActivity;

public class ExampleMonitoredActivity extends MonitoredActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
    }
}
