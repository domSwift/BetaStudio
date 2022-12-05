package com.example.betastudio.model;

public class Changes {

    private String actualUsername;
    private String newPass;
    private String newMed;
    private String newPhone;

    public Changes(String actualUsername, String newPass, String newPhone, String newMed) {
        this.actualUsername = actualUsername;
        this.newPass = newPass;
        this.newMed = newMed;
        this.newPhone = newPhone;
    }

    public String getActualUsername() {
        return actualUsername;
    }

    public String getNewPass() {
        return newPass;
    }

    public String getNewMed() {
        return newMed;
    }

    public String getNewPhone() {
        return newPhone;
    }

}
