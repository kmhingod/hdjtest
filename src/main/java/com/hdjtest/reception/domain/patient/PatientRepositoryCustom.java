package com.hdjtest.reception.domain.patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepositoryCustom {

    Optional<Patient> findByName(String 환자명);

    List<Patient> selectWithOptions(String optionType, String optionValue);
}
