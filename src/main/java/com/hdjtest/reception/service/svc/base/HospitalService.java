package com.hdjtest.reception.service.svc.base;


import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.service.dto.base.HospitalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional
    public Long save(HospitalDto requestDto) {
        return requestDto.insertHospital(requestDto).get병원ID();
    }

    @Transactional(readOnly = true)
    public HospitalDto selectHospital(Long hospId) {
        Hospital hospital = hospitalRepository.findById(hospId)
            .orElseThrow(() -> new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID :" + hospId));

        return new HospitalDto(hospital);
    }

    @Transactional(readOnly = true)
    public Hospital selectHospitalEntity(Long hospId) {
        Hospital hospital = hospitalRepository.findById(hospId)
                .orElseThrow(() -> new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID :" + hospId));

        return hospital;
    }

    public Boolean IsExistingHospitalById(long hospId) {
        return hospitalRepository.existsById(hospId);
    }
}
