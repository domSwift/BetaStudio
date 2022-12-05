package com.example.betastudio.model;

public class Patient {

    private String name;
    private String surname;
    private String taxCode;
    private String email;
    private String password;
    private String medID;
    private String phone;
    private char assigned='N';

    public Patient(){}

    public Patient(String name, String surname, String taxCode, String email, String password, String medID, String phone) {
        this.name = name;
        this.taxCode = taxCode;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.medID = medID;
        this.phone = phone;
    }

    public char getState() {
        return assigned;
    }

    public void setState(char assigned) {
        this.assigned = assigned;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMedID() {
        return medID;
    }

    public void setMedID(String medID) {
        this.medID = medID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatientMgmtFE{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", medID='" + medID + '\'' +
                ", phone='" + phone + '\'' +
                ", assigned='" + assigned + '\'' +
                '}';
    }

}
