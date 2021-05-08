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

