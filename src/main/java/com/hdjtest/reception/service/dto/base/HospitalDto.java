package com.hdjtest.reception.service.dto.base;

import com.hdjtest.reception.domain.base.Hospital;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class HospitalDto {

    private Long hospId;
    private String hospName;
    private String hospOrgNum;
    private String hospPresidentName;

    public HospitalDto(Hospital hospital) {
        this.hospId = hospital.get병원ID();
        this.hospName = hospital.get병원명();
        this.hospOrgNum = hospital.get요양기관번호();
        this.hospPresidentName = hospital.get병원장명();
    }

    @Builder
    //public Hospital insertHospital(String hospName, String hospOrgNum, String hospPresidentName) {
    public Hospital insertHospital(HospitalDto hospitalDto) {
        this.hospName = hospitalDto.hospName;
        this.hospOrgNum = hospitalDto.hospOrgNum;
        this.hospPresidentName = hospitalDto.hospPresidentName;

        return Hospital.builder()
                .병원명(hospName)
                .요양기관번호(hospOrgNum)
                .병원장명(hospPresidentName)
                .build();
    }
}
