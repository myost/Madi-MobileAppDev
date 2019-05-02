package com.example.fishfood;

import java.time.LocalDateTime;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Time extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    private Date lastFed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastFed() {
        return lastFed;
    }

    public void setLastFed(Date lastFed) {
        this.lastFed = lastFed;
    }
}
