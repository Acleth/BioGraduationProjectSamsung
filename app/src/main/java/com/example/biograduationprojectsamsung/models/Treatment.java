package com.example.biograduationprojectsamsung.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Treatment extends RealmObject {
    @PrimaryKey
    @Required
    private Long _id;

    private String author;

    private String content;

    private RealmList<Medication> medications;

    private String name;

    @Required
    private String type;


    // Standard getters & setters
    public Long get_id() { return _id; }
    public void set_id(Long _id) { this._id = _id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public RealmList<Medication> getMedications() { return medications; }
    public void setMedications(RealmList<Medication> medications) { this.medications = medications; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
