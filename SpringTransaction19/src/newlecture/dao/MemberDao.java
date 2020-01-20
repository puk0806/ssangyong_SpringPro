package newlecture.dao;

import java.sql.SQLException;

import newlecture.vo.Member;

public interface MemberDao {
	
	// id -> Member 객체를 반환하는 메서드
	public Member getMember(String id) throws ClassNotFoundException, SQLException;
	
	// 새로운 멤버 추가 매서드
	public int insert(Member member) throws ClassNotFoundException, SQLException;
}
