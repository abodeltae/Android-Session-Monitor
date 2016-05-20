package com.nazeer.sessionmonitor;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nazeer on 5/20/16.
 */
public class MonitoredActivity extends AppCompatActivity {
    Session session=new Session(this);

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
