package controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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
	public String notices( @RequestParam(value = "page",defaultValue = "1",required = false) int p_page, String p_field, String p_query,Model model)throws Exception{
		// 파라미터가 넘어온걸로 알아서 받아진다.
		/*
		String p_page = request.getParameter("page");
		String p_field = request.getParameter("field");
		String p_query = request.getParameter("query");
		*/
		int page = 1;
		String field = "title";
		String query = "%%";
	
		page = p_page;
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
	
	/*
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
	*/
	
	/*
	// 글 쓰기 작업(noticeReg.htm(POST방식)	
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	// title content가 넘어오면 자동으로 notice객체로 만들어준다.(자동으로 파라미터값 형변환 해서 사용)
	public String noticeReg( Notice notice) throws ClassNotFoundException, SQLException{
		
		// p297 자동으로 커맨드 객체는 뷰(jsp 페이지) 전달된다.
		// Notice객체를 따로 model.addAllAttributes 안해도 view로 넘어간다. ex) ${notice.title} (첫글자 소문자로 넘어감)
		int cnt = this.noticeDao.insert(notice);
		String viewName = "redirect:notice.htm";			// notice.htm을 요청해서 redirect사용 (p308)
		
		return viewName;
	}
	*/

	// 글 쓰기 작업(noticeReg.htm(POST방식)	
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg( Notice notice, HttpServletRequest request) throws ClassNotFoundException, SQLException{
	
		CommonsMultipartFile multiPartFile = notice.getFile();
		if(!multiPartFile.isEmpty()) {
			// 1. 파일 customer/upload 저장 코딩 - transferTo()(저장 경로)
			String originalFilename = multiPartFile.getOriginalFilename();
			String uploadPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("실제 저장 경로 : "+uploadPath);
			System.out.println("원본 파일명 : "+originalFilename);
			
			originalFilename = getFileNameCheck(uploadPath, originalFilename);
			
			File dest = new File(uploadPath,originalFilename);
			try {
				multiPartFile.transferTo(dest);
				notice.setFilesrc(originalFilename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		// fileSrc 컬럼 : 파일의 경로
		int cnt = this.noticeDao.insert(notice);
		String viewName = "redirect:notice.htm";			// notice.htm을 요청해서 redirect사용 (p308)
		
		return viewName;
	}
	
	// 파일명 중복되나 확인 후 인덱스 부여 함수
		public String getFileNameCheck(String uploadPath
				, String originalFilename) {
			int index = 1;
			while(true) {
				File f = new File(uploadPath, originalFilename);
				if( !f.exists()) return originalFilename;
				originalFilename = 
						originalFilename.substring(originalFilename.length()-5)
						+" - "
						+ (index++)
						+ originalFilename.substring(originalFilename.length()-4); 
			}
		}
	

	// 글 수정 작업(noticeEdit.htm(GET방식))	
	@RequestMapping(value = "noticeEdit.htm",method = RequestMethod.GET)
	public String noticeEdit(@RequestParam("seq") String seq, Model model ) throws ClassNotFoundException, SQLException {
		
		Notice notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice",notice);
		
		return "noticeEdit.jsp";
	}
	
	
	/*
	// 글 수정 작업(noticeEdit.htm(POST방식))
	// 똑같은 url로 POST 하기 떄문에 seq 파라미터가 같이 넘어온다!!!
	@RequestMapping(value = "noticeEdit.htm",method = RequestMethod.POST)
	public String noticeEdit(Notice notice) throws ClassNotFoundException, SQLException {
		
		int cnt = this.noticeDao.update(notice);
		
		return "redirect:noticeDetail.htm?seq="+notice.getSeq();
	}
	*/
	// 첨부 파일 있는경우 글 수정 작업(noticeEdit.htm(POST방식))
	// 똑같은 url로 POST 하기 떄문에 seq 파라미터가 같이 넘어온다!!!
	@RequestMapping(value = "noticeEdit.htm",method = RequestMethod.POST)
	public String noticeEdit(Notice notice, HttpServletRequest request) throws ClassNotFoundException, SQLException {
		
		CommonsMultipartFile multiPartFile = notice.getFile();
		if(!multiPartFile.isEmpty()) {
			// 그 전에 첨부된 파일이 있따면 삭제하는 코딩
			String originalFilename = multiPartFile.getOriginalFilename();
			String uploadPath = request.getServletContext().getRealPath("/customer/upload");
			
			originalFilename = getFileNameCheck(uploadPath, originalFilename);
			
			File dest = new File(uploadPath,originalFilename);
			try {
				multiPartFile.transferTo(dest);
				notice.setFilesrc(originalFilename);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int cnt = this.noticeDao.update(notice);
		
		return "redirect:noticeDetail.htm?seq="+notice.getSeq();
	}
	

	// 글 삭제 작업(noticeDel.htm(GET방식))
	@RequestMapping("noticeDel.htm")
	public String noticeDel(@RequestParam("seq") String seq) throws ClassNotFoundException, SQLException {
		
		int cnt = this.noticeDao.delete(seq);
		
		return "redirect:notice.htm";
	}
	

}
