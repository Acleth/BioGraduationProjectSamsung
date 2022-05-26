package com.example.biograduationprojectsamsung.search_and_result_fragment_components.results;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biograduationprojectsamsung.R;
import com.example.biograduationprojectsamsung.models.Medication;
import com.example.biograduationprojectsamsung.models.Treatment;

import java.util.ArrayList;
import java.util.List;

public class TreatmentBtnListAdaptor extends RecyclerView.Adapter<TreatmentBtnListAdaptor.MyViewHolder> {
    private ArrayList<Treatment>treatments;
    private Context context;

    public TreatmentBtnListAdaptor(List<Treatment> treatments, Context context) {
        this.treatments = new ArrayList<>();
        this.treatments.addAll(treatments);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.treatment_btn, parent, false);

        return new TreatmentBtnListAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Treatment treatment = treatments.get(position);
        holder.btn.setText(treatment.getName());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.treatment_card);
                dialog.getWindow().setLayout(1000, 900);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                TextView treatmentName = dialog.findViewById(R.id.treatment_name);
                TextView treatmentContent = dialog.findViewById(R.id.treatment_contents);
                TextView treatmentAuthor = dialog.findViewById(R.id.treatment_author);

                treatmentName.setText(treatment.getName());
                treatmentContent.setText(treatment.getContent());
                treatmentAuthor.setText(treatment.getAuthor());

                ArrayList<String>names = new ArrayList<>();

                RecyclerView medicationList = dialog.findViewById(R.id.medication_list);
                medicationList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                ArrayList<Medication> medications = new ArrayList<>();
                medications.addAll(treatment.getMedications());
                medicationList.setAdapter(new MedicationListAdapter(medications));

                dialog.setCanceledOnTouchOutside(true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return treatments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);
        }
    }
}
