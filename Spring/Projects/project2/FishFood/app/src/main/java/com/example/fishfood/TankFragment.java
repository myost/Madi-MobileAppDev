package com.example.fishfood;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class TankFragment extends Fragment implements FishAdapter.ListItemClickListener{
    private MainActivity activity;

    public TankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tank, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        if(recyclerView != null){
            RealmResults<Fish> fishResults = activity.realm.where(Fish.class).findAll();
            FishAdapter fishAdapter = new FishAdapter(fishResults, activity, this, this);
            recyclerView.setAdapter(fishAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(activity));
            recyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL));
        }
        FloatingActionButton fab = rootView.findViewById(R.id.addFishFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft  = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if(prev != null){
                    ft.remove(prev);
                }
                //ft.addToBackStack(null);
                DialogFragment dialog = new AddFishDialog();
                dialog.show(ft, "dialog");
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onListItemClick(String id) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, FishDetailFragment.newInstance(id));
            transaction.addToBackStack(null);
            transaction.commit();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", "My Tank");
        outState.putInt("selectedItemId", R.id.navigation_tank);
    }
}
