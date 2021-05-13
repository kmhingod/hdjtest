package com.hdjtest.reception.domain.patient;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    // 페이징 기능 추가한 환자 검색 함수
    @Override
    public Page<Patient> selectWithOptionsPaging(String optionType, String optionValue, Pageable pageable) {

        // TODO 2021.05.13 김민형 - 스프링부트에서 예외처리 부분을 정확히 어떻게 하는지 학습이 필요하다.
        //                         전반적으로 예외처리들을 제대로 못해놓은 상태인데 공통 예외처리 클래스 별도 생성이 필요하다.

        // 2021.05.13 김민형 - Service 단에서 처리되던 것들이 리포지토리 단까지 내려왔다.
        //                    Service 단에서 쿼리를 생성하는 구조인지 모르기 때문에 우선 구현하는 곳에서 하자.
        System.out.println(">>>> select options : " +  optionType + ", " +  optionValue);

        QPatient p = QPatient.patient;
        QVisit v = QVisit.visit;

        // 2021.05.13 김민형 - 최종방문일은 병월 별로 조회가 되어야 한다.
        JPAQuery<Patient> queryPatientList = jpaQueryFactory.selectFrom(p);

        // TODO 2021.05.13 김민형 - 최종방문일을 가져와하는데 아래 방법으로 안된다. 우선 넘어가자.
//        JPAQuery<Patient> queryPatientList = jpaQueryFactory.select(Projections.fields(Patient.class, p,
//                ExpressionUtils.as(
//                        JPAExpressions.select(v.접수일시.max())
//                                            .from(v)
//                                            .where(v.환자.환자ID.eq(p.환자ID), v.병원.병원ID.eq(p.병원.병원ID)),
//                                            "lastVisitDate")
//                ))
//                .from(p);

        //PathBuilder<Object> orderByExpression;

        if (optionType.equals("patient_name")) {
            queryPatientList.where(p.환자명.eq(optionValue));
            //orderByExpression = new PathBuilder<Object>(Patient.class, "환자명");
        } else if (optionType.equals("patient_reg_num")) {
            queryPatientList.where(p.환자등록번호.eq(optionValue));
            //orderByExpression = new PathBuilder<Object>(Patient.class, "환자등록번호");
        } else if (optionType.equals("patient_birth")) {
            queryPatientList.where(p.생년월일.eq(optionValue));
            //orderByExpression = new PathBuilder<Object>(Patient.class, "생년월일");
        } else {
            return null;
        }

        // 페이징 쿼리 추가
        queryPatientList.offset(pageable.getOffset())
                        .limit(pageable.getPageSize());

        // 정렬 쿼리 추가.
//        for (Sort.Order o : pageable.getSort()) {
//            queryPatientList.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
//                                    orderByExpression.get(o.getProperty())));
//        }

        QueryResults<Patient> queryResult = queryPatientList.fetchResults();
        List<Patient> patientList = queryResult.getResults();
        long totalPatientCount = queryResult.getTotal();

        System.out.println(patientList.size());

        return new PageImpl<Patient> (patientList, pageable, totalPatientCount) ;
    }
}
