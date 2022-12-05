package com.example.betastudio.model;

public enum Contesto {
    Visita(43.75),
    VisitaDomicilio(50),
    EsamiDiagnostici(25),
    EsamiEmatochimici(25),
    PrescrizioneTerapia(31.25),
    PrescrizioneFarmaci(12.5),
    PrescrizioneCertificati(6.25),
    AssistenzaSanitaria(37.5);

    private final double value;
    Contesto(double v) {
        this.value = v;
    }

    double getValue(){
        return this.value;
    }
}
