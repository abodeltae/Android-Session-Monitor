package com.nazeer.sessionmonitor;

import java.util.ArrayList;

/**
 * Created by nazeer on 19/05/16.
 */
public class ClassReportItem {
    private String name;
    private long totalDuration;
    private ArrayList<SessionEntry> sessionEntries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ArrayList<SessionEntry> getSessionEntries() {
        return sessionEntries;
    }

    public void setSessionEntries(ArrayList<SessionEntry> sessionEntries) {
        this.sessionEntries = sessionEntries;
    }
}
