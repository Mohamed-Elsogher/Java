package com.mycompany.hospitalproject;

import java.util.ArrayList;
import java.util.List;

public class Billing {
    private String patientID;
    private double billingAmount;
    private ArrayList<Double> paymentHistory;

    public Billing(String patientID, double billingAmount, List<Double> history) {
        this.patientID = patientID;
        this.billingAmount = billingAmount;
        this.paymentHistory = new ArrayList<>(history);
    }

    public void generateBill() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Balance due: " + billingAmount);
        System.out.println("Payment History: " + paymentHistory.toString());
    }

    public void addPayment(double paymentAmount) {
        if (paymentAmount > 0) {
            paymentHistory.add(paymentAmount);
            billingAmount -= paymentAmount;
            System.out.println("Payment of " + paymentAmount + " added successfully.");
            System.out.println("Updated Payment History: " + paymentHistory);
        } else {
            System.out.println("Invalid payment amount.");
        }
    }

    public String getPaymentStatus() {
        if (billingAmount <= 0) {
            return "Paid successfully";
        } else {
            return "Billing Amount : " + billingAmount;
        }
    }
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public double getBillingAmount() {
        return billingAmount;
    }

    public void setBillingAmount(double billingAmount) {
        this.billingAmount = billingAmount;
    }

    public ArrayList<Double> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(ArrayList<Double> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }
}
