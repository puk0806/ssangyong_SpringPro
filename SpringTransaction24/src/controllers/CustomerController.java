package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import newlecture.dao.NLNoticeDao;
import newlecture.dao.NoticeDao;
import newlecture.service.MemberShipService;
import newlecture.vo.Notice;

@Controller
// p 284 @RequestMapping 어노테이션은 클래스, 메서드에 설정할 수 있다. 
@RequestMapping("/customer/*")
public class CustomerController {

	// 인터페이스 NoticeDao
	private NoticeDao noticeDao;

	@Autowired
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	// 인터페이스 MemberShipService
	
	private MemberShipService memberShipService;
	
	@Autowired
	public void setMemberShipService(MemberShipService memberShipService) {
		this.memberShipService = memberShipService;
	}

	
	/*
	@RequestMapping("/customer/notice.htm")
	public ModelAndView notices( String p_page, String p_field, String p_query )
	{
		ModelAndView mv = null;
		return mv;
	}
	 */


	@RequestMapping("notice.htm")
	public String notices(
			@RequestParam( value = "page",defaultValue = "1",  required = false) int p_page
			, @RequestParam( value = "feild",defaultValue = "title") String p_field
			, String p_query
			, Model model    // 추가
			) throws ClassNotFoundException, SQLException{

		int page = 1;
		String field = "title";
		String query = "%%";		
		page = p_page;		
		field = p_field;
		if( p_query != null && !p_query.equals("")) query = p_query;
		List<Notice>  list = this.noticeDao.getNotices(page, field, query);
		model.addAttribute("list", list);
		model.addAttribute("test", "Hello, SpringMVC15");

		// 총 페이지수   16/15= Math.ceil( 1.1 )= (int)2.0페이지
		int pageCount = (int) Math.ceil( (double)this.noticeDao.getCount(field, query)/15);
		model.addAttribute("pageCount", pageCount);
		//

		String viewName = "notice.jsp";
		return viewName;
	}

	// NoticeDetailController -> 메서드  선언


	// 1. 공지사항  상세 보기 - 컨트롤러 메서드 구현
	// 공지사항  목록에서 제목을 클릭...
	// /customer/noticeDetail.htm?seq=2
	//@RequestMapping("/customer/noticeDetail.htm")
	/*
	@RequestMapping("noticeDetail.htm")
	public ModelAndView noticeDetail(
			// *** HttpServletRequest request,
			HttpSession session,
			@RequestParam("seq") String  seq) 
					throws ClassNotFoundException, SQLException {
		// auth 세션값 ? JSP session.getAttribute("Auth");
		// *** Strin seq = request.getParameter("seq");

		Notice notice = this.noticeDao.getNotice(seq);

		// Notice prevnotice = this.noticeDao.getPrevNotice(seq)
		// Notice nextnotice = this.noticeDao.getNextNotice(seq)

		// 1. 추가 S
		String content = notice.getContent().replace("\r\n", "<br>");
		content = content.replace(" ", "&nbsp;");
		content = content.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		notice.setContent(content);
		// 1. 추가 E

		String viewName = "noticeDetail.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("notice",notice);
		return mv; //		
	}
	*/
	
	
	// 2. 공지사항  상세 보기 - 컨트롤러 메서드 구현
	// Dirty Read 테스트
	/*
	@RequestMapping("noticeDetail.htm")
	public ModelAndView noticeDetail(HttpSession session,@RequestParam("seq") String  seq) throws ClassNotFoundException, SQLException {

		// 조회수 증가 코딩
		// this.noticeDao.hitUp(seq);
		// 쓰레드 객체를 2개 생성
		// A 쓰레드 : getHit() 조회수 얻기
		// B 쓰레드 : hitUp() 죄회수 증가
		
		Thread A = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);			// B쓰레드 우선권 주기 위해서 재움
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
				noticeDao.getHit(seq);			// 조회수 읽어옴
			}
		}, "getHitThread");
		A.start();
		
		Thread B = new Thread(new Runnable() {
			
			@Override
			public void run() {
				noticeDao.hitUp(seq); 		// 조회수 증가
				
			}
		},"HitUpThread" );
		B.start();
		
		
		Notice notice = this.noticeDao.getNotice(seq);

		String viewName = "noticeDetail.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("notice",notice);
		return mv; //		
	}
	*/
	
	// 3. 공지사항  상세 보기 - 컨트롤러 메서드 구현
	@RequestMapping("noticeDetail.htm")
	public ModelAndView noticeDetail(HttpSession session, @RequestParam("seq") String seq)
			throws ClassNotFoundException, SQLException {
		
		this.noticeDao.hitUp(seq);
		
		Notice notice = this.noticeDao.getNotice(seq);

		String viewName = "noticeDetail.jsp";
		ModelAndView mv = new ModelAndView(viewName);
		mv.addObject("notice", notice);
		return mv; //
	}
	
	
	
	
	
	// noticeReg.htm(GET 방식)
	@RequestMapping( value = "noticeReg.htm", method = RequestMethod.GET )
	public String noticeReg(  ){
		// 결과물 저장 X
		String viewName = "noticeReg.jsp";
		return viewName;
	}

	// 1. noticeReg.htm(POST 방식)   
	// form action="" method="post"
	/*
	@RequestMapping( value = "noticeReg.htm", method = RequestMethod.POST )
	public String noticeReg( String title, String content ) 
			throws ClassNotFoundException, SQLException{

		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setContent(content);

		int cnt = this.noticeDao.insert(notice);

		// 결과물 저장 X
		//String viewName = "notice.jsp"; ->  컨메 ->notice.jsp

		// redirect: 재요청
		String viewName = "redirect:notice.htm";
		return viewName;
	}
	 */ 

	// 2. noticeReg.htm(POST 방식)
	// 컨트롤러 메서드의 [커맨드 객체]를 사용하는 파라미터
	// Notice notice
	/*
	@RequestMapping( value = "noticeReg.htm", method = RequestMethod.POST )
	public String noticeReg( Notice notice  ) 
			throws ClassNotFoundException, SQLException{	
		//  p 297  자동으로 커맨드 객체는   뷰( jsp 페이지 ) 전달 된다.
		// model.addAllAttributes("n", notice);   ${ n.title }
		// Notice 클래스명의  notice 이름으로 전달              ${ notice.title }

		int cnt = this.noticeDao.insert(notice);
		//                 p 308 설명....
		String viewName = "redirect:notice.htm";
		return viewName;
	}
	 */

	// 3. noticeReg.htm(POST 방식) +  첨부파일 정보
	//    ㄱ. Notice 클래스의  file 필드 + getter,setter
	@RequestMapping( value = "noticeReg.htm", method = RequestMethod.POST )
	public String noticeReg( Notice notice
			, HttpServletRequest request  ) 
					throws ClassNotFoundException, SQLException{	 

		CommonsMultipartFile multiPartFile = notice.getFile();
		if( !multiPartFile.isEmpty() ) {
			// 1.  파일 customer/upload 저장 코딩 - transferTo(저장경로)
			//  예) abc.jar업로드 -> abc_1.jar  ,  abs(1).jar
			String originalFilename = multiPartFile.getOriginalFilename();
			String uploadPath = request.getServletContext().getRealPath("/customer/upload");
			System.out.println("> uploadPath : " + uploadPath );

			originalFilename = getFileNameCheck(uploadPath, originalFilename);

			File dest = new File(uploadPath, originalFilename);
			try {
				multiPartFile.transferTo(dest);
				// notice fileSrc 컬럼 : 파일의 경로
				notice.setFilesrc(originalFilename); 
			} catch (IllegalStateException | IOException e) { 
				e.printStackTrace();
			} 

		} // if  공지사항에 첨부파일 있다면 if{}

		this.memberShipService.insertAndPointUpOfMember(notice, "admin");
		String viewName = "redirect:notice.htm";
		return viewName;
	}


	// 파일 중복 체크   abc.jar     abs - 2.jar
	public String getFileNameCheck(String uploadPath
			, String originalFilename) {
		int index = 1;
		while(true) {
			File f = new File(uploadPath, originalFilename);
			if( !f.exists()) return originalFilename;
			originalFilename = 
					originalFilename.substring(0, originalFilename.length()-4)
					+" - "
					+ (index++)
					+ originalFilename.substring(originalFilename.length()-4); 
		}
	}

	// noticeEdit.htm?seq=5
	//  1

	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit( @RequestParam("seq") String seq
			, Model model  ) throws ClassNotFoundException, SQLException {
		// 5번 공지사항의 정보 select -> Model 저장
		Notice notice = this.noticeDao.getNotice(seq);
		model.addAttribute("notice", notice);

		return "noticeEdit.jsp";
	}

	// 1.  커맨드 객체 파라미터 선언
	/*
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST )
	public String noticeEdit(
			@RequestParam("seq") String seq
			, Notice notice  ) 
					throws ClassNotFoundException, SQLException {
		int cnt = this.noticeDao.update(notice);
		return  "redirect:noticeDetail.htm?seq="+ seq;
	}
	 */

	// 2.  커맨드 객체 파라미터 선언 + 첨부파일 있는 경우
	@RequestMapping(value = "noticeEdit.htm"
			, method = RequestMethod.POST )
	public String noticeEdit(
			@RequestParam("seq") String seq
			, Notice notice
			, HttpServletRequest request
			, @RequestParam("oFileSrc") String oFileSrc)  // 첨부파일
					throws ClassNotFoundException, SQLException {

		CommonsMultipartFile multiPartFile = notice.getFile();

		if( ! multiPartFile.isEmpty() ) {
			String originalFilename = multiPartFile.getOriginalFilename();
			String uploadPath = request.getServletContext().getRealPath("/customer/upload");
			//System.out.println("> uploadPath : " + uploadPath);		
			originalFilename = getFileNameCheck(uploadPath, originalFilename);		
			File file = new File(uploadPath, originalFilename);
			try {
				// transferTo() - 업로드한 파일 데이터를 지정한 파일에 저장한다.
				multiPartFile.transferTo(file);
				// ""   "이전파일 파일명"
				File deloFile = new File(uploadPath, oFileSrc);
				if( deloFile.exists() ) deloFile.delete();
				//
				notice.setFilesrc(originalFilename);
			} catch (IllegalStateException | IOException e) { 
				e.printStackTrace();
			}
		}else { 		
			notice.setFilesrc(oFileSrc);   
		}

		this.noticeDao.update(notice);
		return "redirect:noticeDetail.htm?seq=" + seq;
	}


	// 
	@RequestMapping("noticeDel.htm")
	public String noticeDel(			
			@RequestParam("seq") String seq
			, @RequestParam("oFileSrc") String oFileSrc
			, HttpServletRequest request)
					throws ClassNotFoundException, SQLException {
		// 첨부된 파일이 있다면 첨부파일 삭제 코딩.
		//Notice delNoteice = this.noticeDao.getNotice(seq);
		//String oFileSrc = delNoteice.getFilesrc();

		String uploadPath = request.getServletContext()
				.getRealPath("/customer/upload");		
		File deloFile = new File(uploadPath, oFileSrc);
		if( deloFile.exists() ) deloFile.delete();

		int cnt = this.noticeDao.delete(seq);
		return "redirect:notice.htm";
	}

	@RequestMapping("download.htm")
	public void download(
			@RequestParam("p") String p     // customer/upload 폴더경로
			, @RequestParam("f") String f   // 다운로드할 파일명
			, HttpServletRequest request
			, HttpServletResponse response)
					throws IOException {

		/*파일 이름에 대한 인코딩 처리 추가*/		
		//System.out.println(f);
		String fname =  f; 
		//  new String(f.getBytes("ISO8859_1"), "UTF-8"); // f
		//System.out.println(fname);
		response.setHeader("Content-Disposition"
				, "attachment;filename="+
						new String(fname.getBytes(), "ISO8859_1"));		
		/*파일 다운로드가 가능하도록 하기 위한 물리적 경로*/
		String fullPath = 
				request.getServletContext().getRealPath(
						p + "/" + fname);
		/*파일 다운로드에 대한 처리 과정 추가*/
		FileInputStream fin = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = 0;
		while((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size); 
		}
		fin.close();
		sout.close();
	} // method

} // class












