package com.example.madi.outfitpicker;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void findOutfit(View view){
        ToggleButton toggle=findViewById(R.id.toggleButton2);
        boolean style = toggle.isChecked();
        String outfit;
        Spinner season = findViewById(R.id.spinner);
        String selectedSeason = String.valueOf(season.getSelectedItem());
        RadioGroup temp = findViewById(R.id.radioGroup);
        ImageView weather = findViewById(R.id.imageView);
        int temp_id = temp.getCheckedRadioButtonId();
        if(temp_id == -1){
            //toast popup to select a temperature
            Context context = getApplicationContext();
            CharSequence text = "Please select a temperature";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
        }else{
            //toggle is on - Casual
                if (style) {
                    if(temp_id == R.id.radioButton){
                        outfit = "sweatpants and a down jacket";
                    }else switch (selectedSeason) {
                        case "Spring":
                            outfit = "pastel pants and a t-shirt";
                            break;
                        case "Summer":
                            outfit = "running shorts and a t-shirt";
                            break;
                        case "Fall":
                            outfit = "leggings and a flannel";
                            break;
                        case "Winter":
                            outfit = "sweatpants and a sweatshirt";
                            break;
                        default:
                            outfit = "jeans and a t-shirt";
                            break;
                    }
            }
            else{ //toggle is off - Formal
                    if(temp_id == R.id.radioButton){
                        outfit = "fur coat and dress pants";
                    }else switch (selectedSeason) {
                        case "Spring":
                            outfit = "floral shirt and pastel pants";
                            break;
                        case "Summer":
                            outfit = "knee-length dress";
                            break;
                        case "Fall":
                            outfit = "boots and a sweater";
                            break;
                        case "Winter":
                            outfit = "dark pants and a long-sleeved dress shirt";
                            break;
                        default:
                            outfit = "pantsuit";
                            break;
                    }
            }
            //change the image view based on the temperature
            if(temp_id == R.id.radioButton){
                weather.setImageResource(R.drawable.snow);
            }else if(temp_id == R.id.radioButton2){
                weather.setImageResource(R.drawable.cloudy);
            }else{
                weather.setImageResource(R.drawable.sun);
            }
            TextView outfitSelection = findViewById(R.id.outfitTextView);
            outfitSelection.setText(outfit);
        }
    }
}
