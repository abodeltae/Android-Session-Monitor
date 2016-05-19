package com.nazeer.sessionmonitor;

/**
 * Created by nazeer on 18/05/16.
 */
public class SessionEntry {
    private String name ,type ;
    private int id, startTimeMillis,endTimeMillis,durationMillis;

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

    public int getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(int durationMillis) {
        this.durationMillis = durationMillis;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
