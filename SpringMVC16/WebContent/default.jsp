<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> 

<a href="/SpringMVC16/customer/notice.htm">notice.htm</a>


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

 -->


</body>
</html>