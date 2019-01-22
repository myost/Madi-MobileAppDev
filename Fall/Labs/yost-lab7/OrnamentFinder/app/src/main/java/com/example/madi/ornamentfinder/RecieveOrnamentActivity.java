package com.example.madi.ornamentfinder;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecieveOrnamentActivity extends AppCompatActivity {
    private String christmasOrnament;
    private String christmasOrnamentURL;
    private String christmasOrnamentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_ornament);

        //get intent
        Intent intent = getIntent();
        christmasOrnament = intent.getStringExtra("ornamentName");
        christmasOrnamentURL = intent.getStringExtra("ornamentURL");
        christmasOrnamentImage = intent.getStringExtra("ornamentImage");
        //log for error checking
        Log.i("type received", christmasOrnament);
        Log.i("URL received", christmasOrnamentURL);

        //update text view
        TextView messageTextView = findViewById(R.id.ornamentTextView);
        messageTextView.setText("You should buy a "+ christmasOrnament);

        //update image view
        ImageView imageView = findViewById(R.id.ornamentImage);
        //get the resource - code in part from stack overflow
        Resources res = this.getResources();
        int resID = res.getIdentifier(christmasOrnamentImage, "drawable", this.getPackageName());
        imageView.setImageResource(resID);

        //create event listener
        final Button button = findViewById(R.id.jumpToURL);
        View.OnClickListener onclick = new View.OnClickListener(){
            public void onClick(View view){
                loadWebSite(view);
            }
        };
        //add listener to the button
        button.setOnClickListener(onclick);
    }

    public void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(christmasOrnamentURL));
        startActivity(intent);
    }
}
