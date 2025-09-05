package com.example.demoappv2.service;

import com.example.demoappv2.repository.Patient;
import com.example.demoappv2.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void saveOnePatien(Patient p){
        this.patientRepository.save(p);
        log.info("Aqui se guarda un paciente",p);
    }
}
