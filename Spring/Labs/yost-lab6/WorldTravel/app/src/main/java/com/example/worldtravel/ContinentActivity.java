package com.example.worldtravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ContinentActivity extends AppCompatActivity {

    public boolean onCreateOptionsMenu(Menu menu){
        //inflate menu to add items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        //get the ID of the item on the action bar that was selected
        switch(item.getItemId()){
            case R.id.explore:
                Intent intent = new Intent(this, ExploreActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);
        //get the toolbar and set it as app bar
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);


        //get continent id from the intent
        int contNum = (Integer)getIntent().getExtras().get("continent id");
        Continent continent = Continent.continents.get(contNum);
        //populate image
        ImageView continentImage = findViewById(R.id.continentImageView);
        continentImage.setImageResource(continent.getImageResourceID());
        //populate name
        TextView continentName = findViewById(R.id.continentName);
        continentName.setText(continent.getName());
    }
}
