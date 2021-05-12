package com.hdjtest.reception.domain.patient;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Patient> findByName(String 환자명) {
        Patient patient = jpaQueryFactory.selectFrom(QPatient.patient)
                        .where(QPatient.patient.환자명.eq(환자명))
                        .fetchOne();

        return Optional.ofNullable(patient);
    }

    @Override
    public List<Patient> selectWithOptions(String optionType, String optionValue) {

        // TODO 2021.05.13 김민형 - 스프링부트에서 예외처리 부분을 정확히 어떻게 하는지 학습이 필요하다.
        //                         전반적으로 예외처리들을 제대로 못해놓은 상태인데 공통 예외처리 클래스 별도 생성이 필요하다.

        // 2021.05.13 김민형 - Service 단에서 처리되던 것들이 리포지토리 단까지 내려왔다.
        //                    Service 단에서 쿼리를 생성하는 구조인지 모르기 때문에 우선 구현하는 곳에서 하자.
        System.out.println(">>>> select options : " +  optionType + ", " +  optionValue);

        JPAQuery<Patient> queryPatientList = jpaQueryFactory.selectFrom(QPatient.patient);

        if (optionType.equals("patient_name")) {
            queryPatientList.where(QPatient.patient.환자명.eq(optionValue));
        } else if (optionType.equals("patient_reg_num")) {
            queryPatientList.where(QPatient.patient.환자등록번호.eq(optionValue));
        } else if (optionType.equals("patient_birth")) {
            queryPatientList.where(QPatient.patient.생년월일.eq(optionValue));
        } else {
            return null;
        }

        return queryPatientList.fetch();
    }
}
