package com.example.fishfood;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FishDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FishDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SELECTED_FISH_ID = "id";

    // TODO: Rename and change types of parameters
    private String fishId;
    private MainActivity activity;
    private TextView fishNameView;
    private TextView fishSpeciesView;
    private TextView fishAgeView;
    private TextView fishDescrView;
    private ImageView fishImageView;


    public FishDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id the id of the selected fish.
     * @return A new instance of fragment FishDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FishDetailFragment newInstance(String id) {
        FishDetailFragment fragment = new FishDetailFragment();
        Bundle args = new Bundle();
        args.putString(SELECTED_FISH_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fishId = getArguments().getString(SELECTED_FISH_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fish_detail, container, false);
        fishNameView = v.findViewById(R.id.fishDetailName);
        fishImageView = v.findViewById(R.id.fishDetailImage);
        fishSpeciesView = v.findViewById(R.id.fishDetailSpecies);
        fishAgeView = v.findViewById(R.id.fishDetailAge);
        fishDescrView = v.findViewById(R.id.fishDetailDescription);
        Fish fish = activity.realm.where(Fish.class).equalTo("id", fishId).findFirst();
        if(fish != null){
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(fish.getFish_name());
            fishNameView.setText(fish.getFish_name());
            fishSpeciesView.setText(fish.getFish_species());
            Date fishBirthday = fish.getFish_birthday();
            String fishAge = getFishAge(fishBirthday);
            fishAgeView.setText(fishAge);
            String fishImg = fish.getFish_species().toLowerCase();
            int imgId = getResources().getIdentifier("@drawable/"+fishImg, "drawable", activity.getPackageName());
            fishImageView.setImageResource(imgId);
            fishDescrView.setText(fish.getFish_details());
        }
//        activity.realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//               Fish fish = realm.where(Fish.class).equalTo("id", fishId).findFirst();
//                ((MainActivity) getActivity()).getSupportActionBar().setTitle(fish.getFish_name());
//                fishNameView.setText(fish.getFish_name());
//                fishSpeciesView.setText(fish.getFish_species());
//                Date fishBirthday = fish.getFish_birthday();
//                String fishAge = getFishAge(fishBirthday);
//                fishAgeView.setText(fishAge);
//                String fishImg = fish.getFish_species().toLowerCase();
//                int imgId = getResources().getIdentifier("@drawable/"+fishImg, "drawable", activity.getPackageName());
//                fishImageView.setImageResource(imgId);
//                fishDescrView.setText(fish.getFish_details());
//            }
//        });
        return v;
    }

    private String getFishAge(Date startDate){
        String age;
        Calendar now = Calendar.getInstance();
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        int years = now.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int monthsBetween = now.get(Calendar.MONTH) - start.get(Calendar.MONTH);
        int monthDiff = years*12 + monthsBetween;
        //Log.i("month difference", String.valueOf(monthDiff));
        if(monthDiff == 0){
            int days = now.get(Calendar.DAY_OF_MONTH) - start.get(Calendar.DAY_OF_MONTH);
            age = days+" days old";
        }else if(monthDiff < 12){
            if(monthDiff == 1){
                age = monthDiff+" month old";
            }else {
                age = monthDiff + " months old";
            }
        }else if(monthDiff >= 12){
            int yearsDiff = monthDiff / 12;
            if(yearsDiff == 1){
                age = yearsDiff+" year";
            }else{
                age = yearsDiff+" years";
            }
            int months = monthDiff % 12;
            if(months == 0){
                age += " old";
            }else{
                age +=months+" months old";
            }
        }else{
            age = "ageless";
        }
        return age;
    }

}
