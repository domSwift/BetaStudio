package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PatientDataMgmtIF {



    @GetMapping("/api/v1/patients/getAll")
    public List<Patient> getAllPatients();

    @GetMapping("/api/v1/patients/getNews")
    public List<Patient> getNews();

    @GetMapping("/api/v1/patients/getAccepted")
    public List<Patient> getAccepted();

    @GetMapping("/api/v1/patients/getbyCF")
    public Patient getbyCF(@RequestParam(name = "cf") String cf);

    @GetMapping("/api/v1/patients/getbyMail")
    public Patient getbyMail(@RequestParam(name = "mail") String mail);

    @GetMapping("/api/v1/patients/getState")
    public String getState(@RequestParam(name = "mail") String mail);

    @PostMapping("/api/v1/patients/accept")
    public Patient assign(@RequestBody String cf);

    @PostMapping("/api/v1/patients/decline")
    public Patient setNA(@RequestBody String cf);

    @PostMapping("/api/v1/patients/save/")
    public Patient save(@RequestBody Patient patient);

    @PostMapping("/api/v1/patients/modify/")
    public Patient modify(@RequestBody Changes changes);

}
