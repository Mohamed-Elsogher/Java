package com.mycompany.hospitalproject;
import java.util.*;
public class BinarySearchTree {
    private Node root;

    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            left = right = null;
        }
    }

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node root, Patient patient) {
        if (root == null) {
            root = new Node(patient);
            return root;
        }
        if (patient.getPatientID().compareTo(root.patient.getPatientID()) < 0) {
            root.left = insertRec(root.left, patient);
        } else if (patient.getPatientID().compareTo(root.patient.getPatientID()) > 0) {
            root.right = insertRec(root.right, patient);
        }
        return root;
    }

    public Patient search(String patientID) {
        return searchRec(root, patientID);
    }

    private Patient searchRec(Node root, String patientID) {
        if (root == null || root.patient.getPatientID().equals(patientID)) {
            return root != null ? root.patient : null;
        }
        if (patientID.compareTo(root.patient.getPatientID()) < 0) {
            return searchRec(root.left, patientID);
        }
        return searchRec(root.right, patientID);
    }

    public void delete(String patientID) {
        root = deleteRec(root, patientID);
    }

    private Node deleteRec(Node root, String patientID) {
        if (root == null) {
            return root;
        }
        if (patientID.compareTo(root.patient.getPatientID()) < 0) {
            root.left = deleteRec(root.left, patientID);
        } else if (patientID.compareTo(root.patient.getPatientID()) > 0) {
            root.right = deleteRec(root.right, patientID);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.patient = minValue(root.right);
            root.right = deleteRec(root.right, root.patient.getPatientID());
        }
        return root;
    }

    private Patient minValue(Node root) {
        Patient minValue = root.patient;
        while (root.left != null) {
            minValue = root.left.patient;
            root = root.left;
        }
        return minValue;
    }
}
