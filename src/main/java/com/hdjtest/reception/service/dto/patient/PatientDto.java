package com.hdjtest.reception.service.dto.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.Visit;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientDto {

    private Long patientId;
    private Long hospId;
    private String patientName;
    private String patientRegNum;
    private String sexCode;
    private String birthDay;
    private String mobileNum;
    private String eMail;

    private List<VisitDto> visitList;

    public PatientDto(Patient patient){
        this.patientId = patient.get환자ID();
        this.hospId = patient.get병원ID();
        this.patientName = patient.get환자명();
        this.patientRegNum = patient.get환자등록번호();
        this.sexCode = patient.get성별코드();
        this.birthDay = patient.get생년월일();
        this.mobileNum = patient.get휴대전화번호();
        this.eMail = patient.get이메일();

        // 2021.05.12 김민형 - 방문목록은 요청시에만 생성한다.
//        if (patient.getVisitList() != null) {
//            this.visitList = ConvertVisitEntityListToDtoList(patient.getVisitList());
//        }
    }

    public Patient createEntity(PatientDto patientDto) throws Exception {
        Hospital hospital = new Hospital();
        hospital.set병원ID(patientDto.getHospId());

        System.out.println(">>>> Class Name : " + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println(">>>> Method Name : " + Thread.currentThread().getStackTrace()[1].getMethodName());
        System.out.println(">>>> 병원ID : " + hospital.get병원ID());
        System.out.println(">>>> 환자명 : " + patientDto.getPatientName());
        System.out.println(">>>> 성별코드 : " + patientDto.getSexCode());

        return Patient.builder()
                .병원(hospital)
                .환자명(patientDto.patientName)
                .환자등록번호(patientDto.patientRegNum)
                .성별코드(patientDto.sexCode)
                .생년월일(patientDto.birthDay)
                .휴대전화번호(patientDto.mobileNum)
                .이메일(patientDto.eMail)
                .build();
    }

    public List<VisitDto> ConvertVisitEntityListToDtoList(List<Visit> visitList) {

        List<VisitDto> visitDtoList = new ArrayList<>();

        System.out.println(">>>>> visit size :" + visitList.size());
        // 2021.05.12 김민형 - Mapper를 사용해야할듯한데...
        for(Visit visit : visitList) {
            VisitDto visitDto = new VisitDto(visit);

            System.out.println(">>>>> visit id :" + visitDto.getVisitId());
            visitDtoList.add(visitDto);
        }

        return visitDtoList;
    }
}
