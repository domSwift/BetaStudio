package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor;

import javax.persistence.*;

@Entity
@Table(name = "doctors") //Entity e Table servono a creare la mappatura della classe su DB, i campi diventano le colonne
public class Doctor{
    //nella mappatura su DB imposta il seguente campo come chiave primaria della tabella "dottori"
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    private Long id;

    private String registerID;
    private String name;
    private String surname;
    private String email;
    private String password;
    public Doctor(){}

    public Doctor(String registerID, String name, String surname, String email, String password) {
        this.registerID = registerID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public String getRegisterID() {
        return registerID;
    }

    public void setRegisterID(String registerID) { this.registerID = registerID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Doctor{" +
                "registerID='" + registerID + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
