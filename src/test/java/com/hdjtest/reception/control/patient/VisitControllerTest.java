package com.hdjtest.reception.control.patient;

import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.domain.patient.PatientRepository;
import com.hdjtest.reception.domain.patient.Visit;
import com.hdjtest.reception.domain.patient.VisitRepository;
import com.hdjtest.reception.service.dto.patient.VisitDto;
import com.hdjtest.reception.service.svc.base.HospitalService;
import com.hdjtest.reception.service.svc.patient.PatientService;
import com.hdjtest.reception.service.svc.patient.VisitService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Sql(scripts = {"classpath:data/data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    VisitService visitService;

    @Autowired
    HospitalService hospitalService;

    @Autowired
    PatientService patientService;

    @Autowired
    VisitRepository visitRepository;

    // 2021.05.12 김민형 - 컨트롤러 테스트에서 리포지토리 바로 접근하는 것은 괜찮은지 모르겠다.
    //                    책에서는 괜찮다고 한 것 같긴한데...
    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    PatientRepository patientRepository;

    @Test
    void selectVisit_Test() throws Exception {
        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 11, 9, 0, 0);

        assertThat(visitService.selectById(1L).getVisitStateCode()).isEqualTo("2");
        assertThat(visitService.selectById(3L).getDeptCode()).isEqualTo("02");
        assertThat(visitService.selectById(5L).getVisitDateTime()).isEqualTo(dateTime);
    }

    @Test
    void insertVisit_Test() throws Exception {

        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 15, 11, 30, 0);
        // 2021.05.12 김민형 - @Builder를 사용하지 않아서 데이터 이리 넣을수밖에..
        VisitDto visitDto = new VisitDto();
        visitDto.setHospId(1L);
        visitDto.setPatientId(3L);
        visitDto.setVisitDateTime(dateTime);
        visitDto.setVisitStateCode("2");
        visitDto.setDeptCode("02");

        System.out.println(">>>>> visitDto : " + visitDto);

        String url = "http://localhost:" + port + "/api/v1/visits";

        ResponseEntity<Long> respEntity = restTemplate.postForEntity(url, visitDto, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());

        List<Visit> visitAll = visitService.selectAll();
        assertThat(visitAll.get(Integer.parseInt(respEntity.getBody().toString()) - 1).get접수일시()).isEqualTo(visitDto.getVisitDateTime());
        assertThat(visitAll.get(Integer.parseInt(respEntity.getBody().toString()) - 1).get환자ID()).isEqualTo(visitDto.getPatientId());
    }

    @Test
    void updateVisit_Test() throws Exception {

        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 15, 11, 30, 0);
        // 2021.05.12 김민형 - 새로운 방문 등록
        VisitDto visitDtoNew = new VisitDto();
        visitDtoNew.setHospId(1L);
        visitDtoNew.setPatientId(3L);
        visitDtoNew.setVisitDateTime(dateTime);
        visitDtoNew.setVisitStateCode("2");
        visitDtoNew.setDeptCode("02");
        System.out.println(">>>>> visitDtoNew : " + visitDtoNew);

        Long newVisitId = visitService.save(visitDtoNew);

        // 2021.05.12 김민형 - 변경할 방문 데이터
        LocalDateTime dateTimeUpd = LocalDateTime.of(2021, 5, 14, 8, 30, 0);

        VisitDto visitDtoUpd = new VisitDto();
        visitDtoUpd.setHospId(visitDtoNew.getHospId());
        visitDtoUpd.setPatientId(visitDtoNew.getPatientId());
        visitDtoUpd.setVisitDateTime(dateTimeUpd);
        visitDtoUpd.setVisitStateCode("1");
        visitDtoUpd.setDeptCode("01");
        System.out.println(">>>>> visitDtoUpd : " + visitDtoUpd);

        String url = "http://localhost:" + port + "/api/v1/visits/" + newVisitId;

        HttpEntity<VisitDto> requestEntity = new HttpEntity<>(visitDtoUpd);
        ResponseEntity<Long> respEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(respEntity.getBody()).isGreaterThan(0L);

        System.out.println(">>>>> Body : " + respEntity.getBody());

        // 2021.05.12 김민형 - 받아오는 방식을 바꿔보자.
//        List<Visit> visitAll = visitService.selectAll();
//        assertThat(visitAll.get(Integer.parseInt(respEntity.getBody().toString()) - 1).get접수일시()).isEqualTo(dateTimeUpd);
//        assertThat(visitAll.get(Integer.parseInt(respEntity.getBody().toString()) - 1).get진료과목코드()).isEqualTo(visitDtoUpd.getDeptCode());

        Visit visitUpd = visitService.selectVisitEntity(newVisitId);
        assertThat(visitUpd.get접수일시()).isEqualTo(dateTimeUpd);
        assertThat(visitUpd.get진료과목코드()).isEqualTo(visitDtoUpd.getDeptCode());
    }

    @Test
    void deleteVisit_Test() throws Exception {

        LocalDateTime dateTime = LocalDateTime.of(2021, 5, 15, 11, 30, 0);
        // 2021.05.12 김민형 - 삭제 처리될 새로운 방문 등록
        VisitDto visitDtoToDel = new VisitDto();
        visitDtoToDel.setHospId(1L);
        visitDtoToDel.setPatientId(3L);
        visitDtoToDel.setVisitDateTime(dateTime);
        visitDtoToDel.setVisitStateCode("2");
        visitDtoToDel.setDeptCode("02");
        System.out.println(">>>>> visitDtoToDel : " + visitDtoToDel);

        // 삽입 전 전체 로우 개수
        Long visitCountStart = visitRepository.count();

        Long toDelVisitId = visitService.save(visitDtoToDel);

        // 삽입 후 전체 로우 개수
        Long visitCountAfterSave = visitRepository.count();

        // 신규 데이터 존재 여부 확인
        Optional<Visit> visitNew = visitRepository.findById(toDelVisitId);
        Assertions.assertTrue(visitNew.isPresent());

        // 신규 데이터 값 확인
        Visit visitUpd = visitService.selectVisitEntity(toDelVisitId);
        assertThat(visitUpd.get접수일시()).isEqualTo(dateTime);
        assertThat(visitUpd.get진료과목코드()).isEqualTo(visitDtoToDel.getDeptCode());

        // 데이터 삽입 후 개수 비교
        // 2021.05.12 김민형 - 초기 개수에서 하나 증가해야만한다.
        assertThat(visitCountAfterSave).isEqualTo(visitCountStart + 1);

        String url = "http://localhost:" + port + "/api/v1/visits/" + toDelVisitId;

        HttpHeaders header = new HttpHeaders();
        HttpEntity httpEntity = new HttpEntity(header);

        ResponseEntity<String> respEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);

        assertThat(respEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 삭제 후 로우 개수 및 삽입 전과 비교
        Long visitCountAfterDelete = visitRepository.count();
        assertThat(visitCountAfterDelete).isEqualTo(visitCountStart);

        // 삭제 데이터 존재 여부 확인
        Optional<Visit> visitDelete = visitRepository.findById(toDelVisitId);
        Assertions.assertFalse(visitDelete.isPresent());
    }
}
