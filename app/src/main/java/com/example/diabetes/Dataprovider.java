package com.example.diabetes;

public class Dataprovider {
    private int type;
    private double value ;
    private String date ;
    private String mess;
    private String fast ;

    @Override
    public String toString() {
        return "Dataprovider{" + "type=" + type + ", value=" + value + ", date='" + date + '\'' + ", mess='" + mess + '\'' + ", fast='" + fast + '\'' + '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String getFast() {
        return fast;
    }

    public void setFast(String fast) {
        this.fast = fast;
    }

    public Dataprovider() {
    }

    public Dataprovider(int type, double value, String date, String mess, String fast) {
        this.type = type;
        this.value = value;
        this.date = date;
        this.mess = mess;
        this.fast = fast;
    }
}
