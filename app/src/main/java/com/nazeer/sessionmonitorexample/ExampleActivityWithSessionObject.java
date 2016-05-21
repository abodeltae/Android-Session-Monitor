package com.nazeer.sessionmonitorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nazeer.sessionmonitor.Session;

public class ExampleActivityWithSessionObject extends AppCompatActivity {
Session session=new Session(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

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
