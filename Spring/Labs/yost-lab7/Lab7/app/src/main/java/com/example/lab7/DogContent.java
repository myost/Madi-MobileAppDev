package com.example.lab7;

import android.content.res.Resources;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DogContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Dog> ITEMS = new ArrayList<Dog>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Dog> ITEM_MAP = new HashMap<String, Dog>();


    public void dataSetup(){
        List<DogContent.Dog> xmlData = new ArrayList<DogContent.Dog>();
        DataActivty xmlDataActivity = new DataActivty();
        if (ITEMS.size() == 0) {
            try {
                xmlData = xmlDataActivity.loadXML();
                for (int i = 0; i < xmlData.size(); i++) {
                    addDog(xmlData.get(i));
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addDog(Dog dog) {
        ITEMS.add(dog);
        ITEM_MAP.put(dog.getId(), dog);
    }

    public static class Dog {
        private String name;
        private String imgFile;
        private String description;
        private int imageResourceID;
        private String id;

        public Dog(String newId, String newName, String newDescr, String newImgName) {
            this.id = newId;
            this.name = newName;
            this.imgFile = newImgName;
            Resources res = MyApplication.getAppContext().getResources();
            this.imageResourceID = res.getIdentifier(newImgName, "drawable", MyApplication.getAppContext().getPackageName());
            this.description = newDescr;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getImgFileName() {
            return imgFile;
        }

        public int getImageResourceID() {
            return imageResourceID;
        }

        public String getId(){
            return id;
        }

        public String toString() {
            return this.name;
        }
    }

}
