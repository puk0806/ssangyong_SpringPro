package newlecture.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import newlecture.dao.NoticeDao;
import newlecture.vo.Notice;

@Service
public class NLMemberhipService implements MemberShipService{
	
	@Autowired
	private NoticeDao noticeDao;
	
	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	// get 공지사항 조회수 증가
	
	
	
	// 5
	// [주업무] 공지사항 등록 + 작성자 포인트 증가 함수
	@Override
	// 인터페이스에 트랜잭션 걸어도 실제 구현클래스 걸린다.
	// @Transactional(propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException {
	
		this.noticeDao.insert(notice);
		/*
		String title = notice.getTitle();
		notice.setTitle(title+"(1)");
		this.noticeDao.insert(notice);
		*/
	}
	
	
}
