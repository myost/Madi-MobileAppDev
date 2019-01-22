package com.example.madi.yostfinal;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PizzeriaActivity extends AppCompatActivity {

    private String pizzeriaName;
    private String pizzeriaURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzeria);

        //get intent
        Intent intent = getIntent();
        pizzeriaName = intent.getStringExtra("pizzeriaName");
        pizzeriaURL = intent.getStringExtra("pizzeriaURL");
        TextView pizzeriaSuggestion = findViewById(R.id.pizzeriaTextView);
        String suggestion = "You should check out "+pizzeriaName+"!";
        pizzeriaSuggestion.setText(suggestion);

        //get image button to create implicit intent
        ImageButton imageButton = findViewById(R.id.imageButton);

        //create onClick listener for button
        View.OnClickListener imgBtnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadWebsite(v);
            }
        };
        //add listener to the button
        imageButton.setOnClickListener(imgBtnClick);

    }

    public void loadWebsite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(pizzeriaURL));
        startActivity(intent);
    }
}
