package com.nazeer.sessionmonitor;

import android.app.Fragment;

/**
 * Created by nazeer on 5/20/16.
 */
public class MonitoredFragment extends Fragment {
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
