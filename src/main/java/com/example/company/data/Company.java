package com.example.company.data;

public class Company {

    private double earnings;
    private int positiveEmployees;
    private int negativeEmployees;

    public Company(double earnings, int positiveEmployees, int negativeEmployees) {
        this.earnings = earnings;
        this.positiveEmployees = positiveEmployees;
        this.negativeEmployees = negativeEmployees;
    }

    public Company() {
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public int getPositiveEmployees() {
        return positiveEmployees;
    }

    public void setPositiveEmployees(int positiveEmployees) {
        this.positiveEmployees = positiveEmployees;
    }

    public int getNegativeEmployees() {
        return negativeEmployees;
    }

    public void setNegativeEmployees(int negativeEmployees) {
        this.negativeEmployees = negativeEmployees;
    }
}
