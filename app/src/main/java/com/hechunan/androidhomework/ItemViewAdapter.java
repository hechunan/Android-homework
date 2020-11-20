package com.hechunan.androidhomework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    private List<String> mItems;

    public ItemViewAdapter(List<String> items) {
        mItems = new ArrayList<>();
        mItems.addAll(items);
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = mItems.get(position);
        holder.itemText.setText(item);
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void dataChangedHandler(List<String> newItems) {
        mItems.clear();
        mItems.addAll(newItems);
        notifyDataSetChanged();
    }
}