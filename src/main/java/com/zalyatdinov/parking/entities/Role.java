package com.zalyatdinov.parking.entities;

public class Role {
    private String assignment;

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public Role(String assignment) {
        this.assignment = assignment;
    }
}
