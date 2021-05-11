package com.hdjtest.reception.service.svc.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.PatientRepository;
import com.hdjtest.reception.service.dto.patient.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // 2021.05.12 김민형 - 환자 저장 전에 병원 존재여부 확인 위해.
    private final HospitalRepository hospitalRepository;

    @Transactional(readOnly = true)
    public PatientDto selectById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + patientId));

        return new PatientDto(patient);
    }

    @Transactional
    public Long save(PatientDto patientDto) throws Exception {
        Patient patient = patientDto.createEntity(patientDto);
        System.out.println(">>>>> Patient Before Save : " + patient);

        patientRepository.save(patient);

        System.out.println(">>>>> Patient After Save : " + patient);

        return patient.get환자ID();
    }

    @Transactional
    public Long update(Long patientId, PatientDto patientDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + patientId));

        Hospital hospital = hospitalRepository.findById(patientDto.getHospId())
                .orElseThrow(() -> new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID : " + patientDto.getHospId()));

        patient.update(hospital, patientDto.getPatientName(), patientDto.getPatientRegNum(), patientDto.getSexCode()
                        , patientDto.getBirthDay(), patientDto.getMobileNum(), patientDto.getEMail());

        return patientId;
    }

    @Transactional
    public void delete(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + patientId));

        patientRepository.delete(patient);
    }

    @Transactional
    public List<Patient> selectAll() {
        return patientRepository.findAll();
    }

    public Boolean IsExistingPatientById(long patientId) {
        return patientRepository.existsById(patientId);
    }

    @Transactional(readOnly = true)
    public PatientDto selectPatientDto(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID :" + patientId));

        return new PatientDto(patient);
    }

    @Transactional(readOnly = true)
    public Patient selectPatientEntity(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID :" + patientId));

        return patient;
    }
}
