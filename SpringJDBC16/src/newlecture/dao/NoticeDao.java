package newlecture.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;
// 공지사항
@Repository
public class NoticeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 1.조건에 맞는 공지사항의 갯수를 반환하는 메서드
	/*
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		
		rs.close();
		st.close();
		con.close();
		
		return cnt;
	}
	*/
	
	
	
	// 2. 조건에 맞는 공지사항의 갯수를 반환하는 메서드
	// this.jdbcTemplate.queryForList(sql);
	// this.jdbcTemplate.queryForInt(sql);
	// this.jdbcTemplate.queryForLong(sql);
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
		
		return this.jdbcTemplate.queryForInt(sql,new Object[] {"%"+query+"%"});
	}
	
	// 1. 공지사항 조회 메서드
	/*
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * FROM";
		sql += "(SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N)";
		sql += "WHERE NUM BETWEEN ? AND ?";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, srow);
		st.setInt(3, erow);
	
		ResultSet rs = st.executeQuery();
		
		List<Notice> list = new ArrayList<Notice>();
		
		while(rs.next()){
			Notice n = new Notice();
			n.setSeq(rs.getInt("seq"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));
			n.setFilesrc(rs.getString("filesrc"));
			
			list.add(n);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	*/
	
	// 2.   공지사항 조회 메서드(위에 코드와 동일)
	// 여러개 레코드 조회 : query()
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * FROM";
		sql += "(SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N)";
		sql += "WHERE NUM BETWEEN ? AND ?";
		
		List<Notice> list = this.jdbcTemplate.query(sql,new Object[] {"%"+query+"%",srow,erow},new BeanPropertyRowMapper<Notice>(Notice.class));
		
		return list;
	}
	

	
	// 1. 해당 seq에 공지사항 삭제
	/*
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE NOTICES WHERE SEQ=?";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, seq);
		
		int af = st.executeUpdate();
		
		return af;
	}
	*/
	
	// 2. 해당 seq에 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "DELETE NOTICES WHERE SEQ=?";
		return this.jdbcTemplate.update(sql,seq);
		
	}
	
	
	
	// 공지사항 수정 (update() 이용)
	/*
	public int update(Notice notice) throws ClassNotFoundException, SQLException{
		
		String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? WHERE SEQ=?";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getFilesrc());
		st.setInt(4, notice.getSeq());		
		
		int af = st.executeUpdate();
		
		return af;
	}
	*/
	
	
	// 공지사항 수정 (update() 이용)
		
		public int update(Notice notice) throws ClassNotFoundException, SQLException{
			
			String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? WHERE SEQ=?";
			return this.jdbcTemplate.update
					(sql,notice.getTitle(),notice.getContent(),notice.getFilesrc(),notice.getSeq());
		}
	
	// 1. 공지사항 보기
	/*
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ= "+seq;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		
		Notice n = new Notice();
		n.setSeq(rs.getInt("seq"));
		n.setTitle(rs.getString("title"));
		n.setWriter(rs.getString("writer"));
		n.setRegdate(rs.getDate("regdate"));
		n.setHit(rs.getInt("hit"));
		n.setContent(rs.getString("content"));
		n.setFilesrc(rs.getString("filesrc"));
		
		rs.close();
		st.close();
		con.close();
		
		return n;
	}
	*/

	// 2.스프링에서 제공하는 JdbcTemplate사용해서 코딩 수정 (위에 코딩과 똑같은 기능)
	// 객체 한개 만 가져옴 queryForObject사용
	
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ= ? " ;
		
		// ParameterizedBeanPropertyRowMapper 조건 (쿼리 컬럼명 == 객체 필드명 동일)
		// ParameterizedBeanPropertyRowMapper : 쿼리의 데이터를 객체로 반환
		Notice notice= this.jdbcTemplate.queryForObject(sql, new Object[] { seq}, ParameterizedBeanPropertyRowMapper.newInstance(Notice.class));
				
		
		return notice;
	}	

	
	// 3.스프링에서 제공하는 JdbcTemplate사용해서 코딩 수정 (위에 코딩과 똑같은 기능)
	// 쿼리 컬럼명 과 객체의 필드명이 다를경우 ParameterizedBeanPropertyRowMapper 사용 X객체를 직접 생성해 줘야한다.
	// 쿼리 나릴때 as이용해서 별칭 사용하면 해결가능
	/*
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ= ? " ;
		
		// ParameterizedBeanPropertyRowMapper 조건 (쿼리 컬럼명 == 객체 필드명 동일)
		// ParameterizedBeanPropertyRowMapper : 쿼리의 데이터를 객체로 반환
		Notice notice= this.jdbcTemplate.queryForObject
					(sql
						, new Object[] { seq}
						,new RowMapper<Notice>(){			// 익명 클래스 이용해서 객체생성 
							@Override
							public Notice mapRow(ResultSet rs, int arg1) throws SQLException {
								Notice n = new Notice();
								n.setSeq(rs.getInt("seq"));
								n.setTitle(rs.getString("title"));
								n.setWriter(rs.getString("writer"));
								n.setRegdate(rs.getDate("regdate"));
								n.setHit(rs.getInt("hit"));
								n.setContent(rs.getString("content"));
								n.setFilesrc(rs.getString("filesrc"));
								
								return n;
							}});
				
		
		return notice;
	}
	*/

	// 1. 공지사항 추가
	/*
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'admin', SYSDATE, 0, ?)";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott", "tiger");
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getFilesrc());
		
		int af = st.executeUpdate();
		
		st.close();
		con.close();
		
		return af;
	}
	*/
	
	// 2. 공지사항 추가 (update() 매개변수로 넘기기)
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'admin', SYSDATE, 0, ?)";
		
		System.out.println("파일명 : "+notice.getFilesrc());
		return this.jdbcTemplate.update(sql,notice.getTitle(),notice.getContent(),notice.getFilesrc());
	}
	
	
	// 3. 공지사항 추가 (update() PreparedStatement 익명객체 생성해서 넘기기)
	/*
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'admin', SYSDATE, 0, ?)";
		System.out.println("파일명 : "+notice.getFilesrc());
		return this.jdbcTemplate.update(sql
				,new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement pstmt) throws SQLException {
						pstmt.setString(1, notice.getTitle());
						pstmt.setString(2, notice.getContent());
						pstmt.setString(3, notice.getFilesrc());
						
					}
			
		});
	}
	*/
	
	// 4. 공지사항 추가 (update() PreparedStatementCreator 익명 객체로 넘기기)
/*
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		return this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'admin', SYSDATE, 0, ?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setString(3, notice.getFilesrc());
				return pstmt;
			}
		});
	}
*/	
	
	
}
