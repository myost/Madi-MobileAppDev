package com.example.madi.ornamentfinder;

public class ChristmasOrnament {
    private String christmasOrnament;
    private String christmasOrnamentURL;
    private String christmasOrnamentImage;
    private void setOrnamentInfo(Integer ornamentType){
        switch (ornamentType){
            case 0: //classy
                christmasOrnament = "gold snowflake ornament";
                christmasOrnamentImage = "goldsnowflake";
                christmasOrnamentURL = "https://www.worldmarket.com/product/gold-metal-snowflake-ornaments-set-of-2.do?sortby=ourPicks";
                break;
            case 1: //retro
                christmasOrnament = "ice skate ornament";
                christmasOrnamentImage = "iceskate";
                christmasOrnamentURL = "https://www.worldmarket.com/product/retro-paper-pulp-ice-skate-ornaments-set-of-4.do?sortby=ourPicks";
                break;
            case 2: //natural
                christmasOrnament = "pine cone ornament";
                christmasOrnamentImage = "pinecone";
                christmasOrnamentURL = "https://www.worldmarket.com/product/12-pack-glitter-pinecone-boxed-ornaments-set-of-2.do?sortby=ourPicks";
                break;
            case 3: //cheesy
                christmasOrnament = "hot dog dachshund ornament";
                christmasOrnamentImage = "daschund";
                christmasOrnamentURL = "https://www.worldmarket.com/product/glass-hot-dog-dachshund-ornament.do?sortby=ourPicks";
                break;
            default: //catch case
                christmasOrnament = "none";
                christmasOrnamentImage = "ornamentgreen";
                christmasOrnamentURL = "https://www.google.com/search?q=christmas+ornament&oq=christmas+ornament&ie=utf-8&oe=utf-8";
        }
    }
    public void setChristmasOrnament(Integer ornamentType){
        setOrnamentInfo(ornamentType);
    }
    public void setChristmasOrnamentURL(Integer ornamentType){
        setOrnamentInfo(ornamentType);
    }
    public void setChristmasOrnamentImage(Integer ornamentType){
        setOrnamentInfo(ornamentType);
    }
    public String getChristmasOrnament(){
        return christmasOrnament;
    }
    public String getChristmasOrnamentURL(){
        return christmasOrnamentURL;
    }
    public String getChristmasOrnamentImage(){
        return christmasOrnamentImage;
    }
}
