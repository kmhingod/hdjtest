package com.hdjtest.reception.service.dto.patient;


//Optional<Patient> patient = patientRepository.getOne(visitDto.getPatientId());
//visit.update(hospital, patient, visitDto.getVisitStateCode(), visitDto.getDeptCode(), visitDot.getRegDateTime());

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.Visit;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VisitDto {

    private Long visitId;
    private Long hospId;
    private Long patientId;
    private LocalDateTime visitDateTime;
    private String visitStateCode;
    private String deptCode;

    public VisitDto(Visit visit) {
        this.visitId = visit.get환자방문ID();
        this.hospId = visit.get병원ID();
        this.patientId = visit.get환자ID();
        this.visitDateTime = visit.get접수일시();
        this.visitStateCode = visit.get방문상태코드();
        this.deptCode = visit.get진료과목코드();
    }

    public Visit createEntity(VisitDto visitDto) throws Exception {

        Hospital hospital = new Hospital();
        Patient patient = new Patient();

        hospital.set병원ID(visitDto.hospId);
        patient.set환자ID(visitDto.patientId);

        System.out.println(">>>> Class Name : " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println(">>>> Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println(">>>> 병원ID : " + hospital.get병원ID());
        System.out.println(">>>> 환자ID : " + patient.get환자ID());
        System.out.println(">>>> 접수일시 : " + visitDto.getVisitDateTime());
        System.out.println(">>>> 방문상태코드 : " + visitDto.getVisitStateCode());
        System.out.println(">>>> 과목코드 : " + visitDto.getDeptCode());

        return Visit.builder()
                .병원(hospital)
                .환자(patient)
                .접수일시(visitDto.visitDateTime)
                .방문상태코드(visitDto.visitStateCode)
                .진료과목코드(visitDto.deptCode)
                .build();

    }
}
