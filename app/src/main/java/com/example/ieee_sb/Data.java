package com.example.ieee_sb;

import java.util.ArrayList;
import java.util.Date;

public class Data {
    public static String[] months = {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
    public static ArrayList<Event> events,workshops;
    public static boolean isMember;
    public static Date d = new Date();
    public static int date = d.getDate();
    public static int month = d.getMonth();
    public static int year = d.getYear();
}
