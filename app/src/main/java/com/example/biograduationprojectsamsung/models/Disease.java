package com.example.biograduationprojectsamsung.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Disease extends RealmObject {
    @PrimaryKey
    @Required
    private Long _id;
    private String description;
    private String name;
    private RealmList<Treatment> treatments;
    @Required
    private String type;
    // Standard getters & setters
    public Long get_id() { return _id; }
    public void set_id(Long _id) { this._id = _id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public RealmList<Treatment> getTreatments() { return treatments; }
    public void setTreatments(RealmList<Treatment> treatments) { this.treatments = treatments; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
