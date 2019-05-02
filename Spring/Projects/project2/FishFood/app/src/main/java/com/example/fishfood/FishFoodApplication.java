package com.example.fishfood;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class FishFoodApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize realm
        Realm.init(this);
        //define the configuration for realm
        RealmConfiguration realmConfig = new RealmConfiguration.Builder().build();
        //set the default realm configuration
//        Realm.deleteRealm(realmConfig);
        Realm.setDefaultConfiguration(realmConfig);
    }
}
