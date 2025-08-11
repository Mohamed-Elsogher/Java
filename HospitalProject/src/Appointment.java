package com.mycompany.hospitalproject;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;
public class Appointment {
    static int id;
    private int appointmentID;
    private String patient;
    private LocalDate date;
    private LocalTime time;
    private String status;

    private static int idCounter=1;
    private static List<Appointment> appointments=new ArrayList<>();

    public Appointment(String patient,LocalDate date,LocalTime time ) {
        this.appointmentID=id;
        id ++;
        this.patient = patient;
        this.date = date;
        this.time = time;
        this.status = "scheduled";
        appointments.add(this);
    }

    public static Appointment schedule(String patient, LocalDate date){
        //TODO:: Fix time
        return new Appointment(patient,date,LocalTime.now());
    }





    public int getAppointmentID(){
        return appointmentID;
    }
    public String getPatient(){
        return patient;
    }
    public void setPatient(String patient){
        this.patient=patient;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date) {
        this.date=date;
    }
    public LocalTime getTime(){
        return time;
    }
    public void setTime(LocalTime time){
        this.time=time;
    }
    public String getStatus(){
        return status;
    }
    public void schedule(){
        this.status="scheduled";
        System.out.println("the id "+appointmentID+"is arranged");
    }
    public void cancel(){
        this.status="canceled";
        System.out.println("the id "+this.appointmentID+"is canceled");
    }
    public void reschedule(LocalDate d2){
        this.date=d2;

        this.status="rescheduled";
        System.out.println("the id"+this.appointmentID+"was rescheduled");

    }
    public static void listAppointment(){
        if (Appointment.appointments.isEmpty()){
            System.out.println("nothing is scheduled");

        }else{
            System.out.println("Appointments --> ");
            for (Appointment appointment : appointments){
                System.out.println(appointment);
            }
        }
    }
    public String toString() {
        return "Appointment{" +
                "ID=" + appointmentID +
                ", Patient='" + patient + '\'' +
                ", Date=" + date +
                ", Time=" + time +
                ", Status='" + status + '\'' +
                '}';
    }
    public String getPatientName() {
        return patient;
  }

}

