package com.nazeer.sessionmonitorexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nazeer.sessionmonitor.MonitoredActivity;

public class MainActivity extends MonitoredActivity implements View.OnClickListener {
    Button button1,button2,button3,showSavedDataBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        showSavedDataBt=(Button)findViewById(R.id.buttonShowaData);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        showSavedDataBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.button1:
                startActivity(new Intent(this,Activity1.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,Activity2.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this,Activity3.class));
                break;
            case R.id.buttonShowaData:
                startActivity(new Intent(this,ActivitySavedData.class));
                break;
        }
    }
}
