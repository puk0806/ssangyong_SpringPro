package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import newlecture.dao.MemberDao;
import newlecture.dao.NoticeDao;
import newlecture.vo.Member;
import newlecture.vo.Notice;

@Controller
@RequestMapping("/joinus/*")
public class JoinusController {

	@Autowired
	private MemberDao memberDao;
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	// [ 컨트롤러 메서드 작성 ] 
	@RequestMapping( value = "join.htm", method = RequestMethod.GET )
	public String join(  ){ 
		return "join.jsp";
	}

	// 3. noticeReg.htm(POST 방식) +  첨부파일 정보
	//    ㄱ. Notice 클래스의  file 필드 + getter,setter
	@RequestMapping( value = "join.htm", method = RequestMethod.POST )
	public String join( Member 	member
			, HttpServletRequest request  )
					throws ClassNotFoundException, SQLException{	 

		int cnt = this.memberDao.insert(member);
		//return "redirect:notice.htm";
		return "redirect:../index.htm";
    }



}












/*
	2020. 1. 20.
1	김건영/박지수/이경섭/천재희/장동환/김예주(11/04  탈락)
2	김수빈/이영재/김지민/정의호/김혜빈
3	정경원/박성훈/여원경/박성재/정영진/고승우
4	박찬호/김호연/박상만/김재우/권미지
 */