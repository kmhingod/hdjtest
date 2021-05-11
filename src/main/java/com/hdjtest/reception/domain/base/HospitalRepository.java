package com.hdjtest.reception.domain.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    @Query("Select p From Hospital p Order By p.병원ID")
    List<Hospital> findAll();

//    @Query("Select p From Hospital p Where p.병원ID = #{병원ID} Order By p.병원ID")
//    Hospital findOne(Long 병원ID);
}
