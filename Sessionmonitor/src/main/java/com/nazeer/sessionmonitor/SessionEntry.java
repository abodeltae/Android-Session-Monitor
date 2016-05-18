package com.nazeer.sessionmonitor;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionEntry {
    private String name ;
    private int id, startTimeMillis,endTimeMillis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(int startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public int getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(int endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }
}
