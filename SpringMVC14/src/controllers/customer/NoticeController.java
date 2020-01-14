package controllers.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

public class NoticeController implements Controller{

	private NoticeDao noticeDao;
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String p_page = request.getParameter("page");
		String p_field = request.getParameter("field");
		String p_query = request.getParameter("query");
		
		int page = 1;
		String field = "title";
		String query = "%%";
		
		if(p_page !=null && !p_page.equals(""))		page = Integer.parseInt(p_page);
		if(p_field !=null && !p_field.equals(""))	field = p_field;
		if(p_query !=null && !p_query.equals(""))	query = p_query;
		
		
		List<Notice> list = this.noticeDao.getNotices(page,field,query);
		
		// 모델에 담아서 넘김
		// ModelAndView mv = new ModelAndView();
		// mv.setViewName("notice.jsp");
		
		String viewName = "notice.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("list",list);
		mv.addObject("test","Hello, SpringMVC");
		
		return mv;
		
		
	}
}
