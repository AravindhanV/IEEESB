package com.example.ieee_sb;

public class CalendarItem {
    private String title,time,venue;
    private int index;

    public CalendarItem(String title,String time,String venue,int index){
        this.title = title;
        this.time = time;
        this.venue = venue;
        this.index = index;
    }

    public String getTitle(){
        return title;
    }

    public String getTime(){
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public int getIndex() {
        return index;
    }
}
