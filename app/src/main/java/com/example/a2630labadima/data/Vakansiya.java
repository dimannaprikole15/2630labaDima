package com.example.a2630labadima.data;

import java.io.Serializable;

public class Vakansiya implements Serializable {
    private int id;
    private String name;
    private String address;
    private int salary;
    private String numTel;

    public Vakansiya(String name, String address, int salary, String numTel) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.numTel = numTel;
    }

    public Vakansiya(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
}
