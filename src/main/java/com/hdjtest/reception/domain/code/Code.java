package com.hdjtest.reception.domain.code;

import javax.persistence.*;

@Entity
public class Code {
    // TODO 2021.05.08 김민형 - Entity 변수명 영어로 변경 필요함.

    @Id
    @Column(length = 10)
    private String 코드;

    @Column(length = 10, nullable = false)
    private String 코드그룹;

    @Column(length = 10, nullable = false)
    private String 코드명;

    public Code() {}

    public Code(String 코드, String 코드그룹, String 코드명) {
        this.코드 = 코드;
        this.코드그룹 = 코드그룹;
        this.코드명 = 코드명;
    }

    public String get코드() {
        return this.코드;
    }

    public String get코드그룹() {
        return this.코드그룹;
    }

    public String get코드명() {
        return this.코드명;
    }
}
