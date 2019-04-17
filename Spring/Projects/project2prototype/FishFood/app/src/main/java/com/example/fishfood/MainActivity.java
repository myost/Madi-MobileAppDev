package com.example.fishfood;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Fragment fragment;
    public Realm realm;

    //function that will launch whatever fragment we pass in
    private void loadFragment(Fragment fragment){
        //check that the fragment exists
        if(fragment != null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, fragment);
            transaction.commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //set all icons back to outlines
            BottomNavigationView bottom_nav = findViewById(R.id.navigation);
            bottom_nav.getMenu().findItem(R.id.navigation_tank).setIcon(R.drawable.ic_tank);
            bottom_nav.getMenu().findItem(R.id.navigation_shop).setIcon(R.drawable.ic_shop);
            bottom_nav.getMenu().findItem(R.id.navigation_feed).setIcon(R.drawable.ic_feed);
            //check which item has been selected
            switch (item.getItemId()) {
                case R.id.navigation_tank:
                    //set selected item to solid fill
                    item.setIcon(R.drawable.ic_tank_solid);
                    getSupportActionBar().setTitle(R.string.title_tank);
                    //load the tank fragment
                    fragment = new TankFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_feed:
                    //set selected item to solid fill
                    item.setIcon(R.drawable.ic_feed_solid);
                    getSupportActionBar().setTitle(R.string.title_feed);
                    //load the feed fragment
                    fragment = new FeedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_shop:
                    //set selected item to solid fill
                    item.setIcon(R.drawable.ic_shop_solid);
                    getSupportActionBar().setTitle(R.string.title_shop);
                    //load the shop fragment
                    fragment = new ShopFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get the navigation menu and set a listener for when an item is selected
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //set the tank icon to solid (that is the default fragment that is opened)
        navigation.getMenu().findItem(R.id.navigation_tank).setIcon(R.drawable.ic_tank_solid);
        //load the tank fragment - default case when app is launched
        loadFragment(new TankFragment());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_tank);

        //initialize the realm instance
        realm = Realm.getDefaultInstance();
        Log.i("realm: ", realm.getPath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //close the realm instance when activity exits
        realm.close();
    }
}
