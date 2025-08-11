package com.mycompany.hospitalproject;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private class Node {
        Patient patient;
        int priority;

        Node(Patient patient, int priority) {
            this.patient = patient;
            this.priority = priority;
        }
    }

    private List<Node> heap;

    public PriorityQueue() {
        this.heap = new ArrayList<>();
    }

    public void add(Patient patient, int priority) {
        Node newNode = new Node(patient, priority);
        heap.add(newNode);
        heapifyUp(heap.size() - 1);
    }

    public Patient delete() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty!");
        }
        Patient patient = heap.get(0).patient;
        Node lastNode = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            heapifyDown(0);
        }
        return patient;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        for (Node node : heap) {
            patients.add(node.patient);
        }
        return patients;
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).priority < heap.get(parentIndex).priority) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < size && heap.get(leftChild).priority < heap.get(smallest).priority) {
                smallest = leftChild;
            }
            if (rightChild < size && heap.get(rightChild).priority < heap.get(smallest).priority) {
                smallest = rightChild;
            }

            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
