package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController //questa classe conterrà tutte le nostre API per il medico
public class DoctorController implements DoctorDataMgmtIF {
    private final DoctorService doctorService;

    @Autowired //indica che il campo verrà istanziato automaticamente e passato come parametro al costruttore
               //ma funziona soltanto se DoctorService è marcato come Servizio
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/api/v1/doctors/getAll")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAll();
    }

    @GetMapping("/api/v1/doctors/getIDbyUser")
    public String getID(@RequestParam(name = "username") String username){
        return doctorService.getIdbyUser(username);
    }

    @GetMapping("/api/v1/doctors/getbyID")
    public Doctor getbyID(@RequestParam(name = "id") String id){
        return doctorService.getbyID(id);
    }

    @GetMapping("/api/v1/doctors/getbyMail")
    public Doctor getbyMail(@RequestParam(name = "mail") String mail){
        return doctorService.getbyMail(mail);
    }

    @PostMapping("/api/v1/doctors/save/")
    public Doctor save(@RequestBody Doctor doctor){
         return doctorService.saveNew(doctor);
    }

}
