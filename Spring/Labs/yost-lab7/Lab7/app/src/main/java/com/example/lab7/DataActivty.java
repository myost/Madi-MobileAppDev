package com.example.lab7;

import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataActivty {
    public List<DogContent.Dog>loadXML() throws XmlPullParserException, IOException{
        String new_name = new String();
        String new_descr = new String();
        String new_img = new String();
        int id_counter = 0;
        List<DogContent.Dog>dogs = new ArrayList<DogContent.Dog>();
        //string for debugging
        StringBuffer stringBuffer = new StringBuffer();
        //get xml file
        XmlResourceParser xpp = MyApplication.getAppContext().getResources().getXml(R.xml.dogs);
        //advances parser to next event
        xpp.next();
        //get the event type/state of parser
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT){
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    //start of document
                    break;
                case XmlPullParser.START_TAG:
                    if(xpp.getName().equals("dog")){
                        stringBuffer.append("\nSTART_TAG: "+xpp.getName());
                    }
                    else if(xpp.getName().equals("name")) {
                        stringBuffer.append("\nSTART_TAG: " + xpp.getName());
                        eventType = xpp.next();
                        new_name = xpp.getText();
                    }
                    else if(xpp.getName().equals("descr")){
                        stringBuffer.append("\nSTART_TAG: "+xpp.getName());
                        eventType = xpp.next();
                        new_descr = xpp.getText();
                    }
                    else if(xpp.getName().equals("img")){
                        stringBuffer.append("\nSTART_TAG: "+xpp.getName());
                        eventType = xpp.next();
                        new_img = xpp.getText();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(xpp.getName().equals("dog")){
                        id_counter++;
                        DogContent.Dog new_dog = new DogContent.Dog(String.valueOf(id_counter), new_name, new_descr, new_img);
                        dogs.add(new_dog);
                    }
                    break;
                case XmlPullParser.TEXT:
                    break;
            }
            eventType = xpp.next();
        }
        return dogs;
    }
}
