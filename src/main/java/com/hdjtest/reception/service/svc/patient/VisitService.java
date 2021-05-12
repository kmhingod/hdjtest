package com.hdjtest.reception.service.svc.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.PatientRepository;
import com.hdjtest.reception.domain.patient.Visit;
import com.hdjtest.reception.domain.patient.VisitRepository;
import com.hdjtest.reception.service.dto.patient.PatientDto;
import com.hdjtest.reception.service.dto.patient.VisitDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public VisitDto selectById(Long visitId)  {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("방문 내역이 존재하지 않습니다. 환자방문ID : " + visitId));

        return new VisitDto(visit);
    }

    @Transactional
    public Long save(VisitDto visitDto) throws Exception {
        Visit visit = visitDto.createEntity(visitDto);
        System.out.println(">>>>> Visit Before Save : " + visit);

        visitRepository.save(visit);
        System.out.println(">>>>> Visit After Save : " + visit);

        return visit.get환자방문ID();
    }

    @Transactional
    public Long update(Long visitId, VisitDto visitDto) throws Exception {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("방문 내역이 존재하지 않습니다. 환자방무ID : " + visitId));

        Hospital hospital = hospitalRepository.findById(visitDto.getHospId())
                .orElseThrow(() -> new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID : " + visitDto.getHospId()));

        // TODO 2021.05.12 김민형 - 환자 존재 여부를 병원ID완 함께 확인 해야 함. 수정 요망
        Patient patient = patientRepository.findById(visitDto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + visitDto.getPatientId()));

        try {
            visit.update(hospital, patient, visitDto.getVisitDateTime(), visitDto.getVisitStateCode(), visitDto.getDeptCode());
        } catch (Exception e) {
            throw new Exception(e);
        }

        return visitId;
    }

    @Transactional
    public void delete(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("방문 내역이 존재하지 않습니다. 환자방무ID : " + visitId));

        visitRepository.delete(visit);
    }

    @Transactional
    public List<Visit> selectAll(){
        return visitRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Visit selectVisitEntity(Long visitId) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalArgumentException("방문이 존재하지 않습니다. 방문ID :" + visitId));

        return visit;
    }
}
