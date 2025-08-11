package com.mycompany.hospitalproject;

import java.util.ArrayList;
import java.util.List;

class ReportGenerator {
    // Attributes
    private String reportType;
    private List<String> data;

    // Constructor
    public ReportGenerator(String reportType, List<String> data) {
        this.reportType = reportType;
        this.data = new ArrayList<>(data);
    }

    // Generate Patient Report: Summarizes a patient's visit history
    public String generatePatientReport(List<String> visitHistory) {
        if (visitHistory.isEmpty()) {
            return "No visit history available for the patient.";
        }
        String report = "Patient Visit History:\n";
        for (String visit : visitHistory) {
            report += visit + "\n";
        }
        return report;
    }

    // Generate Appointment Report: Provides statistics on appointment schedules
    public String generateAppointmentReport(List<String> appointments) {
        if (appointments.isEmpty()) {
            return "No appointment data available.";
        }
        String report = "Total Appointments Scheduled: " + appointments.size() + "\n" + "Appointment Details:\n";
        for (String appointment : appointments) {
            report += appointment + "\n";
        }
        return report;
    }

    // Generate Revenue Report: Summarizes hospital revenue based on billing data
    public String generateRevenueReport(List<Double> revenueData) {
        if (revenueData.isEmpty()) {
            return "No revenue data available.";
        }
        double totalRevenue = 0;
        for (double revenue : revenueData) {
            totalRevenue += revenue;
        }
        String report = "Total Revenue: $" + totalRevenue + "\nRevenue Details:\n";
        for (double revenue : revenueData) {
            report += revenue + "\n";
        }
        return report;
    }

    // Merge Sort: Sorting utility for organizing data
    public void mergeSort(List<Double> data) {
        if (data.size() < 2) {
            return;
        }
        int mid = data.size() / 2;
        List<Double> left = new ArrayList<>();
        List<Double> right = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(data.get(i));
        }
        for (int i = mid; i < data.size(); i++) {
            right.add(data.get(i));
        }

        mergeSort(left);
        mergeSort(right);
        merge(data, left, right);
    }

    private void merge(List<Double> data, List<Double> left, List<Double> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                data.set(k++, left.get(i++));
            } else {
                data.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            data.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            data.set(k++, right.get(j++));
        }
    }

    // Quick Sort: Alternative sorting utility
    public void quickSort(List<Double> data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

    private int partition(List<Double> data, int low, int high) {
        double pivot = data.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (data.get(j) <= pivot) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(List<Double> data, int i, int j) {
        double temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    @Override
    public String toString() {
        String report = "Report Type: " + reportType + "\nData: ";
        for (String item : data) {
            report += item + ", ";
        }
        return report;
    }

    // Example Usage
    public static void main(String[] args) {
        // Example data
        List<String> visitHistory = new ArrayList<>();
        visitHistory.add("Visit 1: 2023-01-01");
        visitHistory.add("Visit 2: 2023-02-15");
        visitHistory.add("Visit 3: 2023-03-10");

        List<String> appointments = new ArrayList<>();
        appointments.add("2023-12-01 10:00 AM");
        appointments.add("2023-12-01 11:00 AM");
        appointments.add("2023-12-02 02:00 PM");

        List<Double> revenueData = new ArrayList<>();
        revenueData.add(1200.50);
        revenueData.add(800.75);
        revenueData.add(950.00);
        revenueData.add(1340.25);

        // Report generation
        ReportGenerator reportGenerator = new ReportGenerator("Patient Report", new ArrayList<>());

        // Patient Visit Report
        System.out.println(reportGenerator.generatePatientReport(visitHistory));

        // Appointment Report
        System.out.println(reportGenerator.generateAppointmentReport(appointments));

        // Revenue Report
        System.out.println(reportGenerator.generateRevenueReport(revenueData));

        // Sorting Revenue Data using Merge Sort
        List<Double> sortableRevenueData = new ArrayList<>(revenueData);
        reportGenerator.mergeSort(sortableRevenueData);
        System.out.println("Sorted Revenue Data (Merge Sort): " + sortableRevenueData);

        // Sorting Revenue Data using Quick Sort
        List<Double> quickSortableData = new ArrayList<>(revenueData);
        reportGenerator.quickSort(quickSortableData, 0, quickSortableData.size() - 1);
        System.out.println("Sorted Revenue Data (Quick Sort): " + quickSortableData);
    }
}
