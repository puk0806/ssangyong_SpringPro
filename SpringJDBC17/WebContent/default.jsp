<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 

<a href="/SpringJDBC16/customer/notice.htm">notice.htm</a>


<!-- 
???.htm -> ???.Controller 클래스 X
한개 컨트롤러 관련 클래스 선언 (controllers.CustomerController)

p 279 컨트롤러 구현
	- 스프링 MVC를 이용한 웹 개발의 핵심인 컨트롤러 구현 방법
	4.1 @Controller 어노테이션
		@RequestMapping 어노테이션
		Model 이용해서 컨트롤러 구현
	4.2 컨트롤러를 구현한다는 것은 클라이언트의 요청을 처리할 메소드 구현하는 것

p 287 @PathVariable 어노테이션 설정
ex) 게시글 삭제 URL : /board/del/10
@RequestMapping("/board/del/{seq}")
public String b_delete(@PathVariable String seq){ 삭제 함수 }


p 356 (09) 컨트롤러 메서드의 파라미터 타입과 리턴 타입
CostomerController 클래스
공지사항 쓰기 처리
1. CP/customer/noticeDetail.htm 요청 URL -> 컨트롤러 메서드 선언
2. 컨트롤러 메서드 선언
CustomerController 클래스 안에 메서드 선언 : noticeReg()
noticeReg.htm(GET) : 글쓰기 페이지
noticeReg.htm(POST) : DB 새글 insert ->  글 목록 페이지 포워딩


리턴 자료형	
1. String
2. void
3. ModelAndView
4. 객체		메서드에 @ResponseBody가 적용된 경우
			
			
컨트롤러 메서드의 파라미터
1.  request,response 선언
2.  @PathVariable
3.  커멘드 객체
4.  Model , ModelMap.... Map
5.  HttpSession
6.  @RequestParam
7.  @RequestHeader, @CookieValue
8.  Erros, BindingResult 검증 결과를 보관하는 객체..
9.  @RequestBody
10. Writer,OutputStream 응답객체 JSP out.print();

p 297 체크박스 어떻게 커맨드 객체 선언 받아야 하나 -> 배열, collection, list사용 해서 받음

p 298 객체를 파라미터로 받아서 객체르 만들떄
	- ex)		// id를 넘겨 받을때 Address.파라미터 로 받음 
		Member{
			int a;
			Address address;
		}

p 305 @ModelAttribute 어노테이션 사용 이유 : 별칭 사용
ex) 
	String regist(@modelAttribute("memberInfo" MemberRegistRequest mr))...
	${memberRegistRequest.email} == ${memberInfo.email}

p 307 @CookieValue, @requestHeader 어노테이션

p 308 리다이렉트 처리
- 뷰 이름 앞에 redirect : 접두어를 붙이면 된다.

파일 업로드 인코딩
enctype="multipart/form-data"

p 438 파일 업로드
// 스프링에서 제공하는 멀티 파트 지원 기능 이용
1. MultipartResolver 설정 -> 스프링 설정 파일(dispatcher-servlet.xml) 등록
	ㄱ. 기능 : 멀티파트 인코딩 형식으로 데이터가 전송되는 경우, 해당 데이터를 스프링MVC에서 사용할 수 있도록 변환해 주는 역할.
	ㄴ. @RequestParam 어노테이션을 이용해서 멀티파트로 전송된 파라미터 값과 파일에 데이터를 사용할 수 있도록 해준다.
2. Spring제공하는  MultipartResolver 2가지 종류
	- web.multipart.commons.CommonsMultipartResolver : Commons FileUpload API 이용
	- web.multipart.support.StandardServletMultipartResolver : 서블릿 3.0의 Part를 이용
3. 스프링 빈으로 등록 
	(주의점!!! : 스프링 빈의 이름 : multipartResolver )
4. com.springsource.org.apache.commons.fileupload-1.2.0.jar 추가
5. noticeReg.htm (POST) -> 컨트롤러 메서드가 호출되어진다.
	첨부파일 (p441)
	1) MultipartFile 인터페이스 : 업로드 한 파일 정보 및 데이터를 표현하기 위한 용도
		transferTo() : 업로드한 파일 데이터를 지정한 [파일에 저장] 한다.
	2) @RequestParam 어노테이션으로 업로드한 파일 접근할 수 있다.
	3) MultipartHttpServletRequest 인터페이스 사용하는 방법
	4) [커멘드 객체]를 통한 업로드 파일 접근
		- 동일한 이른의 Multipartfile 타입 프로퍼티를 추가만 하면된다.
	5) CustomerController.java    ( noticeReg() POST 저장)
		
 -->


</body>
</html>