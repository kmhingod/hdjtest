package com.hdjtest.reception.domain.base;

import com.hdjtest.reception.domain.patient.Patient;
import com.hdjtest.reception.domain.patient.Visit;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// TODO 2021.05.08 김민형 - lombok으로 생성자 및 Getter 등 자동화 고려 필요
// TODO 2021.05.08 김민형 - Entity 변수명 영어로 변경 필요함.
// TODO 2021.05.08 김민형 - 병원 정보가 변경되는 경우를 위해 병원 이력을 위한 별도 처리 필요.
//                         병원 별 순번 추가 혹은 병원 정보 하위 테이블 생성 고민
@Entity
//@RequiredArgsConstructor
// 2021.05.11 김민형 - 우선 @Getter 없이 수동으로 Getter 생성해놨다. 사용하게 되면 모든 Getter 들 안녕...
//@Getter
@NoArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long 병원ID;

    @Column(length = 45, nullable = false)
    private String 병원명;

    @Column(length=20, nullable = false)
    private String 요양기관번호;

    @Column(length=10, nullable = false)
    private String 병원장명;

    // 2021.05.08 김민형 - 병원 별 환자 목록 조회용
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "병원")
    private List<Patient> patientList;

    // 2021.05.08 김민형 - 병원 별 환자 방문 내역 조회용
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "병원", fetch = FetchType.LAZY)
    private List<Visit> visitList;

    // 2021.05.11 김민형 - NoArgsConstructor를 사용하자!
    //public Hospital() {}

    // 2021.05.11 김민형 - 추후 필요 시 @Builder 사용하자.
    public Hospital(String 병원명, String 요양기관번호, String 병원장명) {
        // 2021.05.11 김민형 - Repository 없이 진행하다가 남은 잔재물...
        //this.setNew병원ID();
        this.병원명 = 병원명;
        this.요양기관번호 = 요양기관번호;
        this.병원장명 = 병원장명;
    }

    public Long get병원ID() {
        return this.병원ID;
    }

    public void set병원ID(Long 병원ID) {
        this.병원ID = 병원ID;
    }

    public String get병원명() {
        return this.병원명;
    }

    public String get요양기관번호() {
        return this.요양기관번호;
    }

    public String get병원장명() {
        return this.병원장명;
    }

    public List<Patient> getPatientList() {
        return this.patientList;
    }

    public List<Visit> getVisitList() {
        return this.visitList;
    }

    // 2021.05.11 김민형 - Repository 없이 진행하다가 남은 잔재물...
    //                    Repository 사용하면서 불필요해졌다. 지우자.
//    private void setNew병원ID() {
//        if (this.병원ID == null) {
//            this.병원ID = 1L;
//        } else {
//            this.병원ID++;
//        }
//    }
}
