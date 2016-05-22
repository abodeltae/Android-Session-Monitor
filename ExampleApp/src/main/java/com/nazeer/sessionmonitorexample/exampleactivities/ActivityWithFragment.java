package com.nazeer.sessionmonitorexample.exampleactivities;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.nazeer.sessionmonitor.monitored_screens.MonitoredActivity;
import com.nazeer.sessionmonitorexample.R;

public class ActivityWithFragment extends MonitoredActivity implements View.OnClickListener {
    Button replaceBt,hideBt,showBt;
    RelativeLayout fragmentContainer;
    private ExampleFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        initViews();
    }

    private void initViews() {
        replaceBt=(Button)findViewById(R.id.replaceFragmentButton);
        hideBt=(Button)findViewById(R.id.hideBt);
        showBt=(Button)findViewById(R.id.showBt);
        fragmentContainer=(RelativeLayout)findViewById(R.id.fragmentContainer);
        replaceBt.setOnClickListener(this);
        hideBt.setOnClickListener(this);
        showBt.setOnClickListener(this);
        hideBt.setVisibility(View.INVISIBLE);
        showBt.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.replaceFragmentButton:
                replaceFragment();
                break;
            case R.id.showBt:
                showFragment();
                break;
            case R.id.hideBt:
                hideFragment();
                break;

        }
    }

    private void hideFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    private void replaceFragment() {
        fragment=new ExampleFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.commit();
        hideBt.setVisibility(View.VISIBLE);
        showBt.setVisibility(View.VISIBLE);
    }
}
