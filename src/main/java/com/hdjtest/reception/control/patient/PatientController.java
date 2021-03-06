package com.hdjtest.reception.control.patient;

import com.hdjtest.reception.service.dto.patient.PatientDto;
import com.hdjtest.reception.service.dto.patient.PatientRequestDto;
import com.hdjtest.reception.service.svc.base.HospitalService;
import com.hdjtest.reception.service.svc.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PatientController {

    private final PatientService patientService;
    private final HospitalService hospitalService;

    @PostMapping("/api/v1/patients")
    public Long insertPatient(@RequestBody PatientDto patientDto) throws Exception {
        System.out.println(">>>>> Request Data : " + patientDto);
        return patientService.save(patientDto);
    }

    @GetMapping("/api/v1/patients/{patientId}")
    public PatientDto selectPatient(@PathVariable Long patientId) {
        // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가
        return patientService.selectById(patientId, false);
    }

    @GetMapping("/api/v1/patients")
    public List<PatientDto> selectPatientAll(@RequestParam(value = "searchType", defaultValue = "") String searchType
                                            ,@RequestParam(value = "searchValue", defaultValue = "") String searchValue) {


        // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가
        return patientService.selectDtoAll(searchType, searchValue, false);
    }

    // 페이징 적용한 api
    @GetMapping("/api/v1/patients/paging")
    public PatientRequestDto selectPatientAllPaging(@RequestParam(value = "searchType", defaultValue = "") String searchType,
                                                          @RequestParam(value = "searchValue", defaultValue = "") String searchValue,
                                                          @RequestParam(value = "page", defaultValue = "1") String page,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") String pageSize) {

        // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가
        return patientService.selectDtoAllPaging(searchType, searchValue, false, page, pageSize);
    }

    // 2021.05.12 김민형 - 실제로 사용될일은 없다고 보겠지만 그냥 테스트 삼아 냅두자.
    @GetMapping("/api/v1/patients/include-visit-all")
    public List<PatientDto> selectPatientAllWithVisits(@RequestParam(value = "searchType") String searchType,
                                                       @RequestParam(value = "searchValue") String searchValue) {
        // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가
        return patientService.selectDtoAll(searchType, searchValue,true);
    }

    // 2021.05.12 김민형 - 방문목록 포함하는 파라미터 추가하여 함수 하나로 사용
    @GetMapping("/api/v1/patients/{patientId}/include-visit-all")
    public PatientDto selectPatientWithVisits(@PathVariable Long patientId) {
        System.out.println(">>>> request id : " + patientId);
        return patientService.selectById(patientId, true);
    }

    @PutMapping("/api/v1/patients/{patientId}")
    public Long updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) {
        System.out.println(">>>>> Request Data : " + patientId + ", " + patientDto);

        // 2021.05.11 김민형 - 유요한 병원ID 인지 확인. 컨트롤 단에서 확인할 지 서비스 단에서 할지는 검토 필요.
        if (!hospitalService.IsExistingHospitalById(patientDto.getHospId())) {
            throw new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID : " + patientDto.getHospId());
        }

        return patientService.update(patientId, patientDto);
    }

    @DeleteMapping("/api/v1/patients/{patientId}")
    public Long deletePatient(@PathVariable Long patientId) {
        // TODO 2021.05.12 김민형 - 반환값으로 삭제된 ID와 개수로 변경 핋요함듯함.
        patientService.delete(patientId);

        return patientId;
    }

}
