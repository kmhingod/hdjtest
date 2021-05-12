package com.hdjtest.reception.service.svc.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.base.HospitalRepository;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.PatientRepository;
import com.hdjtest.reception.domain.patient.VisitRepository;
import com.hdjtest.reception.service.dto.patient.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // 2021.05.12 김민형 - 환자 저장 전에 병원 존재여부 확인 위해.
    private final HospitalRepository hospitalRepository;

    private final VisitRepository visitRepository;

    @Transactional(readOnly = true)
    public PatientDto selectById(Long patientId, Boolean includeVisitList) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + patientId));

        PatientDto patientDto = new PatientDto(patient);

        if (includeVisitList) {
            // 2021.05.12 김민형 - Repository에서 받아오는 것도 해봤는데 Patient Entity에서 Onetomany 관계된 것으로 가져오는것도 되는군.
            //patientDto.setVisitList((patientDto.ConvertVisitEntityListToDtoList(visitRepository.selectVisitsByPatientId(patientId))));
            patientDto.setVisitList((patientDto.ConvertVisitEntityListToDtoList(patient.getVisitList())));
        }

        return patientDto;
    }

    @Transactional(readOnly = true)
    public List<PatientDto> selectDtoAll(String searchType, String searchValue, Boolean includeVisitList) {
        List<PatientDto> patientDtoList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<Patient>();

        System.out.println(">>>> request params : " +  searchType + ", " +  searchValue);
        if (searchType.equals("patient_name")) {
            patientList = patientRepository.selectPatientListByName(searchValue);
        } else if (searchType.equals("patient_reg_num")) {
            patientList = patientRepository.selectPatientListByPatientRegNum(searchValue);
        } else if (searchType.equals("patient_birth")) {
            patientList = patientRepository.selectPatientListByBirth(searchValue);
        } else {
            // 2021.05.12 김민형 - 검색 값이 없는 경우는 반환할게 없다.
            //patientList = patientRepository.findAll();
        }

        System.out.println(">>>> patientList Count : " +  patientList.size());
        // 2021.05.12 김민형 - Mapper 사용을 해야하는데...나중에 리펙토링 필요하다.
        for(Patient patient : patientList) {
            PatientDto patientDto = new PatientDto(patient);

            if (includeVisitList) {
                patientDto.setVisitList((patientDto.ConvertVisitEntityListToDtoList(patient.getVisitList())));
            }

            patientDtoList.add(patientDto);
        }

        return patientDtoList;
    }

    // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가하여 함수 하나로 사용
//    @Transactional(readOnly = true)
//    public PatientDto selectByIdWithVisits(Long patientId) throws Exception {
//        System.out.println(">>>> parameter id : " + patientId);
//
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + patientId));
//
//        PatientDto patientDto = new PatientDto(patient);
//
//        // 2021.05.12 김민형 - Repository에서 받아오는 것도 해봤는데 Patient Entity에서 Onetomany 관계된 것으로 가져오는것도 되는군.
//       //patientDto.setVisitList((patientDto.ConvertVisitEntityListToDtoList(visitRepository.selectVisitsByPatientId(patientId))));
//        patientDto.setVisitList((patientDto.ConvertVisitEntityListToDtoList(patient.getVisitList())));
//
//        return patientDto; //new PatientDto(patient);
//    }

    @Transactional
    public Long save(PatientDto patientDto) throws Exception {

        String lastRegNum = patientRepository.getMaxPatientRegNum(patientDto.getHospId());
        System.out.println(">>>>> searchedRegNum : " + lastRegNum);

        // 2021.05.13 김민형 - 우선 화면 단에서 환자등록번호를 가져오고 존재하는 경우 기존 것을 사용하고
        //                    없는 경우 새로 생성한다.
        //                    환자등록번호는 YYYY+9자리 시퀸스로 한다.
        //                    최종 환자등록번호와 다른 번호들은 별도 테이블로 만들어서 관리하는 것이 좋을 것 같다.
        if (lastRegNum == null) {
            String newRegNum = LocalDate.now().getYear() + String.format("%08d%d", 0, 1);
            System.out.println(">>>>> lastRegNum : " + newRegNum);
            patientDto.setPatientRegNum(newRegNum);
        } else if (patientDto.getPatientRegNum() != lastRegNum) {
            Integer newNum = Integer.parseInt(lastRegNum.substring(4)) + 1;
            String newRegNum = lastRegNum.substring(0, 4) + String.format("%09d", newNum);

            System.out.println(">>>>> newRegNum : " + newRegNum);
            patientDto.setPatientRegNum(newRegNum);
        }

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
