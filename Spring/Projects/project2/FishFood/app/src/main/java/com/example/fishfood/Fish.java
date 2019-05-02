package com.example.fishfood;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Fish extends RealmObject{
    @Required
    @PrimaryKey
    private String id;
    private String fish_name;
    private String fish_species;
    private Date fish_birthday;
    private String fish_details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFish_name() {
        return fish_name;
    }

    public void setFish_name(String fish_name) {
        this.fish_name = fish_name;
    }

    public String getFish_species() {
        return fish_species;
    }

    public void setFish_species(String fish_species) {
        this.fish_species = fish_species;
    }

    public Date getFish_birthday() {
        return fish_birthday;
    }

    public void setFish_birthday(Date fish_birthday) {
        this.fish_birthday = fish_birthday;
    }

    public String getFish_details() {
        return fish_details;
    }

    public void setFish_details(String fish_details) {
        this.fish_details = fish_details;
    }
}
