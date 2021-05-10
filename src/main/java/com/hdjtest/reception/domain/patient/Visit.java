package com.hdjtest.reception.domain.patient;

import com.hdjtest.reception.domain.base.Hospital;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
// 2021.05.11 김민형 - 우선 @Getter 없이 수동으로 Getter 생성해놨다. 사용하게 되면 모든 Getter 들 안녕...
@NoArgsConstructor
public class Visit {
    // TODO 2021.05.08 김민형 - Entity 변수명 영어로 변경 필요함.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long 환자방문ID;

    @ManyToOne
    @JoinColumn(name = "병원ID")
    private Hospital 병원;

    @ManyToOne
    @JoinColumn(name = "환자ID")
    private Patient 환자;

    @Column(nullable = false)
    private LocalDateTime 접수일시;

    @Column(length = 10, nullable = false)
    private String 방문상태코드;

    // 2021.05.08 김민형 - 병원에 여러 과목이 있는 경우 필요
    @Column(length = 10)
    private String 진료과목코드;

    // 2021.05.11 김민형 - NoArgsConstructor를 사용하자!
    //public Visit() {}

    @Builder
    public Visit(Hospital 병원, Patient 환자, String 방문상태코드, String 진료과목코드, LocalDateTime 접수일시) {
        this.병원 = 병원;
        this.환자 = 환자;
        this.방문상태코드 = 방문상태코드;
        this.진료과목코드 = 진료과목코드;
        this.접수일시 = 접수일시;
    }

    public Long get환자방문ID() {
        return 환자방문ID;
    }

    public Long get병원ID() {
        return this.병원.get병원ID();
    }

    public Long get환자ID() {
        return this.환자.get환자ID();
    }

    public LocalDateTime get접수일시() {
        return this.접수일시;
    }

    public String get방문상태코드() {
        return this.방문상태코드;
    }

    public String get진료과목코드() {
        return this.진료과목코드;
    }
}
