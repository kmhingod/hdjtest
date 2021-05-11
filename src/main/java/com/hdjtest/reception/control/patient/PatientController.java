package com.hdjtest.reception.control.patient;

import com.hdjtest.reception.service.dto.patient.PatientDto;
import com.hdjtest.reception.service.svc.base.HospitalService;
import com.hdjtest.reception.service.svc.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return patientService.selectById(patientId);
    }

    @PutMapping("/api/v1/patients/{patientId}")
    public Long updatePatient(@PathVariable Long patientId, @RequestBody PatientDto patientDto) throws Exception {
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
