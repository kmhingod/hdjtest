package com.hdjtest.reception.domain.patient;

import com.hdjtest.reception.config.QuerydslConfig;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

@Import(QuerydslConfig.class)
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {

    @Query("SELECT MAX(p.환자등록번호) FROM Patient p WHERE p.병원.병원ID = ?1")
    String getMaxPatientRegNum(Long 병원ID);

    @Query("SELECT p FROM Patient p WHERE p.환자명 = ?1")
    List<Patient> selectPatientListByName(String 환자명);

    @Query("SELECT p FROM Patient p WHERE p.환자등록번호 = ?1")
    List<Patient> selectPatientListByPatientRegNum(String 환자등록번호);

    @Query("SELECT p FROM Patient p WHERE p.생년월일 = ?1")
    List<Patient> selectPatientListByBirth(String 생년월일);

    List<Patient> selectWithOptions(String optionType, String optionValue);

    // 페이징 기능 추가한 환자 검색 함수
    Page<Patient> selectWithOptionsPaging(String optionType, String optionValue, Pageable pageable);

    Optional<Patient> findByName(String 환자명);
}
