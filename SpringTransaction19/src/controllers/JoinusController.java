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
	// noticeReg.htm(GET 방식)
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
		// return "redirect:/customer/notice.htm";
		return "redirect:../index.htm";
    }

	

}
