package com.betastudio.BetaStudioServer.AuthMgmtBE;

import com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor.Doctor;
import com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor.DoctorRepository;
import com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient.Patient;
import com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private final DoctorRepository doctorRepository;
    @Autowired
    private final PatientRepository patientRepository;

    List<Doctor> savedDoctors;
    List<Patient> savedPatients;
    private String email;
    private String password;

    public LoginService(DoctorRepository doctorRepository, PatientRepository patientRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public UserType login(LoginFormData loginForm){
        this.email = loginForm.getLoginEmail();
        this.password = loginForm.getLoginPassword();
        this.savedDoctors = doctorRepository.findAll();
        this.savedPatients = patientRepository.findAll();
        UserType userLogged = UserType.NOBODY;

        for (Doctor doctor: savedDoctors){
            if(doctor.getEmail().equals(email) && doctor.getPassword().equals(password)){
                userLogged = UserType.DOCTOR;
            }
        }
        for (Patient patient: savedPatients){
            if(patient.getEmail().equals(email) && patient.getPassword().equals(password)){
                userLogged = UserType.PATIENT;
            }
        }
        return userLogged;
    }

}
