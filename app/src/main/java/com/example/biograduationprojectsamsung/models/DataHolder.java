package com.example.biograduationprojectsamsung.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class DataHolder {
    private final ArrayList<Medication>medications;
    private final ArrayList<Treatment>treatments;
    private final ArrayList<Disease>diseases;
    private final ArrayList<Symptom>symptoms;
    private static DataHolder instance;

    private DataHolder(Collection<Medication>m, Collection<Treatment>t,
                       Collection<Disease>d, Collection<Symptom>s){
        medications = new ArrayList<>(m);
        medications.addAll(m);

        treatments = new ArrayList<>();
        treatments.addAll(t);

        diseases = new ArrayList<>();
        diseases.addAll(d);

        symptoms = new ArrayList<>();
        symptoms.addAll(s);
    }

    public static void init(Collection<Medication> m, Collection<Treatment>t,
                            Collection<Disease>d, Collection<Symptom>s){
        if(instance == null){
            instance = new DataHolder(m, t, d, s);
        }
    }

    public static DataHolder getInstance(){return instance;}

    public ArrayList<Disease> matchQuery(ArrayList<Symptom> s){
        if(s.size() == 0)
            return null;
        ArrayList<Disease>excluded = new ArrayList<>();
        ArrayList<Disease> results = new ArrayList<>(s.get(0).getDiseases());

        for(int i = 0; i < results.size(); i++){
            Disease d = results.get(i);
            for(int j = 0; j < s.size(); j++){
                ArrayList<Disease> disease = new ArrayList<>(s.get(j).getDiseases());
                if(!disease.contains(d))
                    excluded.add(d);
            }
        }

        results.removeAll(excluded);

        return results;
    }

    public Symptom findQuery(String name){
        Symptom res = null;
        for(int i = 0; i < symptoms.size(); i++){
            if(symptoms.get(i).getName().equals(name))
                res = symptoms.get(i);
        }
        return res;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public ArrayList<Treatment> getTreatments() {
        return treatments;
    }

    public ArrayList<Disease> getDiseases() {
        return diseases;
    }

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }
}
