package com.hdjtest.reception.service.dto.patient;

import com.hdjtest.reception.domain.base.Hospital;
import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.Visit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientRequestDto {

    private long page;
    private long pageSize;
    private long totalItemCount;
    private long pageCount;

    private List<PatientDto> patientDtoList;

    public PatientRequestDto(List<PatientDto> patientDtoList){
        this.patientDtoList = patientDtoList;
    }
}
