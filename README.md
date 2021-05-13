---
layout: post
title:  "HDJuncfion Test"
subtitle: "환자 검색"
categories: envops
tags: SpringBoot etc  
comments: true
---

## [api 설명]
> 페이징이 가능한 환자 검색 기능

- 검색 기능 : 이름, 환자등록번호, 생년월일
- api url : /api/v1/patients/paging
- Query String
  - searchType : patient_name, patient_reg_num, patient_birth
  - searchValue : 이름, 환자번호(YYYY000000000), yyyymmdd
  - page : 1 ~
  - pagesSize : 숫자 (기본값 10)

## [개발 환경]
- Java 11
- Gradle 6.8
- SpringBoot 2.4.5
- H2
- querydsl
- Tool : IntelliJ IDEA 2021
    
## [개발 진행 배경]
처음으로 SpringBoot 를 이용해서 진행한 프로젝트입니다. 때문에 학습하면서 진행을 했는데 
하루에 10시간씩 한 일주일 진행한 것 같습니다. 일반 C/S 구조와는 많이 달랐고 Asp.Net MVC와 
node express 그리고 .Net Core 로 api 작업은 해봤지만 수월한 개발을 위해 제공되는 다양한 
라이브러리들과 라이브러리 버전마다 달라지는 형식 때문에 간단하지는 않았습니다.

## [TO DO]
- 전체 구조를 변경
- querydsl 관련하여 학습 후 query 변경 및 성능 검사
- restdocs 추가



