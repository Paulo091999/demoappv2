package com.example.demoappv2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    List<Patient> findByGenderAndAgeGreaterThan(String gender, int age);

    List<Patient> findByAgeLessThan(int age);
}
