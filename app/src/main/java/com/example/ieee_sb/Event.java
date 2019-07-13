package com.example.ieee_sb;

import java.util.ArrayList;

public class Event {
    private String title;
    private ArrayList<String> tags;
    private String description;
    private int date,year,month;
    private String time;
    private String id;
    public String poster;
    private int nonMemberFee,memberFee;
    private ArrayList<Organizer> organizers;

    //TODO: Need to add venue, diff prices for member and non members

    public Event(){}

    public Event(String title, ArrayList<String> tags, String description,int date,int month, int year,String time, String id, String poster){
        this.tags = tags;
        this.date = date;
        this.id = id;
        this.month = month;
        this.year = year;
        this.title = title;
        this.time = time;
        this.description = description;
        this.poster = poster;
    }

    public ArrayList<Organizer> getOrganizers(){
        return organizers;
    }

    public int getNonMemberFee(){
        return nonMemberFee;
    }

    public int getMemberFee(){
        return memberFee;
    }

    public String getID(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addTag(String s){
        tags.add(s);
    }

    public String getTime() {
        return time;
    }

    public int getDate() {
        return date;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public String getURL(){
        return poster;
    }
}
