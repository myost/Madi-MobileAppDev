package com.example.lab7;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private List<DogContent.Dog> myDogs;

    public DogAdapter(List<DogContent.Dog>dogs){
        myDogs = dogs;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView dogNameTextView;
        ImageView dogImageView;

        //constructor method
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            dogNameTextView = itemView.findViewById(R.id.textView);
            dogImageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View dogView = inflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(dogView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder viewHolder, int i) {
        DogContent.Dog dog = myDogs.get(i);
        viewHolder.dogNameTextView.setText(dog.getName());
        viewHolder.dogImageView.setImageResource(dog.getImageResourceID());
    }

    @Override
    public int getItemCount() {
        return myDogs.size();
    }
}
