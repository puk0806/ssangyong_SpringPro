<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>  index.jsp </title>
</head>
<body>
<!-- SpringMVC15  default.jsp -->
<!-- Web Project Settings 메뉴 클릭 후  Context root를  SpringMVC15 수정 -->
<a href="/SpringMVC15/customer/notice.htm">notice.htm( 공지사항 )</a>

<%
  // session.getAttribute("Auth")
%>
<!-- ???.htm  -> ???Controller 클래스  X 
     controllers.customer 패키지 통째로 삭제 
     
     controllers 패키지를 추가하고
             공지사항 ( 2개 이상 컨트롤러 ) X
             한개  컨트롤러 관련 클래스 선언
       controllers.CustomerController 클래스 선언
       
    p 279 컨트롤러 구현
           - 스프링 MVC를 이용한 웹 개발의 핵심인 컨트롤러 구현 방법
      4.1    @Controller 어노테이션
             @RequestMapping 어노테이션  ***
             Model 
             을 이용해서 컨트롤러 구현...   
             
      4.2  컨트롤러구현한다는 의미는 요청에 대한 메서드를 구현한다는 의미
    
    p 287 @PathVariable 어노테이션 설명
       예)    게시글 삭제 URL :  /board/delete.do?seq=10
       
          int seq  =  request.getParameter(seq);
          this.dao.boardDelete(seq);
          
          -
                 게시글 삭제 URL :  /board/del/10 
         @ReqeustMapping("/board/{ memberid }/del/{ seq }")
         public String b_delete(@PathVariable String seq){
           
          this.dao.boardDelete(seq);
          return "view.jsp";
         }         
         
      회원가입 
      id
      name
      pwd
      add
      20개 입력..
      [저장]
      
      String id = r.g("id");                 
      String id = r.g("id"); // 20개
      
      Member m  = new Member();
      m.setId(id);
      //  20개                 
--> 
 
 
 <!-- 
 http://localhost:8080/SpringMVC15/customer/noticeDetail.htm?seq=2
 404 오류 발생.
 /customer/noticeDetail.htm?seq=2  요청에 해당되는   [컨트롤러  메서드]
 [ 공지사항 상세보기 처리 ] 
  -->
 
 <!--
  p 356 (09) 컨트롤러 메서드의 파라미터 타입과 리턴 타입
  CustomerController 클래스
     String       notices(S p,S f, S q, Model model){}
     ModelAndView noticeDetail(String seq){}
         리턴자료형         컨트롤메서명( 파라미터 ???)
     1) String  ***
     2) void    뷰 직접 생성
     3) ModelAndView  ***
     4) 객체         메서드에  @ResponseBody 가 적용된 경우
                jquery + Ajax  -> 서버요청 -> XML데이타, JSON 데이타 응답.    
  
          컨트롤러 메서드의 파라미터
          1) request, response 선언
          2) @PathVariable
          3) 커맨드 객체 
          4) Model , ModelMap, Map
          5) HttpSession 
          6) @RequestParam
          7) @RequestHeader, @CookieValue
          8) Erros, BindingResult  검증 결과를 보관하는 객체 ..
          9) @RequestBody
          10) Writer, OutputStream  응답 객체   JSP out.print();
           
 [ *** 공지사항 쓰기 처리 *** ]
  1. <a class="btn-write button" href="noticeReg.jsp">글쓰기</a>
     - CP/customer/noticeReg.htm 요청 URL 
         -> 컨트롤러  메서드 선언 ->   noticeReg.jsp 페이지 입력 이동.
  2. 컨트롤러 메서드 선언
     CustomerController 클래스 안에 메서드 선언 : noticeReg()
     
     noticeReg.htm(GET)    글쓰기 페이지 
     noticeReg.htm(POST)   DB 새글 insert -> 글목록 페이지 포워딩. 
       
  -->
 
 

 