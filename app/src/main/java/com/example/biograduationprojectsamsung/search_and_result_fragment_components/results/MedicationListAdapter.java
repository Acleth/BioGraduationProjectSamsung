package com.example.biograduationprojectsamsung.search_and_result_fragment_components.results;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.models.Medication;

import java.util.ArrayList;

public class MedicationListAdapter  extends RecyclerView.Adapter<MedicationListAdapter.MyViewHolder>{
    ArrayList<Medication>medications;

    public MedicationListAdapter(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    @NonNull
    @Override
    public MedicationListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medication_card, parent, false);

        return new MedicationListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicationListAdapter.MyViewHolder holder, int position) {
        holder.name.setText(medications.get(position).getName());
        ArrayList<String>a = new ArrayList<>();
        a.addAll(medications.get(position).getAllergies());
        StringBuilder builder = new StringBuilder("Allergies:\n");
        for (int i = 0; i < a.size(); i++) {
            builder.append(a.get(i)+"\n");
        }
        holder.allergies.setText(builder.toString());
    }

    @Override
    public int getItemCount() {
        return medications.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView allergies;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medication_name);
            allergies = itemView.findViewById(R.id.allergies);
        }
    }
}
