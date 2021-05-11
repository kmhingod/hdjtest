package com.hdjtest.reception.control.base;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.domain.patient.Patient;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
//@NoArgsConstructor
@RestController
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    public Hospital selectHospital(Long 병원ID) {
        return hospitalRepository.getOne(병원ID);
    }
}
