package controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import newlecture.dao.MemberDao;
import newlecture.vo.Member;

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
		System.out.println("join컨트롤러 1요청 들어옴");
		//return "join.jsp";
		return "joinus.join";
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
	
	
	@RequestMapping(value = {"login.htm"},method = RequestMethod.GET)
	public String login() {
		System.out.println("join컨트롤러 2요청 들어옴");
		return "joinus.login";
	}


}
