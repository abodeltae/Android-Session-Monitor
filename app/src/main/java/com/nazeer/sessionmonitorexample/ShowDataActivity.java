package com.nazeer.sessionmonitorexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nazeer.sessionmonitor.models.ScreenReportItem;
import com.nazeer.sessionmonitor.models.SessionEntry;
import com.nazeer.sessionmonitor.SessionMonitorManager;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity implements View.OnClickListener {
    TextView activitiesTimeTv ,fragmentsTimeTv;
    Button activityDetailsBt,fragmnetDetailBt,resetBt;
    private SessionMonitorManager sessionMonitor;
    private long activitiesDuration,fragmentsDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_saved_data);
        sessionMonitor=SessionMonitorManager.getInstance();
/*        ArrayList<String> entrynames = sessionMonitor.getScreenNames();
        ArrayList<ScreenReportItem> report = sessionMonitor.getReportForAllScreens();
        ArrayList<String> types = sessionMonitor.getTypes();
        long activitiesDuration = sessionMonitor.getTotalDurationForType("activity");
        ArrayList<SessionEntry> entries = sessionMonitor.getEntries();
        ArrayList<ScreenReportItem> fragmentsReport = sessionMonitor.getReportForType("fragment");
        ArrayList<ScreenReportItem>activitiesReport=sessionMonitor.getReportForType("activity");

        Log.w("","");*/
        initViews();
        getData();updateUi();
    }

    private void initViews() {
        activitiesTimeTv=(TextView)findViewById(R.id.activitiesTimeTv);
        fragmentsTimeTv=(TextView)findViewById(R.id.fragmentsTimeTv);
        activityDetailsBt=(Button)findViewById(R.id.actvitiesDetailsBt);
        fragmnetDetailBt=(Button)findViewById(R.id.frgmentsDetailsBt);
        resetBt=(Button)findViewById(R.id.resetBt);
        activityDetailsBt.setOnClickListener(this);
        fragmnetDetailBt.setOnClickListener(this);
        resetBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id =view.getId();
        switch (id){
            case R.id.resetBt:
                reset();
                break;
            case R.id.actvitiesDetailsBt:
                showActivitiesDetials();
                break;
            case R.id.frgmentsDetailsBt:
                showFragmentsDetails();
                break;
        }
    }


    private void getData(){
         activitiesDuration = sessionMonitor.getTotalDurationForActivities();
        fragmentsDuration=sessionMonitor.getTotalDurationForFragments();

    }
    private void updateUi(){
        activitiesTimeTv.setText(activitiesDuration/1000+" seconds");
        fragmentsTimeTv.setText(fragmentsDuration/1000+ " seconds");
    }
    private void showFragmentsDetails() {
        Intent intent=new Intent(this,ReportActivity.class);
        intent.putExtra("type","fragment");
        startActivity(intent);
    }

    private void showActivitiesDetials() {
        Intent intent=new Intent(this,ReportActivity.class);
        intent.putExtra("type","activity");
        startActivity(intent);
    }

    private void reset() {
        sessionMonitor.reset();
        getData();
        updateUi();
    }
}
