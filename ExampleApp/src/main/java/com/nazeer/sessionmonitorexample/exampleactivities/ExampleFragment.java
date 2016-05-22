package com.nazeer.sessionmonitorexample.exampleactivities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nazeer.sessionmonitor.monitored_screens.MonitoredSupportFragment;
import com.nazeer.sessionmonitorexample.R;

/**
 * Created by nazeer on 5/20/16.
 */
public class ExampleFragment extends MonitoredSupportFragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment,container,false);
        return rootView;
    }
}
