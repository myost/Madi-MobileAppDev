package com.example.madi.fallfun;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayMessage(View view){
        TextView fallMessage = findViewById(R.id.message);
        EditText name = findViewById(R.id.editText2);
        String nameValue = name.getText().toString();
        EditText activity = findViewById(R.id.editText);
        String activityValue = activity.getText().toString();
        fallMessage.setText(nameValue + "'s favorite fall activity is "+activityValue);
        ImageView leaves = findViewById(R.id.imageView);
        leaves.setImageResource(R.drawable.leaves);
    }
}
