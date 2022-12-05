package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //questa classe conterrà tutte le nostre API per il paziente
public class PatientController implements PatientDataMgmtIF {
    private final PatientService patientService;

    @Autowired //indica che il campo verrà istanziato automaticamente e passato come parametro al costruttore
    //ma funziona soltanto se DoctorService è marcato come Servizio
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/v1/patients/getAll")
    public List<Patient> getAllPatients(){
        return patientService.getAll();
    }

    @GetMapping("/api/v1/patients/getNews")
    public List<Patient> getNews(){
        return patientService.getNews();
    }

    @GetMapping("/api/v1/patients/getAccepted")
    public List<Patient> getAccepted(){
        return patientService.getAccepted();
    }

    @GetMapping("/api/v1/patients/getbyCF")
    public Patient getbyCF(@RequestParam(name = "cf") String cf){
        return patientService.getbyCF(cf);
    }

    @GetMapping("/api/v1/patients/getbyMail")
    public Patient getbyMail(@RequestParam(name = "mail") String mail){
        return patientService.getbyMail(mail);
    }

    @GetMapping("/api/v1/patients/getState")
    public String getState(@RequestParam(name = "mail") String mail){
        return patientService.getState(mail);
    }

    @PostMapping("/api/v1/patients/accept")
    public Patient assign(@RequestBody String cf){
        return patientService.assign(cf);
    }

    @PostMapping("/api/v1/patients/decline")
    public Patient setNA(@RequestBody String cf){
        return patientService.setNA(cf);
    }

    @PostMapping("/api/v1/patients/save/")
    public Patient save(@RequestBody Patient patient){
        return patientService.saveNew(patient);
    }

    @PostMapping("/api/v1/patients/modify/")
    public Patient modify(@RequestBody Changes changes){
        return patientService.modify(changes);
    }

}
