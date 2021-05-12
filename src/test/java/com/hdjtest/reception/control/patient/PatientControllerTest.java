package com.hdjtest.reception.control.patient;

import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.PatientRepository;
import com.hdjtest.reception.service.dto.patient.PatientDto;
import com.hdjtest.reception.service.svc.patient.PatientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Sql(scripts = {"classpath:data/data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PatientService patientService;

    // 2021.05.12 김민형 - 컨트롤러 테스트에서 리포지토리 바로 접근하는 것은 괜찮은지 모르겠다.
    //                    책에서는 괜찮다고 한 것 같긴한데...
    @Autowired
    PatientRepository patientRepository;


    @Test
    void selectPatient_Test() throws Exception {
        //assertThat("aaa").isEqualTo("aaa");
        assertThat(patientService.selectById(1L, true).getPatientName()).isEqualTo("김민형");
    }

//    void selectPatientWithVisits_Test() throws Exception {
//
//        String url = "http://localhost:" + port + "/api/v1/patients/1/visit-all";
//
//        ResponseEntity<Long> respEntity = restTemplate.getForEntity(url, patientDto, Long.class);
//        //assertThat("aaa").isEqualTo("aaa");
//        assertThat(patientService.selectById(1L).getPatientName()).isEqualTo("김민형");
//    }

    @Test
    void insertPatient_Test() throws Exception {

        // 2021.05.12 김민형 - @Builder를 사용하지 않아서 데이터 이리 넣을수밖에..
        PatientDto patientDto = new PatientDto();
        patientDto.setHospId(1L);
        patientDto.setPatientName("남학생");
        patientDto.setPatientRegNum("111111111");
        patientDto.setSexCode("M");
        patientDto.setBirthDay("19800726");
        patientDto.setMobileNum("010-0123-4567");
        patientDto.setEMail("bbb@ccc.net");

        System.out.println(">>>>> patientDto : " + patientDto);

        String url = "http://localhost:" + port + "/api/v1/patients";

        ResponseEntity<Long> respEntity = restTemplate.postForEntity(url, patientDto, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());

        Patient patient = patientService.selectPatientEntity(respEntity.getBody());
        assertThat(patient.get환자명()).isEqualTo(patientDto.getPatientName());
        assertThat(patient.get성별코드()).isEqualTo(patientDto.getSexCode());

    }

    @Test
    void insertPatientWithDifferentRegNum_Test() throws Exception {

        // 2021.05.13 김민형 - 프론트 단에서 이미 존재하는 환자등록번호로 들어온 경우 처리
        PatientDto patientDto = new PatientDto();
        patientDto.setHospId(1L);
        patientDto.setPatientName("남학생");
        // 이미 존재하는 등록번호
        patientDto.setPatientRegNum("2021000000001");
        patientDto.setSexCode("M");
        patientDto.setBirthDay("19800726");
        patientDto.setMobileNum("010-0123-4567");
        patientDto.setEMail("bbb@ccc.net");

        System.out.println(">>>>> patientDto : " + patientDto);

        String url = "http://localhost:" + port + "/api/v1/patients";

        ResponseEntity<Long> respEntity = restTemplate.postForEntity(url, patientDto, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());
        Long newPatientId = Long.parseLong(respEntity.getBody().toString());

        Patient newPatient = patientService.selectPatientEntity(newPatientId);
        System.out.println(">>>>> Patient Reg Num Original: " + patientDto.getPatientRegNum());
        System.out.println(">>>>> Patient Reg Num After Save: " + newPatient.get환자등록번호());
        // 2021.05.12 김민형 - 환자등록번호 달라야한다.
        assertThat(newPatient.get환자등록번호()).isNotEqualTo(patientDto.getPatientRegNum());
    }

    @Test
    void insertPatientWithNewHospital_Test() throws Exception {

        // 2021.05.13 김민형 - 환자를 처음 등록하는 병원의 경우
        PatientDto patientDto = new PatientDto();
        patientDto.setHospId(5L);
        patientDto.setPatientName("남학생");
        // 환자등록번호가 null 이든 어떤 값이든 상관이 없다. 신규 환자등록번호 이므로 YYYY + 000000001 이 된다.
        patientDto.setPatientRegNum("2021000000021");
        patientDto.setSexCode("M");
        patientDto.setBirthDay("19800726");
        patientDto.setMobileNum("010-0123-4567");
        patientDto.setEMail("bbb@ccc.net");

        System.out.println(">>>>> patientDto : " + patientDto);

        String url = "http://localhost:" + port + "/api/v1/patients";

        ResponseEntity<Long> respEntity = restTemplate.postForEntity(url, patientDto, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());
        Long newPatientId = Long.parseLong(respEntity.getBody().toString());

        Patient newPatient = patientService.selectPatientEntity(newPatientId);
        System.out.println(">>>>> Patient Reg Num Original: " + patientDto.getPatientRegNum());
        System.out.println(">>>>> Patient Reg Num After Save: " + newPatient.get환자등록번호());
        // 2021.05.12 김민형 - 환자등록번호는 항상 YYYY + 000000001 이 된다.
        assertThat(newPatient.get환자등록번호()).isEqualTo(LocalDate.now().getYear() +"000000001");
    }

    @Test
    void updatePatient_Test() throws Exception {

        // 2021.05.12 김민형 - 새로운 환자 등록
        PatientDto patientDtoNew = new PatientDto();
        patientDtoNew.setHospId(1L);
        patientDtoNew.setPatientName("남학생");
        patientDtoNew.setPatientRegNum("111111111");
        patientDtoNew.setSexCode("M");
        patientDtoNew.setBirthDay("19800726");
        patientDtoNew.setMobileNum("010-0123-4567");
        patientDtoNew.setEMail("bbb@ccc.net");
        System.out.println(">>>>> patientDtoNew : " + patientDtoNew);

        Long newPatientId = patientService.save(patientDtoNew);

        // 2021.05.12 김민형 - 변경할 환자 데이터
        // TODO 2021.05.12 김민형 - 업데이트 될 때 모든 컬럼 값을 넣어줘야 하는지 모르겠다.
        //                         HospiId 값을 지정 안한 경우 null 에러가 발생하는데
        //                         수십 수백개의 컬럼이 있는 경우 처리가 난감함. 처리 방법 확인 필요.
        PatientDto patientDtoUpd = new PatientDto();
        //patientDtoUpd.setHospId(patientDtoNew.getHospId());
        patientDtoUpd.setHospId(2L);
        patientDtoUpd.setPatientName("여학생");
        patientDtoUpd.setPatientRegNum("111111111");
        patientDtoUpd.setSexCode("F");
        patientDtoUpd.setBirthDay("19871127");
        patientDtoUpd.setMobileNum("010-9898-0909");
        patientDtoUpd.setEMail("ooo@aaa.net");
        System.out.println(">>>>> patientDtoUpd : " + patientDtoUpd);

        String url = "http://localhost:" + port + "/api/v1/patients/" + newPatientId;

        HttpEntity<PatientDto> requestEntity = new HttpEntity<>(patientDtoUpd);

        ResponseEntity<Long> respEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());

        Patient patient = patientService.selectPatientEntity(newPatientId);
        assertThat(patient.get환자명()).isEqualTo(patientDtoUpd.getPatientName());
        assertThat(patient.get성별코드()).isEqualTo(patientDtoUpd.getSexCode());

        PatientDto updPatientDto = patientService.selectPatientDto(newPatientId);
        System.out.println(">>>>> Updated patient information : " + updPatientDto);
    }

    @Test
    void deletePatient_Test() throws Exception {

        // 2021.05.12 김민형 - 삭제 처리될 새로운 환자 등록
        PatientDto patientDtoToDel = new PatientDto();
        patientDtoToDel.setHospId(1L);
        patientDtoToDel.setPatientName("남학생");
        patientDtoToDel.setPatientRegNum("111111111");
        patientDtoToDel.setSexCode("M");
        patientDtoToDel.setBirthDay("19800726");
        patientDtoToDel.setMobileNum("010-0123-4567");
        patientDtoToDel.setEMail("bbb@ccc.net");
        System.out.println(">>>>> patientDtoNew : " + patientDtoToDel);

        // 삽입 전 전체 로우 개수
        Long patientCountStart = patientRepository.count();

        Long toDelPatientId = patientService.save(patientDtoToDel);

        // 삽입 후 전체 로우 개수
        Long patientCountAfterSave = patientRepository.count();

        // 신규 데이터 존재 여부 확인
        Optional<Patient> patientNew = patientRepository.findById(toDelPatientId);
        Assertions.assertTrue(patientNew.isPresent());

        // 신규 데이터 값 확인
        List<Patient> patientAll = patientService.selectAll();
        assertThat(patientAll.get(toDelPatientId.intValue() - 1).get환자명()).isEqualTo(patientDtoToDel.getPatientName());
        assertThat(patientAll.get(toDelPatientId.intValue() - 1).get성별코드()).isEqualTo(patientDtoToDel.getSexCode());

        // 데이터 삽입 후 개수 비교
        // 2021.05.12 김민형 - 초기 개수에서 하나 증가해야만한다.
        assertThat(patientCountAfterSave).isEqualTo(patientCountStart + 1);

        String url = "http://localhost:" + port + "/api/v1/patients/" + toDelPatientId;

        HttpHeaders header = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(header);

        ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 삭제 후 로우 개수 및 삽입 전과 비교
        Long patientCountAfterDelete = patientRepository.count();
        assertThat(patientCountAfterDelete).isEqualTo(patientCountStart);

        // 삭제 데이터 존재 여부 확인
        Optional<Patient> patientDelete = patientRepository.findById(toDelPatientId);
        Assertions.assertFalse(patientDelete.isPresent());
    }
}
