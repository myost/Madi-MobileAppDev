package com.example.fishfood;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class TankFragment extends Fragment {
    private MainActivity activity;

    public TankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tank, container, false);
        this.activity = (MainActivity) getActivity();
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        if(recyclerView != null){
            RealmResults<Fish> fishResults = activity.realm.where(Fish.class).findAll();
            FishAdapter fishAdapter = new FishAdapter(fishResults, activity, this);
            recyclerView.setAdapter(fishAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        RealmResults<Fish> fishResults = activity.realm.where(Fish.class).findAll();
//        FishAdapter fishAdapter = new FishAdapter(fishResults, activity, this);
//        RecyclerView recyclerView = activity.findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(fishAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
//        recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));

    }
}
