package com.hdjtest.reception.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PatientRepositoryCustom {

    Optional<Patient> findByName(String 환자명);

    List<Patient> selectWithOptions(String optionType, String optionValue);

    Page<Patient> selectWithOptionsPaging(String optionType, String optionValue, Pageable pageable);
}
