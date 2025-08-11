package com.mycompany.hospitalproject;

import java.util.ArrayList;
import java.util.List;

public class WaitingList {
    private PriorityQueue queue;

    public WaitingList() {
        this.queue = new PriorityQueue();
    }

    public void addToWaitList(Patient patient, int priority) {
        queue.add(patient, priority);
    }

    public Patient removeHighestPriority() {
        return queue.delete();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public List<Patient> getAllPatients() {
        return queue.getAllPatients();
    }

    public boolean containsID(String id) {
        for (Patient patient : getAllPatients()) {
            if (patient.getPatientID().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
