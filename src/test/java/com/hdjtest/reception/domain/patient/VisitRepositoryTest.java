package com.hdjtest.reception.domain.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VisitRepositoryTest {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Test
    void registerVisit_Test() {
        // 2021.05.11 김민형 - 가상 병원 생성
        Hospital hospital = new Hospital("아무개 병원", "31123456", "아무개");
        hospitalRepository.save(hospital);

        // 2021.05.11 김민형 - 환자 정보 생성
        patientRepository.save(Patient.builder()
                .병원(hospital)
                .환자명("김민형")
                .환자등록번호("123456789")
                .성별코드("M")
                .build());

        // 2021.05.11 김민형 - 전체 환자 목록 조회
        // TODO 2021.05.11 김민형 - 병원별 환자 목록 등 조건 검색 테스트 필요
        List<Patient> patientList =patientRepository.findAll();

        // 2021.05.11 김민형 - 접수일시. 실제로는 저장 시점의 시간이 입력되어야 한다.
        LocalDateTime firstVisitDateTime = LocalDateTime.of(2021, 5, 11, 10, 0, 0);

        // 2021.05.11 김민형 - 방문 정보 생성
        visitRepository.save(Visit.builder()
                .병원(hospital)
                .환자(patientList.get(0))
                .방문상태코드("1")
                .진료과목코드("01")
                .접수일시(firstVisitDateTime)
                .build());

        // 2021.05.11 김민형 - 환자 방문(접수) 목록 가져오기
        List<Visit> visitList = visitRepository.findAll();

        // 2021.05.11 김민형 - 자! 데이터가 잘 들어가는지 마음껏 테스트 해보자.
        assertThat(visitList.get(0).get환자ID()).isEqualTo(patientList.get(0).get환자ID());
        assertThat(visitList.get(0).get방문상태코드()).isEqualTo("1");
        assertThat(visitList.get(0).get진료과목코드()).isEqualTo("01");
        assertThat(visitList.get(0).get접수일시()).isEqualTo(firstVisitDateTime);
    }
}
