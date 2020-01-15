<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Hello world</h3>
<!-- 
Spring MVC 패턴 구조
웹 브라우저 요청 
-> 1. 요청 -> 프론터 컨트롤러 (DispatcherServlet,(MV[C]))
-> 2. 요청URL에 매칭되는 컨트롤러 검색 (HandlerMapping)
-> 2-2. 프론트 컨트롤러 (MV[C]) 전달
-> 3. 처리 요청 (HandlerAdapter)
-> 4. 실행 (Controller,컨트롤러)
-> 5. 결과 리턴 -> HandlerAdapter
-> 6. 프론트 컨트롤러
-> 7. 컨트롤러의 실행결과를 보여줄 View 검색 (ViewResolver)
-> 8. 프론트 컨트롤러
-> 9. 뷰(View)
-> 10. 결과 응답


p 270 DispatcherServlet 서블릿 설정
1. DispatcherServlet은 내부적으로 스프링 컨테이너를 생성한다.
	- 스프링 컨테이너 == IOC 컨테이너 == 어플리케이션 컨텍스트 :  빈 객체 생성, 조립 공장
	- ApplicationContext 구현
		- 웹 어플리케이션 ApplicationContext == WebXmlApplicationContext 객체 생성
2. 내부적으로 초기화 파라미터 <init-param></init-param> 설정이 없다면 자동으로 /WEB-INF/[서블릿이름]-servlet.xml 을 스프링 설정파일로 사용
3. 스프링 [ MVC 설정 ]
	ㄱ. 의존 모듈 추가 -> 메이븐 프로젝트 (pom.xml)
		WEB-INF>lib 폴더에 모듈(jar) 추가
	ㄴ. MV[C] front controller
		1) DispatcherServlet 클래스가 컨트롤러 대신 사용
	ㄷ. DispatcherServlet 서블릿 등록 : web.xml
	ㄹ. WEB-INF/dispatcher-servlet.xml 스프링 설정 파일 추가
4. 공지사항 목록 컨트롤러 작성
	- controllers.customer.NoticeController
5. 	요청 URL과 컨트롤러 매핑 코딩?
	- ???.htm -> 요청 -> NoticeController 처리 매핑 시키는 코딩 X
6. notice.htm 테스트
-->
<a href="/SpringMVC14/customer/notice.htm">notice.htm</a>

<!-- 
생성쿼리
CREATE TABLE NOTICES
(
	SEQ number not null primary key, 
	TITLE VARCHAR2(200 BYTE), 
	WRITER VARCHAR2(50 BYTE), 
	CONTENT clob, 
	REGDATE date default sysdate, 
	HIT NUMBER default 0 , 
	FILESRC VARCHAR2(500 BYTE)
);

CREATE TABLE MEMBER
(	
    ID VARCHAR2(50 BYTE), 
    PWD VARCHAR2(50 BYTE), 
    NAME VARCHAR2(50 BYTE), 
    GENDER VARCHAR2(10 BYTE), 
    BIRTH VARCHAR2(10 BYTE), 
    IS_LUNAR VARCHAR2(10 BYTE), 
    CPHONE VARCHAR2(15 BYTE), 
    EMAIL VARCHAR2(200 BYTE), 
    HABIT VARCHAR2(200 BYTE), 
    REGDATE DATE default sysdate
);
 
create sequence seq_notices
start with 1
increment by 1
nomaxvalue
nocache
nocycle;

-->

<!-- 
공지사항 보기 링크 태그를 클릭 했을 때 처리
<td class="title"><a href="noticeDetail.jsp">${dto.title }</a></td>
 ㄱ. noticeDetail.htm 요청 -> 컨트롤러
     controllers.customer.NoticeDetailController
     

    3.문제점
    noticeEdit.htm?seq=1 -> NoticeEditController
     요청 URL 별로 각각의 ~~Controller 클래스 추가
     수백개의 컨트롤러 생성 -> <bean>태그 코딩 추가
  
    ->어노테이션 사용해서 수정
    ->수백개의 ~~Controller 클래스 추가 X
 -->



</body>
</html>