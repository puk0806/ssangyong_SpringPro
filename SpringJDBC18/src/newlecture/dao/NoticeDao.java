package newlecture.dao;

import java.sql.SQLException;
import java.util.List;

import newlecture.vo.Notice;

public interface NoticeDao {

	// 2. 조건에 맞는 공지사항의 갯수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;

	// 2. 공지사항 조회 메서드(위에 코드와 동일)
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;

	// 2. 해당 seq에 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;

	// 공지사항 수정 (update() 이용)
	public int update(Notice notice) throws ClassNotFoundException, SQLException;

	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException;

	// 2. 공지사항 추가 (update() 매개변수로 넘기기)
	public int insert(Notice notice) throws ClassNotFoundException, SQLException;

}
