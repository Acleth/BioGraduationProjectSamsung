package com.example.biograduationprojectsamsung.search_and_result_fragment_components.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.models.Symptom;

import java.util.ArrayList;


public class TagRecyclerViewAdaptor extends RecyclerView.Adapter<TagRecyclerViewAdaptor.MyViewHolder> {
    Context context;
    ArrayList<Symptom> chosen;

    public TagRecyclerViewAdaptor(Context context, ArrayList<Symptom> chosen){
        this.context = context;
        this.chosen = chosen;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tags_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tagName.setText(chosen.get(position).getName());
        holder.removeTag.setText("X");
        holder.removeTag.setOnClickListener(l ->{
            SearchFragment.onTagRemoved(chosen.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return chosen.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tagName;
        Button removeTag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.tagName);
            removeTag = itemView.findViewById(R.id.removeTag);
        }
    }
}
