package com.example.worldtravel;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    private String name;
    private int imageResourceID;

    //constructor
    private Continent(String newname, int newID){
        this.name = newname;
        this.imageResourceID = newID;
    }

    public static List<Continent> continents = new ArrayList<Continent>(){{
        add(new Continent("Africa", R.drawable.africa));
        add(new Continent("Antarctica", R.drawable.antartica));
        add(new Continent("Asia", R.drawable.asia));
        add(new Continent("Europe", R.drawable.europe));
        add(new Continent("North America", R.drawable.north_america));
        add(new Continent("Oceania", R.drawable.oceania));
        add(new Continent("South America", R.drawable.south_america));
    }};

    public String getName(){
        return name;
    }

    public int getImageResourceID(){
        return imageResourceID;
    }

    //the string representation of a tulip is its name
    public String toString(){
        return this.name;
    }
}
