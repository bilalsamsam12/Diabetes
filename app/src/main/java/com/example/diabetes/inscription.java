package com.example.diabetes;

public class inscription {
    private  String fullname ;
    private String Email ;
    private String telephone;
    private String password;
    private int type;
    private  int idh;
    private  double valeur;
    private String date;
    private  String message;
    private  String fast;


    public inscription(String fullname, String email, String telephone, String password, int type, int idh, double valeur, String date, String message, String fast) {
        this.fullname = fullname;
        Email = email;
        this.telephone = telephone;
        this.password = password;
        this.type = type;
        this.idh = idh;
        this.valeur = valeur;
        this.date = date;
        this.message = message;
        this.fast = fast;
    }

    public inscription() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFast() {
        return fast;
    }

    public void setFast(String fast) {
        this.fast = fast;
    }

    @Override
    public String toString() {
        return "inscription{" + "fullname='" + fullname + '\'' + ", Email='" + Email + '\'' + ", telephone='" + telephone + '\'' + ", password='" + password + '\'' + ", type=" + type + ", idh=" + idh + ", valeur=" + valeur + ", date='" + date + '\'' + ", message='" + message + '\'' + ", fast='" + fast + '\'' + '}';
    }
}
