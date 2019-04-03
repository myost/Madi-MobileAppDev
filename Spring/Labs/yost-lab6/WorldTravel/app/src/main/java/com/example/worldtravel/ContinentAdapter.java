package com.example.worldtravel;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContinentAdapter extends RecyclerView.Adapter<ContinentAdapter.ViewHolder> {
    public interface ListItemClickListener{
        void onListItemClick(int position);
    }

    ListItemClickListener itemClickListener;
    private List<Continent>mContinents;

    public ContinentAdapter(List<Continent>continents, ListItemClickListener continentClickListener){
        mContinents = continents;
        itemClickListener = continentClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView continentTextView;

        //constructor method
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            continentTextView = itemView.findViewById(R.id.textView);
            continentTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            itemClickListener.onListItemClick(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public ContinentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View continentView = inflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(continentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContinentAdapter.ViewHolder viewHolder, int i) {
        Continent continent = mContinents.get(i);
        viewHolder.continentTextView.setText(continent.getName());
    }

    @Override
    public int getItemCount() {
        return mContinents.size();
    }

}
