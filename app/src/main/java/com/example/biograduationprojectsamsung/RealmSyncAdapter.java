package com.example.biograduationprojectsamsung;

import com.example.biograduationprojectsamsung.models.DataHolder;
import com.example.biograduationprojectsamsung.models.Disease;
import com.example.biograduationprojectsamsung.models.Medication;
import com.example.biograduationprojectsamsung.models.Symptom;
import com.example.biograduationprojectsamsung.models.Treatment;


import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.sync.SyncConfiguration;

public class RealmSyncAdapter {
    private static Realm diseaseRealm;
    public static RealmSyncAdapter instance=null;


    private RealmSyncAdapter() {
        initRealm();
    }

    public static RealmSyncAdapter getInstance(){
        if(instance == null)
            instance = new RealmSyncAdapter();
        return instance;
    }

    public static void initRealm(){
        if(diseaseRealm == null) {


            App app = new App(new AppConfiguration.Builder("biograduationproject-aopdz")
                    .build());

            Thread thread = new Thread(){
                public void run(){
                    app.login(Credentials.anonymous());
                }
            };

            thread.start();

            try{
                thread.join();
                SyncConfiguration syncConfiguration = new SyncConfiguration.Builder(Objects.requireNonNull(app.currentUser()), "DiseaseData").allowWritesOnUiThread(true).build();

                diseaseRealm = Realm.getInstance(syncConfiguration);

                DataHolder.init(diseaseRealm.where(Medication.class).findAll(), diseaseRealm.where(Treatment.class).findAll(),
                        diseaseRealm.where(Disease.class).findAll(), diseaseRealm.where(Symptom.class).findAll());
            }catch (Exception e){
                System.exit(-500);
            }
        }
    }

    public Realm getDiseaseRealm() {
        return diseaseRealm;
    }

    //Needed to close the connection
    public void close() {
        diseaseRealm.close();
    }

    //Very important methods


    //Medication block -start
    public boolean addMedication(String name, String description, RealmList<String> allergies) {
        //id that is going to index this medication
        long id = 1000 + diseaseRealm.where(Medication.class).findAll().size();

        if(diseaseRealm.where(Medication.class).equalTo("name", name).findAll().size() > 0)
            return false;

        diseaseRealm.executeTransaction(realm -> {
            //creates object
            Medication m = realm.createObject(Medication.class, id);
            m.setName(name);
            m.setDescription(description);
            m.setAllergies(allergies);
        });

        return true;
    }
    public boolean addMedication(Medication medication) {
        return addMedication(medication.getName(), medication.getDescription(), medication.getAllergies());
    }
    //Medication block -end

    //Treatment block -start
    public void addTreatment(String name, String content, RealmList<Medication> medications, String author) {
        //id that is going to index this medication
        long id = 1000 + diseaseRealm.where(Treatment.class).findAll().size();

        diseaseRealm.executeTransaction(realm -> {
            //creates object
            Treatment t = realm.createObject(Treatment.class, id);
            t.setAuthor(author);
            t.setName(name);
            t.setContent(content);
            t.setMedications(medications);
        });
    }
    public void addTreatment(Treatment treatment){
        addTreatment(treatment.getName(), treatment.getContent(), treatment.getMedications(), treatment.getAuthor());
    }
    //Treatment block -end

    //Disease block -start
    public boolean addDisease(String name, String description, RealmList<Treatment>treatments){
        long id = 1000 + diseaseRealm.where(Disease.class).findAll().size();

        if(diseaseRealm.where(Disease.class).equalTo("name", name).findAll().size() > 0)
            return false;

        diseaseRealm.executeTransaction(realm -> {
            Disease d = realm.createObject(Disease.class, id);
            d.setDescription(description);
            d.setName(name);
            d.setTreatments(treatments);
        });

        return true;
    }
    public boolean addDisease(Disease disease){
        return addDisease(disease.getName(), disease.getDescription(), disease.getTreatments());
    }
    //Disease block -end

    //Symptom block -start
    public boolean addSymptom(String name, String description, RealmList<Disease>diseases){
        long id = 1000 + diseaseRealm.where(Symptom.class).findAll().size();

        if(diseaseRealm.where(Symptom.class).equalTo("name", name).findAll().size() > 0)
            return false;

        diseaseRealm.executeTransaction(realm -> {
            Symptom s = realm.createObject(Symptom.class, id);
            s.setDescription(description);
            s.setName(name);
            s.setDiseases(diseases);
        });

        return true;
    }
    public boolean addSymptom(Symptom symptom){
        return addSymptom(symptom.getName(), symptom.getDescription(), symptom.getDiseases());
    }
    //Symptom block -end

    //General block -start
    public RealmResults findAll(Class c, String field, String value){
        return diseaseRealm.where(c).equalTo(field, value).findAll();
    }

    public RealmResults findAll(Class c, String field, Integer value){
        return diseaseRealm.where(c).equalTo(field, value).findAll();
    }

    public Object findFirst(Class c, String field, String value){
        return diseaseRealm.where(c).equalTo(field, value).findFirst();
    }

    public Object findFirst(Class c, String field, Integer value){
        return diseaseRealm.where(c).equalTo(field, value).findFirst();
    }

    public RealmResults findAll(Class c){return diseaseRealm.where(c).findAll();}
    //General block -end
}
