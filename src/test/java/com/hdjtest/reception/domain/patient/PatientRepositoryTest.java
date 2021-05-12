package com.hdjtest.reception.domain.patient;

import com.hdjtest.reception.config.TestConfig;
import com.hdjtest.reception.domain.base.Hospital;

import com.hdjtest.reception.domain.base.HospitalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
//@DataJpaTest
@Import(TestConfig.class)
@SpringBootTest
public class PatientRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Test
    @Transactional
    void registerPatient_Test() {
        // 2021.05.11 김민형 - 동일인이 다른 병원에 각각 등록항 경우와 다른 병원에 다른 사람이 등록한 경우 테스트
        Hospital hospital = new Hospital("아무개 병원", "31123456", "아무개");
        Hospital hospital2 = new Hospital("아무개 병원2", "31111111", "아무개2");
        Hospital hospital3 = new Hospital("아무개 병원3", "31222222", "아무개3");
        hospitalRepository.save(hospital);
        hospitalRepository.save(hospital2);
        hospitalRepository.save(hospital3);

        // 2021.05.11 김민형 - 우리의 관점은 환자 정보가 저장되었는지 이므로 굳이 HospitalRepository에서 확인은 불필요할 듯 하다.
        //Hospital newHospital = hospitalRepository.getOne(1L);

        System.out.println(">>>> 병원 ID : "+ hospital.get병원ID() + ", 병원명 : " + hospital.get병원명());

        patientRepository.save(Patient.builder()
                                .병원(hospital)
                                .환자명("김민형")
                                .환자등록번호("123456789")
                                .성별코드("M")
                                .build());

        patientRepository.save(Patient.builder()
                                .병원(hospital2)
                                .환자명("김민형")
                                .환자등록번호("987654321")
                                .성별코드("M")
                                .build());

        patientRepository.save(Patient.builder()
                                .병원(hospital3)
                                .환자명("신입생")
                                .환자등록번호("123454321")
                                .성별코드("F")
                                .build());

        patientRepository.save(Patient.builder()
                                .병원(hospital)
                                .환자명("복학생")
                                .환자등록번호("123456788")
                                .성별코드("M")
                                .build());

        System.out.println(">>>> saved");

        List<Patient> patientList = patientRepository.findAll();

        assertThat(patientList.get(0).get병원ID()).isEqualTo(hospital.get병원ID());
        assertThat(patientList.get(0).get환자명()).isEqualTo("김민형");
        assertThat(patientList.get(0).get환자등록번호()).isEqualTo("123456789");
        assertThat(patientList.get(0).get성별코드()).isEqualTo("M");

        assertThat(patientList.get(1).get병원ID()).isEqualTo(hospital2.get병원ID());
        assertThat(patientList.get(1).get환자명()).isEqualTo("김민형");

        // 2021.05.11 김민형 - 실패.  환자번호는 역순으로 저장했다.
        //assertThat(patientList.get(1).get환자등록번호()).isEqualTo("987654321");
        assertThat(patientList.get(1).get환자등록번호()).isEqualTo("123456789");
        assertThat(patientList.get(1).get성별코드()).isEqualTo("M");

        assertThat(patientList.get(2).get병원ID()).isEqualTo(hospital3.get병원ID());
        assertThat(patientList.get(2).get환자명()).isEqualTo("신입생");
        assertThat(patientList.get(2).get환자등록번호()).isEqualTo("123454321");
        assertThat(patientList.get(2).get성별코드()).isEqualTo("F");

        assertThat(patientList.get(3).get병원ID()).isEqualTo(hospital.get병원ID());
        assertThat(patientList.get(3).get환자명()).isEqualTo("복학생");
        assertThat(patientList.get(3).get환자등록번호()).isEqualTo("123456788");

        // 2021.05.11 김민형 - 실패. 분명 성별은 남자였다. 군제대하고 온 복학생이었다.
        //assertThat(patientList.get(3).get성별코드()).isEqualTo("M");
        assertThat(patientList.get(3).get성별코드()).isEqualTo("F");
    }

    @Test
    @Transactional
    void findByName_Test() {
        Patient patient = patientRepository.findByName("김민형")
                .orElseThrow(() -> new IllegalArgumentException("Patient 가 존재하지 않습니다. 환자명 : " + "김민형"));

        System.out.println(">>>>> Patient Name : " + patient.get환자명());
        assertThat(patient.get환자명()).isEqualTo("김민형");
    }
}
