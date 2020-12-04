package com.bytedance.network.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bytedance.network.R;

import java.util.ArrayList;
import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{
    private List<String> mItems = new ArrayList<>();

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void notifyItems(List<String> list){
        mItems = list;
        notifyDataSetChanged();
    }
}
