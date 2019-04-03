package com.example.worldtravel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements ContinentAdapter.ListItemClickListener{

    @Override
    public void onListItemClick(int position){
        Intent intent = new Intent(MainActivity.this, ContinentActivity.class);
        intent.putExtra("continent id", position);
        startActivity(intent);
    }

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
        setContentView(R.layout.activity_main);
        //get the toolbar and set it as app bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get the recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        //define an adapter
        ContinentAdapter adapter = new ContinentAdapter(Continent.continents, this);

        //divider line between rows
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        //assign the adapter to the recycle view
        recyclerView.setAdapter(adapter);

        //set a layout manager on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
