package com.nazeer.sessionmonitorexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.nazeer.sessionmonitor.models.ScreenReportItem;
import com.nazeer.sessionmonitor.SessionMonitorManager;
import com.nazeer.sessionmonitorexample.adapters.ReportAdapter;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    SessionMonitorManager manager;
    private ArrayList<ScreenReportItem> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        manager = SessionMonitorManager.getInstance();
        iniViews();
        getData();
        updateUi();
    }

    private void updateUi() {

        ReportAdapter adapter = new ReportAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void iniViews() {
        listView=(ListView)findViewById(R.id.listView);
    }

    public void getData() {
            String type=getIntent().getStringExtra("type");
        if("activity".equals(type)){
            list=manager.getReportForActivities();
        }
        else if("fragment".equals("type")){
            list=manager.getReportForFragments();
        }
        else{
            list=manager.getReportForType(type);
        }
    }
}
