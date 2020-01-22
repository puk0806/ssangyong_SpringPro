-- 2020.01.14(화요일)
-- 1. scott 소유 테이블 확인.
select * from tabs;
-- 2. 테이블 생성
drop table notices;
desc notices;
CREATE TABLE NOTICES
(
	seq number not null primary key, 
	title VARCHAR2(200 BYTE), 
	writer VARCHAR2(50 BYTE), 
	content   CLOB  , 
	regdate date  default sysdate ,
	hit NUMBER  default 0, 
	filesrc VARCHAR2(500 BYTE)
);

create sequence seq_notices
start with 1
increment by 1
nomaxvalue
nocache
nocycle;
--
select * from member;
drop table member;
--
CREATE TABLE MEMBER
(	
    id VARCHAR2(50 BYTE)  primary  key, 
    pwd VARCHAR2(50 BYTE), 
    name VARCHAR2(50 BYTE), 
    gender VARCHAR2(10 BYTE), 
    birth VARCHAR2(10 BYTE),  -- date
    is_lunar VARCHAR2(10 BYTE), 
    cphone VARCHAR2(15 BYTE), 
    email VARCHAR2(200 BYTE), 
    habit VARCHAR2(200 BYTE), 
    regdate DATE default  sysdate 
);












