package com.mycompany.hospitalproject;

import java.util.ArrayList;
import java.util.*;
public class Patient {
    private String patientID;
    private String name;
    private String age;
    private String contact;

    public Patient(String patientID, String name, String age, String contact) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.contact = contact;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
