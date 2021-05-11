package com.hdjtest.reception.control.patient;

import com.hdjtest.reception.service.dto.patient.VisitDto;
import com.hdjtest.reception.service.svc.base.HospitalService;
import com.hdjtest.reception.service.svc.patient.PatientService;
import com.hdjtest.reception.service.svc.patient.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class VisitController {

    private final VisitService visitService;
    private final HospitalService hospitalService;
    private final PatientService patientService;

    @PostMapping("/api/v1/visits")
    public Long insertVisit(@RequestBody VisitDto visitDto) throws Exception {
        System.out.println(">>>>> Request Data : " + visitDto);
        return visitService.save(visitDto);
    }

    @GetMapping("/api/v1/visits/{visitId}")
    public VisitDto selectVisit(@PathVariable Long visitId) throws Exception {
        return visitService.selectById(visitId);
    }

    @PutMapping("/api/v1/visits/{visitId}")
    public Long updateVisit(@PathVariable Long visitId, @RequestBody VisitDto visitDto) throws Exception {
        System.out.println(">>>>> Request Data : " + visitId + ", " + visitDto);

        // 2021.05.11 김민형 - 유요한 병원ID 인지 확인. 컨트롤 단에서 확인할 지 서비스 단에서 할지는 검토 필요.
        // TODO 2021.05.12 김민형 - 생성되는 쿼리가 count로  존재 여부를 확인하는데 나중에 쿼리 성능 비교하여 변경 검토
        if (!hospitalService.IsExistingHospitalById(visitDto.getHospId())) {
            throw new IllegalArgumentException("병원이 존재하지 않습니다. 병원ID : " + visitDto.getHospId());
        }

        // 2021.05.12 김민형 - 유요한 환자ID 인지 확인. 컨트롤 단에서 확인할 지 서비스 단에서 할지는 검토 필요.
        // TODO 2021.05.12 김민형 - 환자 존재 여부를 병원ID완 함께 확인 해야 함. 수정 요망
        // TODO 2021.05.12 김민형 - 생성되는 쿼리가 count로  존재 여부를 확인하는데 나중에 쿼리 성능 비교하여 변경 검토
        if (!patientService.IsExistingPatientById(visitDto.getPatientId())) {
            throw new IllegalArgumentException("환자가 존재하지 않습니다. 환자ID : " + visitDto.getPatientId());
        }

        return visitService.update(visitId, visitDto);
    }

    @DeleteMapping("/api/v1/visits/{visitId}")
    public Long deleteVisit(@PathVariable Long visitId) throws Exception {
        visitService.delete(visitId);

        return visitId;
    }
}
