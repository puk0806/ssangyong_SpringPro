package newlecture.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import newlecture.vo.Notice;

// 공지사항
// NameParameterJdbcTemplate 사용하는 예
@Repository
public class NLNoticeDao implements NoticeDao{	
	
	// ㅇdispatcher-service.xml <bean> 생성
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	// 2. 조건에 맞는 공지사항의 갯수를 반환하는 메서드
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException	{
		
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE :query";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("query", "%"+query+"%");
		
		return this.jdbcTemplate.queryForInt(sql,paramSource);
	}

	
	// 2.   공지사항 조회 메서드(위에 코드와 동일)
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException {					
		
		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...
		
		String sql = "SELECT * FROM";
		sql += "(SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE :query ORDER BY REGDATE DESC) N)";
		sql += "WHERE NUM BETWEEN :srow AND :erow";
		
		// p496 Map 이용
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("query", "%"+query+"%");
		params.put("srow", srow);
		params.put("erow", erow);
		
		List<Notice> list = jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<Notice>(Notice.class));
		return list;
	}
	

	
	// 2. 해당 seq에 공지사항 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "DELETE NOTICES WHERE SEQ=:seq";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seq", seq);
		
		return jdbcTemplate.update(sql,params);
		
	}

	// 공지사항 수정 (update() 이용)	
		public int update(Notice notice) throws ClassNotFoundException, SQLException{
			
			String sql = "UPDATE NOTICES SET TITLE=:title, CONTENT=:content, FILESRC=:filesrc WHERE SEQ=:src";
			/*
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("title", notice.getTitle());
			params.put("content", notice.getContent());
			params.put("filesrc", notice.getFilesrc());
			params.put("seq", notice.getSeq());
			*/
		
			// 위에 코딩과 똑같은 코딩(주의! DB컬럼명이 같아야한다!!!!!)
			SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
			
			return jdbcTemplate.update(sql,paramSource);
		}

	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException	{
		String sql = "SELECT * FROM NOTICES WHERE SEQ= :seq " ;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seq", seq);
		// ParameterizedBeanPropertyRowMapper 조건 (쿼리 컬럼명 == 객체 필드명 동일)
		// ParameterizedBeanPropertyRowMapper : 쿼리의 데이터를 객체로 반환
		Notice notice= jdbcTemplate.queryForObject(sql, params, ParameterizedBeanPropertyRowMapper.newInstance(Notice.class));
		
		return notice;
	}	

	
	// 2. 공지사항 추가 (update() 매개변수로 넘기기)
	
	// 
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), :title, :content, :id , SYSDATE, 0, :filesrc)";
		/*
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", notice.getTitle());
		params.put("content", notice.getContent());
		params.put("filesrc", notice.getFilesrc());
		*/
		// 위에 코딩과 똑같은 코딩(주의! DB컬럼명이 같아야한다!!!!!)
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(notice);
		System.out.println("파일명 : "+notice.getFilesrc());
		
		return jdbcTemplate.update(sql,paramSource);
	
	}


	
	
}
