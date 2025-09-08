package com.example.demoappv2.service;

import com.example.demoappv2.repository.Patient;
import com.example.demoappv2.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class PatientService {

    Logger log = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatient(){
        log.info("Aqui estamos en el service !!!");
        List<Patient> resultPatient= patientRepository.findAll();

        return resultPatient;
    }

    public Patient getOnePatient(String nro){
        log.info("Aqui estamos en el service !!!");
        Patient onePatient= new Patient();
        List<Patient> resultPatient = patientRepository.findAll();
        for(Patient p: resultPatient){
            if(p.getDocument().equals(nro))
            {
                onePatient=p;
                break;
            }
        }
        return onePatient;
    }

    public List<Patient> getAllPatients(String genero, int edad) {
        log.info("Aqui estamos en el service !!!");
        Patient onePatient = new Patient();
        List<Patient> resultPatient = patientRepository.findAll();
        List<Patient> lstPatients = new ArrayList<>();
        for (Patient p : resultPatient) {
            if (p.getDocument().equals(genero) && p.getAge() > edad)
            {
                lstPatients.add(p);
            }
        }
        return lstPatients;
    }

    public List<Patient> getAllAgePatients( int edad) {
        log.info("Aqui estamos en el service !!!");
        Patient onePatient = new Patient();
        List<Patient> resultPatient = patientRepository.findAll();
        List<Patient> lstPatients = new ArrayList<>();
        for (Patient p: resultPatient) {
            if (p.getAge()>edad)
            {
                lstPatients.add(p);
            }
        }
        return lstPatients;
    }

    public void saveOnePatien(Patient p){
        this.patientRepository.save(p);
        log.info("Aqui se guarda un paciente",p);
    }

    public List<Patient> getAllAgePatientsCustomv1( String gender, int edad){
        log.info("Aqui estamos en el service getAllAgePatientsCustomv1 !!!");
        return  patientRepository.findByGenderAndAgeGreaterThan(gender, edad);
    }

    public Patient saveOnePatienv2(Patient p){

        log.info("Aqui se guarda un paciente saveOnePatientv2",p);
        return this.patientRepository.save(p);
    }

    public Patient updateOnePatient(Long id, Patient p){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no se encuentra" + id));

        patient.setFullName(p.getFullName());
        patient.setDocument(p.getDocument());
        patient.setGender(p.getGender());
        patient.setAge(p.getAge());

        return this.patientRepository.save(patient);
    }

    public void deleteOnePatient(Long id)
    {
        this.patientRepository.deleteById(id);
    }
}
