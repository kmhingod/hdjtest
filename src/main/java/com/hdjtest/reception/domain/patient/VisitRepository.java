package com.hdjtest.reception.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v FROM Visit v WHERE v.환자.환자ID = ?1 ")
    List<Visit> selectVisitsByPatientId(Long 환자ID);
}
