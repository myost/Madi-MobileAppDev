package com.example.madi.ornamentfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class FindOrnamentActivity extends AppCompatActivity {
    private ChristmasOrnament myChristmasOrnament = new ChristmasOrnament();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ornament);

        final Button button = findViewById(R.id.button);
        View.OnClickListener buttonClick = new View.OnClickListener(){
            public void onClick(View view){
                findChristmasOrnament(view);
            }
        };
        button.setOnClickListener(buttonClick);
    }

    public void findChristmasOrnament(View view){
        //get the spinner
        Spinner typeSpinner = findViewById(R.id.spinner);
        //get the user's selected ornament type
        Integer type = typeSpinner.getSelectedItemPosition();
        //set the ornament type - calls class ChristmasOrnament
        myChristmasOrnament.setChristmasOrnament(type);
        //get the ornament name and the URL
        String suggestedOrnament = myChristmasOrnament.getChristmasOrnament();
        String suggestedOrnamentURL = myChristmasOrnament.getChristmasOrnamentURL();
        String suggestedOrnamentImage = myChristmasOrnament.getChristmasOrnamentImage();
        //logging for debugging purposes
//        Log.i("ornament",suggestedOrnament);
//        Log.i("URL", suggestedOrnamentURL);
        //explicit intent
        //create an intent
        Intent intent = new Intent(this, RecieveOrnamentActivity.class);
        //pass data to intent
        intent.putExtra("ornamentName", suggestedOrnament);
        intent.putExtra("ornamentURL", suggestedOrnamentURL);
        intent.putExtra("ornamentImage", suggestedOrnamentImage);
        //now start intent
        startActivity(intent);
    }
}
