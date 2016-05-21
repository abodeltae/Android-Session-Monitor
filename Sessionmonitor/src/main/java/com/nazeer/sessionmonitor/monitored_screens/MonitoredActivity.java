package com.nazeer.sessionmonitor.monitored_screens;

import android.support.v7.app.AppCompatActivity;

import com.nazeer.sessionmonitor.SessionHelper;

/**
 * Created by nazeer on 5/20/16.
 */
public class MonitoredActivity extends AppCompatActivity {
    SessionHelper session=new SessionHelper(this);

    @Override
    protected void onResume() {
        super.onResume();
        session.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        session.onStop();
    }
}
