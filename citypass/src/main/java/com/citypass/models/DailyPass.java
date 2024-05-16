package com.citypass.models;

public class DailyPass {
    private int id;
    private double cijena;

    public DailyPass(int id, double cijena) {
        this.id = id;
        this.cijena = cijena;
    }

    public DailyPass() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
}
