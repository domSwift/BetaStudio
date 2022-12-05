package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DoctorDataMgmtIF {

    @GetMapping("/api/v1/doctors/getAll")
    public List<Doctor> getAllDoctors();

    @GetMapping("/api/v1/doctors/getIDbyUser")
    public String getID(@RequestParam(name = "username") String username);

    @GetMapping("/api/v1/doctors/getbyID")
    public Doctor getbyID(@RequestParam(name = "id") String id);

    @GetMapping("/api/v1/doctors/getbyMail")
    public Doctor getbyMail(@RequestParam(name = "mail") String mail);

    @PostMapping("/api/v1/doctors/save/")
    public Doctor save(@RequestBody Doctor doctor);

}
