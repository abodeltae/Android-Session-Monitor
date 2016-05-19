package com.nazeer.sessionmonitor;

import java.util.ArrayList;

/**
 * Created by nazeer on 19/05/16.
 */
public class ClassReportItem {
    private String name;
    private int totalDuration;
    private ArrayList<SessionEntry> sessionEntries;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public ArrayList<SessionEntry> getSessionEntries() {
        return sessionEntries;
    }

    public void setSessionEntries(ArrayList<SessionEntry> sessionEntries) {
        this.sessionEntries = sessionEntries;
    }
}
