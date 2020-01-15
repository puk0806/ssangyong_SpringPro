package controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

@Controller
// @RequestMapping() 클래스, 메서드에 설정 할수 있다. p 284
@RequestMapping("/customer/*")


public class CustomerController {
	
	private NoticeDao noticeDao;
	
	// 자동 주입
	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	// NoticeController			-> 메서드로 선언
	// [/customer/notice.htm] 요청 -> notices() 호출		
	// (request매핑)(@RequestMapping 사용) (요청 URL == 컨트롤러(메서드) 매핑)
	// "컨트롤러 메서드" 라고 호칭
	//@RequestMapping("/customer/notice.htm")
	@RequestMapping("notice.htm")
	// 반환 타입 상관없음 ModelAndview가능
	public String notices( @RequestParam(value = "page",defaultValue = "1",required = false) String p_page, String p_field, String p_query,Model model)throws Exception{
		// 파라미터가 넘어온걸로 알아서 받아진다.
		/*
		String p_page = request.getParameter("page");
		String p_field = request.getParameter("field");
		String p_query = request.getParameter("query");
		*/
		int page = 1;
		String field = "title";
		String query = "%%";
	
		if(p_page !=null && !p_page.equals(""))		page = Integer.parseInt(p_page);
		if(p_field !=null && !p_field.equals(""))	field = p_field;
		if(p_query !=null && !p_query.equals(""))	query = p_query;
		
		
		List<Notice> list = this.noticeDao.getNotices(page,field,query);
		
		
		/*
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("list",list);
		mv.addObject("test","Hello, SpringMVC");
		*/
		
		model.addAttribute("list",list);
		model.addAttribute("test","Hello SpringMVC16");
		String viewName = "notice.jsp";
		
		return viewName;
		
	}
	
	// NoticeDetailController	-> 메서드로 선언
	//@RequestMapping("/customer/noticeDetail.htm")
	// 반환 타입 상관없음 String 가능
	// /SpringMVC16/customer/customer/noticeDetail
 
	@RequestMapping("noticeDetail.htm")
	public ModelAndView noticeDetial(@RequestParam("seq") String seq) throws ClassNotFoundException, SQLException {
		
		Notice notice = this.noticeDao.getNotice(seq);
		String viewName = "noticeDetail.jsp";		
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("notice",notice);
		
		return mv;
	}
	 
	/*
	@RequestMapping("noticeDetail.htm")
	public String noticeDetial(@RequestParam("seq") String seq, Model model) throws ClassNotFoundException, SQLException {
		
		Notice notice = this.noticeDao.getNotice(seq);		 
		model.addAttribute("notice",notice); 		
		return "noticeDetail.jsp";
	}
	*/
	
	// 글 쓰기 작업(noticeReg.htm(GET방식)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg(){
		String viewName = "noticeReg.jsp";
		return viewName;
	}
	
	// 글 쓰기 작업(noticeReg.htm(POST방식)
		@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
		public String noticeReg( String title, String content) throws ClassNotFoundException, SQLException{
			Notice notice = new Notice();
			notice.setTitle(title);
			notice.setContent(content);
			
			int cnt = this.noticeDao.insert(notice);
			
			String viewName = "redirect:notice.htm";			// notice.htm을 요청해서 redirect사용
			
			return viewName;
		}
	
	

}
