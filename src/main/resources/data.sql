-- 그룹코드 기초 자료
INSERT INTO CODE_GROUP ("코드그룹", "코드그룹명", "설명") VALUES('성별코드', '성별코드', '성별을 표시');
INSERT INTO CODE_GROUP ("코드그룹", "코드그룹명", "설명") VALUES('방문상태코드', '방문상태코드', '환자방문의 상태(방문중, 종료, 취소)');
INSERT INTO CODE_GROUP ("코드그룹", "코드그룹명", "설명") VALUES('진료과목코드', '진료과목코드', '진료과목(내과, 안과 등)');
INSERT INTO CODE_GROUP ("코드그룹", "코드그룹명", "설명") VALUES('진료유형코드', '진료유형코드', '진료의 유형(약처방, 검사 등)');

-- 코드 기초 자료
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('성별코드', 'M', '남');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('성별코드', 'F', '여');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('방문상태코드', '1', '방문중');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('방문상태코드', '2', '종료');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('방문상태코드', '3', '취소');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('진료과목코드', '01', '내과');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('진료과목코드', '02', '안과');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('진료유형코드', 'D', '약처방');
INSERT INTO CODE ("코드그룹", "코드", "코드명") VALUES('진료유형코드', 'T', '검사');

INSERT INTO Hospital ("병원명", "요양기관번호", "병원장명") VALUES ('아무개 병원', '31123456', '아무개');
INSERT INTO Hospital ("병원명", "요양기관번호", "병원장명") VALUES ('아무개 병원2', '31111111', '아무개2');
INSERT INTO Hospital ("병원명", "요양기관번호", "병원장명") VALUES ('아무개 병원3', '31222222', '아무개3');
INSERT INTO Hospital ("병원명", "요양기관번호", "병원장명") VALUES ('아무개 병원4', '31333333', '아무개4');
INSERT INTO Hospital ("병원명", "요양기관번호", "병원장명") VALUES ('아무개 병원5', '31444444', '아무개5');

INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '김민형', '2021000000001', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름2', '2021000000002', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름3', '2021000000003', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름4', '2021000000004', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름5', '2021000000005', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름6', '2021000000006', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름7', '2021000000007', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름8', '2021000000008', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름9', '2021000000009', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름10', '2021000000010', 'F', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름11', '2021000000011', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름12', '2021000000012', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (1, '병원1이름13', '2021000000013', 'M', '19800726', '010-0000-0000', 'aaa@aaa.net');

INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '신입생', '2021000000001', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름2', '2021000000002', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름3', '2021000000003', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름4', '2021000000004', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름5', '2021000000005', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름6', '2021000000006', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름7', '2021000000007', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름8', '2021000000008', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름9', '2021000000009', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름10', '2021000000010', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름11', '2021000000011', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름12', '2021000000012', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름13', '2021000000013', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름14', '2021000000014', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름15', '2021000000015', 'F', '20030101', '010-1234-1234', 'bbb@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (2, '병원2이름16', '2021000000016', 'M', '20030101', '010-1234-1234', 'bbb@aaa.net');

INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '복학생', '2021000000001', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '재학생', '2021000000002', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름3', '2021000000003', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름4', '2021000000004', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름5', '2021000000005', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름6', '2021000000006', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름7', '2021000000007', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름8', '2021000000008', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름9', '2021000000009', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름10', '2021000000010', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름11', '2021000000011', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름12', '2021000000012', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름13', '2021000000013', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름14', '2021000000014', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름15', '2021000000015', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름16', '2021000000016', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름17', '2021000000017', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름18', '2021000000018', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름19', '2021000000019', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름20', '2021000000020', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름21', '2021000000021', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름22', '2021000000022', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');
INSERT INTO Patient ("병원ID", "환자명", "환자등록번호", "성별코드", "생년월일", "휴대전화번호", "이메일") VALUES (3, '병원3이름23', '2021000000023', 'F', '19901112', '010-1111-2222', 'ccc@aaa.net');

-- 방문 가상 데이터
INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(1, 1, '2021-05-09 10:00:00', '2', '01');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(1, 2, '2021-05-10 11:00:00', '1', '01');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(1, 3, '2021-05-10 13:00:00', '2', '02');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(2, 2, '2021-05-10 14:00:00', '1', '01');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(2, 3, '2021-05-11 09:00:00', '1', '02');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(3, 1, '2021-05-12 12:10:00', '1', '01');

INSERT INTO Visit ("병원ID", "환자ID", "접수일시", "방문상태코드", "진료과목코드")
VALUES(4, 3, '2021-05-11 15:30:00', '1', '02');
