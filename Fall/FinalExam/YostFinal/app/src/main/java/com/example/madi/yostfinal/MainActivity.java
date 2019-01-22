package com.example.madi.yostfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ImageView pizzaImage;
    private TextView pizzaDescription;
    private int imageId;

    private Pizza myPizza = new Pizza();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //image view
        pizzaImage = findViewById(R.id.pizza_img);
        pizzaDescription = findViewById(R.id.pizza_descr);

        if(savedInstanceState != null){
            int imgId = savedInstanceState.getInt("imageId");
            pizzaImage.setImageResource(imgId);
            String description = savedInstanceState.getString("description");
            pizzaDescription.setText(description);
        }
    }

    public void createPizza(View view){
        //size
        Spinner size = findViewById(R.id.spinner);
        String pizzaSize = String.valueOf(size.getSelectedItem());

        //crust
        RadioGroup crust = findViewById(R.id.radioGroup);
        int crust_id = crust.getCheckedRadioButtonId();

        //toppings
        CheckBox cheeseBox = findViewById(R.id.cheese);
        Boolean cheese = cheeseBox.isChecked();

        CheckBox meatBox = findViewById(R.id.meat);
        Boolean meat = meatBox.isChecked();

        CheckBox veggieBox = findViewById(R.id.veggie);
        Boolean veggie = veggieBox.isChecked();

        CheckBox supremeBox = findViewById(R.id.supreme);
        Boolean supreme = supremeBox.isChecked();

        //gluten free
        Switch gfSwitch = findViewById(R.id.switch1);
        Boolean gf = gfSwitch.isChecked();

        if(crust_id == -1){ //user did not select a crust
            crustToast();
        }else{
            //set default pizza image in case of no checkboxes checked
            imageId = R.drawable.pizza_cheese;
            String pizzaDescr = "Your pizza is a ";
            pizzaDescr += pizzaSize+" ";
            if(gf){
                pizzaDescr+="gluten free ";
            }
            if(crust_id == R.id.radioThin){
                pizzaDescr+="thin crust ";
                myPizza.setPizzeria(gf, "thin");
            }else if(crust_id == R.id.radioThick){
                pizzaDescr+="thick crust ";
                myPizza.setPizzeria(gf, "thick");
            }
            if(cheese){
                pizzaDescr+="cheese ";
                imageId = R.drawable.pizza_cheese;
            }
            if(meat){
                pizzaDescr+="meat ";
                imageId  = R.drawable.pizza_meat;
            }
            if(veggie){
                pizzaDescr+="veggie ";
                imageId = R.drawable.pizza_veggie;
            }
            if(supreme){
                pizzaDescr+="supreme ";
                imageId = R.drawable.pizza_supreme;
            }
            pizzaDescr+="pizza.";
            myPizza.setPizzaDescr(pizzaDescr);
            pizzaDescription = findViewById(R.id.pizza_descr);
            pizzaDescription.setText(pizzaDescr);
            pizzaImage.setImageResource(imageId);
        }
    }
    public void findPizzeria(View view){
        //crust
        RadioGroup crust = findViewById(R.id.radioGroup);
        int crust_id = crust.getCheckedRadioButtonId();
        //gluten free
        Switch gfSwitch = findViewById(R.id.switch1);
        Boolean gf = gfSwitch.isChecked();
        if(crust_id == -1){ //user did not select a crust
            crustToast();
        }else {
            if (crust_id == R.id.radioThick) {
                myPizza.setPizzeria(gf, "thick");
            } else if (crust_id == R.id.radioThin) {
                myPizza.setPizzeria(gf, "thin");
            }

            String pizzeriaName = myPizza.getPizzeria();
            String pizzeriaURL = myPizza.getPizzeriaURL();

            //create Intent
            Intent intent = new Intent(this, PizzeriaActivity.class);

            intent.putExtra("pizzeriaName", pizzeriaName);
            intent.putExtra("pizzeriaURL", pizzeriaURL);

            //start the intent
            startActivity(intent);
        }
    }

    public void crustToast(){
        Context context = getApplicationContext();
        CharSequence text = "Please select a crust type";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String pizzaDescrText = (String)pizzaDescription.getText();
        outState.putString("description", pizzaDescrText);
        outState.putInt("imageID", imageId);
    }
}
