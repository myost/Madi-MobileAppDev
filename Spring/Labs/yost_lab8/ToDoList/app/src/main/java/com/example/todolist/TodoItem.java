package com.example.todolist;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class TodoItem extends RealmObject {
    @Required
    @PrimaryKey
    private String id;
    private String name;
    private Boolean done;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDone(){
        return done;
    }

    public void setDone(Boolean check){
        done = check;
    }
}
