package com.hdjtest.reception.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT MAX(p.환자등록번호) FROM Patient p WHERE p.병원.병원ID = ?1")
    String getMaxPatientRegNum(Long 병원ID);

    @Query("SELECT p FROM Patient p WHERE p.환자명 = ?1")
    List<Patient> selectPatientListByName(String 환자명);

    @Query("SELECT p FROM Patient p WHERE p.환자등록번호 = ?1")
    List<Patient> selectPatientListByPatientRegNum(String 환자등록번호);

    @Query("SELECT p FROM Patient p WHERE p.생년월일 = ?1")
    List<Patient> selectPatientListByBirth(String 생년월일);
}
