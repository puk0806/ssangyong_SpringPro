package newlecture.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import newlecture.vo.Notice;

// 공지사항 DAO
public interface NoticeDao{
 
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	public List<Notice> getNotices(	int page, String field, String query)					throws ClassNotFoundException, SQLException; 
	public int delete(String seq) throws ClassNotFoundException, SQLException; 
	public int update(Notice notice) throws ClassNotFoundException, SQLException;
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException;  	 
	public int insert(Notice notice) throws ClassNotFoundException, SQLException;	

	// 트랜잭션 테스트 용도의 메서드 
	public void insertAndPointUpOfMember(Notice notice, String id);
} // class


