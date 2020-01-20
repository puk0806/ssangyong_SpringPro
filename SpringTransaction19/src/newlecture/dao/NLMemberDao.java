package newlecture.dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import newlecture.vo.Member;

@Repository
public class NLMemberDao implements MemberDao{
	
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate ; 
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	
	
	//  id -> Member 객체를 반환하는 메서드
	public Member getMember(String id) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM MEMBER WHERE ID = :id";
		MapSqlParameterSource paramSource =	new MapSqlParameterSource();
		paramSource.addValue("id", id);		
		return this.jdbcTemplate.queryForObject(sql, paramSource
				, new BeanPropertyRowMapper<Member>(Member.class) );
	}
	
	// 새로운 멤버 추가하는 메서드 
	public int insert(Member member) throws ClassNotFoundException, SQLException
	{
		// 필요조건 : 컬럼명 == 필드명 
		String sql =
				"INSERT INTO MEMBER( ID, PWD, NAME, GENDER, BIRTH, IS_LUNAR, CPHONE, EMAIL, HABIT, REGDATE) "
				+ "VALUES( :id, :pwd, :name, :gender, :birth, :is_lunar, :cphone, :email, :habit, SYSDATE)";
		
		return this.jdbcTemplate.update(sql
				, new BeanPropertySqlParameterSource(member) );
	}
}
