package controllers.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

// /customer/noticeDetail.htm 요청
public class NoticeDetailController implements Controller{
	
	private NoticeDao noticeDao;
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String p_seq = request.getParameter("seq");
		String seq ="";
		if(p_seq != null) 	seq = p_seq;
		
		Notice notice = this.noticeDao.getNotice(seq);
		
		String viewName = "noticeDetail.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject(notice);
		
		return mv;
	}

}
