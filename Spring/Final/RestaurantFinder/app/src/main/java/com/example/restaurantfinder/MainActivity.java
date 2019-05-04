package com.example.restaurantfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RestaurantAdapter.ListItemClickListener{
    private Realm realm;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        RealmResults<Restaurant> restaurants = realm.where(Restaurant.class).findAll();

        restaurantAdapter = new RestaurantAdapter(restaurants, this, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = new LinearLayout(MainActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText nameEditText = new EditText(MainActivity.this);
                nameEditText.setHint("Restaurant name");
                layout.addView(nameEditText);

                final EditText urlEditText = new EditText(MainActivity.this);
                urlEditText.setHint("Restaurant web site");
                layout.addView(urlEditText);

                //create an alert dialog
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Add Restaurant");
                dialog.setView(layout);
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String newRestaurantName = nameEditText.getText().toString();
                        final String newRestaurantUrl = urlEditText.getText().toString();
                        if(!newRestaurantName.isEmpty() && !newRestaurantUrl.isEmpty()){
                            addRestaurant(UUID.randomUUID().toString(), newRestaurantName, newRestaurantUrl);
                        }
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void addRestaurant(final String newId, final String newName, final String newUrl){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Restaurant newRestaurant = realm.createObject(Restaurant.class, newId);
                newRestaurant.setName(newName);
                newRestaurant.setUrl(newUrl);
            }
        });
    }

    private void changeRestaurant(final String restaurantId, final String restaurantName, final String restaurantUrl){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Restaurant restaurant = realm.where(Restaurant.class).equalTo("id", restaurantId).findFirst();
                restaurant.setName(restaurantName);
                restaurant.setUrl(restaurantUrl);
            }
        });
    }

    public void editRestaurant(final String restId){
        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        Restaurant restaurant = realm.where(Restaurant.class).equalTo("id", restId).findFirst();

        final EditText restNameEditText = new EditText(MainActivity.this);
        restNameEditText.setText(restaurant.getName());
        layout.addView(restNameEditText);

        final EditText restUrlEditText = new EditText(MainActivity.this);
        restUrlEditText.setText(restaurant.getUrl());
        layout.addView(restUrlEditText);

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Edit Restaurant");
        dialog.setView(layout);
        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String updatedRestName = restNameEditText.getText().toString();
                final String updatedRestUrl = restUrlEditText.getText().toString();
                if(!updatedRestName.isEmpty() && !updatedRestUrl.isEmpty()){
                    changeRestaurant(restId, updatedRestName, updatedRestUrl);
                }
            }
        });
        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRestaurant(restId);
            }
        });
        dialog.create();
        dialog.show();
    }

    private void deleteRestaurant(final String restId){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(Restaurant.class).equalTo("id", restId).findFirst()
                        .deleteFromRealm();
            }
        });
    }

    @Override
    public void onListItemClick(Restaurant restaurant) {

        Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
        intent.putExtra("name", restaurant.getName());
        intent.putExtra("url", restaurant.getUrl());
        startActivity(intent);
    }
}
