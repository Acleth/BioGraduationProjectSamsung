package com.example.biograduationprojectsamsung.search_and_result_fragment_components.results;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.models.Disease;

import java.util.ArrayList;


public class DiseaseRecyclerViewAdaptor extends RecyclerView.Adapter<DiseaseRecyclerViewAdaptor.MyViewHolder>{
    private ArrayList<Disease>diseases;
    private Context context;

    public DiseaseRecyclerViewAdaptor(ArrayList<Disease> diseases, Context context) {
        this.diseases = diseases;
        this.context = context;
    }

    @NonNull
    @Override
    public DiseaseRecyclerViewAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_card, parent, false);

        return new DiseaseRecyclerViewAdaptor.MyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseRecyclerViewAdaptor.MyViewHolder holder, int position) {
        holder.name.setText(diseases.get(position).getName());
        holder.description.setText(diseases.get(position).getDescription());
        holder.btnList.setAdapter(new TreatmentBtnListAdaptor(diseases.get(position).getTreatments(), context));
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView description;
        RecyclerView btnList;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            name = itemView.findViewById(R.id.disease_name_view);
            description = itemView.findViewById(R.id.disease_description_view);

            btnList = itemView.findViewById(R.id.treatment_btn_list);
            btnList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        }
    }
}
