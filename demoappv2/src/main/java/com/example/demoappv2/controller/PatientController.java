package com.example.demoappv2.controller;

import com.example.demoappv2.repository.Patient;
import com.example.demoappv2.service.PatientService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.Locale;


@RestController
@RequestMapping("/patients")

public class PatientController {
    Logger log= LoggerFactory.getLogger(PatientController.class);

    private final Faker faker= new Faker(new Locale("es"));
    private final Random random= new Random();

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients(){
        log.info("Aqui estamos con una petición");
        return patientService.getAllPatient();
    }

    @GetMapping("/buscar")
    public Patient getOnePatient(@RequestParam String nro)
    {
        if(nro.length()>0){
            return patientService.getOnePatient(nro);
        }
        return null;
    }

    @GetMapping("/buscarv2")
    public List<Patient> getOnePatientv2(@RequestParam("otro") String genero, @RequestParam("edad") int edad)
    {
        if(genero.length()>0){
            return patientService.getAllPatients(genero,edad);
        }
        return null;
    }

    @GetMapping("/buscarv3")
    public Patient getOnePatientv3(@RequestParam("otrov3") String otrov2)
    {
        if(otrov2.length()>0){
            return patientService.getOnePatient(otrov2);
        }
        return null;
    }

    @GetMapping("/buscarv4/{edad}")
    public List <Patient> getOnePatientv4(@PathVariable("edad") int edad)
    {
        if(edad>0){
            return  patientService.getAllAgePatients(edad);
        }
        return null;
    }

    @GetMapping("/buscarv5/{gender}/{edad}")
    public List <Patient> getOnePatientv5(@PathVariable("gender") String gender,
                                          @PathVariable("edad") int edad)
    {
        if(edad>0){
            return  patientService.getAllAgePatientsCustomv1(gender,edad);
        }
        return null;
    }

    @PostMapping("/fake")
    public Patient creationNewPatient() {
        Patient patient = new Patient();
        patient.setFullName(faker.name().fullName());
        patient.setDocument(faker.number().digits(8));
        patient.setAge(10 + random.nextInt(70));
        patient.setGender(random.nextBoolean() ? "M" : "F");

        this.patientService.saveOnePatien(patient);
        return patient;


    }

    @PostMapping
    public Patient create(@RequestBody Patient p){

        return this.patientService.saveOnePatienv2(p);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient p){

        return this.patientService.updateOnePatient(id,p);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.patientService.deleteOnePatient(id);
    }
}
