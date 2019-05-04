package com.example.restaurantfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class RestaurantAdapter extends RealmRecyclerViewAdapter<Restaurant, RestaurantAdapter.ViewHolder> {
    private MainActivity activity;
    public interface ListItemClickListener{
        void onListItemClick(Restaurant restaurant);
    }

    ListItemClickListener itemClickListener;

    public RestaurantAdapter(RealmResults<Restaurant> data, MainActivity activity, ListItemClickListener nameClickListener){
        super(data, true);
        this.activity = activity;
        itemClickListener = nameClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item_, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder viewHolder, int i) {
        final Restaurant restaurant = getItem(i);
        viewHolder.restaurantName.setText(restaurant.getName());
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                activity.editRestaurant(restaurant.getId());
                return true;
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView restaurantName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            restaurantName = itemView.findViewById(R.id.nameTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Restaurant rest = getItem(position);
            itemClickListener.onListItemClick(rest);
        }
    }
}
