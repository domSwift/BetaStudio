package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEpatient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAll(){
        List<Patient> patients = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(patients::add);
        return patients;
    }

    public List<Patient> getNews(){
        List<Patient> patients = new ArrayList<>();
        List<Patient> news = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(patients::add);
        for(Patient p: patients){
            if(p.getState()=='N'){
               news.add(p);
            }
        }
        return news;
    }

    public List<Patient> getAccepted(){
        List<Patient> patients = new ArrayList<>();
        List<Patient> news = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(patients::add);
        for(Patient p: patients){
            if(p.getState()=='S'){
                news.add(p);
            }
        }
        return news;
    }

    public Patient getbyCF(String cf){
        Patient res = null;
        List<Patient> pats = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(pats::add);
        for(Patient p: pats){
            if(p.getTaxCode().equals(cf)){
                res = p;
            }
        }
        return res;
    }

    public Patient getbyMail(String mail){
        Patient res = null;
        List<Patient> pats = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(pats::add);
        for(Patient p: pats){
            if(p.getEmail().equals(mail)){
                res = p;
            }
        }
        return res;
    }

    public String getState(String mail){
        Character res = ' ';
        List<Patient> pats = new ArrayList<>();
        Streamable.of(patientRepository.findAll())
                .forEach(pats::add);
        for(Patient p: pats){
            if(p.getEmail().equals(mail)){
                res = p.getState();
            }
        }
        return res.toString();
    }

    public Patient assign(String cf){
       Patient p = getbyCF(cf);
       p.setState('S');
       patientRepository.save(p);
       return p;
    }

    public Patient setNA(String cf){
        Patient p = getbyCF(cf);
        p.setState('E');
        patientRepository.save(p);
        return p;
    }

    public Patient saveNew(Patient patient){
        if(getbyMail(patient.getEmail())!=null || getbyCF(patient.getTaxCode())!=null) return null;
        else return patientRepository.save(patient);
    }

    public Patient modify(Changes changes){
        Patient p = getbyMail(changes.getActualUsername());
        p.setPassword(changes.getNewPass());
        p.setPhone(changes.getNewPhone());
        if(!changes.getNewMed().equals(p.getMedID()))
        {
            p.setMedID(changes.getNewMed());
            p.setState('N');
        }
        patientRepository.save(p);
        return p;
    }

}
