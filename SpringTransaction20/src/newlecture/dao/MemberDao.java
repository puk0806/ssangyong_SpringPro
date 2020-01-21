package newlecture.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import newlecture.vo.Member;

public interface MemberDao {
	
	// 회원 정보 얻어오는 메서드
	public Member getMember(String id) 
			throws ClassNotFoundException, SQLException;
	// 회원 추가  메서드
	public int insert(Member member) 
			throws ClassNotFoundException, SQLException;
	
}
