package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor;

import com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAll(){
        List<Doctor> doctors = new ArrayList<>();
        Streamable.of(doctorRepository.findAll())
                .forEach(doctors::add);
        return doctors;
    }

    public String getIdbyUser(String username){
        String res = "";
        List<Doctor> doctors = new ArrayList<>();
        Streamable.of(doctorRepository.findAll())
                .forEach(doctors::add);
        for(Doctor d: doctors){
            if(d.getEmail().equals(username)){
                res = d.getRegisterID();
            }
        }
        return res;
    }

    public Doctor getbyID(String id){
        Doctor res = null;
        List<Doctor> docs = new ArrayList<>();
        Streamable.of(doctorRepository.findAll())
                .forEach(docs::add);
        for(Doctor d: docs){
            if(d.getRegisterID().equals(id)){
                res = d;
            }
        }
        return res;
    }

    public Doctor getbyMail(String mail){
        Doctor res = null;
        List<Doctor> docs = new ArrayList<>();
        Streamable.of(doctorRepository.findAll())
                .forEach(docs::add);
        for(Doctor d: docs){
            if(d.getEmail().equals(mail)){
                res = d;
            }
        }
        return res;
    }

    public Doctor saveNew(Doctor doctor){
        if(getbyMail(doctor.getEmail())!=null || getbyID(doctor.getRegisterID())!=null) return null;
        else return doctorRepository.save(doctor);
    }

}
