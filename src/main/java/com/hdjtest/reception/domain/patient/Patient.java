package com.hdjtest.reception.domain.patient;


import com.hdjtest.reception.domain.base.Hospital;
import lombok.*;

import javax.persistence.*;
import java.util.List;

// TODO 2021.05.08 김민형 - lombok으로 생성자 및 Getter 등 자동화 고려 필요

@Entity
// 2021.05.11 김민형 - 우선 @Getter 없이 수동으로 Getter 생성해놨다. 사용하게 되면 모든 Getter 들 안녕...
@NoArgsConstructor
@ToString
public class Patient {
    // TODO 2021.05.08 김민형 - Entity 변수명 영어로 변경 필요함.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long 환자ID;

    @ManyToOne(fetch = FetchType.LAZY)
    // 2021.05.08 김민형 - 병원(Hospital) 테이블에 외래키로 연결되어야 한다.
    //@Column(length = 45, nullable = false)
    @JoinColumn(name = "병원ID")
    private Hospital 병원;

    @Column(length = 45, nullable = false)
    private String 환자명;

    // 2021.05.08 김민형 - 한 DB에서 여러 병원 데이터가 같이 사용되고
    //                    병원ID에 따라 병원 별 데이터가 분리된다면
    //                    병원ID에 따른 환자등록번호 중복 여유 확인 필요.
    //                    이하 파생되는 모든 데이터도 마찬가지.
    @Column(length = 13, nullable = false)
    private String 환자등록번호;

    // TODO 2021.05.08 김민형 - 성별 컬럼 길이가 10이나 필요할까요?
    @Column(length = 10, nullable = false)
    private String 성별코드;

    @Column(length = 10)
    private String 생년월일;

    @Column(length = 20)
    private String 휴대전화번호;

    @Column(length = 100)
    private String 이메일;

//    @Column(length = 100)
//    private String 기본주소;
//
//    @Column(length = 100)
//    private String 상세주소;

    // 2021.05.08 김민형 - 환자병 방문 내역 조회용
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "환자")
    private List<Visit> visitList;

    // 2021.05.11 김민형 - NoArgsConstructor를 사용하자!
    // public Patient() {}


    @Builder
    public Patient(Hospital 병원, String 환자명, String 환자등록번호, String 성별코드, String 생년월일, String 휴대전화번호, String 이메일) {
        this.병원 = 병원;
        this.환자명 = 환자명;
        this.환자등록번호 = 환자등록번호;
        this.성별코드 = 성별코드;
        this.생년월일 = 생년월일;
        this.휴대전화번호 = 휴대전화번호;
        this.이메일 = 이메일;
    }

    @Builder
    public Patient(Hospital 병원, String 환자명, String 환자등록번호, String 성별코드) {
        this.병원 = 병원;
        this.환자명 = 환자명;
        this.환자등록번호 = 환자등록번호;
        this.성별코드 = 성별코드;
    }

    public Long get환자ID() {
        return this.환자ID;
    }

    public Long get병원ID() {
        return this.병원.get병원ID();
    }

    public String get환자명() {
        return this.환자명;
    }

    public String get환자등록번호() {
        return this.환자등록번호;
    }

    public String get성별코드() {
        return this.성별코드;
    }

    public String get생년월일() {
        return this.생년월일;
    }

    public String get휴대전화번호() {
        return this.휴대전화번호;
    }

    public String get이메일() {
        return this.이메일;
    }

    public void set환자ID(Long 환자ID) {
        this.환자ID = 환자ID;
    }

    public List<Visit> getVisitList() {
        return this.visitList;
    }


    public void update(Hospital 병원, String 환자명, String 환자등록번호, String 성별코드, String 생년월일, String 휴대전화번호, String 이메일) {
        this.병원 = 병원;
        this.환자명 = 환자명;
        this.환자등록번호 = 환자등록번호;
        this.성별코드 = 성별코드;
        this.생년월일 = 생년월일;
        this.휴대전화번호 = 휴대전화번호;
        this.이메일 = 이메일;
    }
}
