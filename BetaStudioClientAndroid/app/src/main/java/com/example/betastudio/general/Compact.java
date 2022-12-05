package com.example.betastudio.general;

import com.example.betastudio.model.Patient;
import com.example.betastudio.model.Request;

public class Compact {
    static public String compactPatientData(String nome, String cognome, String cf) {
        return nome + " " + cognome + " - " + cf;
    }

    static public String compactForShowPatientInTextView(Patient p) {
        String s = p.getName() + " " + p.getSurname() + "\n\n"
                + "CF: " + p.getTaxCode() + "\n\n" + "Contatto mail:\n" + p.getEmail() + "\n\n"
                + "Telefono: " + p.getPhone();
        return s;
    }

    static public String compactForShowRequestInTextView(Request r) {
        String s = r.getNomePaziente() + "\n\n"
                + "CF: " + r.getcfPaziente() + "\n\n" + "Contatto mail:\n" + r.getUsername() + "\n\n"
                + "Telefono: " + r.getTelPaziente() + "\n\n"
                + "Contesto: " + r.getContesto() + "\n"
                + "Area Sanitaria: " + r.getAreaSanitaria() + "\n\n"
                + "Messaggio:\n" + "[" + r.getBody() + "]";
        return s;
    }

    static public String compactRequestData(Request r){
        String s = "\n" + r.getAreaSanitaria() + " - " + r.getContesto() + "\n\n"
                + "inviato alle: " + r.getOraRicezione() + "\n\n" + "STATO: non ancora completata" +
                "\n\nMESSAGGIO:" + "\n" + r.getBody() + "\n";
        return s;
    }

    static public String compactRequestDataForTheDoc(Request r){
        String s = "\n" + r.getAreaSanitaria() + " - " + r.getContesto() + "\n\n"
                + "ricevuto alle: " + r.getOraRicezione() + "#" + r.getPriority();
        return s;
    }

}
