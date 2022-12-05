package com.betastudio.BetaStudioServer.RequestMgmtBE;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @SequenceGenerator(
            name = "request_sequence",
            sequenceName = "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator = "request_sequence"
    )
    private Long id;

    private String nomePaziente;
    private String cfPaziente;
    private String username;

    private String telPaziente;
    private String medRef;
    private String body;
    private String areaSanitaria;
    private String contesto;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime oraRicezione;

    private double priority = 0;

    public Request(String nomePaziente, String cf, String user, String tel, String medRef, String body, AreaSanitaria areaSanitaria, Contesto contesto, LocalDateTime oraRicezione) {
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

    public LocalDateTime getOraRicezione() {
        return oraRicezione;
    }

    public void setOraRicezione(LocalDateTime oraRicezione) {
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
                ", oraRicezione=" + oraRicezione +
                ", priority=" + priority +
                '}';
    }

}
