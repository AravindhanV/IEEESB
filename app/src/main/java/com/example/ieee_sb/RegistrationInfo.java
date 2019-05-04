package com.example.ieee_sb;

public class RegistrationInfo {
    public String name;
    public String usn;
    public String sem;
    public String id;

    public RegistrationInfo(){}

    public RegistrationInfo(String name,String sem,String usn,String id){
        this.name = name;
        this.usn = usn;
        this.sem = sem;
        this.id = id;
    }
}
