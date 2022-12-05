package com.example.betastudio.model;

public enum AreaSanitaria {
    Cardiologia(37.5),
    Chirurgia(45),
    ChirurgiaUrgenza(50),
    ChirurgiaGenerale(47.5),
    Dermatologia(15),
    DayHospital(42.5),
    EmatologiaEndocrinologia(20),
    Gastroenterologia(20),
    Geriatria(25),
    Ginecologia(15),
    Neurochirurgia(40),
    Neurologia(35),
    Oculistica(20),
    Odontomastologia(10),
    Oncologia(40),
    Otorinolaringoiatria(10),
    Pediatria(35),
    Radiologia(25),
    Urologia(25),
    Virologia(30);

    private final double value;
    AreaSanitaria(double i) {
        this.value = i;
    }

    double getValue(){
        return this.value;
    }
}
