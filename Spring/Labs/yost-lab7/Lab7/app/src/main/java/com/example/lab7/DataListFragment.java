package com.example.lab7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class DataListFragment extends Fragment {


    public DataListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_data_list, container, false);
        //read in the xml data
        DogContent dogContent = new DogContent();
        dogContent.dataSetup();

        //set up recycler view
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        DogAdapter adapter = new DogAdapter(DogContent.ITEMS);
        recyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getAppContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getAppContext()));
        return rootView;
    }

}
