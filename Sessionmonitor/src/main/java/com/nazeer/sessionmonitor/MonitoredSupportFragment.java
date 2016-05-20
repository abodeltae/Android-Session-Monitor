package com.nazeer.sessionmonitor;

import android.support.v4.app.Fragment;

/**
 * Created by nazeer on 5/20/16.
 */
public class MonitoredSupportFragment extends Fragment {
    Session session=new Session(this);
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
