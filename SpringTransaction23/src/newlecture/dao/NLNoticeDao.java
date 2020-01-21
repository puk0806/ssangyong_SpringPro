package newlecture.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import newlecture.vo.Notice;

// 공지사항 DAO 
// NamedParameterJdbcTemplate 사용하는 예.
@Repository
// @Transactional			// 클래스에 트랜잭션 적용하기 
public class NLNoticeDao implements NoticeDao{
	
	
	// dispatcher-service.xml  <bean> 생성
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate ; 
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	
	// 2번. 트랜잭션 매니저 주입 받기(DI)
	
	@Autowired
	//private DataSourceTransactionManager transactionManager;	// 트랜잭션 매니저 받기
	private PlatformTransactionManager transactionManager;		// 부모 인터페이스로 트렌잭션 매니저 받기	
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	
	// 3번 트랜잭션 템플릿 DI
	@Autowired(required = false)
	private TransactionTemplate transactionTemplate;	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}


	// 2. jdbcTemplate. 
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{		
		String sql = "SELECT COUNT(*) CNT "
				+ " FROM NOTICES "
				+ " WHERE "+field+" LIKE :query";
		// 497. 1) SqlParameterSource
		MapSqlParameterSource  paramSource = new MapSqlParameterSource();
		paramSource.addValue("query", "%"+query+"%");
		return this.jdbcTemplate.queryForInt(sql
				, paramSource);
	}

	 
	// 2.  조회 : query()
	public List<Notice> getNotices(
			int page, String field, String query)
					throws ClassNotFoundException, SQLException
	{					
		// 페이징 처리 
		int srow = 1 + (page-1)*15; 
		int erow = 15 + (page-1)*15;		

		String sql = 
				"SELECT * FROM " 
						+"("
						+ "   SELECT ROWNUM NUM, N.* "
						+ "   FROM "
						+ "   ("
						+ "      SELECT * FROM NOTICES "
						+ "      WHERE " + field + " LIKE :query "
						+ "      ORDER BY REGDATE DESC"
						+ "   ) N"
						+ ")"  
						+ "  WHERE NUM BETWEEN :srow AND :erow";

		// p 496  Map 이용
		Map<String, Object> params = new HashMap<>();
		params.put("query", "%"+query+"%");
		params.put("srow", srow);
		params.put("erow", erow);
		
		List<Notice> list= this.jdbcTemplate.query(
				sql
				, params
				, new  BeanPropertyRowMapper<Notice>(Notice.class)
				);

		return list;
	}

	// 해당 seq에  공지사항 삭제 
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{ 
		String sql = "DELETE FROM NOTICES WHERE SEQ=:seq";
		// p 497 SqlParameterSource 를 이용한 파라미터 값 설정... 
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		return this.jdbcTemplate.update(sql, paramSource ); 
	}

	// 공지사항 수정       : 수정할 seq 
	public int update(Notice notice) throws ClassNotFoundException, SQLException{
		String sql = "UPDATE NOTICES SET TITLE=:title"
				+ ", CONTENT=:content,"
				+ " FILESRC=:filesrc WHERE SEQ=:seq";		
		// p 498 SqlParameterSource 를 이용한 파라미터 값 설정... 
		SqlParameterSource paramSource = 
				new BeanPropertySqlParameterSource(notice);
		return this.jdbcTemplate.update(sql, paramSource );	
	}

	// 2.
	// Spring JDBC 제공하는 Jdbctemplate 사용해서 코딩 수정
	// queryForObject() 메서드 사용	 
	public Notice getNotice(String seq) 
			throws ClassNotFoundException, SQLException
	{
		String sql = " SELECT * FROM NOTICES  WHERE SEQ= :seq "; 
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("seq", seq);
		Notice notice = this.jdbcTemplate.queryForObject(
				sql, paramSource
				, new BeanPropertyRowMapper<Notice>(Notice.class));
		return notice;
	}
  	 
	// 공지사항 추가        String title, String content
	// 1. 추,수,삭 update(      args 순서대로 입력) 
	/*
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, 'kenik', SYSDATE, 0, :filesrc)";
		return this.jdbcTemplate.update(sql	,  new BeanPropertySqlParameterSource(notice));
	}  // insert
	*/
	
	// 공지사항 추가        String title, String content
	// 2. 트랜잭션 전파 방식에 대한 설명
	// @Transactional(propagation = Propagation.REQUIRED)	// 현재 진행중인 트랙잭션 사용
	// @Transactional(propagation = Propagation.REQUIRES_NEW)	// 기존 트랙잭션 중지, 새로운 트랜잭션 사용
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, 'admin', SYSDATE, 0, :filesrc)";
		String sqlPointUp = "UPDATE MEMBER SET POINT=POINT+1 WHERE ID= :id ";
		
		// 글작성 코딩
		int result = this.jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(notice));
		
		// 포인트 업 코딩
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", "admin");
		this.jdbcTemplate.update(sqlPointUp, parameterSource);
		
		return result;
	}  // insert

	// 1. 공지사항 등록 + 작성자 포인트 증가 함수 (트랜잭션 테스트) (트랜잭션 처리 X)
	/*
	@Override
	public void insertAndPointUpOfMember(Notice notice, String id) {
		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, '"+id+"', SYSDATE, 0, :filesrc)";
		String sqlPointUp = "UPDATE MEMBER SET POINT=POINT+1 WHERE ID= :id ";
		
		// 글작성 코딩
		this.jdbcTemplate.update(sql	,  new BeanPropertySqlParameterSource(notice));
		
		// 포인트 업 코딩
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		this.jdbcTemplate.update(sqlPointUp, parameterSource);
	}
	*/
	
	// 2. 공지사항 등록 + 작성자 포인트 증가 함수 (트랜잭션 테스트) (트랜잭션 처리 O)
	// try{ 1작업, 2작업 }catch(){롤백}
	// 트랜잭션 매니저를 이용한 코딩
 /*
	@Override
	public void insertAndPointUpOfMember(Notice notice, String id) {
		
		System.out.println("포인트 함수 들어옴");
		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, 'kenik', SYSDATE, 0, :filesrc)";
		
		String sqlPointUp = "UPDATE MEMBER SET POINT=POINT+1 WHERE ID= :id ";
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction( def );
		
		try{
			// 글작성 코딩
			this.jdbcTemplate.update(sql,  new BeanPropertySqlParameterSource(notice));
			
			// 포인트 업 코딩
			MapSqlParameterSource parameterSource = new MapSqlParameterSource();
			parameterSource.addValue("id", id);
			this.jdbcTemplate.update(sqlPointUp, parameterSource);
			
			// 커밋
			this.transactionManager.commit( status );
			System.out.println("예외 발생 X");
		}catch (Exception e) {
			// 롤백
			System.out.println( e.toString() );
			this.transactionManager.rollback(status);
		}
		
	}
 */

	// 3. 공지사항 등록 + 작성자 포인트 증가 함수 (트랜잭션 테스트) (트랜잭션 처리 O)
	// 트랜잭션 템플릿을 이용한 코딩
	/*
	@Override
	public void insertAndPointUpOfMember(Notice notice, String id) {
		
		System.out.println("포인트 함수 들어옴");
		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, '"+id+"', SYSDATE, 0, :filesrc)";
		String sqlPointUp = "UPDATE MEMBER SET POINT=POINT+1 WHERE ID= :id ";
		
		
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				try {
					// this 붙이면 안된다!! 익명 클래스여서 this.jdbcTemplate존재 X
					// 글작성 코딩
					jdbcTemplate.update(sql,  new BeanPropertySqlParameterSource(notice));
					System.out.println("글작성");
					// 포인트 업 코딩
					MapSqlParameterSource parameterSource = new MapSqlParameterSource();
					parameterSource.addValue("id", id);
					jdbcTemplate.update(sqlPointUp, parameterSource);
					System.out.println("예외발생 X");
				}catch (Exception e) {
					System.out.println("예외발생");
					status.setRollbackOnly();
				} 

			}
		});
	}
	*/
	
	// 4.
	// [주업무] 공지사항 등록 + 작성자 포인트 증가 함수
	/*
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(Notice notice, String id) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES("
				+ " (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES)"
				+ ", :title, :content, '"+id+"', SYSDATE, 0, :filesrc)";
		String sqlPointUp = "UPDATE MEMBER SET POINT=POINT+1 WHERE ID= :id ";
		
		// 글작성 코딩
		this.jdbcTemplate.update(sql,new BeanPropertySqlParameterSource(notice));
		
		// 포인트 업 코딩
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		this.jdbcTemplate.update(sqlPointUp, parameterSource);
	}
	*/
	
	//5 . NLMemberShipSeice에서 구현
	
	
	@Override
	public void hitUp(String seq) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getHit(String seq) {
		// TODO Auto-generated method stub
		return 0;
	}


} // class


