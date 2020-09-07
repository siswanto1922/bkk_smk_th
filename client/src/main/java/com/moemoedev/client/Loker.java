package com.moemoedev.client;

import com.google.firebase.database.Exclude;

public class Loker {
    private String industry;
    private String imageURL;
    private String key;
    private String description;
    private String deadline;
    private int position;

    public Loker() {

    }
    public Loker(int position){
        this.position = position;
    }
    public Loker(String industry, String imageURL, String description, String deadline){
        this.industry = industry;
        this.imageURL = imageURL;
        this.description = description;
        this.deadline = deadline;
    }

    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry){
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline(){
        return deadline;
    }
    public void setDeadline(String deadline){
        this.deadline = deadline;
    }


    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    @Exclude
    public String getKey(){
        return key;
    }
    @Exclude
    public void setKey(String key){
        this.key = key;
    }
}
