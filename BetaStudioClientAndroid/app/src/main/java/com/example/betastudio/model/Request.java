package com.example.betastudio.model;
public class Request {

    private String nomePaziente;
    private String cfPaziente;
    private String username;
    private String telPaziente;
    private String medRef;
    private String body;
    private String areaSanitaria;
    private String contesto;
    private String oraRicezione;

    private double priority = 0;

    public Request(String nomePaziente, String cf, String user, String tel, String medRef, String body, AreaSanitaria areaSanitaria, Contesto contesto, String oraRicezione) {
        this.nomePaziente = nomePaziente;
        this.cfPaziente = cf;
        this.username = user;
        this.telPaziente = tel;
        this.medRef = medRef;
        this.body = body;
        this.areaSanitaria = areaSanitaria.name();
        this.contesto = contesto.name();
        this.oraRicezione = oraRicezione;
    }

    public Request() {
    }

    public String getNomePaziente() {
        return nomePaziente;
    }

    public void setNomePaziente(String nomePaziente) {
        this.nomePaziente = nomePaziente;
    }

    public String getcfPaziente() {
        return cfPaziente;
    }

    public void setcfPaziente(String cf) {
        this.cfPaziente = cf;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getTelPaziente() {
        return telPaziente;
    }

    public void setTelPaziente(String telPaziente) {
        this.telPaziente = telPaziente;
    }

    public String getMedRef() {
        return medRef;
    }

    public void setMedRef(String medRef) {
        this.medRef = medRef;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAreaSanitaria() {
        return areaSanitaria;
    }

    public void setAreaSanitaria(String areaSanitaria) {
        this.areaSanitaria = areaSanitaria;
    }

    public String getContesto() {
        return contesto;
    }

    public void setContesto(String contesto) {
        this.contesto = contesto;
    }

    public String getOraRicezione() {
        return oraRicezione;
    }

    public void setOraRicezione(String oraRicezione) {
        this.oraRicezione = oraRicezione;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Request{" +
                "nomePaziente='" + nomePaziente + '\'' +
                ", cfPaziente='" + cfPaziente + '\'' +
                ", username='" + username + '\'' +
                ", telPaziente='" + telPaziente + '\'' +
                ", medRef='" + medRef + '\'' +
                ", body='" + body + '\'' +
                ", areaSanitaria='" + areaSanitaria + '\'' +
                ", contesto='" + contesto + '\'' +
                ", oraRicezione='" + oraRicezione + '\'' +
                ", priority=" + priority +
                '}';
    }
}
