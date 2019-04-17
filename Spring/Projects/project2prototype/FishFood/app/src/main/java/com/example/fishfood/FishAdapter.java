package com.example.fishfood;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class FishAdapter extends RealmRecyclerViewAdapter<Fish, FishAdapter.ViewHolder> {
    private MainActivity activity;
    private TankFragment tankFragment;

    public FishAdapter(RealmResults<Fish>data, MainActivity activity, TankFragment fragment){
        super(data, true);
        this.activity = activity;
        this.tankFragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.fish_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FishAdapter.ViewHolder viewHolder, int i) {
        Fish fish = getItem(i);
        viewHolder.fishName.setText(fish.getFish_name());
        String species = fish.getFish_species();
        viewHolder.speciesName.setText(species);
        int imageID = R.drawable.unknown;
        if(species.equals("Goldfish")) {
            imageID = R.drawable.goldfish;
        }else if(species.equals("Guppy")){
            imageID = R.drawable.guppy;

        }else if(species.equals("Beta")){
            imageID = R.drawable.beta;
        }
        viewHolder.fishImage.setImageResource(imageID);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView fishName;
        TextView speciesName;
        ImageView fishImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fishName = itemView.findViewById(R.id.nameView);
            speciesName = itemView.findViewById(R.id.speciesView);
            fishImage = itemView.findViewById(R.id.fishImageView);
        }
    }
}
