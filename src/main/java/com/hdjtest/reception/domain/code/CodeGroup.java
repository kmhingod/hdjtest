package com.hdjtest.reception.domain.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodeGroup {
    // TODO 2021.05.08 김민형 - Entity 변수명 영어로 변경 필요함.

    @Id
    @Column(length = 10)
    private String 코드그룹;

    @Column(length = 10, nullable = false)
    private String 코드그룹명;

    // 2021.05.08 김민형 - ERD에는 길이가 10인데 설명 내용은 10글자 넘어서 컬럼 길이 100으로 함
    @Column(length = 100, nullable = false)
    private String 설명;

    public CodeGroup() {}

    public CodeGroup(String 코드그룹, String 코드그룹명, String 설명) {
        this.코드그룹 = 코드그룹;
        this.코드그룹명 = 코드그룹명;
        this.설명 = 설명;
    }

    public String get코드그룹() {
        return this.코드그룹;
    }

    public String get코드그룹명() {
        return this.코드그룹명;
    }

    public String get설명() {
        return this.설명;
    }
}
