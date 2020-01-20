package newlecture.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;
// 공지사항
@Repository
public class NoticeDao extends SimpleJdbcDaoSupport{
	
	// [초기화] datasource 주입, jdbcTemplate 객체 주입
	@Autowired
	private DataSource dataSource;
	
	@PostConstruct		// 빈 객체 생성될 때 자동으로 생성자처럼 호출되는 메서드 입니다.
	public void init() {
		this.setDataSource(this.dataSource);
	}
	
	
	// 2. 조건에 맞는 공지사항의 갯수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException	{
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
		
		return this.getJdbcTemplate().queryForInt(sql,new Object[] {"%"+query+"%"});
	}

	
	// 2.   공지사항 조회 메서드(위에 코드와 동일)
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException {					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * FROM";
		sql += "(SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N)";
		sql += "WHERE NUM BETWEEN ? AND ?";
		
		List<Notice> list = this.getJdbcTemplate().query(sql,new Object[] {"%"+query+"%",srow,erow},new BeanPropertyRowMapper<Notice>(Notice.class));
		
		return list;
	}
	

	
	// 2. 해당 seq에 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "DELETE NOTICES WHERE SEQ=?";
		return this.getJdbcTemplate().update(sql,seq);
		
	}
	
	

	// 공지사항 수정 (update() 이용)	
		public int update(Notice notice) throws ClassNotFoundException, SQLException{
			
			String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? WHERE SEQ=?";
			return this.getJdbcTemplate().update
					(sql,notice.getTitle(),notice.getContent(),notice.getFilesrc(),notice.getSeq());
		}

	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ= ? " ;
		
		// ParameterizedBeanPropertyRowMapper 조건 (쿼리 컬럼명 == 객체 필드명 동일)
		// ParameterizedBeanPropertyRowMapper : 쿼리의 데이터를 객체로 반환
		Notice notice= this.getJdbcTemplate().queryForObject(sql, new Object[] { seq}, ParameterizedBeanPropertyRowMapper.newInstance(Notice.class));
				
		
		return notice;
	}	

	
	// 2. 공지사항 추가 (update() 매개변수로 넘기기)
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'admin', SYSDATE, 0, ?)";
		
		System.out.println("파일명 : "+notice.getFilesrc());
		return this.getJdbcTemplate().update(sql,notice.getTitle(),notice.getContent(),notice.getFilesrc());
	}
	
	
	
}
