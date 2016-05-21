package com.nazeer.sessionmonitor.monitored_screens;

import android.support.v4.app.Fragment;

import com.nazeer.sessionmonitor.SessionHelper;

/**
 * Created by nazeer on 5/20/16.
 */
public class MonitoredSupportFragment extends Fragment {
    SessionHelper session=new SessionHelper(this);
    @Override
    public void onResume() {
        super.onResume();
        session.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        session.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        session.onHiddenChanged(hidden);
    }
}
