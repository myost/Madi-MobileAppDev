package com.example.fishfood;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AddFishDialog extends DialogFragment {
    private String selectedDate = "";
    private Calendar fishCal = Calendar.getInstance();
    private Date fishBirthday = fishCal.getTime();
    private TextView dateTextView;
    private Button datePickerButton;
    private Button saveButton;
    private Button cancelButton;
    private EditText name;
    private Spinner speciesSpinner;

    private String betaDescr = "Beta fish need to be fed twice a day, once in the morning and once at night. Beta fish should never be in a tank with other beta fish but get along well with other fish species.";
    private String guppyDescr = "Guppies generally swim towards the top or middle of the tank. They should generally be fed twice a day. It is recommended to have a tank with plants if you have a guppy. Guppies get along well with each other and with other fish species.";
    private String goldDescr = "Goldfish generally need to be fed twice a day but they can survive up to 2 weeks without feeding. An average goldfish will live 10 years or longer. Goldfish are a great fish for beginners but need a large tank to be comfortable.";

    public AddFishDialog(){
        //empty constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        LayoutInflater i = requireActivity().getLayoutInflater();
        View v = i.inflate(R.layout.add_fish_dialog, null);
        b.setView(v);
        final AlertDialog addDialog = b.create();

        datePickerButton = v.findViewById(R.id.dateButton);
        saveButton = v.findViewById(R.id.buttonSave);
        cancelButton = v.findViewById(R.id.buttonCancel);
        dateTextView = v.findViewById(R.id.dateTextView);
        name = v.findViewById(R.id.nameInput);
        speciesSpinner = v.findViewById(R.id.speciesPicker);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + month+ "/" + year;
                        fishCal.set(year, month, dayOfMonth);
                        fishBirthday = fishCal.getTime();
                        dateTextView.setText(selectedDate);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
                dpd.show();
//                DialogFragment pickDate = new DatePickerFragment();
//                pickDate.show(getFragmentManager(), "show");
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().equals("")){
                    addDialog.cancel();
                }
                else{
                    if(!selectedDate.equals("")){
                        String species = String.valueOf(speciesSpinner.getSelectedItem());
                        String descr = "";
                        switch(species){
                            case "Beta":
                                descr = betaDescr;
                                break;
                            case "Guppy":
                                descr = guppyDescr;
                                break;
                            case "Goldfish":
                                descr = goldDescr;
                                break;
                        }
                        String newFishName = String.valueOf(name.getText());
                        MainActivity activity = (MainActivity) getActivity();
                        activity.addFish(UUID.randomUUID().toString(), newFishName, species, fishBirthday, descr);
                        Log.i("in save", "added the fish to realm");
                        addDialog.cancel();
                    }else{
                        addDialog.cancel();
                    }
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDialog.cancel();
            }
        });
        return addDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}
