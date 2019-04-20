package com.example.todolist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class TodoListAdapter extends RealmRecyclerViewAdapter<TodoItem, TodoListAdapter.ViewHolder> {
    private MainActivity activity;

    public TodoListAdapter(RealmResults<TodoItem> data, MainActivity activity){
        super(data, true);
        this.activity = activity;
    }

    @NonNull
    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TodoListAdapter.ViewHolder viewHolder, int i) {
        TodoItem todoItem = getItem(i);
        viewHolder.itemName.setText(todoItem.getName());
        viewHolder.hasDone.setChecked(todoItem.getDone());
        viewHolder.hasDone.setTag(i);
        viewHolder.hasDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isShown()){
                    int position = (Integer) viewHolder.hasDone.getTag();
                    TodoItem todo = getItem(position);
                    activity.changeItemDone(todo.getId());
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName;
        CheckBox hasDone;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            itemName = itemView.findViewById(R.id.textView);
            hasDone = itemView.findViewById(R.id.checkBox);
        }
    }
}
